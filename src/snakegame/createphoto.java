/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class createphoto {
    public static void main(String[] args) throws IOException {
        int x = 0;
        int y = 0;
        int TYPE_INT_RGB;
        BufferedImage result = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_RGB);
        Graphics g = result.getGraphics();

        String[] images = {
                "E:/java/project/picture/head.png",
                "E:/java/project/picture/headdown.png",
                "E:/java/project/picture/headleft.png",
                "E:/java/project/picture/headright.png",
                "E:/java/project/picture/headup.png",
                "E:/java/project/picture/worm.png",
                "E:/java/project/picture/worm.png",
                "E:/java/project/picture/worm.png",
                "E:/java/project/picture/body.png"
        };

        for (int i = 0; i < images.length; i++) {
            String image = images[i];
            System.out.println(image);
            BufferedImage bi = null;
            BufferedImage resize_bi = null;
            try {
                bi = ImageIO.read(new File(image));
                System.out.println("x:" + bi.getWidth() + "; y = " + bi.getHeight());
                resize_bi = resizeImage(bi, 200, 200);
                System.out.println("width: " + resize_bi.getWidth() + "; heigh: " + resize_bi.getHeight());
            } catch (IOException ex) {

            }

            g.drawImage(resize_bi, x, y, null);
            x += resize_bi.getWidth();
            if (x > result.getWidth()) {
                x = 0;
                y += resize_bi.getHeight();
            }
        }
        ImageIO.write(result, "png", new File("result.png"));
    }

    static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
            throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}