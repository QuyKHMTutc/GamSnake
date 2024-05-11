package snakegame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class Data {

    public static BufferedImage result;

    public static Image imageHead;

    public static Image imageHead_GoLeft;
    public static Image imageHead_GoRight;
    public static Image imageHead_GoUp;
    public static Image imageHead_GoDown;

    public static Image imageWorm2;
    public static Image imageWorm3;

    public static Image imageBody;
    public static Image imageWorm;

    public static Amination HeadGoUp;
    public static Amination HeadGoDown;
    public static Amination HeadGoLeft;
    public static Amination HeadGoRight;

    public static Amination Worm;

    public static void LoadImage() {
        try {
            result = ImageIO.read(new File("E:/java/project/GamSnake/src/snakegame/picture/result.png"));
            imageHead = result.getSubimage(0, 0, 200, 200);
            imageBody = result.getSubimage(1600, 0, 200, 200);
            imageWorm = result.getSubimage(1000, 0, 200, 200);

            imageHead_GoDown = result.getSubimage(200, 0, 200, 200);
            imageHead_GoUp = result.getSubimage(800, 0, 200, 200);
            imageHead_GoLeft = result.getSubimage(400, 0, 200, 200);
            imageHead_GoRight = result.getSubimage(600, 0, 200, 200);

            imageWorm2 = result.getSubimage(1200, 0, 200, 200);
            imageWorm3 = result.getSubimage(1400, 0, 200, 200);
        } catch (Exception e) {
        }
    }

    public static void loadallAmi() {
        HeadGoUp = new Amination();
        HeadGoUp.addImage(imageHead);
        HeadGoUp.addImage(imageHead_GoUp);

        HeadGoDown = new Amination();
        HeadGoDown.addImage(imageHead);
        HeadGoDown.addImage(imageHead_GoDown);

        HeadGoLeft = new Amination();
        HeadGoLeft.addImage(imageHead);
        HeadGoLeft.addImage(imageHead_GoLeft);

        HeadGoRight = new Amination();
        HeadGoRight.addImage(imageHead);
        HeadGoRight.addImage(imageHead_GoRight);

        Worm = new Amination();
        Worm.addImage(imageWorm);
        Worm.addImage(imageWorm2);
        Worm.addImage(imageWorm3);
        Worm.addImage(imageWorm2);
    }
}