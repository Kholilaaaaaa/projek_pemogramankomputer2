package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    public void insert(Transaksi t) throws SQLException {
        String sql = """
            INSERT INTO transaksi(tanggal, pelanggan_id, motor_id, qty, total)
            VALUES (?,?,?,?,?)
            """;
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate   (1, Date.valueOf(t.getTanggal()));
            ps.setInt    (2, t.getPelanggan().getId());
            ps.setInt    (3, t.getMotor().getId());
            ps.setInt    (4, t.getQty());
            ps.setDouble (5, t.getTotal());
            ps.executeUpdate();
        }
    }

    public List<Transaksi> getAll(PelangganDAO pDAO, MotorDAO mDAO) throws SQLException {
        String q = "SELECT * FROM transaksi ORDER BY id DESC";
        List<Transaksi> list = new ArrayList<>();
        try (Connection c = DBConnection.get();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(q)) {
            while (rs.next()) {
                Pelanggan p = pDAO.findById(rs.getInt("pelanggan_id"));
                Motor m     = mDAO.findById(rs.getInt("motor_id"));
                list.add(new Transaksi(
                        rs.getInt("id"),
                        rs.getDate("tanggal").toLocalDate(),
                        p, m,
                        rs.getInt("qty"),
                        rs.getDouble("total")));
            }
        }
        return list;
    }
}
