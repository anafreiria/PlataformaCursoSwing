package pct.example.model;

public class Aula {
    private int id;
    private int moduloId;
    private String titulo;
    private String tipo;
    private String conteudoUrl;
    private int duracaoMin;
    private int ordem;

    public Aula(int id, int moduloId, String titulo, String tipo, String conteudoUrl, int duracaoMin, int ordem) {
        this.id = id;
        this.moduloId = moduloId;
        this.titulo = titulo;
        this.tipo = tipo;
        this.conteudoUrl = conteudoUrl;
        this.duracaoMin = duracaoMin;
        this.ordem = ordem;
    }

    public Aula() {}

    public int getId() { return id; }
    public int getModuloId() { return moduloId; }
    public String getTitulo() { return titulo; }
    public String getTipo() { return tipo; }
    public String getConteudoUrl() { return conteudoUrl; }
    public int getDuracaoMin() { return duracaoMin; }
    public int getOrdem() { return ordem; }

    public void setId(int id) { this.id = id; }
    public void setModuloId(int moduloId) { this.moduloId = moduloId; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setConteudoUrl(String conteudoUrl) { this.conteudoUrl = conteudoUrl; }
    public void setDuracaoMin(int duracaoMin) { this.duracaoMin = duracaoMin; }
    public void setOrdem(int ordem) { this.ordem = ordem; }
}
