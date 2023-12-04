package org.test.service;

import org.spring.annotation.Autowired;
import org.spring.annotation.Component;
import org.spring.annotation.Scope;
import org.spring.annotation.enums.ScopeType;

@Component
@Scope(ScopeType.SINGLETON)
public class OrderService {
    @Autowired
    UserService userService;

    public void test() {
        userService.sayHi();
        System.out.println(userService);
    }
}
