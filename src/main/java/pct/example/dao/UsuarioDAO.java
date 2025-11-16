package pct.example.dao;

import pct.example.ConnectionFactory;
import pct.example.model.Usuario;

import java.sql.*;

public class UsuarioDAO {

    public Usuario login(String email, String senha) {

        String sql = "SELECT id, nome, email, tipo FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, email);
            st.setString(2, senha);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("tipo")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
