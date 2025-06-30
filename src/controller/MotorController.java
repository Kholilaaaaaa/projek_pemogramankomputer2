package controller;

import model.Motor;
import model.MotorDAO;
import view.MotorFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class MotorController {
    private final MotorFrame view;
    private final MotorDAO dao = new MotorDAO();

    public MotorController(MotorFrame view) {
        this.view = view;
        loadTable();
    }

    public void saveOrUpdate() {
        try {
            Motor m = collectFromForm();
            if (m.getId() == null) dao.insert(m); else dao.update(m);
            clearForm(); loadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteSelected() {
        int row = view.getTblMotor().getSelectedRow();
        if (row < 0) return;
        int id = (int) view.getTblMotor().getValueAt(row, 0);
        try {
            dao.delete(id);
            loadTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    private void loadTable() {
        try {
            List<Motor> data = dao.getAll();
            DefaultTableModel mdl = new DefaultTableModel(
                    new String[]{"ID","Merk","Tipe","Harga","Stok"}, 0);
            for (Motor m : data) {
                mdl.addRow(new Object[]{
                        m.getId(), m.getMerk(), m.getTipe(),
                        m.getHarga(), m.getStok()
                });
            }
            view.getTblMotor().setModel(mdl);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Motor collectFromForm() {
        String merk  = view.getTxtMerk().getText();
        String tipe  = view.getTxtTipe().getText();
        double harga = Double.parseDouble(view.getTxtHarga().getText());
        int stok     = Integer.parseInt(view.getTxtStok().getText());

        Integer id = view.getHiddenId();   // mis. label tersembunyi / variabel
        return new Motor(id, merk, tipe, harga, stok);
    }

    private void clearForm() {
        view.getTxtMerk().setText("");
        view.getTxtTipe().setText("");
        view.getTxtHarga().setText("");
        view.getTxtStok().setText("");
        view.setHiddenId(null);
    }
}
