package controller;

import model.User;
import model.UserDAO;
import view.LoginForm;
import view.MenuForm;

import javax.swing.*;

/**
 * Controller khusus proses login.
 * Dipanggil dari LoginForm melalui tombol "Login".
 */
public class LoginController {

    private final LoginForm view;
    private final UserDAO userDAO = new UserDAO();

    public LoginController(LoginForm view) {
        this.view = view;
    }

    /** Dipanggil ketika btnLogin ditekan */
    public void login() {
        // 1. Baca input
        String uname = view.getTxtUsername().getText().trim();
        String pwd   = new String(view.getTxtPassword().getPassword());

        // 2. Validasi sederhana
        if (uname.isEmpty() || pwd.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Username dan Password wajib diisi!",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Cek ke database
        User user = userDAO.login(uname, pwd);

        if (user != null) {
            // 4a. Berhasil
            JOptionPane.showMessageDialog(view,
                    "Selamat datang, " + user.getUsername() + "!",
                    "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);

            // Buka dashboard
            new MenuForm(user).setVisible(true);

            // Tutup jendela login
            view.dispose();
        } else {
            // 4b. Gagal
            JOptionPane.showMessageDialog(view,
                    "Username atau Password salah!",
                    "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }
}
