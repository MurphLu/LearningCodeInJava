package org.mybatis_spring;

import org.mybatis_spring.service.OrderService;
import org.mybatis_spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		test();
	}

	public static void test() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = (UserService) context.getBean("userService");
		userService.test();
		OrderService orderService = (OrderService) context.getBean("orderService");
		orderService.test();
	}
}