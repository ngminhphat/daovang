/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.vang;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Player  {
    int positionX, positionY;
    BufferedImage image;
    static Moc moc;
    public Player(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        moc = new Moc(this.positionX + 20, this.positionY + 110);
        try {
            this.image = ImageIO.read(new File("Resources/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(BufferedImage bufferedImage){
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, positionX, positionY, null);
    }
}
