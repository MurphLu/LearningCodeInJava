package org.mybatis_spring.mybatis_spring_selfimpl;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MapperBeanImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
		String path = ((String[])importingClassMetadata.getAnnotationAttributes(MyMapperScan.class.getName()).get("value"))[0];
		System.out.println(path);
		MbBeanDefinitionScanner scanner = new MbBeanDefinitionScanner(registry);

		// 添加 includeFilter，保证扫描到不加 @Component 的 mapper interface 时不会忽略
		scanner.addIncludeFilter(new TypeFilter() {
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				return true;
			}
		});
		int count = scanner.scan(path);
	}
}
