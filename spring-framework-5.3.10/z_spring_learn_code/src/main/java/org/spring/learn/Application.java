package org.spring.learn;

import com.sun.tools.sjavac.Log;
import org.spring.learn.config.AppConfig;
import org.spring.learn.node_code.OrderComparatorCodes;
import org.spring.learn.pojo.Order;
import org.spring.learn.pojo.Product;
import org.spring.learn.pojo.Server;
import org.spring.learn.service.OrderService;
import org.spring.learn.service.UserService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println();
		UserService bean = context.getBean(UserService.class);
        bean.sayHi();

		OrderService orderService = context.getBean(OrderService.class);
		orderService.test();

		OrderComparatorCodes.testWithAnnotation(context);
		OrderComparatorCodes.testWithInterface(context);
	}

	/**
	 * 通过 xml 初始化 context
 	 */

	private static void withXmlConfig() {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        UserService service2 = context1.getBean(UserService.class);
        service2.sayHi();
    }
}
