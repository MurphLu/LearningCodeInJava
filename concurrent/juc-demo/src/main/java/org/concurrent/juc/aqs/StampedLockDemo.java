package org.concurrent.juc.aqs;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    public static void main(String[] args) {
        StampedLock lock = new StampedLock();
        long readLock = lock.readLock();
        long writeLock = lock.writeLock();

    }

    static class Point{
        private final StampedLock lock = new StampedLock();
        private double x;
        private double y;

        public void move(double deltaX, double deltaY) {
            long stamp = lock.writeLock();
            System.out.println("获取到 write lock");
            try{
                x+= deltaX;
                y += deltaY;
            }finally {
                lock.unlockWrite(stamp);
                System.out.println("释放 write lock");
            }
        }

        public double distanceFromOrigin() {
            long stamp = lock.tryOptimisticRead();
            double currentX = x;
            System.out.println("第一次读, x: "+x+", Y: "+y+", currentX: "+currentX);
            try {
                Thread.sleep(2000);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            double currentY = y;
            System.out.println("第二一次读, x: "+x+", Y: "+y+", currentY: "+currentY);

            //检查获取乐观锁之后是否有写操作，如果有，则获取读锁进行读，否则直接使用上面的结果
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    currentX = x;
                    currentY = y;

                    System.out.println("最终结果，x: " +x+ ", Y: "+y+ ", currentX: " + currentX + ", currentY:" + currentY );
                }finally {
                    lock.unlockRead(stamp);
                }

            }
            return Math.sqrt(currentX*currentX + currentY* currentY);
        }
    }
}
