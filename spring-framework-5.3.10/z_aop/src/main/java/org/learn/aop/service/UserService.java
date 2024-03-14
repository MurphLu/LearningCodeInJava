package org.learn.aop.service;


import org.springframework.stereotype.Component;

@Component
public class UserService implements UserInterface{
	public void test(){
		System.out.println("test");
	}

	public void a() {
		System.out.println("a");
	}
}
