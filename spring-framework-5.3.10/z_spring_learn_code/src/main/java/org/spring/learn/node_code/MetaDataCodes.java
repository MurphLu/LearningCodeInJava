package org.spring.learn.node_code;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class MetaDataCodes {
	/**
	 * 获取类的元数据
	 * @throws IOException
	 */
	public static void testMetaData() throws IOException {
		SimpleMetadataReaderFactory simpleMetadataReaderFactory = new SimpleMetadataReaderFactory();
		MetadataReader metadataReader = simpleMetadataReaderFactory.getMetadataReader("org.spring.learn.config.AppConfig");
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		System.out.println(classMetadata.getClassName());
		System.out.println(annotationMetadata.getAnnotationTypes());

		MetadataReader serviceMetadata = simpleMetadataReaderFactory.getMetadataReader("org.spring.learn.service.UserService");

		// 可以检查更深层次
		System.out.println(serviceMetadata.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));

		// 只检查当前类上定义的注解
		System.out.println(serviceMetadata.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
	}
}
