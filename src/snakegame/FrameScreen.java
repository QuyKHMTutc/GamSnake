/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class FrameScreen extends JFrame {

    GameScreen game;

    public static ArrayList<User> users;

    public FrameScreen() {
        setTitle("SnakeGame");
        setSize(750, 450);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        users = new ArrayList<User>();
        ReadData();

        game = new GameScreen();
        add(game);

        this.addKeyListener(new handler());

        // this.addWindowListener(new WindowAdapter() {
        // @Override
        // public void windowClosing(WindowEvent e) {
        // UpdateData();
        // }

        // });

        setVisible(true);// hiển thị frame
    }

    public static void main(String[] args) {
        FrameScreen f = new FrameScreen();
        // Thực hiện cập nhật dữ liệu trước khi chương trình kết thúc
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UpdateData();
            }
        });
    }

    private class handler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                GameScreen.isPlaying = !GameScreen.isPlaying;// hàm ấn Space để dừng
                if (GameScreen.isGameOver) {
                    GameScreen.isGameOver = false;
                    game.ran.resertGame();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                game.ran.setVector(Snake.GO_UP);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                game.ran.setVector(Snake.GO_DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                game.ran.setVector(Snake.GO_LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                game.ran.setVector(Snake.GO_RIGHT);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

    // public static void UpdateData() {
    // BufferedWriter bw = null;
    // try {
    // FileWriter fw = new FileWriter("data/data1.txt");
    // bw = new BufferedWriter(fw);

    // for (User u : users) {
    // bw.write(u.getName() + " " + u.getLevel());
    // bw.newLine();
    // }

    // } catch (IOException ex) {
    // } finally {
    // try {
    // bw.close();
    // } catch (IOException ex) {
    // }
    // }
    // }

    // public static void ReadData() {
    // try {
    // FileReader fr = new FileReader("data/data1.txt");
    // BufferedReader br = new BufferedReader(fr);

    // String line = null;
    // while ((line = br.readLine()) != null) {
    // String[] str = line.split(" ");
    // users.add(new User(str[0], str[1]));
    // }

    // br.close();
    // } catch (IOException ex) {
    // }
    // }
    public static void UpdateData() {
        // Thực hiện cập nhật dữ liệu vào cơ sở dữ liệu thay vì ghi vào tệp văn bản
        String dbUrl = "jdbc:sqlserver://DESKTOP-46N1BIK\\SQLEXPRESS:1433;databaseName=game;user=sa;password=123456;"
                + "encrypt=true;trustServerCertificate=true";

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            // Xóa dữ liệu cũ trong bảng Player
            String deleteDataSql = "DELETE FROM Player";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteDataSql);
            deleteStmt.executeUpdate();

            // Thêm dữ liệu mới từ danh sách người chơi vào cơ sở dữ liệu
            String insertDataSql = "INSERT INTO Player (username, level) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertDataSql);
            for (User u : users) {
                pstmt.setString(1, u.getName());
                pstmt.setInt(2, u.getLevel()); // Sử dụng setInt() cho cột kiểu INT
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }

    public static void ReadData() {
        // Thực hiện đọc dữ liệu từ cơ sở dữ liệu thay vì từ tệp văn bản
        String dbUrl = "jdbc:sqlserver://DESKTOP-46N1BIK\\SQLEXPRESS:1433;databaseName=game;user=sa;password=123456;"
                + "encrypt=true;trustServerCertificate=true";

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Player");

            while (rs.next()) {
                String name = rs.getString("username");
                int level = rs.getInt("level");
                users.add(new User(name, level));
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }

}