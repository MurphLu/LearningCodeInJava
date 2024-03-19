package org.learn.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.learn.aop.service.CustomerImpl;
import org.learn.aop.service.CustomerInterface;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderServiceProxy {

	// 给一个类添加一个接口
	@DeclareParents(value = "org.learn.aop.service.CustomerService", defaultImpl = CustomerImpl.class)
	CustomerInterface customerInterface;

	@Before("execution(public void org.learn.aop.service.OrderService.orderTest())")
	public void before(JoinPoint joinPoint) {
		System.out.println("aspect before ");
	}


	@Before("execution(public void org.learn.aop.service.DeliveryService.test())")
	public void test(JoinPoint joinPoint) {
		System.out.println("aspect delivery");
	}

	@Before(value = "execution(public void org.learn.aop.service.DeliveryService.test(..)) && args(a, b)", argNames = "a,b")
	public void testArgs(String a, String b) {
		System.out.println("++++ test args +++");
		System.out.println(a);
		System.out.println(b);
	}
}
