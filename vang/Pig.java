package com.mycompany.dao.vang;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Pig extends Object {
	int score;
	int weight;
	BufferedImage image;
        int speedX;
        boolean biKeo = false;
	public Pig(int posX, int posY, String img, int score, int weight) {
		super(posX, posY);
		this.score = score;
		this.weight = weight;
		speedX = -3;
		try {
			this.image = ImageIO.read(new File(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Image bImg) {
		Graphics g = bImg.getGraphics();
		g.drawImage(this.image, this.get_x(), this.get_y(), null);
	}
	
	
	public void update() {
		this.set_x(this.get_x()+ speedX);
		if (this.get_x() < -10) {
			this.set_x(810);
		}
	}
}
