package org.spring.learn;

import com.sun.tools.sjavac.Log;
import org.spring.learn.config.AppConfig;
import org.spring.learn.pojo.Order;
import org.spring.learn.pojo.Product;
import org.spring.learn.pojo.Server;
import org.spring.learn.service.UserService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) {
		Logger logger = Logger.getGlobal();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		System.out.println();
		System.out.println();
		UserService bean = context.getBean(UserService.class);
        bean.sayHi();

		System.out.println();
		MessageSource bean1 = context.getBean(MessageSource.class);

		System.out.println(bean1.getMessage("info", null, new Locale("cn")));
	}

	private static void useClassPathScanner() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);

		scanner.scan("org.spring.learn");
		System.out.println();
		System.out.println();
		UserService bean = context.getBean(UserService.class);
	}

	private static void defineBeanDefinitionsManually(AnnotationConfigApplicationContext context) {
		System.out.println("------------ register bean with beanDefinition manually----------------");
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClass(Order.class);
		context.registerBeanDefinition("order",beanDefinition);
		((Order) context.getBean("order")).add();
		System.out.println(context.getBean("order"));
		System.out.println(context.getBean("order"));

		System.out.println();
		System.out.println("------------ register bean with AnnotatedBeanDefinitionReader witch can scan annotations of the class ----------------");
		AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
		annotatedBeanDefinitionReader.register(Server.class);
		((Server) context.getBean("server")).connect();
		System.out.println(context.getBean("server"));
		System.out.println(context.getBean("server"));

		System.out.println();
		System.out.println("------------ register bean with XmlBeanDefinitionReader ----------------");
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
		xmlBeanDefinitionReader.loadBeanDefinitions("spring.xml");
		System.out.println(context.getBean(Product.class));
	}

	private static void withXmlConfig() {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        UserService service2 = context1.getBean(UserService.class);
        service2.sayHi();
    }
}
