package org.spring.learn.dao;

import org.spring.learn.pojo.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
/**
 * spring 调用构造方法原则（有参的话，参数需要是 spring 维护的 bean，查找spring context 中 bean 的逻辑为先类型（同一类型多个的话）再名字）：
 * 1. 如果类没有实现构造方法，那么使用默认构造方法（无参）
 * 2. 如果类中有一个有参构造方法，那么使用已经实现的构造方法
 * 3. 类实现多个构造方法
 * 	  a. 如果构造方法都没有加 @Autowired，且有无参构造方法，那么使用无参构造方法
 * 	  b. 如果构造方法都没有加 @Autowired，且没有无参构造方法，抛异常（找不到默认构造方法）
 * 	  c. 如果有一个构造方法加 @Autowired，则使用该方法
 * 	  d. 如果有多个构造方法加 @Autowired，抛异常
 */
@Component
public class UserDao implements InitializingBean {
    @Autowired
    private User user;

	public UserDao() {
		System.out.println("user dao constructor without params");
	}

	public UserDao(User user) {
		System.out.println("user dao constructor with one params");
		this.user = user;
	}

	public UserDao(User user, User user1) {
		System.out.println("user dao constructor with two params");
		this.user = user;
	}

	@PostConstruct
    private void initialUser() {
		System.out.println("PostConstruct");
        user.setAdmin(false);
    }

    public String getName() {
        return (user.isAdmin() ? "Admin " : "User ") + "Fr";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
        user.setAdmin(true);
    }
}
