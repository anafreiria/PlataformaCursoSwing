package pct.example.model;

public class Curso {

    private int id;
    private String titulo;
    private String categoria;
    private int cargaHoraria;

    // Construtor completo
    public Curso(int id, String titulo, String categoria, int cargaHoraria) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.cargaHoraria = cargaHoraria;
    }

    // Construtor vazio (opcional)
    public Curso() {}

    // GETTERS
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
