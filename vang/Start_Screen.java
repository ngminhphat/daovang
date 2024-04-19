/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.vang;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Start_Screen implements Screen {
    BufferedImage backGround, startImage;

    public Start_Screen(){
        try {
            backGround = ImageIO.read(new File("Resources/startMenu.png"));
            startImage = ImageIO.read(new File("Resources/button_start.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void click() {
        Game_Manager.getInstance().getStackScreen().push(new Game_Screen());
    }

    @Override
    public void update() {

    }

    public void startGame(){
        click();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backGround, 0, 0, null);
        g.drawImage(startImage, 50, 50, null);
    }
}