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

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPosicao() {
        return posicao;
    }
}
