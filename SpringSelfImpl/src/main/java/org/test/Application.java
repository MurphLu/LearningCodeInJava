package org.test;

import org.spring.ApplicationContext;
import org.test.config.AppConfig;
import org.test.service.UserService;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(AppConfig.class);
        UserService obj = (UserService)context.getBean("userService");
        UserService obj2 = context.getBean(UserService.class);
        System.out.println(obj);
        System.out.println(obj2);
    }
}
