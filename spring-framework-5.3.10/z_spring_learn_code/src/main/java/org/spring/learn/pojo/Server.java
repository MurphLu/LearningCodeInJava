package org.spring.learn.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Server {
	@Autowired
	User user;

	public void connect() {
		System.out.println(user);
		System.out.println("connected");
	}
}
