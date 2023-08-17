package org.jy.tank.model;

import org.jy.tank.enums.Direction;

import java.awt.*;
import java.util.ArrayList;

public class Tank {
    private int x, y;
    private Direction direction;
    private static final int SPEED = 10;

    private boolean moving = false;

    private ArrayList<Bullet> bullets = new ArrayList<>();

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.fillRect(x, y, 50, 50);
        g.setColor(c);
        move();
        for (Bullet bullet: bullets) {
            bullet.paint(g);
        }
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void move() {
        if(!moving) { return; }
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

    public void shoot() {
        bullets.add(new Bullet(x, y, direction));
    }
}
