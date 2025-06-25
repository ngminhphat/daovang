package Controller;

import View.Game_Screen2;
import View.Game_Screen3;
import View.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

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

    // Định giá vật phẩm
    private static final int PRICE_TNT = 150;
    private static final int PRICE_CHISO = 250;
    private static final int PRICE_TIME = 300;


    private int chiso = 1;
    private int time = 30;
    private int them_tnt = 0;

    // Thêm các chuỗi mô tả
    private String currentDescription;
    private static final String DEFAULT_DESCRIPTION = "Chọn vật phẩm bạn muốn mua, sau đó nhấn \"Next Level\" để tiếp tục.";
    // Thêm giá vào mô tả
    private static final String TNT_DESCRIPTION = "Thuốc nổ ($" + PRICE_TNT + "): Phá hủy vật phẩm không mong muốn.";
    private static final String CHISO_DESCRIPTION = "Cỏ USA ($" + PRICE_CHISO + "): Tăng sức kéo màn tiếp theo.";
    private static final String TIME_DESCRIPTION = "Thêm thời gian ($" + PRICE_TIME + "): Tăng 30 giây cho màn sau.";


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
            // Khởi tạo mô tả mặc định
            currentDescription = DEFAULT_DESCRIPTION;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm cập nhật trạng thái hover và mô tả
    public void updateHover(int mouseX, int mouseY) {
        hoverTnt = rectMuaTnt.contains(mouseX, mouseY);
        hoverChiso = rectMuaChiso.contains(mouseX, mouseY);
        hoverTg = rectMuaTg.contains(mouseX, mouseY);
        hoverNextButton = rectNextButton.contains(mouseX, mouseY);

        if (hoverTnt) {
            currentDescription = TNT_DESCRIPTION;
        } else if (hoverChiso) {
            currentDescription = CHISO_DESCRIPTION;
        } else if (hoverTg) {
            currentDescription = TIME_DESCRIPTION;
        } else {
            currentDescription = DEFAULT_DESCRIPTION;
        }
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
        else if (currentLevel == 3) {
            Game_Manager.getInstance().getStackScreen().push(new Game_Screen4(chiso, time, them_tnt));
            Game_Manager.setCurrentLevel(4);
        }
        else if (currentLevel == 4) {
            Game_Manager.getInstance().getStackScreen().push(new Game_Screen5(chiso, time, them_tnt));
            Game_Manager.setCurrentLevel(5);
        }

    }

    public void add_tnt() {
        if (Game_Manager.playerScore >= PRICE_TNT) {
            Game_Manager.playerScore -= PRICE_TNT;
            them_tnt++;
            System.out.println("Bought TNT! Current TNT to add: " + them_tnt);
        } else {
            System.out.println("Not enough money for TNT!");
        }
    }

    public void increase_chiso() {
        if (Game_Manager.playerScore >= PRICE_CHISO) {
            Game_Manager.playerScore -= PRICE_CHISO;
            chiso++;
            if (chiso > 3) chiso = 1;
            System.out.println("Bought lucky clover!");
        } else {
            System.out.println("Not enough money for lucky clover!");
        }
    }

    public void increase_time() {
        if (Game_Manager.playerScore >= PRICE_TIME) {
            Game_Manager.playerScore -= PRICE_TIME;
            time += 30;
            if (time > 120) time = 30;
            System.out.println("Bought time!");
        } else {
            System.out.println("Not enough money for time!");
        }
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, 0, 0, null);

        Graphics2D g2d = (Graphics2D) g.create();

        // --- Vẽ cấu trúc bàn trước khi vẽ vật phẩm ---
        // 1. Vẽ mặt bàn (nơi sẽ đặt giá sau này)
        g2d.setColor(new Color(160, 82, 45)); // Màu nâu của gỗ
        g2d.fillRect(0, 380, 800, 40);

        // 2. Vẽ thân bàn (nơi hiển thị mô tả)
        g2d.setColor(new Color(248, 226, 149)); // Màu vàng nhạt
        g2d.fillRect(0, 410, 800, 180);
        // --- Kết thúc vẽ bàn ---

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

        // Vẽ chữ mô tả lên trên thân bàn
        g2d.setColor(Color.BLACK); // Màu chữ
        g2d.setFont(new Font("Arial", Font.BOLD, 20)); // Font chữ
        // Căn giữa dòng chữ
        int stringWidth = g2d.getFontMetrics().stringWidth(currentDescription);
        int x = (800 - stringWidth) / 2;
        g2d.drawString(currentDescription, x, 510);

        g2d.dispose();
    }
}
