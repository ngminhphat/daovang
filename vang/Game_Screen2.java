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
public class Game_Screen2 implements Screen {
	BufferedImage bg, bg2, bufferedImage2;
        int count = 0;
        int count2 = 0;
        int tempScore;
        int scoreX = 80;
        int timeChuc, timeDonVi;
        int dem =30;
        int targetScore = 500;
	Player player;
        kim_cuong kc;
	public ArrayList<Gold> listGold = new ArrayList<>();
	public ArrayList<Image> listDiem  = new ArrayList<>();
	public ArrayList<Da> listDa = new ArrayList<>();
	public ArrayList<Pig> listPig = new ArrayList<>();
	public ArrayList<Tnt> listTnt = new ArrayList<>();
        public ArrayList<kim_cuong> listKc = new ArrayList<>();
	Gold g1, g2, g3, g4, g5, g6, g7;
	Da d1, d2;
	Pig pig1;
	Tnt tnt,tnt1;

	public Game_Screen2(int chiso,int tg ) {
		player = new Player(300, 24);
                listDiem = new ArrayList<>();
                dem = 60;
                Player.moc.chiso_Diem=2;
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
			kc= new kim_cuong(650,450,"Resources/kc.png",500);
		} catch (Exception e) {
			e.printStackTrace();
		}
                try {
                        listDiem.add(ImageIO.read(new File("Resources/so0.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so1.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so2.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so3.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so4.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so5.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so6.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so7.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so8.png")));
                        listDiem.add(ImageIO.read(new File("Resources/so9.png")));
                    } catch (IOException e) {
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
	}

	@Override
	public void click() {
		
		Game_Manager.getInstance().getStackScreen().push(new game_over());
	}
        
        public void next_man() {
            Game_Manager.getInstance().getStackScreen().push(new Screen_win());
        }
	@Override
	public void update() {
		
		Player.moc.an_vang(listGold);
		Player.moc.anTnt(listTnt);
                Player.moc.anDa(listDa);
                Player.moc.anPig(listPig);
                Player.moc.anKc(listKc);
		Player.moc.update();
                for (Pig pig : listPig) {
			pig.update();
		}
                count++;
                if(count2 == 59){
                    dem--;
                    count2 = 0;
                }
                count2++;
                if( dem==0 &&(tempScore <targetScore )) {
                    this.click();
                }
                if(dem == 0 || (tempScore >=targetScore )) {
                    this.next_man();
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
                if (s.biKeo) {
                    itr7.remove();
                        
                }
            }
            Iterator itr8 = listKc.iterator();
            while (itr8.hasNext()) {
                kim_cuong s = (kim_cuong) itr7.next();
                if (s.biKeo) {
                    itr8.remove();
                        
                }

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
                tempScore = player.moc.tongDiem;
                scoreX = 80;
                for(int i = 0; i < 4; i++){
                    bufferGraphic2D.drawImage(listDiem.get(tempScore%10), scoreX, 50, null);
                    tempScore = tempScore/10;
                    scoreX -= 15;
                }
                timeDonVi = dem % 10;
                timeChuc = dem / 10;
                bufferGraphic2D.drawImage(listDiem.get(timeChuc), 685, 50, null);
                bufferGraphic2D.drawImage(listDiem.get(timeDonVi), 700, 50, null);
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
                for (kim_cuong kc : listKc) {
			kc.draw(bufferedImage2);
		}
		g2d.drawImage(bufferedImage2, 0, 0, null);
	}

	
}
