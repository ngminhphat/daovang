/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Game_Screen2;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Screen_nextlevel implements Screen{
    BufferedImage image;
    private int chiso =1;
    private int time = 30;
    int them_tnt=0;
    public Screen_nextlevel(){
        try {
            image = ImageIO.read(new File("Resources/nextman.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void click() {
        
	Game_Manager.getInstance().getStackScreen().push(new Game_Screen2(chiso,time,them_tnt));
    }
    public void set_chiso(){
        chiso = 2;
    }
    public void set_time() {
        time = 60;
    }
    public void them_tnt() {
        them_tnt=1;
    }
    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,0,0,null);
    }
}
