package com.ziyin.mapper;

import com.ziyin.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ziyin
 * @create 2019-07-31 23:30
 */
@Mapper
public interface UserMapper {
	public List<User> queryUserList();
}