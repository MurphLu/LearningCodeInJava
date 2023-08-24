package org.dp.core.decorator;

import java.awt.*;

public class GoDecorator extends GameObject{
    GameObject go;

    public GoDecorator(GameObject go) {
        this.go = go;
    }

    @Override
    void print(Graphics g) {
        go.print(g);
        System.out.println("go decorator.....");
    }
}
