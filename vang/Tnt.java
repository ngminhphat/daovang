/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.vang;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Tnt extends Object {
    
    boolean biKeo = false;
    BufferedImage image;
    int weight;
    static int count=0;
    public Tnt(int posX,int posY) {
        super(posX,posY);        
        this.weight= weight;
        try {
            image = ImageIO.read(new File("Resources/tnt1.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        count++;
    }

    @Override
    public void draw(Image bufferedImage) {
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, this.get_x(), this.get_y(), null);
    }
    public void draw1(Image bufferedImage,int x,int y) {
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, x, y, null);
    }
}
