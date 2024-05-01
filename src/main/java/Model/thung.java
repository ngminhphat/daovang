/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class thung extends Object{
    BufferedImage image;
    
    public boolean biKeo = false;
    
    public thung(int posX,int posY) {
        super(posX,posY);        
        
        try {
            image = ImageIO.read(new File("Resources/thung.png"));
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
