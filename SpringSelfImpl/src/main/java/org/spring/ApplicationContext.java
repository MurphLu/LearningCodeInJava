package org.spring;

import org.spring.annotation.*;
import org.spring.annotation.enums.ScopeType;
import org.spring.interfaces.BeanNameAware;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {

    private final Class<?> configClass;
    private final Map<String, Object> singletonObjects;
    private final Map<String, BeanDefinition> beanDefinitionMap;

    private final List<BeanPostProcessor> beanPostProcessorList;

    public ApplicationContext(Class<?> appConfigClass) {
        this.configClass = appConfigClass;
        this.singletonObjects = new HashMap<>();
        this.beanDefinitionMap = new HashMap<>();
        this.beanPostProcessorList = new ArrayList<>();
        afterConstructor();
    }

    private void afterConstructor() {
        scan();
        initializeNonLazyBean();
    }

    /**
     * 扫描 configClass 中指定的包
     */
    private void scan() {
        if (this.configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan annotation = this.configClass.getAnnotation(ComponentScan.class);
            String packageName = annotation.value();
            ClassLoader cl = ApplicationContext.class.getClassLoader();

            String path = packageName.replace(".", "/");

            scanAndLoadClass(cl, path, packageName);
        }
    }

    /**
     * 递归遍历当前包下的类，并调用 loadClass
     * @param cl classLoader
     * @param path 当前路径
     * @param basePackage 扫描的根包名
     */
    private void scanAndLoadClass(ClassLoader cl, String path, String basePackage) {
        URL resource = cl.getResource(path);
        if (resource == null || resource.getFile() == null) {return;}
        File file = new File(resource.getFile());
        File[] files = file.listFiles();
        if (files == null) { return; }
        for (File f: files) {
            if (f.isDirectory()) {
                String nextPath = f.getPath();
                scanAndLoadClass(cl, nextPath.substring(nextPath.lastIndexOf(path)), basePackage);
            } else {
                if (!f.getPath().endsWith(".class")) {
                    continue;
                }
                loadClass(cl, f, basePackage);
            }
        }
    }

    /**
     * 加载类并调用 generateBeanDefinition 生成 Bean 的类信息
     * @param cl classLoader
     * @param f 类文件
     * @param basePackage 扫描的根包名
     */
    private void loadClass(ClassLoader cl, File f, String basePackage) {
        String path = f.getPath();
        String className = path.replace("/", ".");
        className = className.substring(className.lastIndexOf(basePackage), className.lastIndexOf(".class"));
        try {
            Class<?> aClass = cl.loadClass(className);
            generateBeanDefinition(aClass, className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成 beanDefinition 对象
     * @param aClass 加载的类
     * @param className 类名
     */
    private void generateBeanDefinition(Class<?> aClass, String className) {
        if (aClass.isAnnotationPresent(Component.class)) {
            if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                try {
                    BeanPostProcessor processor = (BeanPostProcessor)aClass.getConstructor(new Class[]{}).newInstance();
                    beanPostProcessorList.add(processor);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                return;
            }

            Component component = aClass.getAnnotation(Component.class);
            String beanName = component.value();
            beanName = beanName.isEmpty() ? getDefaultBeanName(aClass) : beanName;
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setType(aClass);
            beanDefinition.setScopeType(ScopeType.SINGLETON);
            if (aClass.isAnnotationPresent(Scope.class)) {
                Scope scope = aClass.getAnnotation(Scope.class);
                beanDefinition.setScopeType(scope.value());
            }
            beanDefinitionMap.put(beanName, beanDefinition);
        }
    }

    /**
     * 实例化非懒加载的 Bean
     */
    private void initializeNonLazyBean() {
        for (Map.Entry<String, BeanDefinition> entry: beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if(!beanDefinition.isLazy() && !singletonObjects.containsKey(beanName)) {
                singletonObjects.put(beanName, createBean(beanName, beanDefinition));
            }
        }
    }

    /**
     * 实例化 Bean
     * @param beanName bean name
     * @param beanDefinition beanDefinition
     * @return 返回实例化完成的 Bean
     */
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getType();
        try{
            Object instance = clazz.getConstructor(new Class[]{}).newInstance();
            setProperties(instance, beanDefinition);

            if (instance instanceof BeanNameAware) {
                ((BeanNameAware)instance).setBeanName(beanName);
            }

            for (BeanPostProcessor processor : beanPostProcessorList) {
                processor.postProcessBeforeInitialization(instance, beanName);
            }

            if(instance instanceof InitializeBean) {
                ((InitializeBean) instance).afterPropertiesSet();
            }
            for (BeanPostProcessor processor : beanPostProcessorList) {
                instance = processor.postProcessAfterInitialization(instance, beanName);
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setProperties(Object instance, BeanDefinition beanDefinition) {
        Field[] fields = beanDefinition.getType().getDeclaredFields();
        for (Field field: fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                try {
                    String fieldName = field.getName();
                    fieldName = fieldName.substring(fieldName.lastIndexOf(".") + 1);
                    Object value = getBean(fieldName);
                    field.set(instance, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 根据 beanName 获取 bean
     * @param beanName beanName
     * @return 返回 bean
     */
    public Object getBean(String beanName) {
        if(!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException("can not find " + beanName + " in spring context");
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScopeType() == ScopeType.SINGLETON) {
            if (!singletonObjects.containsKey(beanName)) {
                singletonObjects.put(beanName, createBean(beanName, beanDefinition));
            }
            return singletonObjects.get(beanName);
        } else {
            return createBean(beanName, beanDefinition);
        }
    }

    /**
     * 根据类获取 bean
     * @param clazz 类
     * @return 返回 bean
     * @param <T> T
     */
    public <T> T getBean(Class<T> clazz) {
        Object o = getBean(getDefaultBeanName(clazz));
        return o == null ? null : (T)o;
    }

    /**
     * 如果 component 未设置bean name，则默认用首字母小写的类名作为 bean 名
     * @param clazz 类
     * @return 返回默认 bean 名字
     */
    private String getDefaultBeanName(Class<?> clazz) {
        return Introspector.decapitalize(clazz.getSimpleName());
    }
}
