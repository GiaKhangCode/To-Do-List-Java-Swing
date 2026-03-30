/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author SONY
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // 1. Khai báo thông tin kết nối
    // Đổi "xe" thành SID hoặc Service Name thực tế của bạn
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl"; 
    private static final String USER = "ToDoList";
    private static final String PASS = "Admin123";

    /**
     * Hàm thực hiện kết nối tới Oracle Database
     * @return Đối tượng Connection nếu thành công, null nếu thất bại
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 2. Đăng ký Oracle Driver (bắt buộc với các phiên bản JDBC cũ, phiên bản mới có thể tự nhận diện)
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 3. Thực hiện kết nối
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("✅ Kết nối Oracle Database thành công!");

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Không tìm thấy Oracle JDBC Driver. Vui lòng kiểm tra lại thư viện ojdbc.jar.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Kết nối thất bại. Hãy kiểm tra lại URL, Username, Password hoặc xem Oracle service đã chạy chưa.");
            e.printStackTrace();
        }
        return conn;
    }

    // Hàm main để chạy thử nghiệm
    public static void main(String[] args) {
        Connection myConnection = getConnection();
        
        // Luôn nhớ đóng kết nối khi không còn sử dụng để giải phóng tài nguyên
        if (myConnection != null) {
            try {
                myConnection.close();
                System.out.println("Đã đóng kết nối an toàn.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
