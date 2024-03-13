package org.mybatis_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.mybatis_spring.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public void test() {
		String s = userMapper.selectById();
		System.out.println(s);
	}
}
