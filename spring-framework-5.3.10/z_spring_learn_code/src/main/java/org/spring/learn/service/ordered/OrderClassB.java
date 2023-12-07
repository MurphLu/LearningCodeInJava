package org.spring.learn.service.ordered;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class OrderClassB implements Ordered {
	@Override
	public int getOrder() {
		return 1;
	}
}
