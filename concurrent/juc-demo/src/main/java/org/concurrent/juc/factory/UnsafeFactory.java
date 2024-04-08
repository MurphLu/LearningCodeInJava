package org.concurrent.juc.factory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeFactory {
    public static Unsafe getUnsafe() {
        try{
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static long getUnsafeOffset(Unsafe unsafe, Class clazz, String filedName) {
        try{
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return unsafe.objectFieldOffset(field);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0L;

    }
}
