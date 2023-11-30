package org.dp.core.singleton;

/**
 * 同 01
 */
public class Singleton02 {
    private static final Singleton02 INSTANCE;
    static {
        INSTANCE = new Singleton02();
    }

    public static Singleton02 getInstance() { return INSTANCE; }

    private Singleton02() {}

    public void m() {
        System.out.println("m");
    }

    public static void test() {
        Singleton02 s1 = Singleton02.getInstance();
        Singleton02 s2 = Singleton02.getInstance();
        System.out.println(s1 == s2);
    }
}
