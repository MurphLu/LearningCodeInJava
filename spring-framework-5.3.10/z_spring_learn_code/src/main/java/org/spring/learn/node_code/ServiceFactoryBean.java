package org.spring.learn.node_code;

import org.spring.learn.service.ProduceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * ServiceFactoryBean 会经过 bean 的完整生命周期，
 * 通过 serviceFactoryBean 获取到的是 getObject() 的返回值，
 * 实际的 对象不会走整个的生命周期，但是会走初始化后的方法，来实现 AOP
 * 获取之后 object 会放在 cache 中，
 *
 * 而通过 @Bean 定义的 Bean 或经过  Bean 的完整生命周期
 */
@Component
public class ServiceFactoryBean implements FactoryBean {
	@Override
	public Object getObject() throws Exception {
		return new ProduceService();
	}

	@Override
	public Class<?> getObjectType() {
		return ProduceService.class;
	}
}
