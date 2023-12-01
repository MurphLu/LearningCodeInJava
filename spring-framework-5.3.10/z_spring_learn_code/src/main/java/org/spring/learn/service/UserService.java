package org.spring.learn.service;

import org.spring.learn.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// class -> 无参构造方法 ->
// 实例化 -> 依赖注入（属性赋值）->
// 构造方法结束 @postConstruct ->
// 一些初始化工作（InitializingBean->afterPropertiesSet）->
// AOP -> if(AOP) 代理对象 -> Bean
@Service
public class UserService {

    @Autowired
    UserDao dao;

    public void sayHi() {
        System.out.println(dao.getName() + " Hi");
    }
}
