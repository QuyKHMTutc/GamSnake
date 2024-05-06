// package snakegame;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;
// import java.sql.Statement;

// public class Database {
// public static void main(String[] args) {
// // Thông tin kết nối CSDL
// String dbUrl =
// "jdbc:sqlserver://DESKTOP-46N1BIK\\SQLEXPRESS:1433;databaseName=game;user=sa;password=123456;"
// + "encrypt=true;trustServerCertificate=true";

// // Tên tệp dữ liệu
// String fileName = "data1.txt";

// try {
// // Kết nối CSDL
// Connection conn = DriverManager.getConnection(dbUrl);

// // Tạo bảng Player
// Statement stmt = conn.createStatement();
// String createTableSql = "CREATE TABLE Player (" +
// "username VARCHAR(50) NOT NULL," +
// "lever INT PRIMARY KEY)";
// stmt.executeUpdate(createTableSql);
// System.out.println("Table created successfully.");

// // Đọc từ tệp và thêm dữ liệu vào bảng
// BufferedReader br = new BufferedReader(new FileReader(fileName));
// String line;
// while ((line = br.readLine()) != null) {
// // Chia dòng thành các phần tử
// String[] parts = line.split(",");

// // Thêm dữ liệu vào bảng
// String insertDataSql = "INSERT INTO Player (username, lever) VALUES (?, ?)";
// PreparedStatement pstmt = conn.prepareStatement(insertDataSql);
// pstmt.setString(1, parts[0]); // username
// pstmt.setInt(2, Integer.parseInt(parts[1])); // lever

// pstmt.executeUpdate();
// pstmt.close();
// }
// br.close();
// conn.close();

// System.out.println("Data inserted successfully.");

// } catch (SQLException e) {
// System.out.println("SQL Exception: " + e.getMessage());
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }
