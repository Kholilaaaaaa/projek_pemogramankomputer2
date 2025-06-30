package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data‑access object untuk tabel <b>user</b>.
 * Pastikan DBConnection.get() mengarah ke database Anda.
 */
public class UserDAO {

    /* =====================================================
       LOGIN  (validasi username + password)
       ===================================================== */
    public User login(String uname, String pwd) {
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, uname);
            ps.setString(2, pwd);           // ← hash jika password disimpan hash
            ResultSet rs = ps.executeQuery();

            return rs.next() ? map(rs) : null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /* =====================================================
       CRUD  STANDARD
       ===================================================== */
    public void insert(User u) throws SQLException {
        String s = "INSERT INTO user(username,password,role) VALUES (?,?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(s, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) u.setId(rs.getInt(1));
            }
        }
    }

    public void update(User u) throws SQLException {
        String s = "UPDATE user SET username=?, password=?, role=? WHERE id=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(s)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            ps.setInt   (4, u.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM user WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<User> getAll() throws SQLException {
        List<User> list = new ArrayList<>();
        try (Connection c = DBConnection.get();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM user ORDER BY id DESC")) {

            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public User findById(int id) throws SQLException {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM user WHERE id=?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    /* =====================================================
       PRIVATE HELPER
       ===================================================== */
    private User map(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt   ("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role")
        );
    }
}
