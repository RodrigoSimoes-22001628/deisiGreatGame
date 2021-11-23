package pt.ulusofona.lp2.deisiGreatGame;

public class Abismo {
    int id;
    String titulo;
    int posicao;


    public Abismo(int id, String titulo, int posicao) {
        this.id = id;
        this.titulo = titulo;
        this.posicao = posicao;
    }

    public Abismo() {
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
