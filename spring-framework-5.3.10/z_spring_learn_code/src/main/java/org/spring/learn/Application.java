package org.spring.learn;

import org.spring.learn.config.AppConfig;
import org.spring.learn.config.AppConfigWithFilter;
import org.spring.learn.node_code.OrderComparatorCodes;
import org.spring.learn.pojo.Product;
import org.spring.learn.service.OrderService;
import org.spring.learn.service.UserService;
import org.spring.learn.service.typeConverter.TypeConverterCode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
		ApplicationContext context = initApplicationContext();
//		ApplicationContext context1 = withXmlConfig();
//		filteredContext();
		System.out.println(context.getBean("serviceFactoryBean"));
		//		getBeanTest(context);

//		testTypeConverter(context);

//		testComparator(context);

//		MetaDataCodes.testMetaData();
	}

	private static void filteredContext() {
		ApplicationContext contextWithFilter = new AnnotationConfigApplicationContext(AppConfigWithFilter.class);
		System.out.println(contextWithFilter.getBean(Product.class));
		System.out.println(contextWithFilter.getBean(UserService.class));
		System.out.println(contextWithFilter.getBean(OrderService.class));
	}

	private static ApplicationContext initApplicationContext() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println();
		return context;
	}

	private static void testComparator(AnnotationConfigApplicationContext context) {
		OrderComparatorCodes.testWithAnnotation(context);
		OrderComparatorCodes.testWithInterface(context);
	}

	private static void testTypeConverter(AnnotationConfigApplicationContext context) {
		TypeConverterCode code = context.getBean(TypeConverterCode.class);
		code.test();
	}

	private static void getBeanTest(AnnotationConfigApplicationContext context) {
		UserService bean = context.getBean(UserService.class);
		bean.sayHi();
	}

	/**
	 * 通过 xml 初始化 context
 	 */

	private static ApplicationContext withXmlConfig() {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        UserService service2 = context1.getBean(UserService.class);
        service2.sayHi();
		return context1;
    }
}
