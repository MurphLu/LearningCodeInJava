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
            return Unsafe.getUnsafe();
        }
    }
}
