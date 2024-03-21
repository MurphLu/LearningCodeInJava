package org.learn.transaction;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionContextManager;

import javax.annotation.sql.DataSourceDefinition;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

// 开启事务
@ComponentScan("org.learn.transaction")
@Configuration
@EnableTransactionManagement
public class AppConfig {

//	DataSource dataSource() {
//		DataSource dataSource = new DriverManagerDataSource();
//	}
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(
				"jdbc:postgresql://127.0.0.1:5433/postgres",
				"postgres",
				"admin"
		);
		dataSource.setDriverClassName("org.postgresql.Driver");
		return dataSource;
	}

	@Bean
	PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		// 部分失败是否回滚，
		// 默认为 true，部分失败回滚，
		// false 的话部分失败不回滚，会提交
		transactionManager.setGlobalRollbackOnParticipationFailure(true);
		return transactionManager;
	}

	@Bean
	JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}

}
