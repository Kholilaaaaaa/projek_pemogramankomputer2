package model;

import java.time.LocalDate;

public class Transaksi {

    private Integer id;          // null ⇒ transaksi baru
    private LocalDate tanggal;
    private Pelanggan pelanggan;
    private Motor motor;
    private int qty;
    private double total;

    /* ---------- Konstruktor ---------- */
    public Transaksi() { }

    public Transaksi(Integer id,
                     LocalDate tanggal,
                     Pelanggan pelanggan,
                     Motor motor,
                     int qty,
                     double total) {
        this.id        = id;
        this.tanggal   = tanggal;
        this.pelanggan = pelanggan;
        this.motor     = motor;
        this.qty       = qty;
        this.total     = total;
    }

    /* ---------- GETTER ---------- */
    public Integer   getId()        { return id; }
    public LocalDate getTanggal()   { return tanggal; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public Motor     getMotor()     { return motor; }
    public int       getQty()       { return qty; }
    public double    getTotal()     { return total; }

    /* ---------- SETTER ---------- */
    public void setId(Integer id)                { this.id = id; }
    public void setTanggal(LocalDate tanggal)    { this.tanggal = tanggal; }
    public void setPelanggan(Pelanggan pelanggan){ this.pelanggan = pelanggan; }
    public void setMotor(Motor motor)            { this.motor = motor; }
    public void setQty(int qty)                  { this.qty = qty; }
    public void setTotal(double total)           { this.total = total; }

    /* Opsional: tampil rapi di log / JComboBox */
    @Override
    public String toString() {
        return "TRX#" + (id == null ? "baru" : id) + " – " + tanggal;
    }
}
