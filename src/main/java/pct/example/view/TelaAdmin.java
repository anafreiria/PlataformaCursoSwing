package pct.example.view;

import javax.swing.*;
import java.awt.*;

public class TelaAdmin extends JFrame {

    public TelaAdmin() {
        setTitle("Área Administrativa");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnGerenciarCursos = new JButton("Gerenciar Cursos");
        JButton btnGerenciarModulos = new JButton("Gerenciar Módulos");
        JButton btnGerenciarAulas = new JButton("Gerenciar Aulas");
        JButton btnFechar = new JButton("Fechar");

        add(btnGerenciarCursos);
        add(btnGerenciarModulos);
        add(btnGerenciarAulas);
        add(btnFechar);

        btnFechar.addActionListener(e -> dispose());

        // Estas telas ainda vamos criar depois
        btnGerenciarCursos.addActionListener(e -> {
            new TelaAdminCursos().setVisible(true);
        });


        btnGerenciarModulos.addActionListener(e -> {
            new TelaAdminModulos().setVisible(true);
        });


        btnGerenciarAulas.addActionListener(e -> {
            new TelaAdminAulas().setVisible(true);
        });

    }
}
