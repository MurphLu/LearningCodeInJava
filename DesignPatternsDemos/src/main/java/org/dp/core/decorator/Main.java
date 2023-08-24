package org.dp.core.decorator;

public class Main {
    public static void main(String[] args) {
        GameObject o = new TailDecorator(new RectDecorator(new GoDecorator(new Bullet())));
        o.print(null);
    }
}
