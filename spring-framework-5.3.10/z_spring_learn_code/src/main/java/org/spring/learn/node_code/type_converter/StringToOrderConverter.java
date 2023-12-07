package org.spring.learn.node_code.type_converter;

import org.spring.learn.config.AppConfig;
import org.spring.learn.service.OrderService;
import org.spring.learn.pojo.Order;
import org.spring.learn.service.typeConverter.TypeConverterCode;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Set;

/**
 * provided by Spring
 * witch can convert from one type to another
 * like order provided by @Value with string type
 * and spring can use the convertor convert the string to Order obj
 * like PropertyEditor
 * @see AppConfig#conversionService()
 * @see TypeConverterCode#order
 */
public class StringToOrderConverter implements ConditionalGenericConverter {
	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return sourceType.getType().equals(String.class) && targetType.getType().equals(Order.class);
	}


	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(String.class, Order.class));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Order order = new Order();
		order.setCode((String) source);
		return order;
	}
}
