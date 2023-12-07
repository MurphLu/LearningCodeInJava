package org.spring.learn.node_code;

import org.spring.learn.service.ordered.OrderClassA;
import org.spring.learn.service.ordered.OrderClassB;
import org.springframework.context.ApplicationContext;
import org.springframework.core.OrderComparator;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * define of Ordered class see
 * {@link OrderClassA}
 * {@link OrderClassB}
 * the class can impl Ordered interface
 * or use the {@link org.springframework.core.annotation.Order} annotation
 * to set the orders
 */
public class OrderComparatorCodes {
	/**
	 * AnnotationAwareOrderComparator
	 * can compare beans/obj with @Order Annotation or impl Ordered Interface
	 * @param context
	 */
	public static void testWithAnnotation(ApplicationContext context) {
		OrderClassA orderA = context.getBean(OrderClassA.class);
		OrderClassB orderB = context.getBean(OrderClassB.class);
		List serviceList = new ArrayList();
		serviceList.add(orderA);
		serviceList.add(orderB);
		serviceList.sort(new AnnotationAwareOrderComparator());
		System.out.println(new AnnotationAwareOrderComparator().compare(orderA, orderB));
		System.out.println(serviceList);
	}

	/**
	 * OrderComparator
	 * can compare beans/obj which are impl Ordered Interface
	 * @param context
	 */
	public static void testWithInterface(ApplicationContext context) {
		OrderClassA orderService = context.getBean(OrderClassA.class);
		OrderClassB userService = context.getBean(OrderClassB.class);
		List serviceList = new ArrayList();
		serviceList.add(userService);
		serviceList.add(orderService);
		serviceList.sort(new OrderComparator());
		System.out.println(new OrderComparator().compare(orderService, userService));
		System.out.println(serviceList);
	}

}
