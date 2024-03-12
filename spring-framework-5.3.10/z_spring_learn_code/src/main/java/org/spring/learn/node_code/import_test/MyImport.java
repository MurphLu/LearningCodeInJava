package org.spring.learn.node_code.import_test;

import org.spring.learn.pojo.Order;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImport implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{Order.class.getName()};
	}
}
