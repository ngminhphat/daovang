package Controller;

import View.Game_Screen2;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Screen_nextlevel implements Screen {
    BufferedImage image;
    private int chiso = 1;
    private int time = 30;
    private int them_tnt = 0;

    public Screen_nextlevel() {
        try {
            image = ImageIO.read(getClass().getResource("/Resources/nextman.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void click() {
        // Khi click: tạo màn mới theo giá trị hiện tại
        Game_Manager.getInstance().getStackScreen().push(new Game_Screen2(chiso, time, them_tnt));
    }

    public void add_tnt() {
        them_tnt++;
        System.out.println("TNT: " + them_tnt);
    }

    public void increase_chiso() {
        chiso++;
        if (chiso > 3) chiso = 1; // Giới hạn chỉ số nếu cần
        System.out.println("Chiso: " + chiso);
    }

    public void increase_time() {
        time += 30;
        if (time > 120) time = 30; // Reset vòng
        System.out.println("Time: " + time);
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
