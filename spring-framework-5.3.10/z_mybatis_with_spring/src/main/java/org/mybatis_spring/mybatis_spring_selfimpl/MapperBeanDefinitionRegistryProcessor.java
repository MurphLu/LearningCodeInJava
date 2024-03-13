package org.mybatis_spring.mybatis_spring_selfimpl;

import org.mybatis_spring.Main;
import org.mybatis_spring.mapper.OrderMapper;
import org.mybatis_spring.mapper.UserMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Component
public class MapperBeanDefinitionRegistryProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
		String[] beanDefinitionNames = registry.getBeanDefinitionNames();

		List<String> mapperList = new ArrayList<>();
		for(String name: beanDefinitionNames) {
			String className = registry.getBeanDefinition(name).getBeanClassName();
			ClassLoader classLoader = Main.class.getClassLoader();
			Class<?> aClass = null;
			try{
				aClass = classLoader.loadClass(className);
			}catch (Exception e) {
				e.printStackTrace();
			}
			if (aClass == null) {
				continue;
			}
			MyMapperScan[] annotationsByType = aClass.getAnnotationsByType(MyMapperScan.class);
			for(MyMapperScan annotation: annotationsByType) {
				String[] value = annotation.value();
				mapperList.addAll(Arrays.asList(value));
			}
		}
		System.out.println(mapperList);

		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClass(MybatisSpringFactoryBean.class);
		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
		registry.registerBeanDefinition("userMapper", beanDefinition);


		AbstractBeanDefinition beanDefinition1 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition1.setBeanClass(MybatisSpringFactoryBean.class);
		beanDefinition1.getConstructorArgumentValues().addGenericArgumentValue(OrderMapper.class);
		registry.registerBeanDefinition("orderMapper", beanDefinition1);
	}
}
