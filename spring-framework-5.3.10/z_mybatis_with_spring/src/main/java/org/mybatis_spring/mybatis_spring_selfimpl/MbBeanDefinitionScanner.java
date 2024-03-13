package org.mybatis_spring.mybatis_spring_selfimpl;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Set;

public class MbBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
	public MbBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	// 只扫描接口
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface();
	}

	// 扫描出来的 mapper 设置 beanClassName，及参数
	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
		for(BeanDefinitionHolder holder : beanDefinitionHolders) {
			BeanDefinition beanDefinition = holder.getBeanDefinition();
			beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
			beanDefinition.setBeanClassName(MybatisSpringFactoryBean.class.getName());

		}
		return beanDefinitionHolders;
	}


}
