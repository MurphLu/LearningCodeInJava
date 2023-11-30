package org.dp.core.singleton;

/**
 * lazy loading
 * 虽然达到了按需初始化的目的，但是线程不安全
 */
public class Singleton03 {
    private static Singleton03 INSTANCE;

    public static Singleton03 getInstance() {
        if (INSTANCE == null){
            try{
                Thread.sleep(1);
            }  catch (InterruptedException e){
                e.printStackTrace();
            }
            INSTANCE = new Singleton03();
        }
        return INSTANCE;
    }

    private Singleton03() {}

    public void m() {
        System.out.println("m");
    }

    public static void test() {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton03.getInstance().hashCode());
            }).start();
        }
    }
}
