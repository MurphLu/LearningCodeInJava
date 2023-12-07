package org.spring.learn.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Order {
	@Autowired
	User user;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	String code;

	public void add() {
		System.out.println(user);
		System.out.println("add order");
	}
}
