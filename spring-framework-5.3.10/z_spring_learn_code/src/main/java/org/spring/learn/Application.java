package org.spring.learn;

import org.spring.learn.config.AppConfig;
import org.spring.learn.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = context.getBean(UserService.class);
        bean.sayHi();
    }

    private static void withXmlConfig() {
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
        UserService service2 = context1.getBean(UserService.class);
        service2.sayHi();
    }
}
