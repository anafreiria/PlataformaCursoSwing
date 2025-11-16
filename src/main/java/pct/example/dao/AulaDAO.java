package pct.example.dao;

import pct.example.ConnectionFactory;
import pct.example.model.Aula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO {

    // LISTAR AULAS POR MÓDULO
    public List<Aula> listarPorModulo(int moduloId) {
        List<Aula> lista = new ArrayList<>();
        String sql = "SELECT * FROM aula WHERE modulo_id = ? ORDER BY ordem";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, moduloId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Aula(
                        rs.getInt("id"),
                        rs.getInt("modulo_id"),
                        rs.getString("titulo"),
                        rs.getString("tipo"),
                        rs.getString("conteudo_url"),
                        rs.getInt("duracao_min"),
                        rs.getInt("ordem")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // INSERIR NOVA AULA
    public boolean inserir(Aula a) {
        String sql = "INSERT INTO aula (modulo_id, titulo, tipo, conteudo_url, duracao_min, ordem) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, a.getModuloId());
            stmt.setString(2, a.getTitulo());
            stmt.setString(3, a.getTipo());
            stmt.setString(4, a.getConteudoUrl());
            stmt.setInt(5, a.getDuracaoMin());
            stmt.setInt(6, a.getOrdem());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // EDITAR AULA
    public boolean editar(Aula a) {
        String sql = "UPDATE aula SET titulo=?, tipo=?, conteudo_url=?, duracao_min=?, ordem=? WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getTitulo());
            stmt.setString(2, a.getTipo());
            stmt.setString(3, a.getConteudoUrl());
            stmt.setInt(4, a.getDuracaoMin());
            stmt.setInt(5, a.getOrdem());
            stmt.setInt(6, a.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) {
        String sql = "DELETE FROM aula WHERE id=?";

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
