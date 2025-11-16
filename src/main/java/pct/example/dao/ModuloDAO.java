package pct.example.dao;

import pct.example.ConnectionFactory;
import pct.example.model.Modulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAO {

    public List<Modulo> listarTodos() {
        List<Modulo> lista = new ArrayList<>();
        String sql = "SELECT * FROM modulo ORDER BY curso_id, ordem";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Modulo(
                        rs.getInt("id"),
                        rs.getInt("curso_id"),
                        rs.getString("titulo"),
                        rs.getInt("ordem")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }



    // LISTAR MÓDULOS DE UM CURSO
    public List<Modulo> listarPorCurso(int cursoId) {
        List<Modulo> lista = new ArrayList<>();
        String sql = "SELECT * FROM modulo WHERE curso_id = ? ORDER BY ordem";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cursoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Modulo(
                        rs.getInt("id"),
                        rs.getInt("curso_id"),
                        rs.getString("titulo"),
                        rs.getInt("ordem")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // INSERIR
    public boolean inserir(Modulo m) {
        String sql = "INSERT INTO modulo (curso_id, titulo, ordem) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, m.getCursoId());
            stmt.setString(2, m.getTitulo());
            stmt.setInt(3, m.getOrdem());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // EDITAR
    public boolean editar(Modulo m) {
        String sql = "UPDATE modulo SET titulo=?, ordem=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getTitulo());
            stmt.setInt(2, m.getOrdem());
            stmt.setInt(3, m.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) {
        String sql = "DELETE FROM modulo WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
