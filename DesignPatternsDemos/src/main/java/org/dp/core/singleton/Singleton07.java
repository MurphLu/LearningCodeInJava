package org.dp.core.singleton;

/**
 * 静态内部类方式
 * jvm 保证单例
 * 加载外部类时不会加载内部类，这样实现懒加载
 */
public class Singleton07 {
    private static class SingletonHolder {
        private final static Singleton07 INSTANCE = new Singleton07();
    }
    public static Singleton07 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Singleton07() {}

    public void m() {
        System.out.println("m");
    }

    public static void test() {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton07.getInstance().hashCode());
            }).start();
        }
    }
}
