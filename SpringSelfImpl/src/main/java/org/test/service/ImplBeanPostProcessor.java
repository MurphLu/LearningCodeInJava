package org.test.service;

import org.spring.BeanPostProcessor;
import org.spring.annotation.Component;
import org.test.service.annotation.ServerAddress;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class ImplBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        for (Field field:bean.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(ServerAddress.class)) {
                ServerAddress annotation = field.getAnnotation(ServerAddress.class);
                field.setAccessible(true);
                try {
                    field.set(bean, annotation.value());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals("orderService")){
            return Proxy.newProxyInstance(ImplBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("before " + method.getName() + " call");
                    Object invoke = method.invoke(bean, args);
                    System.out.println("after " + method.getName() + " call");
                    return invoke;
                }

            });
        }
        return bean;
    }
}
