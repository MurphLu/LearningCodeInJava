package org.jy.tank;

import org.jy.tank.enums.Direction;
import org.jy.tank.model.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private static final int GAME_W = 800, GAME_H = 600;
    Tank myTank = new Tank(10, 10, Direction.DOWN);

    public TankFrame() {
        this.setSize(GAME_W, GAME_H);
        this.setTitle("Tank War");
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(new FrameKeyListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_W, GAME_H);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_W, GAME_H);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0,0, null);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
    }

    class FrameKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;


        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                    bU = true;
                    break;
                case KeyEvent.VK_S:
                    bD = true;
                    break;
                case KeyEvent.VK_A:
                    bL = true;
                    break;
                case KeyEvent.VK_D:
                    bR = true;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.shoot();
                default:
                    break;
            }
            myTank.setMoving(true);
            setMainTankDirection();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                    bU = false;
                    break;
                case KeyEvent.VK_S:
                    bD = false;
                    break;
                case KeyEvent.VK_A:
                    bL = false;
                    break;
                case KeyEvent.VK_D:
                    bR = false;
                    break;
                default:
                    break;
            }
            setMainTankDirection();
        }

        private void setMainTankDirection() {
            if(!(bL || bU || bR || bD)) {
                myTank.setMoving(false);
                return;
            }
            myTank.setMoving(true);
            if(bL) myTank.setDirection(Direction.LEFT);
            if(bU) myTank.setDirection(Direction.UP);
            if(bR) myTank.setDirection(Direction.RIGHT);
            if(bD) myTank.setDirection(Direction.DOWN);
        }
    }
}
