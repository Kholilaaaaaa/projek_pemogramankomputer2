package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Util koneksi JDBC tunggal.
 * Panggil:  Connection c = DBConnection.get();
 */
public final class DBConnection {

    // --- ganti sesuai lingkungan Anda ---
    private static final String URL  = "jdbc:mysql://localhost:3306/motor_sales?useSSL=false&serverTimezone=Asia/Jakarta";
    private static final String USER = "root";
    private static final String PASS = "";
    // -------------------------------------

    private DBConnection() { /* utility class */ }

    /**
     * Ambil koneksi baru. Caller WAJIB menutupnya (try‑with‑resources).
     */
    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
