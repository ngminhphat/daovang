/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;




import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Administrator
 */

public class game_window extends Frame implements Runnable {
    BufferedImage bufferedImage;
    public game_window(){
        this.setTitle("Gold Miner");
        this.setSize(800, 600);
        this.setVisible(true);
        Game_Manager.getInstance().getStackScreen().push(new Start_Screen());
        this.setLocationRelativeTo(null);
        Listener listen = new Listener(this);
    }
    public void gameUpdate(){
        Game_Manager.getInstance().getStackScreen().peek().update();
    }

    @Override
    public void update(Graphics g) {
        if(bufferedImage == null) {
            bufferedImage = new BufferedImage(800, 600, 1);
        }
        Graphics bufferGraphic = bufferedImage.getGraphics();
        Game_Manager.getInstance().getStackScreen().peek().draw(bufferGraphic);
        g.drawImage(bufferedImage, 0, 0, null);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(17);
                gameUpdate();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}