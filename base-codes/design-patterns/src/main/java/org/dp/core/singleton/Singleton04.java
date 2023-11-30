package org.dp.core.singleton;

/**
 * lazy loading
 * 虽然达到了按需初始化的目的，但是线程不安全
 * 可以通过 synchronized 加锁来保证线程安全，但是效率下降
 */
public class Singleton04 {
    private static Singleton04 INSTANCE;

    public static synchronized Singleton04 getInstance() {
        if (INSTANCE == null){
            try{
                Thread.sleep(1);
            }  catch (InterruptedException e){
                e.printStackTrace();
            }
            INSTANCE = new Singleton04();
        }
        return INSTANCE;
    }

    private Singleton04() {}

    public void m() {
        System.out.println("m");
    }

    public static void test() {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton04.getInstance().hashCode());
            }).start();
        }
    }
}
