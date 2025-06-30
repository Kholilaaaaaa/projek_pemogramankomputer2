package model;

/**
 * Entitas user di tabel <b>user</b>.
 * Struktur tabel contoh:
 *   id        INT AUTO_INCREMENT PRIMARY KEY
 *   username  VARCHAR(50) UNIQUE
 *   password  VARCHAR(255)   -- bisa hash BCrypt/MD5/PLAINTEXT
 *   role      VARCHAR(20)    -- admin | kasir | ...
 */
public class User {

    private Integer id;          // null → record baru
    private String username;
    private String password;
    private String role;

    public User() { }

    public User(Integer id, String username, String password, String role) {
        this.id       = id;
        this.username = username;
        this.password = password;
        this.role     = role;
    }

    /* ===== GETTERS & SETTERS ===== */
    public Integer getId()             { return id; }
    public void    setId(Integer id)   { this.id = id; }

    public String  getUsername()       { return username; }
    public void    setUsername(String username) { this.username = username; }

    public String  getPassword()       { return password; }
    public void    setPassword(String password) { this.password = password; }

    public String  getRole()           { return role; }
    public void    setRole(String role){ this.role = role; }

    /* Untuk JComboBox dsb. */
    @Override public String toString() { return username + " (" + role + ")"; }
}
