package com.ziyin;

import com.ziyin.domain.User;
import com.ziyin.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author ziyin
 * @create 2019-07-31 23:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class MybatislTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void test(){
		List<User> users = userMapper.queryUserList();
		System.out.println(users);
	}

	@Bean
	public Redisson redisson() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
		return (Redisson)Redisson.create(config);
	}

}
