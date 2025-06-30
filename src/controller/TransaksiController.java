package controller;

import model.*;
import view.TransaksiFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransaksiController {
    private final TransaksiFrame view;
    private final TransaksiDAO tDAO = new TransaksiDAO();
    private final PelangganDAO pDAO = new PelangganDAO();
    private final MotorDAO     mDAO = new MotorDAO();

    public TransaksiController(TransaksiFrame v) {
        this.view = v;
        loadCombo();
        loadTable();
    }

    private void loadCombo() {
        try {
            view.getCmbPelanggan().removeAllItems();
            for (Pelanggan p : pDAO.getAll())
                view.getCmbPelanggan().addItem(p);
            view.getCmbMotor().removeAllItems();
            for (Motor m : mDAO.getAll())
                view.getCmbMotor().addItem(m);
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void tambahItem() {
        Motor m = (Motor) view.getCmbMotor().getSelectedItem();
        int qty = Integer.parseInt(view.getTxtQty().getText());
        double sub = m.getHarga() * qty;
        DefaultTableModel mdl = (DefaultTableModel) view.getTblDetail().getModel();
        mdl.addRow(new Object[]{ m.getId(), m.getMerk(), qty, m.getHarga(), sub });
        updateTotal();
    }

    public void simpanTransaksi() {
        try {
            Pelanggan p = (Pelanggan) view.getCmbPelanggan().getSelectedItem();
            Motor     m = (Motor) view.getCmbMotor().getSelectedItem();
            int  qty    = Integer.parseInt(view.getTxtQty().getText());
            double tot  = qty * m.getHarga();

            Transaksi t = new Transaksi(null, LocalDate.now(), p, m, qty, tot);
            tDAO.insert(t);

            JOptionPane.showMessageDialog(view,"Transaksi tersimpan!");
            view.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,ex.getMessage());
        }
    }

    private void loadTable() {
        view.getTblDetail().setModel(new DefaultTableModel(
                new String[]{"ID","Motor","Qty","Harga","Subtotal"}, 0));
    }
    private void updateTotal() {
        double sum = 0;
        DefaultTableModel mdl = (DefaultTableModel) view.getTblDetail().getModel();
        for (int i = 0; i < mdl.getRowCount(); i++)
            sum += (double) mdl.getValueAt(i,4);
        view.getLblTotal().setText(String.format("%.0f", sum));
    }
}
