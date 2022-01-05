package pt.ulusofona.lp2.deisiGreatGame;

import java.io.Serializable;

public class Ferramenta implements Serializable {
   protected int posicao;
   protected int id;
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
class Heranca extends Ferramenta {
    public Heranca(int id, String titulo, int posicao) {
        super(posicao, id, titulo);
    }
}
class ProgramacaoFuncional extends Ferramenta {
    public ProgramacaoFuncional(int id, String titulo, int posicao) {
        super(posicao, id, titulo);
    }
}
class TestesUnitarios extends Ferramenta {
    public TestesUnitarios(int id, String titulo, int posicao) {
        super(posicao, id, titulo);
    }
}
class TratamentoExcepcoes extends Ferramenta {
    public TratamentoExcepcoes(int id, String titulo, int posicao) {
        super(posicao, id, titulo);
    }
}
class IDE extends Ferramenta {
    public IDE(int id, String titulo, int posicao) {
        super(posicao, id, titulo);
    }
}
class AjudaDoProfessor extends Ferramenta {
    public AjudaDoProfessor(int id, String titulo, int posicao) {
        super(posicao, id, titulo);
    }
}