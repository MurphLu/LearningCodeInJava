package org.dp.core.decorator;

import java.awt.*;

public class TailDecorator extends GameObject{
    GameObject go;

    public TailDecorator(GameObject go) {
        this.go = go;
    }

    @Override
    void print(Graphics g) {
        go.print(g);
        System.out.println("tail .......");
    }
}
