package org.mybatis_spring.mybatis_spring_selfimpl;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.mybatis_spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MybatisSpringFactoryBean implements FactoryBean<Object> {

	private SqlSession sqlSession;

	private Class<Object> mapperInterface;

	public MybatisSpringFactoryBean(Class<Object> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	@Autowired
	public void setSqlSession(SqlSessionFactory factory) {
		// 获取 session
		factory.getConfiguration().addMapper(mapperInterface);
		sqlSession = factory.openSession();
	}

	@Override
	public Object getObject() throws Exception {
		// 生成 mapper 代理对象
		return sqlSession.getMapper(mapperInterface);
//		Object o = Proxy.newProxyInstance(MybatisSpringFactoryBean.class.getClassLoader(), new Class[]{mapperInterface}, new InvocationHandler() {
//			@Override
//			public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//				System.out.println(method.getName());
//				Select annotation = method.getAnnotation(Select.class);
//				return null;
//			}
//		});
//		return o;
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}
