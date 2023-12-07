package org.spring.learn.config;

import org.spring.learn.node_code.type_converter.StringToOrderConverter;
import org.spring.learn.node_code.type_converter.StringToUserPropertyEditor;
import org.spring.learn.pojo.Product;
import org.spring.learn.pojo.User;
import org.spring.learn.service.OrderService;
import org.spring.learn.service.UserService;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.beans.PropertyEditor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * spring 内部 Bean 的扫描就是通过添加 Component.class 等的 includeFilters 来实现的
 * excludeFilters，includeFilters 可以指定任何类
 */
@ComponentScan(
		value = "org.spring.learn",
		excludeFilters = {
				@ComponentScan.Filter(
						type = FilterType.ASSIGNABLE_TYPE,
						classes = OrderService.class
				)
		},
		includeFilters = {
				@ComponentScan.Filter(
						type = FilterType.ASSIGNABLE_TYPE,
						classes = Product.class
				)
		})
public class AppConfigWithFilter {
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
