package org.dp.core.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 享元模式
// 链接池，线程池。。。
public class Main {
    public static void main(String[] args) {
        BulletPool bp = new BulletPool();
        for (int i = 0; i < 10; i++) {
            System.out.println(bp.getBullet());
        }
    }
}


class Bullet {
    public UUID id = UUID.randomUUID();
    boolean living = false;

    @Override
    public String toString() {
        return "Bullet{" +
                "id=" + id +
                ", living=" + living +
                '}';
    }
}

class BulletPool{
    List<Bullet> bullets = new ArrayList<>();
    {
        for (int i = 0; i < 5; i++) {
            bullets.add(new Bullet());
        }
    }

    public Bullet getBullet() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if(!b.living) return b;
        }
        return new Bullet();
    }
}