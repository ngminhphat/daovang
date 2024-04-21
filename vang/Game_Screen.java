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
	Player player;
	public ArrayList<Gold> listGold = new ArrayList<>();
	public ArrayList<Image> listTime = new ArrayList<>();
	public ArrayList<Da> listDa = new ArrayList<>();
	public ArrayList<Pig> listPig = new ArrayList<>();
	public ArrayList<Tnt> listTnt = new ArrayList<>();

	Gold g1, g2, g3, g4, g5, g6, g7;
	Da d1, d2;
	Pig pig1;
	Tnt tnt;

	public Game_Screen() {
		player = new Player(300, 24);
		try {
			g1 = new Gold(90, 390, "Resources/vang_1.png", 400, 1);
			g2 = new Gold(600, 320, "Resources/vang_3.png", 300, 3);
			g3 = new Gold(430, 430, "Resources/vang_3.png", 100, 3);
			g4 = new Gold(200, 350, "Resources/vang_4.png", 50, 4);
			g5 = new Gold(600, 250, "Resources/vang_4.png", 50, 4);
			g6 = new Gold(450, 300, "Resources/vang_4.png", 50, 4);
			g7 = new Gold(130, 250, "Resources/vang_4.png", 50, 4);
			d1 = new Da(250, 390, "Resources/da_1.png", 20, 1);
			d2 = new Da(660, 250, "Resources/da_2.png", 15, 2);
			pig1 = new Pig(300, 520, "Resources/pig.png", 501, 4);
			tnt = new Tnt(350, 290);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listGold.add(g1);
		listGold.add(g2);
		listGold.add(g3);
		listGold.add(g4);
		listGold.add(g5);
		listGold.add(g6);
		listGold.add(g7);
		listDa.add(d1);
		listDa.add(d2);
		listPig.add(pig1);
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
		Player.moc.anTnt(listTnt);
                Player.moc.anDa(listDa);
                Player.moc.anPig(listPig);
                
		Player.moc.update();
                for (Pig pig : listPig) {
			pig.update();
		}
		if (player.moc.keoVe) {
			Iterator itr1 = listGold.iterator();
			while (itr1.hasNext()) {
				Gold g = (Gold) itr1.next();
				if (g.biKeo)
					itr1.remove();
			}
			Iterator itr2 = listTnt.iterator();
			while (itr2.hasNext()) {
				Tnt g1 = (Tnt) itr2.next();
				if (g1.biKeo)
					itr2.remove();
			}

            Iterator itr5 = listDa.iterator();
            while (itr5.hasNext()) {
                Da s = (Da) itr5.next();
                if (s.biKeo) itr5.remove();
            }
            Iterator itr6 = listPig.iterator();
            while (itr6.hasNext()) {
                Pig s = (Pig) itr6.next();
                if (s.biKeo) itr6.remove();
            }
            Iterator itr7 = listTnt.iterator();
            while (itr7.hasNext()) {
                Tnt s = (Tnt) itr7.next();
                if (s.biKeo) itr7.remove();
            }

		}

	}

	public void gapDo() {
		player.moc.speedY = 3;
                
	}

	public void huyDovat() {
		if (tnt.count != 0 && Player.moc.keoVe) {
			try {
				Player.moc.image = ImageIO.read(new File("Resources/moc.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
                        tnt.count--;
		}
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d;
		g2d = (Graphics2D) g.create();
		if (bufferedImage2 == null) {
			bufferedImage2 = new BufferedImage(800, 600, 1);
		}

		Graphics2D bufferGraphic2D = (Graphics2D) bufferedImage2.getGraphics();
		bufferGraphic2D.drawImage(Player.moc.image, Player.moc.positionX, Player.moc.positionY, null);
		bufferGraphic2D.drawImage(bg2, 0, 0, null);
		bufferGraphic2D.drawImage(bg, 0, 150, null);
		player.draw(bufferedImage2);
		Player.moc.draw(bufferedImage2);

		for (Gold gold : listGold) {
			gold.draw(bufferedImage2);
		}
		for (Da da : listDa) {
			da.draw(bufferedImage2);
		}
		for (Pig pig : listPig) {
			pig.draw(bufferedImage2);
		}
		for (Tnt tnt : listTnt) {
			tnt.draw(bufferedImage2);
		}
		g2d.drawImage(bufferedImage2, 0, 0, null);
	}

	
}
