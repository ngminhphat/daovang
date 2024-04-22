/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.vang;

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
    public Screen_nextlevel(){
        try {
            image = ImageIO.read(new File("Resources/gameover.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void click() {
        
	Game_Manager.getInstance().getStackScreen().push(new Game_Screen2());
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,0,0,null);
    }
}
