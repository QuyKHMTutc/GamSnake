// package snakegame;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// public class Database {
// public static ArrayList<User> users;

// public static void UpdateData() {
// String dbUrl =
// "jdbc:sqlserver://DESKTOP-46N1BIK\\SQLEXPRESS:1433;databaseName=game;user=sa;password=123456;"
// + "encrypt=true;trustServerCertificate=true";
// try (Connection conn = DriverManager.getConnection(dbUrl)) {
// // Thêm dữ liệu mới từ danh sách người chơi vào cơ sở dữ liệu
// String insertDataSql = "INSERT INTO Player (username, level) VALUES (?, ?)";
// try (PreparedStatement pstmt = conn.prepareStatement(insertDataSql)) {
// conn.setAutoCommit(false);

// for (User u : users) {
// pstmt.setString(1, u.getName());
// pstmt.setInt(2, u.getLevel());
// pstmt.addBatch();
// }

// pstmt.executeBatch();
// conn.commit();
// }
// } catch (SQLException ex) {
// System.out.println("SQL Exception: " + ex.getMessage());
// }
// }

// public static void ReadData() {
// // Thực hiện đọc dữ liệu từ cơ sở dữ liệu thay vì từ tệp văn bản
// String dbUrl =
// "jdbc:sqlserver://DESKTOP-46N1BIK\\SQLEXPRESS:1433;databaseName=game;user=sa;password=123456;"
// + "encrypt=true;trustServerCertificate=true";

// // Tạo mới danh sách users
// users = new ArrayList<>();

// try (Connection conn = DriverManager.getConnection(dbUrl)) {
// // Thực hiện truy vấn SQL để lấy tên và cấp độ của 5 người chơi có cấp độ cao
// // nhất
// String query = "SELECT TOP 5 username, level FROM Player ORDER BY level
// DESC";
// try (java.sql.Statement stmt = conn.createStatement()) {
// ResultSet rs = stmt.executeQuery(query);

// while (rs.next()) {
// String name = rs.getString("username");
// int level = rs.getInt("level");
// // Thêm người chơi vào danh sách với tên và cấp độ thực tế từ cơ sở dữ liệu
// users.add(new User(name, level));
// }
// }
// } catch (SQLException ex) {
// System.out.println("SQL Exception: " + ex.getMessage());
// }
// }

// }
