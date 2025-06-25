package View;

import Controller.Game_Manager;
import Controller.Screen;
import Controller.Screen_win;
import Controller.game_over;
import Model.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class Game_Screen5 implements Screen {
    BufferedImage bg, bg2, bufferedImage2;
    int count2 = 0;
    int tempScore;
    int scoreX = 80;
    int timeChuc, timeDonVi;
    int dem;
    int targetScore = 1800; // tăng điểm cần đạt

    Player player;

    public ArrayList<Gold> listGold = new ArrayList<>();
    public ArrayList<Da> listDa = new ArrayList<>();
    public ArrayList<Pig> listPig = new ArrayList<>();
    public ArrayList<Tnt> listTnt = new ArrayList<>();
    public ArrayList<kim_cuong> listKc = new ArrayList<>();
    public ArrayList<thung> listThung = new ArrayList<>();
    public ArrayList<Image> listDiem = new ArrayList<>();

    public Game_Screen5(int chiso, int tg, int themtnt) {
        player = new Player(300, 24);
        dem = tg > 0 ? tg : 35;
        Player.moc.setChiso_Diem(chiso);
        Player.moc.tongDiem = Game_Manager.playerScore;
        Player.moc.setCountTnt(Game_Manager.playerTnt + themtnt);
        loadResources();
    }

    private void loadResources() {
        try {
            // Vàng khó hơn
            listGold.add(new Gold(100, 420, "Resources/vang_4.png", 650, 4));
            listGold.add(new Gold(550, 310, "Resources/vang_4.png", 600, 4));
            listGold.add(new Gold(420, 430, "Resources/vang_4.png", 550, 4));
            listGold.add(new Gold(260, 250, "Resources/vang_4.png", 500, 4));
            listGold.add(new Gold(600, 190, "Resources/vang_3.png", 350, 3));

            // Đá to, nhiều
            listDa.add(new Da(150, 350, "Resources/da_2.png", 45, 3));
            listDa.add(new Da(320, 300, "Resources/da_2.png", 50, 3));
            listDa.add(new Da(500, 330, "Resources/da_2.png", 40, 3));
            listDa.add(new Da(620, 250, "Resources/da_2.png", 30, 3));

            // Pig
            listPig.add(new Pig(360, 520, "Resources/pig.png", 501, 6));
            listPig.add(new Pig(480, 470, "Resources/pig.png", 501, 7));

            // TNT đặt xa
            listTnt.add(new Tnt(120, 300));
            listTnt.add(new Tnt(700, 280));
            listTnt.add(new Tnt(400, 250));

            // Kim cương giá trị cao, duy nhất
            listKc.add(new kim_cuong(200, 420, "Resources/kc.png", 800));

            // Ít thùng
            listThung.add(new thung(350, 340));

            for (int i = 0; i < 10; i++) {
                listDiem.add(ImageIO.read(new File("Resources/so" + i + ".png")));
            }

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
        Game_Manager.playerScore = player.moc.tongDiem;
        Game_Manager.playerTnt = player.moc.getCountTnt();
        Game_Manager.getInstance().getStackScreen().push(new Screen_win());
    }

    @Override
    public void update() {
        Player.moc.an_vang(listGold);
        Player.moc.anTnt(listTnt);
        Player.moc.anDa(listDa);
        Player.moc.anPig(listPig);
        Player.moc.anKc(listKc);
        Player.moc.anThung(listThung, dem);

        Player.moc.update();
        listPig.forEach(Pig::update);

        if (count2 == 59) {
            dem--;
            count2 = 0;
        }
        count2++;

        tempScore = player.moc.tongDiem;
        if (dem == 0) {
            if (tempScore >= targetScore) next_man();
            else click();
        }

        if (player.moc.keoVe) {
            listGold.removeIf(g -> g.biKeo);
            listTnt.removeIf(t -> t.biKeo);
            listDa.removeIf(d -> d.biKeo);
            listPig.removeIf(p -> p.biKeo);
            listKc.removeIf(k -> k.biKeo);
            listThung.removeIf(t -> t.biKeo);
        }
    }

    public void gapDo() {
        if (player.moc.getSpeedY() == 0) {
            player.moc.setSpeedY(3);
            player.moc.keoVe = false;
        }
    }

    public void huyDovat() {
        if (player.moc.getCountTnt() != 0 && player.moc.keoVe) {
            player.moc.huyVatThe();
            player.moc.setCountTnt(-1);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (bufferedImage2 == null) {
            bufferedImage2 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        }

        Graphics2D g2d = (Graphics2D) g.create();
        Graphics2D bufferG = (Graphics2D) bufferedImage2.getGraphics();

        bufferG.drawImage(bg2, 0, 0, null);
        bufferG.drawImage(bg, 0, 150, null);

        player.draw(bufferedImage2);
        Player.moc.draw(bufferedImage2);

        tempScore = player.moc.tongDiem;
        scoreX = 80;
        for (int i = 0; i < 4; i++) {
            bufferG.drawImage(listDiem.get(tempScore % 10), scoreX, 50, null);
            tempScore /= 10;
            scoreX -= 15;
        }

        int targetX = 80;
        int tempTarget = targetScore;
        for (int i = 0; i < 4; i++) {
            bufferG.drawImage(listDiem.get(tempTarget % 10), targetX, 100, null);
            tempTarget /= 10;
            targetX -= 15;
        }

        int demTnt = player.moc.getCountTnt();
        bufferG.drawImage(listDiem.get(Math.max(0, Math.min(demTnt, 9))), 608, 100, null);

        timeDonVi = dem % 10;
        timeChuc = dem / 10;
        bufferG.drawImage(listDiem.get(timeChuc), 685, 50, null);
        bufferG.drawImage(listDiem.get(timeDonVi), 700, 50, null);

        listGold.forEach(item -> item.draw(bufferedImage2));
        listDa.forEach(item -> item.draw(bufferedImage2));
        listPig.forEach(item -> item.draw(bufferedImage2));
        listTnt.forEach(item -> item.draw(bufferedImage2));
        listKc.forEach(item -> item.draw(bufferedImage2));
        listThung.forEach(item -> item.draw(bufferedImage2));

        g2d.drawImage(bufferedImage2, 0, 0, null);
    }
}
