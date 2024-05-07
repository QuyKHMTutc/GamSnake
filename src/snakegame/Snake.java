package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Snake {

    int DoDai = 3;

    int[] x;
    int[] y;
    // cài các biến tĩnh
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;

    int vector = Snake.GO_DOWN;

    long t1 = 0;
    long t2 = 0;

    int speed = 200;
    int maxLen = 8;

    boolean udAfterChangeVT = true;

    public Snake() {
        x = new int[20];
        y = new int[20];

        x[0] = 5;
        y[0] = 4;

        x[1] = 5;
        y[1] = 3;

        x[2] = 5;
        y[2] = 2;

    }

    public void resertGame() {
        x = new int[100];
        y = new int[100];

        x[0] = 5;
        y[0] = 4;

        x[1] = 5;
        y[1] = 3;

        x[2] = 5;
        y[2] = 2;

        DoDai = 3;

        vector = Snake.GO_DOWN;
    }

    public void setVector(int v) {
        if (vector != -v && udAfterChangeVT) // điều kiện k đi đc ngược chiều
        {
            vector = v;
            udAfterChangeVT = false;
        }
    }

    public boolean toaDoCoNamTrongThanRanKhong(int x1, int y1) {
        for (int i = 0; i < DoDai; i++) {
            if (x[i] == x1 && y[i] == y1) {
                return true;
            }
        }
        return false;
    }

    // lấy toạ độ ngẫu nhiên mồi
    public Point LayToaDoMoi() {
        Random r = new Random();
        int x, y;
        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        } while (toaDoCoNamTrongThanRanKhong(x, y));

        return new Point(x, y);
    }

    public int getcurrentSpeed() {
        int speed = 200;
        for (int i = 0; i < GameScreen.currentLevel; i++) {
            speed *= 0.9;
        }
        return speed;
    }

    public void update() {

        if (DoDai == maxLen) {

            resertGame();
            GameScreen.currentLevel++;
            maxLen += 5;
            speed = getcurrentSpeed();
        }
        for (int i = 1; i < DoDai; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {

                String name = JOptionPane.showInputDialog("Mời bạn nhập tên: ");
                FrameScreen.users.add(new User(name, Integer.valueOf(GameScreen.currentLevel)));

                GameScreen.isPlaying = false;
                GameScreen.isGameOver = true;

                GameScreen.diem = 0;
                GameScreen.currentLevel = 1;

            }

        }
        if (System.currentTimeMillis() - t2 > 200) {

            udAfterChangeVT = true;

            Data.HeadGoUp.update();
            Data.HeadGoDown.update();
            Data.HeadGoLeft.update();
            Data.HeadGoRight.update();

            t2 = System.currentTimeMillis();

        }

        if (System.currentTimeMillis() - t1 > speed) {

            if (GameScreen.bg[x[0]][y[0]] == 2) {
                DoDai++;
                GameScreen.bg[x[0]][y[0]] = 0;
                GameScreen.bg[LayToaDoMoi().x][LayToaDoMoi().y] = 2;
                GameScreen.diem += 100;
            }
            for (int i = DoDai - 1; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            // Cài đặt hướng đi của con rắn
            if (vector == Snake.GO_UP) {
                y[0]--;
            }
            if (vector == Snake.GO_DOWN) {
                y[0]++;
            }
            if (vector == Snake.GO_LEFT) {
                x[0]--;
            }
            if (vector == Snake.GO_RIGHT) {
                x[0]++;
            }

            // điều kiện khi rắn qua vùng biên
            // if (x[0] < 0)
            // x[0] = 19;
            // if (x[0] > 19)
            // x[0] = 0;

            // if (y[0] < 0)
            // y[0] = 19;
            // if (y[0] > 19)
            // y[0] = 0;
            if (x[0] < 0) {
                String name = JOptionPane.showInputDialog("Mời bạn nhập tên: ");
                FrameScreen.users.add(new User(name, Integer.valueOf(GameScreen.currentLevel)));
                String.valueOf(GameScreen.currentLevel);
                GameScreen.isPlaying = false;
                GameScreen.isGameOver = true;
                GameScreen.diem = 0;
                GameScreen.currentLevel = 1;
            } else if (x[0] > 19) {
                String name = JOptionPane.showInputDialog("Mời bạn nhập tên: ");
                FrameScreen.users.add(new User(name, Integer.valueOf(GameScreen.currentLevel)));
                String.valueOf(GameScreen.currentLevel);
                GameScreen.isPlaying = false;
                GameScreen.isGameOver = true;
                GameScreen.diem = 0;
                GameScreen.currentLevel = 1;
            } else if (y[0] < 0) {
                String name = JOptionPane.showInputDialog("Mời bạn nhập tên: ");
                FrameScreen.users.add(new User(name, Integer.valueOf(GameScreen.currentLevel)));
                String.valueOf(GameScreen.currentLevel);
                GameScreen.isPlaying = false;
                GameScreen.isGameOver = true;
                GameScreen.diem = 0;
                GameScreen.currentLevel = 1;
            } else if (y[0] > 19) {
                String name = JOptionPane.showInputDialog("Mời bạn nhập tên: ");
                FrameScreen.users.add(new User(name, Integer.valueOf(GameScreen.currentLevel)));
                String.valueOf(GameScreen.currentLevel);
                GameScreen.isPlaying = false;
                GameScreen.isGameOver = true;
                GameScreen.diem = 0;
                GameScreen.currentLevel = 1;
            }
            t1 = System.currentTimeMillis();
        }
    }

    public void VeRan(Graphics g) {
        g.setColor(Color.yellow); // Màu rắn than ran;
        for (int i = 1; i < DoDai; i++) {
            g.drawImage(Data.imageBody, x[i] * 20 + GameScreen.padding, y[i] * 20 + GameScreen.padding, 18, 18, null);
        }
        if (vector == Snake.GO_UP) {
            g.drawImage(Data.HeadGoUp.getCurrentImage(), x[0] * 20 - 7 + GameScreen.padding,
                    y[0] * 20 - 7 + GameScreen.padding, 30, 30, null);
        } else if (vector == Snake.GO_DOWN) {
            g.drawImage(Data.HeadGoDown.getCurrentImage(), x[0] * 20 - 7 + GameScreen.padding,
                    y[0] * 20 - 7 + GameScreen.padding, 30, 30, null);
        } else if (vector == Snake.GO_LEFT) {
            g.drawImage(Data.HeadGoLeft.getCurrentImage(), x[0] * 20 - 7 + GameScreen.padding,
                    y[0] * 20 - 7 + GameScreen.padding, 30, 30, null);
        } else if (vector == Snake.GO_RIGHT) {
            g.drawImage(Data.HeadGoRight.getCurrentImage(), x[0] * 20 - 7 + GameScreen.padding,
                    y[0] * 20 - 7 + GameScreen.padding, 30, 30, null);
        }
    }
}