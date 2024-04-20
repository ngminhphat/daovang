package com.mycompany.dao.vang;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Game_Screen implements Screen {
    BufferedImage bg, bg2, bufferedImage2;
    public ArrayList<Gold> listGold = new ArrayList<>();
    Player player;
    Gold g1,g2,g3,g4,g5,g6;
    Tnt tnt;
    public ArrayList<Image> listTime = new ArrayList<>();
    public ArrayList<Tnt> listTnt = new ArrayList<>();
    public Game_Screen() {
        player = new Player(300, 24);        
        try{
        g4 = new Gold(152,405,"Resources/vang_1.png",400,1);
        g3 = new Gold(452,350,"Resources/vang_3.png",300,3);
        g2 = new Gold(341,286,"Resources/vang_3.png",100,3);
        g1 = new Gold(200,280,"Resources/vang_4.png",50,4);
        g5 = new Gold(600,300,"Resources/vang_4.png",50,4);
        g6 = new Gold(50,250,"Resources/vang_4.png",50,4);
        tnt = new Tnt(400,200);
        }catch(Exception e ){
            e.printStackTrace();
        }
        listGold.add(g1);
        listGold.add(g2);
        listGold.add(g3);
        listGold.add(g4);
        listGold.add(g5);
        listGold.add(g6);
        listTnt.add(tnt);
        try {
            bg = ImageIO.read(new File("Resources/bg.png"));
            bg2 = ImageIO.read(new File("Resources/bg2.png"));          
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void click() {
        Game_Manager.getInstance().getStackScreen().pop();
        Game_Manager.getInstance().getStackScreen().push(new Game_Screen2());
    }

    @Override
    public void update() {
        Player.moc.an_vang(listGold);
        //Player.moc.anTnt(tnt);
        Player.moc.update();
        if(player.moc.keoVe){
            Iterator itr1 = listGold.iterator();
            while (itr1.hasNext()) {
                Gold g= (Gold) itr1.next();
                if (g.biKeo) itr1.remove();
            }
            Iterator itr2 = listTnt.iterator();
            while (itr1.hasNext()){
            Tnt g1 = (Tnt) itr2.next();
            if (g1.biKeo) itr2.remove();
            }
            
//            Iterator itr5 = listDa1.iterator();
//            while (itr5.hasNext()) {
//                Da s = (Da) itr5.next();
//                if (s.biKeo) itr5.remove();
//            }
//            Iterator itr6 = listPig.iterator();
//            while (itr6.hasNext()) {
//                Pig s = (Pig) itr6.next();
//                if (s.biKeo) itr6.remove();
//            }
//            Iterator itr7 = listTnt.iterator();
//            while (itr7.hasNext()) {
//                Tnt s = (Tnt) itr7.next();
//                if (s.biKeo) itr7.remove();
//            }

        }
        
    }
    public void  gapDo() {
        
        player.moc.speedY=3;        
    }
    public void huyDovat() {
        if(tnt.count !=0 && Player.moc.keoVe ){
            try {
                Player.moc.image=ImageIO.read(new File("Resources/moc.png"));
        }catch(Exception e) {
            e.printStackTrace();;
    }
    }
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
    public ArrayList getArray() {
        return listGold;
    }
}
