package pt.ulusofona.lp2.deisiGreatGame;

public class Ferramentas {
    int posicao;
    int id;
    String titulo;


    public Ferramentas(int posicao, int id, String titulo) {
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
}
