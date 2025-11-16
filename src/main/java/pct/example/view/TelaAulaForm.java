package pct.example.view;

import pct.example.dao.AulaDAO;
import pct.example.model.Aula;

import javax.swing.*;
import java.awt.*;

public class TelaAulaForm extends JFrame {

    public TelaAulaForm(Aula aula, int moduloId, TelaAdminAulas telaPai) {

        setTitle(aula == null ? "Adicionar Aula" : "Editar Aula");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(null);

        JTextField txtTitulo = new JTextField(aula == null ? "" : aula.getTitulo());
        JTextField txtTipo = new JTextField(aula == null ? "" : aula.getTipo());
        JTextField txtUrl = new JTextField(aula == null ? "" : aula.getConteudoUrl());
        JTextField txtDuracao = new JTextField(aula == null ? "" : String.valueOf(aula.getDuracaoMin()));
        JTextField txtOrdem = new JTextField(aula == null ? "" : String.valueOf(aula.getOrdem()));

        add(new JLabel("Título:"));
        add(txtTitulo);

        add(new JLabel("Tipo:"));
        add(txtTipo);

        add(new JLabel("URL Conteúdo:"));
        add(txtUrl);

        add(new JLabel("Duração (min):"));
        add(txtDuracao);

        add(new JLabel("Ordem:"));
        add(txtOrdem);

        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            String titulo = txtTitulo.getText();
            String tipo = txtTipo.getText();
            String url = txtUrl.getText();
            int duracao = Integer.parseInt(txtDuracao.getText());
            int ordem = Integer.parseInt(txtOrdem.getText());

            AulaDAO dao = new AulaDAO();
            boolean ok;

            if (aula == null) {
                ok = dao.inserir(new Aula(0, moduloId, titulo, tipo, url, duracao, ordem));
            } else {
                aula.setTitulo(titulo);
                aula.setTipo(tipo);
                aula.setConteudoUrl(url);
                aula.setDuracaoMin(duracao);
                aula.setOrdem(ordem);

                ok = dao.editar(aula);
            }

            if (ok) {
                JOptionPane.showMessageDialog(this, "Salvo!");
                telaPai.atualizarTabela();
                dispose();
            }
        });

        setVisible(true);
    }
}
