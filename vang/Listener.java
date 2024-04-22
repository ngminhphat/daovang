/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.vang;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
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
                if((e.getX() >= 50 && e.getX() <= 367) && (e.getY() >= 50 && e.getY() <= 337)){
                    ((Start_Screen)Game_Manager.getInstance().getStackScreen().peek()).startGame();
                }
                if(e.getX() >= 300 && e.getX() <= 600 && e.getY() >= 400 && e.getY() <= 500){
                    ((game_over)Game_Manager.getInstance().getStackScreen().peek()).resetGame();
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
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        if(Game_Manager.getInstance().getStackScreen().peek() instanceof Game_Screen  )
                        ((Game_Screen) (Game_Manager.getInstance().getStackScreen().peek())).gapDo();
                        else if(Game_Manager.getInstance().getStackScreen().peek() instanceof Game_Screen2 ){
                            ((Game_Screen2) (Game_Manager.getInstance().getStackScreen().peek())).gapDo();
                        }
                        break;                   
                    case KeyEvent.VK_ENTER:
                        
                         if(Game_Manager.getInstance().getStackScreen().peek() instanceof Screen_nextlevel ){
                            ((Screen_nextlevel) (Game_Manager.getInstance().getStackScreen().peek())).click();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(Game_Manager.getInstance().getStackScreen().peek() instanceof Game_Screen  )
                        ((Game_Screen) (Game_Manager.getInstance().getStackScreen().peek())).huyDovat();
                        else if(Game_Manager.getInstance().getStackScreen().peek() instanceof Game_Screen2 ){
                            ((Game_Screen2) (Game_Manager.getInstance().getStackScreen().peek())).huyDovat();
                        }
                        break;
                    case KeyEvent.VK_3:
                        ((Game_Screen2) (Game_Manager.getInstance().getStackScreen().peek())).set_time();
                        break;
                    case  KeyEvent.VK_2:
                        ((Game_Screen2) (Game_Manager.getInstance().getStackScreen().peek())).player.moc.set_chisoDiem();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    }


