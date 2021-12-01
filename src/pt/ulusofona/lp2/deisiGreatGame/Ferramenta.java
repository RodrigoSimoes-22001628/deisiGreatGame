package pt.ulusofona.lp2.deisiGreatGame;

public class Ferramenta {
   protected int posicao;
   protected int id;
    String titulo;

    public Ferramenta() {

    }

    static class Heranca extends Ferramenta {
        Heranca() {
            super();
        }
    }

    static class ProgramacaoFuncional extends Ferramenta {
        ProgramacaoFuncional() {
            super();
        }
    }

    static class TestesUnitarios extends Ferramenta {
        TestesUnitarios() {
            super();
        }
    }

    static class TratamentoDeExcepcoes extends Ferramenta {
        TratamentoDeExcepcoes() {
            super();
        }
    }

    static class IDE extends Ferramenta {
        IDE() {
            super();
        }
    }

    static class AjudaDoProfessor extends Ferramenta {
        AjudaDoProfessor() {
            super();
        }
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
