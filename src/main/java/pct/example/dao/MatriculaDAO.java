package pct.example.dao;

import pct.example.ConnectionFactory;
import pct.example.model.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public List<Matricula> listarPorAluno(int alunoId) {
        List<Matricula> lista = new ArrayList<>();

        String sql = """
                SELECT m.id, c.titulo AS curso, m.progresso_percent AS progresso
                FROM matricula m
                JOIN curso c ON c.id = m.curso_id
                WHERE m.aluno_id = ?
                """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, alunoId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                lista.add(new Matricula(
                        rs.getInt("id"),
                        rs.getString("curso"),
                        rs.getInt("progresso")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
