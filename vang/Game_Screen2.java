package com.mycompany.dao.vang;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Game_Screen2 implements Screen {
    BufferedImage bg, bg2, bufferedImage2;
    ArrayList<Gold> listGold = new ArrayList<>();
    
    Player player;
    Gold g1,g2,g3,g4,g5,g6;
    
    public Game_Screen2() {
        player = new Player(300, 24);
        try{
        g4 = new Gold(385,495,"Resources/vang_1.png",100,1);
        g3 = new Gold(145,410,"Resources/vang_2.png",200,2);
        g2 = new Gold(58,150,"Resources/vang_3.png",300,3);
        g1 = new Gold(700,364,"Resources/vang_4.png",400,4);
        g5 = new Gold(450,445,"Resources/vang_3.png",100,1);
        g6 = new Gold(600,500,"Resources/vang_2.png",200,2);
        }catch(Exception e ){
            e.printStackTrace();
        }
        listGold.add(g1);
        listGold.add(g2);
        listGold.add(g3);
        listGold.add(g4);
        listGold.add(g5);
        listGold.add(g6);
        try {
            bg = ImageIO.read(new File("Resources/bg.png"));
            bg2 = ImageIO.read(new File("Resources/bg2.png"));          
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void click() {
    }

    @Override
    public void update() {
        Player.moc.update();
    }
    public void  gapDo() {
        player.moc.speedY=3;
        
    }
    @Override
    public void draw(Graphics g) {   
        Graphics2D g2d;
        g2d = (Graphics2D)g.create();
        if(bufferedImage2 == null){
            bufferedImage2 = new BufferedImage(800, 600, 1);
        }
           
        
        Graphics2D bufferGraphic2D = (Graphics2D) bufferedImage2.getGraphics();
        bufferGraphic2D.drawImage(Player.moc.image, Player.moc.positionX, Player.moc.positionY, null);
        bufferGraphic2D.drawImage(bg2, 0, 0, null);
        bufferGraphic2D.drawImage(bg, 0, 150, null);
          player.draw( bufferedImage2);
        Player.moc.draw( bufferedImage2);
        for(Gold gold :listGold ){
            gold.draw(bufferedImage2);
        }
        g2d.drawImage(bufferedImage2, 0, 0, null);
    }
    
}
