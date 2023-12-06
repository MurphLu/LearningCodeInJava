package org.test.service;

import org.spring.InitializeBean;
import org.spring.annotation.Autowired;
import org.spring.annotation.Component;
import org.spring.annotation.Scope;
import org.spring.annotation.enums.ScopeType;

@Scope(ScopeType.SINGLETON)
@Component
public class UserService{

    public void sayHi() {
        System.out.println("Hi~~~");
    }


}
