package org.spring.learn.service.typeConverter;

import org.spring.learn.pojo.Order;
import org.spring.learn.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TypeConverterCode {
	@Value("ZhangSan")
	User user;

	@Value("111111")
	Order order;

	public void test() {
		System.out.println(user.getName());
		System.out.println(order.getCode());
	}
}
