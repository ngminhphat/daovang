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
    int chiso_Diem=1;
    
    
    
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
    public void set_chisoDiem() {
        chiso_Diem=2;
    }
    public void draw(BufferedImage bufferedImage){
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.rotate(Math.toRadians(anpha), 385, 134);
        g.drawImage(this.imageDay, 385, 134, 2, this.positionY - 134, null);
        g.drawImage(image, 320, this.positionY, null);
    }
    public void an_vang(ArrayList<Gold> listGold){
        Rectangle moc = new Rectangle(this.positionX,y,this.image.getWidth(),this.image.getHeight());
        Rectangle vang = new Rectangle();
        for(Gold gold : listGold) {
            vang = new Rectangle(gold.get_x(),gold.get_y(),gold.image.getWidth(),gold.image.getHeight());
            if(moc.intersects(vang)){
                try {
                    if(gold.weight==1){
                    
                        this.image= ImageIO.read(new File("Resources/gapvang_1.png"));
                    
                    
                    speedY=-1;
                }else if(gold.weight ==3){
                    
                        this.image= ImageIO.read(new File("Resources/gapvang_3.png"));
                    
                    
                    speedY=-2;
                }else if(gold.weight ==4 ){
                    
                        this.image= ImageIO.read(new File("Resources/gapvang_4.png"));
                    
                    }
                    
                    
                    speedY=-3;
                } catch(Exception e) {
                    e.printStackTrace();
                }
                keoVe=true;
                gold.biKeo=true;
                tongDiem+=chiso_Diem*gold.score;
                break;
                
                }
            
    }
    }
    public void anTnt(ArrayList<Tnt> listTnt) {
        Rectangle moc = new Rectangle(this.positionX,y,this.image.getWidth(),this.image.getHeight());
        Rectangle bom;
        for(Tnt tnt :listTnt){
            bom = new Rectangle(tnt.get_x(),tnt.get_y(),tnt.image.getWidth(),tnt.image.getHeight());
        if(moc.intersects(bom)){
            keoVe = true;
            tnt.biKeo=true;
            speedY=-1;
            try{
                image = ImageIO.read(new File("Resources/gaptnt.png"));
            }catch(Exception e) {
                e.printStackTrace();
            }   
        }
        }
        
    }
    public void anDa(ArrayList<Da> listDa) {
        Rectangle moc = new Rectangle(this.positionX,y,this.image.getWidth(),this.image.getHeight());
        Rectangle stone;
        for(Da da : listDa){
            stone = new Rectangle(da.get_x(),da.get_y(),da.image.getWidth(),da.image.getHeight());
        if(moc.intersects(stone)){
            try{
                
                image = ImageIO.read(new File("Resources/gapda_1.png"));
                
            }catch(Exception e) {
                e.printStackTrace();
            }
            keoVe = true;
            da.biKeo=true;
            speedY=-1;
            tongDiem+=chiso_Diem*da.score;
        }
        }
    }
    public void anPig(ArrayList<Pig> listPig) {
        Rectangle moc = new Rectangle(this.positionX,y,this.image.getWidth(),this.image.getHeight());
        Rectangle lon;
        for(Pig pig : listPig){
            lon = new Rectangle(pig.get_x(),pig.get_y(),pig.image.getWidth(),pig.image.getHeight());
        if(moc.intersects(lon)){
            try{
                
                image = ImageIO.read(new File("Resources/gappig.png"));
                
            }catch(Exception e) {
                e.printStackTrace();
            }
            keoVe = true;
            pig.biKeo=true;
            speedY=-1;
            tongDiem+=chiso_Diem*pig.score;
        }
        }
    }
    public void anKc(ArrayList<kim_cuong> listKc) {
        Rectangle moc = new Rectangle(this.positionX,y,this.image.getWidth(),this.image.getHeight());
        Rectangle kimcuong;
        for(kim_cuong kc :listKc){
            kimcuong = new Rectangle(kc.get_x(),kc.get_y(),kc.image.getWidth(),kc.image.getHeight());
        if(moc.intersects(kimcuong)){
            try{
                image = ImageIO.read(new File("Resources/gapkc.png"));
            }catch(Exception e) {
                e.printStackTrace();
            }
            keoVe = true;
            kc.biKeo=true;
            speedY=-2;
            tongDiem+=chiso_Diem*kc.score;
        }
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
        //móc sẽ quay quanh 
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

