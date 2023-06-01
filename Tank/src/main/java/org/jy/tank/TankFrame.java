package org.jy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    Tank myTank = new Tank(10, 10, Direction.DOWN);

    public TankFrame() {
        this.setSize(800, 600);
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

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        myTank.move();
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
