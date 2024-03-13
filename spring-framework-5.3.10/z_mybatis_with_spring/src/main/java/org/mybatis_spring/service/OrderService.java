package org.mybatis_spring.service;

import org.mybatis_spring.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	OrderMapper orderMapper;

	public void test() {
		String s = orderMapper.selectById();
		System.out.println(s);
	}
}
