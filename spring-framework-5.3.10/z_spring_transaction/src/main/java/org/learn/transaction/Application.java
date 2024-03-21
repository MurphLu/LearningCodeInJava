package org.learn.transaction;

import org.learn.transaction.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Application {
	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService bean = context.getBean(UserService.class);
		bean.test();
		System.out.println("Hello world!");
	}
}