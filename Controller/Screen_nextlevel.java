package Controller;

import View.Game_Screen2;
import View.Game_Screen3;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Administrator
 */
public class Screen_nextlevel implements Screen {
    BufferedImage image, muatg, muachiso, muatnt, nextmanbutton;
    // Vị trí và kích thước vật phẩm (giả sử mỗi ảnh 64x64, bạn chỉnh lại nếu cần)
    public final Rectangle rectMuaTnt = new Rectangle(100, 300, 64, 64);
    public final Rectangle rectMuaChiso = new Rectangle(300, 300, 64, 64);
    public final Rectangle rectMuaTg = new Rectangle(500, 300, 64, 64);
    public final Rectangle rectNextButton = new Rectangle(580, 45, 172, 64); // vùng click cũ

    private int chiso = 1;
    private int time = 30;
    private int them_tnt = 0;

    // Thêm biến trạng thái hover
    private boolean hoverTnt = false;
    private boolean hoverChiso = false;
    private boolean hoverTg = false;
    private boolean hoverNextButton = false;

    public Screen_nextlevel() {
        try {
            image = ImageIO.read(getClass().getResource("/Resources/nextman.png"));
            muatg = ImageIO.read(new File("Resources/muatg.png"));
            muachiso = ImageIO.read(new File("Resources/muachiso.png"));
            muatnt = ImageIO.read(new File("Resources/muatnt.png"));
            nextmanbutton = ImageIO.read(new File("Resources/nextmanbutton.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm cập nhật trạng thái hover
    public void updateHover(int mouseX, int mouseY) {
        hoverTnt = rectMuaTnt.contains(mouseX, mouseY);
        hoverChiso = rectMuaChiso.contains(mouseX, mouseY);
        hoverTg = rectMuaTg.contains(mouseX, mouseY);
        hoverNextButton = rectNextButton.contains(mouseX, mouseY);
    }

    @Override
    public void click() {
        int currentLevel = Game_Manager.getCurrentLevel();
        if (currentLevel == 1) {
            Game_Manager.getInstance().getStackScreen().push(new Game_Screen2(chiso, time, them_tnt));
            Game_Manager.setCurrentLevel(2);
        } else if (currentLevel == 2) {
            Game_Manager.getInstance().getStackScreen().push(new Game_Screen3(chiso, time, them_tnt));
            Game_Manager.setCurrentLevel(3);
        }
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

        Graphics2D g2d = (Graphics2D) g.create();

        // Vật phẩm TNT
        if (hoverTnt) {
            g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(muatnt, rectMuaTnt.x, rectMuaTnt.y, null);
            g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        } else {
            g2d.drawImage(muatnt, rectMuaTnt.x, rectMuaTnt.y, null);
        }

        // Vật phẩm Chiso
        if (hoverChiso) {
            g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(muachiso, rectMuaChiso.x, rectMuaChiso.y, null);
            g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        } else {
            g2d.drawImage(muachiso, rectMuaChiso.x, rectMuaChiso.y, null);
        }

        // Vật phẩm Tg
        if (hoverTg) {
            g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(muatg, rectMuaTg.x, rectMuaTg.y, null);
            g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        } else {
            g2d.drawImage(muatg, rectMuaTg.x, rectMuaTg.y, null);
        }

        // Nút Next Level
        if (hoverNextButton) {
            g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(nextmanbutton, rectNextButton.x, rectNextButton.y, null);
            g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        } else {
            g2d.drawImage(nextmanbutton, rectNextButton.x, rectNextButton.y, null);
        }

        g2d.dispose();
    }
}
