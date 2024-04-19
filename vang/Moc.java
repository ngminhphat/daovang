/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.vang;


import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 *
 * @author Administrator
 */
public class Moc  {
    int tongDiem = 0;
    int positionX, positionY;
    BufferedImage image;
    BufferedImage imageDay;
    int speedY = 0, speedX = 0;
    boolean keoVe = false;
    static int anpha = 0, anphaPlus = 1;
    int y;
    
    

    public Moc(int positionX, int positionY) {
        
        this.positionX = positionX;
        this.positionY = positionY;
        
        try {
            image = ImageIO.read(new File("Resources/moc.png"));
            this.imageDay = ImageIO.read(new File("Resources/day.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(BufferedImage bufferedImage){
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.rotate(Math.toRadians(anpha), 385, 134);
        g.drawImage(this.imageDay, 385, 134, 2, this.positionY - 134, null);
        g.drawImage(image, 320, this.positionY, null);
    }
    public void an_vang(ArrayList<Gold> listGold){
        Rectangle vang = new Rectangle();
        for(Gold gold : listGold) {
            
        }
    }
    

    public void update(){
        this.positionY += speedY;
        double d1 = Math.cos(Math.toRadians(anpha));
        double d = Math.sin(Math.toRadians(anpha));
        y = ((int) ((positionY - 134)*d1)) + 134;
        this.positionX  = -((int) ((positionY - 134)*d)) + 350;
        if(this.positionY <= 134){
            speedY = 0;
            speedX = 0;
            this.positionX = 350;
            this.positionY = 134;
            try {
                this.image = ImageIO.read(new File("Resources/moc.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(this.positionY >= 600){
            keoVe = true;
            speedY = -3;
            this.speedX = (int) (this.speedY*d);
        }
        if(this.positionX == 350 && this.positionY == 134){
            if(anpha == 60){
                anphaPlus = -1;
            } else if(anpha == -60){
                anphaPlus = 1;
            }
            anpha += anphaPlus;
        }
   }    
}

