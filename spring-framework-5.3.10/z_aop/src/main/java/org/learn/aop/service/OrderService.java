package org.learn.aop.service;

import org.springframework.stereotype.Component;

@Component
public class OrderService implements OrderInterface{
	public void orderTest() {
		System.out.println("orderTest");
	}
}
