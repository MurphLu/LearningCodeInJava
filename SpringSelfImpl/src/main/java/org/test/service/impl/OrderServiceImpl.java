package org.test.service.impl;

import org.spring.InitializeBean;
import org.spring.annotation.Autowired;
import org.spring.annotation.Component;
import org.spring.annotation.Scope;
import org.spring.annotation.enums.ScopeType;
import org.spring.interfaces.BeanNameAware;
import org.test.service.OrderService;
import org.test.service.UserService;
import org.test.service.annotation.ServerAddress;

@Component("orderService")
@Scope(ScopeType.SINGLETON)
public class OrderServiceImpl implements OrderService, InitializeBean, BeanNameAware {
    @Autowired
    UserService userService;

    @ServerAddress("127.0.0.1")
    String server;

    String beanName;

    public void test() {
        System.out.println("beanName: " + beanName + ", server address is: " + server);
        userService.sayHi();
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet call");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
