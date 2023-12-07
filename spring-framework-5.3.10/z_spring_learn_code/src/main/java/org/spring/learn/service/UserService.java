package org.spring.learn.service;

import org.spring.learn.dao.UserDao;
import org.spring.learn.pojo.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Locale;

// class -> 无参构造方法 ->
// 实例化 -> 依赖注入（属性赋值）->
// 构造方法结束 @postConstruct ->
// 一些初始化工作（InitializingBean->afterPropertiesSet）->
// AOP -> if(AOP) 代理对象 -> Bean
@Service
public class UserService implements ApplicationContextAware {

	ApplicationContext context;

    @Autowired
    UserDao dao;


    public void sayHi() {
        System.out.println(dao.getName() + " Hi");
		System.out.println("get message:" + context.getBean(MessageSource.class).getMessage("info", null, new Locale("en")));
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
