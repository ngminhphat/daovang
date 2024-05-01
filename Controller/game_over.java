package Controller;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class game_over implements Screen {
    BufferedImage gameover, reset;

    public game_over(){
        try {
            reset = ImageIO.read(new File("Resources/button_reset.png"));
            gameover = ImageIO.read(new File("Resources/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void resetGame(){
        Game_Manager.getInstance().getStackScreen().push(new Start_Screen());
    }


    @Override
    public void click() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(gameover, 0, 0, null);
        g.drawImage(reset, 300, 400, null);
    }
}