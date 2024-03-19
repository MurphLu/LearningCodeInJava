package org.learn.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.learn.aop.advisor.SelfPontCutAdvisor;
import org.learn.aop.interceptor.SelfAdvice;
import org.learn.aop.service.UserService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@ComponentScan("org.learn.aop")
@Import(DefaultAdvisorAutoProxyCreator.class)
@EnableAspectJAutoProxy // 注册了 AnnotationAwareAspectJAutoProxyCreator bean
public class AppConfig {

	// use ProxyFactoryBean to create proxy
//	@Bean
	ProxyFactoryBean userService() {
		UserService userService = new UserService();
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.addAdvice(new MethodInterceptor() {
			@Nullable
			@Override
			public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
				System.out.println("before");
				Object proceed = invocation.proceed();
				System.out.println("after");
				return proceed;
			}
		});
		proxyFactoryBean.setTarget(userService);
		return proxyFactoryBean;
	}

	// use beanName filter create proxy
//	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		creator.setBeanNames("userS*");
		creator.setInterceptorNames("selfAdvice");
		return creator;
	}

	// DefaultPointcutAdvisor create proxy，需要配合 DefaultAdvisorAutoProxyCreator 食用
	// 有对应 methodName 的 bean 都会创建代理 bean
	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor() {
		NameMatchMethodPointcut pointcut  = new NameMatchMethodPointcut();
		pointcut.addMethodName("a");
		DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
		defaultPointcutAdvisor.setPointcut(pointcut);
		defaultPointcutAdvisor.setAdvice(new SelfAdvice());
		return defaultPointcutAdvisor;
	}

//	 也可以用 @Import(DefaultAdvisorAutoProxyCreator.class) 生成一个 bean
//	@Bean
//	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//		return defaultAdvisorAutoProxyCreator;
//	}
}
