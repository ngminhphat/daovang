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

public class Game_Screen4 implements Screen {
    BufferedImage bg, bg2, bufferedImage2;
    int count2 = 0;
    int tempScore;
    int scoreX = 80;
    int timeChuc, timeDonVi;
    int dem;
    int targetScore = 1300;

    Player player;

    public ArrayList<Gold> listGold = new ArrayList<>();
    public ArrayList<Da> listDa = new ArrayList<>();
    public ArrayList<Pig> listPig = new ArrayList<>();
    public ArrayList<Tnt> listTnt = new ArrayList<>();
    public ArrayList<kim_cuong> listKc = new ArrayList<>();
    public ArrayList<thung> listThung = new ArrayList<>();
    public ArrayList<Image> listDiem = new ArrayList<>();
    public ArrayList<HamRang> listHamRang = new ArrayList<>();

    public Game_Screen4(int chiso, int tg, int themtnt) {
        player = new Player(300, 24);
        dem = tg > 0 ? tg : 35;
        Player.moc.setChiso_Diem(chiso);

        Player.moc.setChiso_Diem(chiso);
        Player.moc.tongDiem = Game_Manager.playerScore;
        Player.moc.setCountTnt(Game_Manager.playerTnt + themtnt);

        loadResources();
    }

    private void loadResources() {
        try {
            listGold.add(new Gold(50, 420, "Resources/vang_3.png", 400, 3));
            listGold.add(new Gold(600, 300, "Resources/vang_4.png", 600, 4));
            listGold.add(new Gold(400, 400, "Resources/vang_4.png", 550, 4));
            listGold.add(new Gold(250, 250, "Resources/vang_4.png", 500, 4));
            listGold.add(new Gold(550, 200, "Resources/vang_3.png", 300, 3));
            listGold.add(new Gold(120, 200, "Resources/vang_3.png", 250, 3));
            
            listDa.add(new Da(660, 250, "Resources/da_2.png", 25, 2));
            listDa.add(new Da(350, 320, "Resources/da_2.png", 40, 2));
            listDa.add(new Da(450, 250, "Resources/da_2.png", 35, 2));

            listPig.add(new Pig(300, 520, "Resources/pig.png", 501, 6));
            listPig.add(new Pig(500, 480, "Resources/pig.png", 501, 7));

            listTnt.add(new Tnt(550, 280));

            listKc.add(new kim_cuong(650, 450, "Resources/kc.png", 600));
            listKc.add(new kim_cuong(150, 400, "Resources/kc.png", 600));

            listThung.add(new thung(450, 350));
            listThung.add(new thung(250, 300));

            for (int i = 0; i < 10; i++) {
                listDiem.add(ImageIO.read(new File("Resources/so" + i + ".png")));
            }

            bg = ImageIO.read(new File("Resources/bg.png"));
            bg2 = ImageIO.read(new File("Resources/bg2.png"));

            // Thêm hàm răng
            listHamRang.add(new HamRang(200, 220, 2)); // Di chuyển sang phải
            listHamRang.add(new HamRang(500, 300, -2)); // Di chuyển sang trái
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void click() {
        Game_Manager.getInstance().getStackScreen().push(new game_over());
    }

    public void next_man() {
        // LƯU TRẠNG THÁI VÀO GAME_MANAGER TRƯỚC KHI CHUYỂN MÀN
        Game_Manager.playerScore = player.moc.tongDiem;
        Game_Manager.playerTnt = player.moc.getCountTnt();
        Game_Manager.getInstance().getStackScreen().push(new Controller.Screen_nextlevel());
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

        // Cập nhật hàm răng
        for (HamRang hamRang : listHamRang) {
            hamRang.update();
        }

        // Kiểm tra va chạm với hàm răng
        checkHamRangCollision();

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

    // Kiểm tra va chạm với hàm răng
    private void checkHamRangCollision() {
        if (Player.moc.getSpeedY() != 0) { // Chỉ kiểm tra khi móc đang di chuyển
            int ropeX = Player.moc.getX();
            int ropeY = Player.moc.getY();
            
            for (HamRang hamRang : listHamRang) {
                if (hamRang.checkCollisionWithRope(ropeX, ropeY)) {
                    // Cắt dây móc - quay về điểm xuất phát ngay lập tức
                    Player.moc.setSpeedY(0);
                    Player.moc.keoVe = false;
                    Player.moc.setPositionX(350);
                    Player.moc.setPositionY(134);
                    
                    // Reset điểm tạm thời để không cộng điểm khi bị đứt dây
                    Player.moc.setScoreTemp(0);
                    
                    // Giảm số dây
                    Player.moc.setCountRope(Player.moc.getCountRope() - 1);
                    
                    // Kiểm tra nếu hết dây
                    if (Player.moc.getCountRope() <= 0) {
                        // Nếu đủ điểm thì qua màn ngay lập tức
                        if (player.moc.tongDiem >= targetScore) {
                            next_man();
                        } else {
                            // Nếu chưa đủ điểm thì game over
                            click();
                        }
                    }
                    
                    break; // Chỉ cắt một lần
                }
            }
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

        // Hiển thị số dây còn lại
        int demRope = player.moc.getCountRope();
        bufferG.drawImage(listDiem.get(Math.max(0, Math.min(demRope, 9))), 650, 100, null);

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
        listHamRang.forEach(item -> item.draw(bufferedImage2));

        g2d.drawImage(bufferedImage2, 0, 0, null);
    }
}
