package org.dp.core.singleton;

/**
 * lazy loading
 * 虽然达到了按需初始化的目的，但是线程不安全
 * 可以通过 synchronized 加锁来保证线程安全，但是效率下降
 */
public class Singleton05 {
    private static Singleton05 INSTANCE;

    public static  Singleton05 getInstance() {
        if (INSTANCE == null){
            // 减小锁的范围来提高效率，但是不起作用
            synchronized (Singleton05.class){
                try{
                    Thread.sleep(1);
                }  catch (InterruptedException e){
                    e.printStackTrace();
                }
                INSTANCE = new Singleton05();
            }
        }
        return INSTANCE;
    }

    private Singleton05() {}

    public void m() {
        System.out.println("m");
    }

    public static void test() {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton05.getInstance().hashCode());
            }).start();
        }
    }
}
