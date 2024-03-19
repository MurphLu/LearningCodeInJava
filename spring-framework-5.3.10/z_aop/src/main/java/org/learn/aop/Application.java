package org.learn.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInvocation;
import org.learn.aop.service.*;
import org.springframework.aop.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.cglib.proxy.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		// 如果 bean 有接口，则需要使用接口来接
		UserInterface userService = (UserInterface)context.getBean("userService");
		userService.test();
		userService.a();
		OrderInterface orderService = (OrderInterface)context.getBean("orderService");
//		orderService.getClass();
		orderService.orderTest();


		CustomerInterface customerInterface = (CustomerInterface) context.getBean("customerService");
		customerInterface.testDefaultImpl();

		DeliveryService deliveryService = (DeliveryService) context.getBean("deliveryService");
		deliveryService.test();
 //		testProxy();
	}

	private static void testProxy() {
		UserService userService = new UserService();
		userService.test();
//		withCglib(userService);
//		withJDK(userService);
		withProxyFactory(userService);
	}


	private static void withProxyFactory(UserService userService) {
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(userService);
		// 如果加上这一句，那么后边 getObject 时就需要使用 UserInterface 来接
//		factory.setInterfaces(UserInterface.class);
		factory.addAdvice((MethodBeforeAdvice) (method, args, target) -> System.out.println("before invoke " + method.getName() + " in class " + target.getClass()));
		factory.addAdvice((AfterReturningAdvice) (returnValue, method, args, target) -> System.out.println("before invoke " + method.getName() + " in class " + target.getClass()));

		// void afterThrowing([Method, args, target], ThrowableSubclass);
		factory.addAdvice(new ThrowsAdvice(){
			public void afterThrowing(Exception ex) {

			}
			// exception 可以指定特定的 exception
			public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {

			}
		});
		factory.addAdvice(new org.aopalliance.intercept.MethodInterceptor() {
			@Nullable
			@Override
			public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
				System.out.println("before");
				Object proceed = invocation.proceed();
				System.out.println("after");
                return proceed;
            }
		});

		factory.addAdvisor(new PointcutAdvisor(){

			@Override
			public Pointcut getPointcut() {
				return new StaticMethodMatcherPointcut() {
					@Override
					public boolean matches(Method method, Class<?> targetClass) {
						return method.getName().equals("a");
					}
				};
			}

			@Override
			public Advice getAdvice() {
				return new org.aopalliance.intercept.MethodInterceptor() {
					@Nullable
					@Override
					public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
						System.out.println("point cut before");
						Object proceed = invocation.proceed();
						System.out.println("point cut after");
						return proceed;
					}
				};
			}

			@Override
			public boolean isPerInstance() {
				return false;
			}
		});
		UserService proxy = (UserService) factory.getProxy();
		proxy.test();
		proxy.a();
	}


	private static void withJDK(UserInterface userService) {
		UserInterface o = (UserInterface)Proxy.newProxyInstance(Application.class.getClassLoader(), new Class[]{UserInterface.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
				System.out.println("before invoke " + method.getName() + " in class " + o.getClass());
				Object invoke = method.invoke(userService, objects);
				System.out.println("after invoke " + method.getName() + " in class " + o.getClass());
				return invoke;
			}
		});
		o.test();

	}

	private static void withCglib(UserService userService) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserService.class);
		enhancer.setCallbacks(new Callback[]{
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    // o -- 代理对象
                    // method -- 方法
                    // objects -- 参数
                    System.out.println(method);
                    System.out.println(methodProxy);
                    // methodProxy --
                    System.out.println("before invoke " +method.getName() + " in class " + o.getClass());
                    // 被代理的对象，目标方法
                    Object res = method.invoke(userService, objects);
//						Object r = method.invoke(o, objects);

                    Object result = methodProxy.invoke(userService, objects);
//						Object resu = methodProxy.invokeSuper(o, objects);
                    // 由于 o 是代理对象，那么 o 来执行方法是会导致循环调用，栈溢出
//						Object resu = methodProxy.invoke(o, objects);
                    System.out.println("after invoke " +method.getName() + " in class " + o.getClass());
                    return result;
                },
				NoOp.INSTANCE
        });
		enhancer.setCallbackFilter(new CallbackFilter() {

			// 返回的数字表示使用上面数组中哪一个 Interceptor
			@Override
			public int accept(Method method) {
                if (method.getName().equals("test")) {
                    return 0;
                }
                return 1;
            }
		});

		UserService enc = (UserService) enhancer.create();
		enc.test();
		enc.a();
	}
}