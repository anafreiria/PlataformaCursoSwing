package pct.example.view;

import pct.example.dao.ModuloDAO;
import pct.example.dao.AulaDAO;
import pct.example.model.Modulo;
import pct.example.model.Aula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaAdminAulas extends JFrame {

    private JComboBox<Modulo> comboModulos;
    private JTable tabela;
    private DefaultTableModel model;

    public TelaAdminAulas() {
        setTitle("Gerenciar Aulas");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topo = new JPanel();
        topo.add(new JLabel("Módulo:"));

        comboModulos = new JComboBox<>();
        carregarModulos();
        topo.add(comboModulos);

        JButton btnCarregar = new JButton("Carregar Aulas");
        topo.add(btnCarregar);

        add(topo, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"ID", "Título", "Tipo", "Duração", "Ordem"},
                0
        );

        tabela = new JTable(model);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel botoes = new JPanel();

        JButton btnAdd = new JButton("Adicionar");
        JButton btnEdit = new JButton("Editar");
        JButton btnDel = new JButton("Excluir");

        botoes.add(btnAdd);
        botoes.add(btnEdit);
        botoes.add(btnDel);

        add(botoes, BorderLayout.SOUTH);

        btnCarregar.addActionListener(e -> carregarAulas());
        btnAdd.addActionListener(e -> abrirForm(null));
        btnEdit.addActionListener(e -> editarAula());
        btnDel.addActionListener(e -> excluirAula());
    }

    private void carregarModulos() {
        comboModulos.removeAllItems();

        ModuloDAO dao = new ModuloDAO();

        // Listar TODOS os módulos, independente do curso
        List<Modulo> modulos = dao.listarTodos();

        for (Modulo m : modulos) {
            comboModulos.addItem(m);
        }
    }


    private void carregarAulas() {
        model.setRowCount(0);

        Modulo modulo = (Modulo) comboModulos.getSelectedItem();
        if (modulo == null) return;

        AulaDAO dao = new AulaDAO();
        List<Aula> lista = dao.listarPorModulo(modulo.getId());

        for (Aula a : lista) {
            model.addRow(new Object[]{
                    a.getId(),
                    a.getTitulo(),
                    a.getTipo(),
                    a.getDuracaoMin(),
                    a.getOrdem()
            });
        }
    }

    private void abrirForm(Aula aula) {
        Modulo modulo = (Modulo) comboModulos.getSelectedItem();
        new TelaAulaForm(aula, modulo.getId(), this);
    }

    private void editarAula() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma aula!");
            return;
        }

        Modulo modulo = (Modulo) comboModulos.getSelectedItem();

        Aula aula = new Aula(
                (int) tabela.getValueAt(row, 0),
                modulo.getId(),
                (String) tabela.getValueAt(row, 1),
                (String) tabela.getValueAt(row, 2),
                "", // URL será editada no form
                (int) tabela.getValueAt(row, 3),
                (int) tabela.getValueAt(row, 4)
        );

        abrirForm(aula);
    }

    private void excluirAula() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma aula!");
            return;
        }

        int id = (int) tabela.getValueAt(row, 0);

        if (JOptionPane.showConfirmDialog(this,
                "Excluir aula?", "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            AulaDAO dao = new AulaDAO();
            if (dao.excluir(id)) {
                JOptionPane.showMessageDialog(this, "Removida!");
                carregarAulas();
            }
        }
    }

    public void atualizarTabela() {
        carregarAulas();
    }
}
