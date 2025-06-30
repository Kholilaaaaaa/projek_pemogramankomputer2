package controller;

import model.Pelanggan;
import model.PelangganDAO;
import view.PelangganFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class PelangganController {
    private final PelangganFrame view;
    private final PelangganDAO dao = new PelangganDAO();

    public PelangganController(PelangganFrame v) {
        this.view = v; loadTable();
    }

    public void saveOrUpdate() {
        // mirip MotorController.collectFromForm()
    }

    public void deleteSelected() { /* … */ }

    private void loadTable() { /* … sama pola … */ }
}
