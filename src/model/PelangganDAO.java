package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO untuk tabel <b>pelanggan</b>
 * Struktur tabel (contoh):
 *   id        INT AUTO_INCREMENT PRIMARY KEY
 *   nama      VARCHAR(100)
 *   telepon   VARCHAR(25)
 *   alamat    TEXT
 */
public class PelangganDAO {

    /* =======================  CRUD  ======================= */

    /** Tambah data baru; id hasil INSERT diset ke objek */
    public void insert(Pelanggan p) throws SQLException {
        String sql = "INSERT INTO pelanggan(nama, telepon, alamat) VALUES (?,?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNama());
            ps.setString(2, p.getTelepon());
            ps.setString(3, p.getAlamat());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        }
    }

    /** Perbarui data berdasar id */
    public void update(Pelanggan p) throws SQLException {
        String sql = "UPDATE pelanggan SET nama=?, telepon=?, alamat=? WHERE id=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getNama());
            ps.setString(2, p.getTelepon());
            ps.setString(3, p.getAlamat());
            ps.setInt   (4, p.getId());
            ps.executeUpdate();
        }
    }

    /** Hapus data by id */
    public void delete(int id) throws SQLException {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM pelanggan WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /** Ambil semua baris, urut id desc */
    public List<Pelanggan> getAll() throws SQLException {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan ORDER BY id DESC";
        try (Connection c = DBConnection.get();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    /** Cari 1 pelanggan by id; null jika tak ada */
    public Pelanggan findById(int id) throws SQLException {
        String sql = "SELECT * FROM pelanggan WHERE id=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        }
    }

    /* ===================  Helper mapper  =================== */

    private Pelanggan map(ResultSet rs) throws SQLException {
        return new Pelanggan(
                rs.getInt   ("id"),
                rs.getString("nama"),
                rs.getString("telepon"),
                rs.getString("alamat")
        );
    }
}
