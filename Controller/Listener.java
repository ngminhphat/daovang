/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Game_Screen;
import View.Game_Screen2;
import View.Game_Screen3;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author Administrator
 */
public class Listener extends Frame {
    public Listener(Frame x) {
        x.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        x.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object screen = Game_Manager.getInstance().getStackScreen().peek();
                int mx = e.getX();
                int my = e.getY();

                if (screen instanceof Start_Screen) {
                    if ((mx >= 50 && mx <= 367) && (my >= 50 && my <= 337)) {
                        ((Start_Screen) screen).startGame();
                    }
                } else if (screen instanceof game_over) {
                    if (mx >= 300 && mx <= 600 && my >= 400 && my <= 500) {
                        ((game_over) screen).resetGame();
                    }
                } else if (screen instanceof Screen_nextlevel snl) {
                    if (snl.rectMuaTnt.contains(mx, my)) {
                        snl.add_tnt();
                    } else if (snl.rectMuaChiso.contains(mx, my)) {
                        snl.increase_chiso();
                    } else if (snl.rectMuaTg.contains(mx, my)) {
                        snl.increase_time();
                    } else if (snl.rectNextButton.contains(mx, my)) {
                        snl.click();
                    }
                } else if (screen instanceof Screen_win) {
                    if (mx >= 391 && mx <= 671 && my >= 510 && my <= 550) {
                        ((Screen_win) screen).click();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // Thêm MouseMotionListener để cập nhật hover
        x.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Object screen = Game_Manager.getInstance().getStackScreen().peek();
                if (screen instanceof Screen_nextlevel snl) {
                    snl.updateHover(e.getX(), e.getY());
                    x.repaint();
                }
            }
        });

        x.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Object screen = Game_Manager.getInstance().getStackScreen().peek();

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_DOWN:
                        if (screen instanceof Game_Screen gs) {
                            gs.gapDo();
                        } else if (screen instanceof Game_Screen2 gs2) {
                            gs2.gapDo();
                        } else if (screen instanceof Game_Screen3 gs3) {
                            gs3.gapDo();
                        }
                        break;

                    case KeyEvent.VK_UP:
                        if (screen instanceof Game_Screen gs) {
                            gs.huyDovat();
                        } else if (screen instanceof Game_Screen2 gs2) {
                            gs2.huyDovat();
                        } else if (screen instanceof Game_Screen3 gs3) {
                            gs3.huyDovat();
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}


