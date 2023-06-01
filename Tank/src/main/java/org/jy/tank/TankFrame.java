package org.jy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private static final int STEP = 10;
    int paintX = 10;
    int paintY = 10;
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
        g.fillRect(paintX, paintY, 50, 50);
    }

    class FrameKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                    paintY -= STEP;
                    break;
                case KeyEvent.VK_S:
                    paintY += STEP;
                    break;
                case KeyEvent.VK_A:
                    paintX -= STEP;
                    break;
                case KeyEvent.VK_D:
                    paintX += STEP;
                    break;
                default:
                    break;
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
        }
    }
}
