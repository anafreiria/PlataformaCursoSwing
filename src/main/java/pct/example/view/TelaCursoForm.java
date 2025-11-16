package pct.example.view;

import pct.example.dao.CursoDAO;
import pct.example.model.Curso;

import javax.swing.*;
import java.awt.*;

public class TelaCursoForm extends JFrame {

    public TelaCursoForm(Curso curso, TelaAdminCursos telaPai) {

        setTitle(curso == null ? "Adicionar Curso" : "Editar Curso");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JTextField txtTitulo = new JTextField(curso == null ? "" : curso.getTitulo());
        JTextField txtCategoria = new JTextField(curso == null ? "" : curso.getCategoria());
        JTextField txtCargaHoraria = new JTextField(curso == null ? "" : String.valueOf(curso.getCargaHoraria()));

        add(new JLabel("Título:"));
        add(txtTitulo);

        add(new JLabel("Categoria:"));
        add(txtCategoria);

        add(new JLabel("Carga Horária:"));
        add(txtCargaHoraria);

        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            String titulo = txtTitulo.getText();
            String categoria = txtCategoria.getText();
            int carga = Integer.parseInt(txtCargaHoraria.getText());

            CursoDAO dao = new CursoDAO();

            boolean sucesso;

            if (curso == null) {
                sucesso = dao.inserir(new Curso(0, titulo, categoria, carga));
            } else {
                curso.setTitulo(titulo);
                curso.setCategoria(categoria);
                curso.setCargaHoraria(carga);
                sucesso = dao.editar(curso);
            }

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
                telaPai.carregarCursos();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar!");
            }
        });

        setVisible(true);
    }
}
