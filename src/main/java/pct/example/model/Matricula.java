package pct.example.model;

public class Matricula {
    private int id;
    private String curso;
    private int progresso;

    public Matricula(int id, String curso, int progresso) {
        this.id = id;
        this.curso = curso;
        this.progresso = progresso;
    }

    public int getId() { return id; }
    public String getCurso() { return curso; }
    public int getProgresso() { return progresso; }
}
