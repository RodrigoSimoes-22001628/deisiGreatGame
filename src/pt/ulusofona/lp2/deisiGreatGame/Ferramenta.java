package pt.ulusofona.lp2.deisiGreatGame;
public class Ferramenta {
    int posicao;
    int id;
    String titulo;

    public Ferramenta() {
    }

    public Ferramenta(int posicao, int id, String titulo) {
        this.posicao = posicao;
        this.id = id;
        this.titulo = titulo;
    }

    public int getPosicao() {
        return posicao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
