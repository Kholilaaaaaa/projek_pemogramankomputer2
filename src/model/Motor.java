package model;

public class Motor {
    private Integer id;            // null ⇒ baru
    private String merk;
    private String tipe;
    private double harga;
    private int stok;

    public Motor() {}
    public Motor(Integer id, String merk, String tipe, double harga, int stok) {
        this.id = id;
        this.merk = merk;
        this.tipe = tipe;
        this.harga = harga;
        this.stok = stok;
    }

    // getters & setters
    public Integer getId()           { return id; }
    public void setId(Integer id)    { this.id = id; }
    public String getMerk()          { return merk; }
    public void setMerk(String merk) { this.merk = merk; }
    public String getTipe()          { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }
    public double getHarga()         { return harga; }
    public void setHarga(double h)   { this.harga = h; }
    public int getStok()             { return stok; }
    public void setStok(int stok)    { this.stok = stok; }
}
