package org.mybatis_spring;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis_spring.mybatis_spring_selfimpl.MyMapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;

@ComponentScan("org.mybatis_spring")
@MyMapperScan("org.mybatis_spring.mapper")
public class AppConfig {
	@Bean
	SqlSessionFactory sqlSessionFactory() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		return new SqlSessionFactoryBuilder().build(is);
	}

}
