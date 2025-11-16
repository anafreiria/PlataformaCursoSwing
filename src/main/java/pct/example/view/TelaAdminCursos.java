package pct.example.view;

import pct.example.dao.CursoDAO;
import pct.example.model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaAdminCursos extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;

    public TelaAdminCursos() {
        setTitle("Gerenciar Cursos");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Título", "Categoria", "Carga Horária"};
        model = new DefaultTableModel(colunas, 0);
        tabela = new JTable(model);

        carregarCursos();
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);

        add(painelBotoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> new TelaCursoForm(null, this));
        btnEditar.addActionListener(e -> editarCurso());
        btnExcluir.addActionListener(e -> excluirCurso());
    }

    public void carregarCursos() {
        model.setRowCount(0);
        CursoDAO dao = new CursoDAO();
        List<Curso> cursos = dao.listarCursos();

        for (Curso c : cursos) {
            model.addRow(new Object[]{
                    c.getId(),
                    c.getTitulo(),
                    c.getCategoria(),
                    c.getCargaHoraria()
            });
        }
    }

    private void editarCurso() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um curso!");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);
        String titulo = (String) tabela.getValueAt(row, 1);
        String categoria = (String) tabela.getValueAt(row, 2);
        int cargaHoraria = (int) tabela.getValueAt(row, 3);

        Curso curso = new Curso(id, titulo, categoria, cargaHoraria);
        new TelaCursoForm(curso, this);
    }

    private void excluirCurso() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um curso!");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);

        int confirmar = JOptionPane.showConfirmDialog(
                this,
                "Deseja excluir este curso?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmar == JOptionPane.YES_OPTION) {
            CursoDAO dao = new CursoDAO();
            if (dao.excluir(id)) {
                JOptionPane.showMessageDialog(this, "Curso excluído!");
                carregarCursos();
            }
        }
    }
}
