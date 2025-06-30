package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotorDAO {

    public void insert(Motor m) throws SQLException {
        String sql = "INSERT INTO motor(merk, tipe, harga, stok) VALUES (?,?,?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getMerk());
            ps.setString(2, m.getTipe());
            ps.setDouble(3, m.getHarga());
            ps.setInt(4, m.getStok());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) m.setId(rs.getInt(1));
            }
        }
    }

    public void update(Motor m) throws SQLException {
        String sql = "UPDATE motor SET merk=?, tipe=?, harga=?, stok=? WHERE id=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getMerk());
            ps.setString(2, m.getTipe());
            ps.setDouble(3, m.getHarga());
            ps.setInt(4, m.getStok());
            ps.setInt (5, m.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM motor WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Motor> getAll() throws SQLException {
        List<Motor> list = new ArrayList<>();
        try (Connection c = DBConnection.get();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM motor ORDER BY id DESC")) {
            while (rs.next())
                list.add(map(rs));
        }
        return list;
    }

    public Motor findById(int id) throws SQLException {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM motor WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    private Motor map(ResultSet rs) throws SQLException {
        return new Motor(
                rs.getInt("id"),
                rs.getString("merk"),
                rs.getString("tipe"),
                rs.getDouble("harga"),
                rs.getInt("stok")
        );
    }
}
