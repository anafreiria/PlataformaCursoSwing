package pct.example.dao;

import pct.example.ConnectionFactory;
import pct.example.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    // ===============================
    // LISTAR TODOS OS CURSOS
    // ===============================
    public List<Curso> listarCursos() {
        List<Curso> lista = new ArrayList<>();

        String sql = "SELECT * FROM curso ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("categoria"),
                        rs.getInt("carga_horaria")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ===============================
    // INSERIR NOVO CURSO
    // ===============================
    public boolean inserir(Curso curso) {
        String sql = "INSERT INTO curso (titulo, categoria, carga_horaria) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getTitulo());
            stmt.setString(2, curso.getCategoria());
            stmt.setInt(3, curso.getCargaHoraria());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===============================
    // EDITAR CURSO EXISTENTE
    // ===============================
    public boolean editar(Curso curso) {
        String sql = "UPDATE curso SET titulo=?, categoria=?, carga_horaria=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getTitulo());
            stmt.setString(2, curso.getCategoria());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getId());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===============================
    // EXCLUIR CURSO
    // ===============================
    public boolean excluir(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===============================
    // BUSCAR CURSO POR ID (OPCIONAL)
    // ===============================
    public Curso buscarPorId(int id) {
        String sql = "SELECT * FROM curso WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Curso(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("categoria"),
                        rs.getInt("carga_horaria")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
