package org.spring.learn.config;

import org.spring.learn.node_code.type_converter.StringToOrderConverter;
import org.spring.learn.node_code.type_converter.StringToUserPropertyEditor;
import org.spring.learn.pojo.User;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.beans.PropertyEditor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * config class for application
 */
@ComponentScan("org.spring.learn")
@EnableAspectJAutoProxy
@PropertySource("classpath:spring.properties")
public class AppConfig {
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		// 通过指定默认编码，读取 resource 中的中文，防止乱码
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public ApplicationListener applicationListener (){
		return event -> System.out.println("这是一条测试消息 " + event.getSource());
	}

	@Bean
	public CustomEditorConfigurer customEditorConfigurer() {
		CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
		Map<Class<?>, Class<? extends PropertyEditor>> map = new HashMap<>();
		// also you can put more <k,v> for other different config
		map.put(User.class, StringToUserPropertyEditor.class);
		customEditorConfigurer.setCustomEditors(map);
		return customEditorConfigurer;
	}

	@Bean
	public ConversionServiceFactoryBean conversionService(){
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		conversionServiceFactoryBean.setConverters(Collections.singleton(new StringToOrderConverter()));
		return conversionServiceFactoryBean;
	}
}
