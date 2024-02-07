package org.base.map.generic;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException {
        ChildClass bc = new ChildClass();
        System.out.println(Arrays.toString(bc.getClass().getSuperclass().getTypeParameters()));
        System.out.println(bc.getClass().getGenericSuperclass().getTypeName());
        System.out.println(bc.getClass().getField("t").getGenericType());
    }
}
