package View;

import Controller.Game_Manager;
import Controller.Screen;
import Controller.Screen_nextlevel;
import Controller.game_over;
import Model.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class Game_Screen implements Screen {
    BufferedImage bg, bg2, bufferedImage2, level_1, target;
    int count2 = 0;
    int tempScore;
    int scoreX = 80;
    int timeChuc, timeDonVi;
    int dem = 30; // thời gian chơi
    int targetScore = 500;

    Player player, player1;

    public ArrayList<Gold> listGold = new ArrayList<>();
    public ArrayList<Da> listDa = new ArrayList<>();
    public ArrayList<Pig> listPig = new ArrayList<>();
    public ArrayList<Tnt> listTnt = new ArrayList<>();
    public ArrayList<kim_cuong> listKc = new ArrayList<>();
    public ArrayList<HamRang> listHamRang = new ArrayList<>();
    public ArrayList<Image> listDiem = new ArrayList<>();

    public Game_Screen() {
        player = new Player(300, 24);
//		player1 = new Player(100, 24);
        loadResources();
    }

    private void loadResources() {
        try {
            // Vật phẩm
            listGold.add(new Gold(25, 390, "Resources/vang_1.png", 400, 1));
            listGold.add(new Gold(600, 320, "Resources/vang_3.png", 300, 3));
            listGold.add(new Gold(430, 430, "Resources/vang_3.png", 100, 3));
            listGold.add(new Gold(200, 350, "Resources/vang_4.png", 50, 4));
            listGold.add(new Gold(600, 250, "Resources/vang_4.png", 50, 4));
            listGold.add(new Gold(450, 300, "Resources/vang_4.png", 50, 4));
            listGold.add(new Gold(130, 250, "Resources/vang_4.png", 50, 4));
            listDa.add(new Da(250, 390, "Resources/da_1.png", 20, 1));
            listDa.add(new Da(660, 250, "Resources/da_1.png", 15, 1));
            listPig.add(new Pig(300, 520, "Resources/pig.png", 501, 4));
            listTnt.add(new Tnt(350, 290));
            listKc.add(new kim_cuong(650, 400, "Resources/kc.png", 500));

            // Thêm hàm răng
            listHamRang.add(new HamRang(200, 200, 2)); // Di chuyển sang phải
            listHamRang.add(new HamRang(500, 300, -1)); // Di chuyển sang trái

            // Ảnh số điểm
            for (int i = 0; i < 10; i++) {
                listDiem.add(ImageIO.read(new File("Resources/so" + i + ".png")));
            }

            // Background
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
        // LƯU TRẠNG THÁI HIỆN TẠI VÀO GAME_MANAGER
        Game_Manager.playerScore = player.moc.tongDiem;
        Game_Manager.playerTnt = player.moc.getCountTnt();
        
        Game_Manager.getInstance().getStackScreen().push(new Screen_nextlevel());
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

        // Cập nhật hàm răng
        for (HamRang hamRang : listHamRang) {
            hamRang.update();
        }

        // Kiểm tra va chạm với hàm răng
        checkHamRangCollision();

        // Cập nhật thời gian
        if (count2 == 59) {
            dem--;
            count2 = 0;
        }
        count2++;

        // Kiểm tra điều kiện chuyển màn hoặc kết thúc
        tempScore = player.moc.tongDiem;
        if (dem == 0) {
            if (tempScore >= targetScore) next_man();
            else click();
        }

        // Xóa vật phẩm nếu bị kéo về
        if (player.moc.keoVe) {
            listGold.removeIf(g -> g.biKeo);
            listTnt.removeIf(t -> t.biKeo);
            listDa.removeIf(d -> d.biKeo);
            listPig.removeIf(p -> p.biKeo);
            listKc.removeIf(k -> k.biKeo);
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

        // Vẽ nền
        bufferG.drawImage(bg2, 0, 0, null);
        bufferG.drawImage(bg, 0, 150, null);
        bufferG.drawImage(target, 50, 100, null);
        bufferG.drawImage(level_1, 650, 100, null);

        // Vẽ nhân vật và móc
        player.draw(bufferedImage2);
        Player.moc.draw(bufferedImage2);

        // Hiển thị điểm
        tempScore = player.moc.tongDiem;
        scoreX = 80;
        for (int i = 0; i < 4; i++) {
            bufferG.drawImage(listDiem.get(tempScore % 10), scoreX, 50, null);
            tempScore /= 10;
            scoreX -= 15;
        }

		//Hien thi target
        int targetX = 80;
        int tempTarget = targetScore;
        for (int i = 0; i < 4; i++) {
            bufferG.drawImage(listDiem.get(tempTarget % 10), targetX, 100, null);
            tempTarget /= 10;
            targetX -= 15;
        }

        // Hiển thị số TNT
        int demTnt = player.moc.getCountTnt();
        bufferG.drawImage(listDiem.get(Math.max(0, Math.min(demTnt, 9))), 608, 100, null);

        // Hiển thị số dây còn lại
        int demRope = player.moc.getCountRope();
        bufferG.drawImage(listDiem.get(Math.max(0, Math.min(demRope, 9))), 650, 100, null);

        // Hiển thị thời gian
        timeDonVi = dem % 10;
        timeChuc = dem / 10;
        bufferG.drawImage(listDiem.get(timeChuc), 685, 50, null);
        bufferG.drawImage(listDiem.get(timeDonVi), 700, 50, null);

        // Vẽ các vật phẩm
        listGold.forEach(item -> item.draw(bufferedImage2));
        listDa.forEach(item -> item.draw(bufferedImage2));
        listPig.forEach(item -> item.draw(bufferedImage2));
        listTnt.forEach(item -> item.draw(bufferedImage2));
        listKc.forEach(item -> item.draw(bufferedImage2));
        listHamRang.forEach(item -> item.draw(bufferedImage2));

        g2d.drawImage(bufferedImage2, 0, 0, null);
    }
}
