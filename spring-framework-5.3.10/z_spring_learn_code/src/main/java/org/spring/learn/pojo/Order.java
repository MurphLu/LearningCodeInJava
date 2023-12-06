package org.spring.learn.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Order {
	@Autowired
	User user;

	public void add() {
		System.out.println(user);
		System.out.println("add order");
	}
}
