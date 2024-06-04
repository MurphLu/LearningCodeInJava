package org.concurrent.juc.obj;

import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁撤销之调用对象HashCode
 * 调用锁对象的obj.hashCode() 或 System.identityHashCode(obj) 方法会导致该对象的偏向锁被撤销。
 * 因为对于一个对象，其Hashcode只会生成一次并保存，偏向锁是没有地方保存hashcode的。
 * 轻量级锁会在锁记录中记录 hashCode
 *
 * 重量级锁会在 Monitor 中记录 hashCode当对象处于可偏向(也就是线程ID为0)和已偏向的状态下，调用HashCode计算将会使对象再也无法偏向
 *
 * 当对象可偏向时，MarkWord将变成未锁定状态，并只能升级成轻量锁;
 * 当对象正处于偏向锁时，调用HashCode将使偏向锁强制升级成重量锁。
 * <img src="obj_struct.png" />
 */
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