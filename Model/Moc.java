/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Administrator
 */
public class Moc {

    int positionX, positionY;
    int speedY = 0, speedX = 0;
    public boolean keoVe = false;
    static int anpha = 0, anphaPlus = 1;
    int y;
    int countTnt = 0;
    int chiso_Diem = 1;
    public int tongDiem = 0;
    int score_temp = 0;
    public BufferedImage image;
    public BufferedImage imageDay;
    int dang_gap = 0;
    public ArrayList<Tnt> myListTnt = new ArrayList<>();

    public void setChiso_Diem(int chiso_Diem) {
        this.chiso_Diem = chiso_Diem;
    }

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

    //Sẽ gọi đến khi người chơi chọn cỏ 4 lá trong cửa hàng
    public void set_chisoDiem() {
        chiso_Diem = 2;
    }

    public int getChiso_Diem() {
        return chiso_Diem;
    }
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void draw(BufferedImage bufferedImage) {
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.rotate(Math.toRadians(anpha), 385, 134);
        g.drawImage(this.imageDay, 385, 134, 2, this.positionY - 134, null);
        g.drawImage(image, 320, this.positionY, null);
    }

    public void an_vang(ArrayList<Gold> listGold) {
        Rectangle moc = new Rectangle(this.positionX, y, this.image.getWidth(), this.image.getHeight());
        Rectangle vang = new Rectangle();
        for (Gold gold : listGold) {
            vang = new Rectangle(gold.get_x(), gold.get_y(), gold.image.getWidth(), gold.image.getHeight());
            if (moc.intersects(vang) && !keoVe) {
                try {
                    if (gold.weight == 1) {
                        this.image = ImageIO.read(new File("Resources/gapvang_1.png"));
                        speedY = -1;
                    } else if (gold.weight == 3) {
                        this.image = ImageIO.read(new File("Resources/gapvang_3.png"));
                        speedY = -2;
                    } else if (gold.weight == 4) {
                        this.image = ImageIO.read(new File("Resources/gapvang_4.png"));
                        speedY = -3;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                keoVe = true;
                gold.biKeo = true;
                score_temp = chiso_Diem * gold.score;
                break;
            }
        }
    }

    public void anTnt(ArrayList<Tnt> listTnt) {
        Rectangle moc = new Rectangle(this.positionX, y, this.image.getWidth(), this.image.getHeight());
        Rectangle bom;
        for (Tnt tnt : listTnt) {
            bom = new Rectangle(tnt.get_x(), tnt.get_y(), tnt.image.getWidth(), tnt.image.getHeight());
            if (moc.intersects(bom) && !keoVe) {
                keoVe = true;
                tnt.biKeo = true;
                speedY = -3;
                countTnt++;
                try {
                    image = ImageIO.read(new File("Resources/gaptnt.png"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setCountTnt(int k) {
        this.countTnt += k;
    }

    public int getCountTnt() {
        return countTnt;
    }

    public void anDa(ArrayList<Da> listDa) {
        Rectangle moc = new Rectangle(this.positionX, y, this.image.getWidth(), this.image.getHeight());
        Rectangle stone;
        for (Da da : listDa) {
            stone = new Rectangle(da.get_x(), da.get_y(), da.image.getWidth(), da.image.getHeight());
            if (moc.intersects(stone) && !keoVe) {
                try {

                    image = ImageIO.read(new File("Resources/gapda_1.png"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                keoVe = true;
                da.biKeo = true;
                speedY = -1;
                score_temp = chiso_Diem * da.score;
            }
        }
    }

    public void anThung(ArrayList<thung> listThung, int dem) {
        Rectangle moc = new Rectangle(this.positionX, y, this.image.getWidth(), this.image.getHeight());
        Rectangle Thung1;
        for (thung Thung : listThung) {
            Thung1 = new Rectangle(Thung.get_x(), Thung.get_y(), Thung.image.getWidth(), Thung.image.getHeight());
            if (moc.intersects(Thung1) && !keoVe) {

                keoVe = true;
                Thung.biKeo = true;
                speedY = -1;
                if (dem % 2 == 0) {
                    countTnt++;
                } else {
                    score_temp = 300;
                }

            }
        }
    }

    public void anPig(ArrayList<Pig> listPig) {
        Rectangle moc = new Rectangle(this.positionX, y, this.image.getWidth(), this.image.getHeight());
        Rectangle lon;
        for (Pig pig : listPig) {
            lon = new Rectangle(pig.get_x(), pig.get_y(), pig.image.getWidth(), pig.image.getHeight());
            if (moc.intersects(lon) && !keoVe) {
                try {

                    image = ImageIO.read(new File("Resources/gappig.png"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                keoVe = true;
                pig.biKeo = true;
                speedY = -3;
                score_temp = chiso_Diem * pig.score;
            }
        }
    }

    public void anKc(ArrayList<kim_cuong> listKc) {
        Rectangle moc = new Rectangle(this.positionX, y, this.image.getWidth(), this.image.getHeight());
        Rectangle kimcuong;
        for (kim_cuong kc : listKc) {
            kimcuong = new Rectangle(kc.get_x(), kc.get_y(), kc.image.getWidth(), kc.image.getHeight());
            if (moc.intersects(kimcuong) && !keoVe) {
                try {
                    image = ImageIO.read(new File("Resources/gapkc.png"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                keoVe = true;
                kc.biKeo = true;
                speedY = -3;
                score_temp = chiso_Diem * kc.score;
            }
        }
    }

    //vẽ móc
    public void update() {
        this.positionY += speedY;
        double d1 = Math.cos(Math.toRadians(anpha));
        double d = Math.sin(Math.toRadians(anpha));
        y = ((int) ((positionY - 134) * d1)) + 134;
        this.positionX = -((int) ((positionY - 134) * d)) + 350;
        //khi móc quay quanh trục 
        if (this.positionY <= 134) {
            tongDiem+= score_temp;
            score_temp = 0;
            keoVe = false;
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
        //khi móc vượt quá màn hình sẽ cho quay trở lại
        if (this.positionY >= 600) {
            keoVe = true;
            speedY = -speedY;
        }
        //móc sẽ quay quanh trục 
        if (this.positionX == 350 && this.positionY == 134) {
            if (anpha == 60) {
                anphaPlus = -1;
            } else if (anpha == -60) {
                anphaPlus = 1;
            }
            anpha += anphaPlus;
        }
    }

    public void huyVatThe() {
        score_temp = 0;
        speedY = -3;
        try {
            this.image = ImageIO.read(new File("Resources/moc.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

