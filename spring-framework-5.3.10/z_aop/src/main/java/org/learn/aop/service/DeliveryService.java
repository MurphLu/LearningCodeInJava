package org.learn.aop.service;

import org.springframework.stereotype.Component;

@Component
public class DeliveryService {
	public void test() {
		System.out.println("delivery service test");
	}


	public void test(String s1, String s2) {
		System.out.println("delivery service test with args");
	}
}
