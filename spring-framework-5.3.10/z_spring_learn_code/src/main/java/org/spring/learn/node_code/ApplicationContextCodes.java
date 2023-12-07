package org.spring.learn.node_code;

import org.spring.learn.pojo.Order;
import org.spring.learn.pojo.Product;
import org.spring.learn.pojo.Server;
import org.spring.learn.service.UserService;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class ApplicationContextCodes {

	/**
	 * event publish
	 * have to register a bean of ApplicationListener
	 * ApplicationContext implement interface: ApplicationEventPublisher
	 * @param context
	 */
	private static void publishEvent(ApplicationContext context) {
		context.publishEvent(new ApplicationEvent(new Object()) {
			@Override
			public Object getSource() {
				return "哈哈哈哈哈";
			}
		}); //publishEvent("new Object()");
	}


	/**
	 * 获取环境变量信息
	 * ApplicationContext implement interface: EnvironmentCapable
	 * @param context
	 */
	private static void getEnvironments(AnnotationConfigApplicationContext context) {
		Map<String, Object> systemEnvironment = context.getEnvironment().getSystemEnvironment();
		System.out.println(systemEnvironment);

		Map<String, Object> systemProperties = context.getEnvironment().getSystemProperties();
		System.out.println(systemProperties);

		// 获取所有 getEnvironment resource 包括 @PropertySource 方式引入的 resource
		MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
		System.out.println(propertySources);

		// 获取任意环境变量，包括上面所有配置中包含的环境信息
		System.out.println(context.getEnvironment().getProperty("PATH"));
		System.out.println(context.getEnvironment().getProperty("USER"));
	}

	/**
	 * 资源加载
	 * ApplicationContext implement interface: ResourceLoader
	 * @param context
	 * @throws IOException
	 */
	private static void getResources(AnnotationConfigApplicationContext context) throws IOException {
		// load file from disk
		Resource resource = context.getResource("file:///Users/murph/Documents/learn/code/Java/learn-java/spring-framework-5.3.10/z_spring_learn_code/src/main/java/org/spring/learn/config/AppConfig.java");
		System.out.println(resource.contentLength());
		System.out.println(resource.getFilename());

		// load file from web
		Resource resourceHttp = context.getResource("https://www.baidu.com");
		System.out.println(resourceHttp.contentLength());
		System.out.println(resourceHttp.getURL());

		// load file from classpath
		Resource resourceClasspath = context.getResource("classpath:beanConfig.xml");
		System.out.println(resourceClasspath.contentLength());
		System.out.println(resourceClasspath.getFilename());

		Resource resource1 = context.getResource("classpath:org.spring.learn");
		System.out.println(resource1);
	}

	/**
	 * message bean 读取 resources 中的国际化文件，并输出对应的配置信息
	 * 中文可以设置默认编码为 UTF-8，并将 resource 文件格式设置为 UTF-8 以正确读取
	 * ApplicationContext implement interface: MessageSource
	 * @param context
	 */
	private static void messageBean(AnnotationConfigApplicationContext context) {
		System.out.println();
		MessageSource bean1 = context.getBean(MessageSource.class);

		System.out.println(bean1.getMessage("info", null, new Locale("cn")));
	}

	/**
	 * 通过 ClassPathBeanDefinitionScanner 可以手动指定扫描并加载某一包下的 bean
	 * 此时不需要  AppConfig 类即可对 context 进行初始化
	 * 需要调用 context.refresh()
	 */
	private static void useClassPathScanner() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);

		scanner.scan("org.spring.learn");
		System.out.println();
		System.out.println();
		UserService bean = context.getBean(UserService.class);
	}

	/**
	 * 通过不同方式手动创建 BeanDefinition 或 自动读取扫描并生成 BeanDefinition，并交给 context 创建 bean
	 *
	 * @param context
	 */
	private static void defineBeanDefinitionsManually(AnnotationConfigApplicationContext context) {
		System.out.println("------------ register bean with beanDefinition manually----------------");
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClass(Order.class);
		context.registerBeanDefinition("order",beanDefinition);
		((Order) context.getBean("order")).add();
		System.out.println(context.getBean("order"));
		System.out.println(context.getBean("order"));

		System.out.println();
		System.out.println("------------ register bean with AnnotatedBeanDefinitionReader witch can scan annotations of the class ----------------");
		AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
		annotatedBeanDefinitionReader.register(Server.class);
		((Server) context.getBean("server")).connect();
		System.out.println(context.getBean("server"));
		System.out.println(context.getBean("server"));

		System.out.println();
		System.out.println("------------ register bean with XmlBeanDefinitionReader ----------------");
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context);
		xmlBeanDefinitionReader.loadBeanDefinitions("beanConfig.xml");
		System.out.println(context.getBean(Product.class));
	}
}
