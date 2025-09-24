package Modelo;

public class Usuario {
    private int id;
    private String username;
    private String contraseña;

    public Usuario() {}

    public Usuario(int id, String username, String contraseña) {
        this.id = id;
        this.username = username;
        this.contraseña = contraseña;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
    