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
 * @author Administrator
 */
public class kim_cuong extends BaseObject {
    int score;
    BufferedImage image;

    public kim_cuong(int posX, int posY, String img, int score) {
        super(posX, posY);
        this.score = score;

        try {
            image = ImageIO.read(new File(img));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Image bufferedImage) {
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(this.image, this.get_x(), this.get_y(), null);
    }


}
