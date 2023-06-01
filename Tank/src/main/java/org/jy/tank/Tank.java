package org.jy.tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private Direction direction;
    private static final int speed = 10;

    private boolean moving = false;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void move() {
        if(!moving) { return; }
        switch (direction){
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
    }
}
