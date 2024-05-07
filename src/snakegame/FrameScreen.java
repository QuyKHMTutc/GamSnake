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

        // users = new ArrayList<User>();
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

    public static void UpdateData() {
        String dbUrl = "jdbc:sqlserver://DESKTOP-46N1BIK\\SQLEXPRESS:1433;databaseName=game;user=sa;password=123456;"
                + "encrypt=true;trustServerCertificate=true";

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            // Xóa dữ liệu cũ trong bảng Player
            String deleteDataSql = "DELETE FROM Player";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteDataSql)) {
                deleteStmt.executeUpdate();
            }

            // Thêm dữ liệu mới từ danh sách người chơi vào cơ sở dữ liệu
            String insertDataSql = "INSERT INTO Player (username, level) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertDataSql)) {
                conn.setAutoCommit(false); // Disable auto-commit

                for (User u : users) {
                    pstmt.setString(1, u.getName());
                    pstmt.setInt(2, u.getLevel());
                    pstmt.addBatch();
                }

                pstmt.executeBatch(); // Execute all updates in a batch
                conn.commit(); // Commit the transaction
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }

    public static void ReadData() {
        // Thực hiện đọc dữ liệu từ cơ sở dữ liệu thay vì từ tệp văn bản
        String dbUrl = "jdbc:sqlserver://DESKTOP-46N1BIK\\SQLEXPRESS:1433;databaseName=game;user=sa;password=123456;"
                + "encrypt=true;trustServerCertificate=true";

        // Tạo mới danh sách users
        users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            // Thực hiện truy vấn SQL để lấy tên và cấp độ của 5 người chơi có cấp độ cao
            // nhất
            String query = "SELECT TOP 5 username, level FROM Player ORDER BY level DESC";
            try (java.sql.Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String name = rs.getString("username");
                    int level = rs.getInt("level");
                    // Thêm người chơi vào danh sách với tên và cấp độ thực tế từ cơ sở dữ liệu
                    users.add(new User(name, level));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
    }

}