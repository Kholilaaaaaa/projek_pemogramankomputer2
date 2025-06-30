package model;

public class Pelanggan {
    private Integer id;
    private String nama;
    private String telepon;
    private String alamat;

    public Pelanggan() {}

    public Pelanggan(Integer id, String nama, String telp, String alamat) {
        this.id = id;
        this.nama = nama;
        this.telepon = telp;
        this.alamat = alamat;
    }

    // Getter dan Setter lengkap

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
