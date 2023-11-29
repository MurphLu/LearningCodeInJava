package org.spring.learn.service;

import org.spring.learn.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao dao;

    public void sayHi() {
        System.out.println(dao.getName() + " Hi");
    }
}
