package pct.example.model;

public class Modulo {

    private int id;
    private int cursoId;
    private String titulo;
    private int ordem;

    @Override
    public String toString() {
        return titulo + " (Curso " + cursoId + ")";
    }


    public Modulo(int id, int cursoId, String titulo, int ordem) {
        this.id = id;
        this.cursoId = cursoId;
        this.titulo = titulo;
        this.ordem = ordem;
    }

    public Modulo() {}

    public int getId() { return id; }
    public int getCursoId() { return cursoId; }
    public String getTitulo() { return titulo; }
    public int getOrdem() { return ordem; }

    public void setId(int id) { this.id = id; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setOrdem(int ordem) { this.ordem = ordem; }
}
