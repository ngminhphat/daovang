    package Model;

    import java.awt.Graphics;
    import java.awt.Image;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import javax.imageio.ImageIO;

    public class Da extends Object {
            int score;
            int weight;
            public boolean biKeo = false;
            BufferedImage image;

            public Da(int posX, int posY, String img, int score, int weight) {
                    super(posX, posY);
                    this.score = score;
                    this.weight = weight;
                    try {
                            image = ImageIO.read(new File(img));
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
            }

            @Override
            public void draw(Image bufferedImage) {
                    Graphics g = bufferedImage.getGraphics();
                    g.drawImage(this.image, this.get_x(), this.get_y(), null);
            }


    }
