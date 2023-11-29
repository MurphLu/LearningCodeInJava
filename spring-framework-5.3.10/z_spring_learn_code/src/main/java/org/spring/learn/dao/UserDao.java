package org.spring.learn.dao;

import org.spring.learn.pojo.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserDao implements InitializingBean {
    @Autowired
    private User user;

    @PostConstruct
    private void initialUser() {
        user.setAdmin(true);
    }

    public String getName() {
        return (user.isAdmin() ? "Admin " : "User ") + "Fr";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        user.setAdmin(false);
    }
}
