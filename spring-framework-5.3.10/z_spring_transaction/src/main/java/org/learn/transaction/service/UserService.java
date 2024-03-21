package org.learn.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Component
public class UserService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	UserService userService;

	@Transactional
	public void test() throws SQLException {
		System.out.println(jdbcTemplate.getDataSource().getConnection());
		jdbcTemplate.execute("insert into public.user (name) values('t4')");
		jdbcTemplate.execute("insert into public.user (name) values('t6')");
		jdbcTemplate.execute("insert into public.user (name) values('t7')");

		// 调用的时候需要使用 userService.a(); 否则 a 的注解会失效，代理对象方法调用过程
		userService.a();
	}

	// Propagation.REQUIRES_NEW 会挂起外层的 conn，并建立新的连接
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	// REQUIRES_NEW 此处为一个新的事务，与其他事务不没有关系，他的回滚提交也与其他事务无关
	// 如果其他事务调用到了该事务，并 handle 了 exception，那么则不影响其他的事务
	// 当然如果不 handle 那么跑出异常也会影响其他事务，但和事务本身无关
	public void a() throws SQLException {
		System.out.println(jdbcTemplate.getDataSource().getConnection());
		jdbcTemplate.execute("insert into public.user (name) values('t5')");
	}
}
