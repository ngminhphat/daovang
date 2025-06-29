package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class HamRang extends BaseObject {
    private BufferedImage imageMo, imageDong;
    private int speedX;
    private boolean isOpen;
    private int animationCounter;
    private static final int ANIMATION_SPEED = 30; // Tốc độ đổi ảnh

    public HamRang(int posX, int posY, int speed) {
        super(posX, posY);
        this.speedX = speed;
        this.isOpen = true;
        this.animationCounter = 0;
        loadImages();
    }

    private void loadImages() {
        try {
            imageMo = ImageIO.read(new File("Resources/ham rang mo.png"));
            imageDong = ImageIO.read(new File("Resources/ham rang dong.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Image bImg) {
        Graphics g = bImg.getGraphics();
        BufferedImage currentImage = isOpen ? imageMo : imageDong;
        // Vẽ ảnh với kích thước nhỏ (50x40)
        g.drawImage(currentImage, this.get_x(), this.get_y(), 50, 40, null);
    }

    public void update() {
        // Di chuyển theo chiều ngang
        this.set_x(this.get_x() + speedX);
        
        // Quay lại khi ra khỏi màn hình
        if (this.get_x() > 800) {
            this.set_x(-50);
        } else if (this.get_x() < -50) {
            this.set_x(800);
        }
        
        // Cập nhật animation
        animationCounter++;
        if (animationCounter >= ANIMATION_SPEED) {
            isOpen = !isOpen;
            animationCounter = 0;
        }
    }

    // Kiểm tra va chạm với dây móc - hitbox bằng với kích thước hiển thị
    public boolean checkCollisionWithRope(int ropeX, int ropeY) {
        // Khoảng va chạm của hàm răng (50x40)
        int sawWidth = 50;
        int sawHeight = 40;
        
        return (ropeX >= this.get_x() && ropeX <= this.get_x() + sawWidth &&
                ropeY >= this.get_y() && ropeY <= this.get_y() + sawHeight);
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
} 