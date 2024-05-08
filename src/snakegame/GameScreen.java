package snakegame;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class GameScreen extends JPanel implements Runnable {
    static int[][] bg = new int[20][20];

    static int padding = 10;
    static int WIDTH = 400;
    static int HEIGH = 400;

    static boolean isPlaying = false;
    static boolean enableTextStartGame = true;// Bien lam dong chu nhap nhay
    Snake ran;

    Thread thread;

    static int currentLevel = 1;
    static int diem = 0;

    static boolean isGameOver = false;

    public GameScreen() {
        ran = new Snake();
        Data.LoadImage();
        Data.loadallAmi();
        bg[10][10] = 2;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        long t = 0;
        long t2 = 0;
        while (true) {
            if (System.currentTimeMillis() - t2 > 500) {
                enableTextStartGame = !enableTextStartGame;
                t2 = System.currentTimeMillis();
            }

            if (isPlaying) {
                if (System.currentTimeMillis() - t > 200) {
                    Data.Worm.update();
                    t = System.currentTimeMillis();
                }
                ran.update();
            }
            repaint();
            try {
                sleep(20);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void painBG(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH + padding * 2 + 300, HEIGH + padding * 2);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                // g.fillRect(i*20+1, j*20+1,18, 18); //vẽ lưới
                if (bg[i][j] == 2) {
                    // ve moi
                    g.drawImage(Data.Worm.getCurrentImage(), i * 20 - 3 + padding, j * 20 - 3 + padding, 28, 28, null);
                }
            }
        }
    }

    private void VeKhung(Graphics g) {
        g.setColor(Color.orange);

        g.drawRect(0, 0, WIDTH + padding * 2, HEIGH + padding * 2);
        g.drawRect(1, 1, WIDTH + padding * 2 - 2, HEIGH + padding * 2 - 2);
        g.drawRect(2, 2, WIDTH + padding * 2 - 4, HEIGH + padding * 2 - 4);

        g.drawRect(0, 0, WIDTH + padding * 2 + 300, HEIGH + padding * 2);
        g.drawRect(1, 1, WIDTH + padding * 2 - 2 + 300, HEIGH + padding * 2 - 2);
        g.drawRect(2, 2, WIDTH + padding * 2 - 4 + 300, HEIGH + padding * 2 - 4);
    }

    public void paint(Graphics g) {
        painBG(g);
        ran.VeRan(g);
        VeKhung(g);

        if (!isPlaying) {
            if (enableTextStartGame) {
                g.setColor(Color.white);
                g.setFont(g.getFont().deriveFont(18.0f));
                g.drawString("PRESS SPACE TO PLAY GAME! ", 100, 200);
            }

        }
        if (isGameOver) {
            g.setColor(Color.white);
            g.setFont(g.getFont().deriveFont(28.0f));
            g.drawString("GAME OVER! ", 100, 250);
        }
        // vẽ bảng lever
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(22.0f));
        g.drawString(" LEVEL:  " + currentLevel, 450, 50);

        // điểm
        g.setFont(g.getFont().deriveFont(22.0f));
        g.drawString(" Score:  " + diem, 450, 100);

        for (int i = 0; i < FrameScreen.users.size(); i++) {
            g.drawString(FrameScreen.users.get(i).toString(), 450, i * 30 + 150);
        }
    }
}