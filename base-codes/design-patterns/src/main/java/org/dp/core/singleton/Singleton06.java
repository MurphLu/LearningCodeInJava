package org.dp.core.singleton;

/**
 * lazy loading
 * 虽然达到了按需初始化的目的，但是线程不安全
 * 可以通过 synchronized 加锁来保证线程安全，但是效率下降
 */
public class Singleton06 {
    private static volatile Singleton06 INSTANCE;

    public static Singleton06 getInstance() {
        if (INSTANCE == null){
            // 减小锁的范围来提高效率, 需要加双判断才可行
            synchronized (Singleton06.class){
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton06();
                }
            }
        }
        return INSTANCE;
    }

    private Singleton06() {}

    public void m() {
        System.out.println("m");
    }

    public static void test() {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton06.getInstance().hashCode());
            }).start();
        }
    }
}
