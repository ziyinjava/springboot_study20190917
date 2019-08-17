package com.ziyin.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyin
 * @create 2019-08-01 8:04
 */
@RestController
public class IndexController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@RequestMapping("/deduct_stock")
	public String deducStock() {
		String lockKey = "product001";
		String clientId = UUID.randomUUID().toString();
		try {
//			Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "ziyin");
//			stringRedisTemplate.expire(lockKey,10, TimeUnit.SECONDS);

			//这行代码会将上面的两行代码作为原子块执行
			Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId,10, TimeUnit.SECONDS);

			if (!result) {
				return "error";
			}

			//扣减的动作应该是原子性的, 不应该被多线程打断,
			//在分布式锁的情况下,synchronize就锁不住了
			//synchronized (this) {
			int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
			if (stock > 0) {
				int realStock = stock - 1;
				stringRedisTemplate.opsForValue().set("stock",realStock+"");
				System.out.println("扣减成功,剩余库存: " + realStock);
			} else {
				System.out.println("扣减失败,库存不足");
			}
			//}
		}finally {
			if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
				//如果不是程序抛异常,直接程序所在机器挂了,这时候finally也不行了
				stringRedisTemplate.delete(lockKey);
			}
		}
		return "end";
	}

}
