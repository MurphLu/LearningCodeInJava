package org.learn.aop.service;

public class CustomerImpl implements CustomerInterface{
	@Override
	public void testDefaultImpl() {
		System.out.println("default impl");
	}
}
