package org.learn.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderServiceProxy {
	@Before("execution(public void org.learn.aop.service.OrderService.orderTest())")
	public void before(JoinPoint joinPoint) {
		System.out.println("aspect before ");
	}
}
