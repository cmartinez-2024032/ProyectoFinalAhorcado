package Modelo;

import Config.Conexion;
import java.sql.*;

public class UsuarioDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Usuario verificarLogin(String username, String contraseña) {
        Usuario usuario = null;

        String sql = "SELECT id, username FROM usuarios WHERE username = ? AND contraseña = ?";

        try {
            Conexion cn = new Conexion();
            con = cn.Conexion(); 

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
            }

        } catch (Exception e) {
            System.out.println("Error en UsuarioDAO (verificarLogin):");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return usuario;
    }
}
