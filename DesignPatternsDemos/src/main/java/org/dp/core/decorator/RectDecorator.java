package org.dp.core.decorator;

import java.awt.*;

public class RectDecorator extends GameObject{
    GameObject go;

    public RectDecorator(GameObject go) {
        this.go = go;
    }

    @Override
    void print(Graphics g) {
        go.print(g);
        System.out.println("rect decorator.....");
    }
}
