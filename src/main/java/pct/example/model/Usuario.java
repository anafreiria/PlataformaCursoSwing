package pct.example.model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String tipo;

    public Usuario(int id, String nome, String email, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTipo() { return tipo; }
}

