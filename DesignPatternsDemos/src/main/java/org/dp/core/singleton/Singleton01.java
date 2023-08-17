package org.dp.core.singleton;

/**
 * 饿汉式

 * 类加载到内存后，就实例化一个单利，JVM 保证线程安全
 * 简单实用，推荐使用
 * 缺点：不管用到与否，类装载时都会完成实例化
 */
public class Singleton01 {
    private static final Singleton01 INSTANCE = new Singleton01();

    public static Singleton01 getInstance() { return INSTANCE; }

    private Singleton01() {}

    public void m() {
        System.out.println("m");
    }

    public static void test() {
        Singleton01 s1 = Singleton01.getInstance();
        Singleton01 s2 = Singleton01.getInstance();
        System.out.println(s1 == s2);
    }
}
