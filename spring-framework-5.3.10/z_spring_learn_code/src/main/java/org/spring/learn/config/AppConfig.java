package org.spring.learn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("org.spring.learn")
@EnableAspectJAutoProxy
public class AppConfig {

}
