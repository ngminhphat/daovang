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
public class Gold extends Object {
    int score;
    boolean biKeo = false;
    BufferedImage image;
    int weight;
    public Gold(int posX,int posY,String loaiGold,int score,int weight) {
        super(posX,posY);
        this.score =score;
        this.weight= weight;
        try {
            image = ImageIO.read(new File(loaiGold));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Image bufferedImage) {
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, this.get_x(), this.get_y(), null);
    }
    
}