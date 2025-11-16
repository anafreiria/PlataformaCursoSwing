package pct.example.view;

import pct.example.dao.ModuloDAO;
import pct.example.model.Modulo;

import javax.swing.*;
import java.awt.*;

public class TelaModuloForm extends JFrame {

    public TelaModuloForm(Modulo modulo, int cursoId, TelaAdminModulos telaPai) {

        setTitle(modulo == null ? "Adicionar Módulo" : "Editar Módulo");
        setSize(300, 200);
        setLayout(new GridLayout(3,2,10,10));
        setLocationRelativeTo(null);

        JTextField txtTitulo = new JTextField(modulo == null ? "" : modulo.getTitulo());
        JTextField txtOrdem = new JTextField(modulo == null ? "" : String.valueOf(modulo.getOrdem()));

        add(new JLabel("Título:"));
        add(txtTitulo);

        add(new JLabel("Ordem:"));
        add(txtOrdem);

        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            String titulo = txtTitulo.getText();
            int ordem = Integer.parseInt(txtOrdem.getText());

            ModuloDAO dao = new ModuloDAO();

            boolean ok;

            if (modulo == null) {
                ok = dao.inserir(new Modulo(0, cursoId, titulo, ordem));
            } else {
                modulo.setTitulo(titulo);
                modulo.setOrdem(ordem);
                ok = dao.editar(modulo);
            }

            if (ok) {
                JOptionPane.showMessageDialog(this, "Salvo!");
                telaPai.carregarModulos();
                dispose();
            }
        });

        setVisible(true);
    }
}
