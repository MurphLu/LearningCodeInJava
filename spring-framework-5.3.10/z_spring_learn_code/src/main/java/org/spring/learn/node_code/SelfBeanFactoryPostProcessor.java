package org.spring.learn.node_code;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class SelfBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	// interface BeanFactoryPostProcessor
	// 添加 bean
	// 修改 beanDefinition 等操作
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	// 注册新的 beanDefinition
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

	}
}
