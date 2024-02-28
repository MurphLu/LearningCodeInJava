### Bean 生命周期

### Bean 依赖注入

### Bean 循环依赖

### 推断构造方法

1. 默认情况，用无参构造方法，或者只有一个构造方法就用那一个
2. 指定了构造方法入参值，通过getBean()或者 BeanDefinition.getConstructorArgumentValues() 指定，那就用所匹配的构造方法
3. 让 Spring 自动选择构造方法以及构造方法的入参值，autowire="constructor"
4. 通过 @Autowired 注解指定了某个构造方法，但是希望Spring自动找该构造方法的入参值
