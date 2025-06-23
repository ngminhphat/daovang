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
                if ((e.getX() >= 50 && e.getX() <= 367) && (e.getY() >= 50 && e.getY() <= 337)) {
                    ((Start_Screen) Game_Manager.getInstance().getStackScreen().peek()).startGame();
                }
                if (e.getX() >= 300 && e.getX() <= 600 && e.getY() >= 400 && e.getY() <= 500) {
                    ((game_over) Game_Manager.getInstance().getStackScreen().peek()).resetGame();
                }
                if (e.getX() >= 580 && e.getX() <= 752 && e.getY() >= 218 && e.getY() <= 282) {
                    ((Screen_nextlevel) (Game_Manager.getInstance().getStackScreen().peek())).click();
                }
                if (e.getX() >= 391 && e.getX() <= 671 && e.getY() >= 510 && e.getY() <= 550) {
                    ((Screen_win) (Game_Manager.getInstance().getStackScreen().peek())).click();
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

                    case KeyEvent.VK_1:
                        if (screen instanceof Screen_nextlevel snl) {
                            snl.add_tnt();
                        }
                        break;

                    case KeyEvent.VK_2:
                        if (screen instanceof Screen_nextlevel snl) {
                            snl.increase_chiso();
                        }
                        break;

                    case KeyEvent.VK_3:
                        if (screen instanceof Screen_nextlevel snl) {
                            snl.increase_time();
                        }
                        break;

                    case KeyEvent.VK_SPACE:
                        if (screen instanceof Screen_nextlevel snl) {
                            snl.click();
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


