package org.concurrent.juc.obj;

import org.openjdk.jol.info.ClassLayout;

public class ObjTest {
    public static void main(String[] args) {
        Object o = new Object();
        ClassLayout classLayout = ClassLayout.parseInstance(o);
        System.out.println(classLayout.toPrintable());

        Test t = new Test();
        ClassLayout tcl = ClassLayout.parseInstance(t);
        System.out.println(tcl);
    }
}
class Test extends Object {
    int a;
    long b;
    boolean c;
}