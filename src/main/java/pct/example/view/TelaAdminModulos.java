package pct.example.view;

import pct.example.dao.CursoDAO;
import pct.example.dao.ModuloDAO;
import pct.example.model.Curso;
import pct.example.model.Modulo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaAdminModulos extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;
    private JComboBox<Curso> comboCursos;

    public TelaAdminModulos() {
        setTitle("Gerenciar Módulos");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TOPO - Seleção de curso
        JPanel topo = new JPanel(new FlowLayout());
        topo.add(new JLabel("Curso:"));

        comboCursos = new JComboBox<>();
        carregarCursos();
        topo.add(comboCursos);

        JButton btnCarregarModulos = new JButton("Carregar Módulos");
        topo.add(btnCarregarModulos);

        add(topo, BorderLayout.NORTH);

        // TABELA
        model = new DefaultTableModel(new String[]{"ID", "Título", "Ordem"}, 0);
        tabela = new JTable(model);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // BOTÕES CRUD
        JPanel botoes = new JPanel();

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        botoes.add(btnAdicionar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);

        add(botoes, BorderLayout.SOUTH);

        // AÇÕES
        btnCarregarModulos.addActionListener(e -> carregarModulos());
        btnAdicionar.addActionListener(e -> abrirFormulario(null));
        btnEditar.addActionListener(e -> editarModulo());
        btnExcluir.addActionListener(e -> excluirModulo());
    }

    private void carregarCursos() {
        CursoDAO dao = new CursoDAO();
        List<Curso> cursos = dao.listarCursos();

        for (Curso c : cursos) {
            comboCursos.addItem(c);
        }
    }

    public void carregarModulos() {
        model.setRowCount(0);

        Curso curso = (Curso) comboCursos.getSelectedItem();
        if (curso == null) return;

        ModuloDAO dao = new ModuloDAO();
        List<Modulo> lista = dao.listarPorCurso(curso.getId());

        for (Modulo m : lista) {
            model.addRow(new Object[]{ m.getId(), m.getTitulo(), m.getOrdem() });
        }
    }

    private void abrirFormulario(Modulo m) {
        Curso curso = (Curso) comboCursos.getSelectedItem();
        new TelaModuloForm(m, curso.getId(), this);
    }

    private void editarModulo() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um módulo!");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);
        String titulo = (String) tabela.getValueAt(row, 1);
        int ordem = (int) tabela.getValueAt(row, 2);

        Curso curso = (Curso) comboCursos.getSelectedItem();

        Modulo m = new Modulo(id, curso.getId(), titulo, ordem);
        abrirFormulario(m);
    }

    private void excluirModulo() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um módulo!");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);

        if (JOptionPane.showConfirmDialog(this,
                "Excluir módulo?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            ModuloDAO dao = new ModuloDAO();
            if (dao.excluir(id)) {
                JOptionPane.showMessageDialog(this, "Excluído!");
                carregarModulos();
            }
        }
    }
}
