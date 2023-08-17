package org.jy.tank.model;

import org.jy.tank.enums.Direction;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 20;
    private int x, y;
    private Direction direction;

    public Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, 10, 10);
        g.setColor(c);
        move();
    }

    private void move() {
        switch (direction){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }
}
