package org.spring.learn.service;

import org.spring.learn.pojo.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Value("111111111")
	Order order;

	public void test(){
		System.out.println(order.getCode());
	}
}
