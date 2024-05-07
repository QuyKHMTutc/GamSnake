// package snakegame;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// public class Database {
// private static final String DB_URL =
// "jdbc:sqlserver://DESKTOP-46N1BIK\\SQLEXPRESS:1433;databaseName=game;user=sa;password=123456;"
// + "encrypt=true;trustServerCertificate=true";

// public static void updateData(List<User> users) {
// try (Connection conn = DriverManager.getConnection(DB_URL)) {
// // Xóa dữ liệu cũ trong bảng Player
// String deleteDataSql = "DELETE FROM Player";
// try (PreparedStatement deleteStmt = conn.prepareStatement(deleteDataSql)) {
// deleteStmt.executeUpdate();
// }

// // Thêm dữ liệu mới từ danh sách người chơi vào cơ sở dữ liệu
// String insertDataSql = "INSERT INTO Player (username, level) VALUES (?, ?)";
// try (PreparedStatement pstmt = conn.prepareStatement(insertDataSql)) {
// conn.setAutoCommit(false); // Disable auto-commit

// for (User u : users) {
// pstmt.setString(1, u.getName());
// pstmt.setInt(2, u.getLevel());
// pstmt.addBatch();
// }

// pstmt.executeBatch(); // Execute all updates in a batch
// conn.commit(); // Commit the transaction
// }
// } catch (SQLException ex) {
// System.out.println("SQL Exception: " + ex.getMessage());
// }
// }

// public static List<User> ReadData() {
// List<User> users = new ArrayList<>();
// try (Connection conn = DriverManager.getConnection(DB_URL)) {
// // Thực hiện truy vấn SQL để lấy tên và cấp độ của 5 người chơi có cấp độ cao
// // nhất
// String query = "SELECT TOP 5 username, level FROM Player ORDER BY level
// DESC";
// try (PreparedStatement pstmt = conn.prepareStatement(query)) {
// ResultSet rs = pstmt.executeQuery();

// while (rs.next()) {
// String name = rs.getString("username");
// int level = rs.getInt("level");
// // Thêm người chơi vào danh sách với tên và cấp độ từ cơ sở dữ liệu
// users.add(new User(name, level));
// }
// }
// } catch (SQLException ex) {
// System.out.println("SQL Exception: " + ex.getMessage());
// }
// return users;
// }

// public static void updateData() {
// // TODO Auto-generated method stub
// throw new UnsupportedOperationException("Unimplemented method 'updateData'");
// }
// }
