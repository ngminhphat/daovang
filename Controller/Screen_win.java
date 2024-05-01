package Controller;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Screen_win implements Screen {
    Image gamewin;

    public Screen_win(){
        try {
            gamewin=ImageIO.read(new File("Resources/screen_win.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void click() {
        Game_Manager.getInstance().getStackScreen().push(new Start_Screen());
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(gamewin, 0, 0, null);
        
    }
}