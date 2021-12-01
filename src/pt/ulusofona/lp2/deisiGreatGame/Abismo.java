package pt.ulusofona.lp2.deisiGreatGame;

import java.util.HashSet;

public class Abismo {
    int id;
    String titulo;
    int posicao;
    HashSet<String> ferramentas = new HashSet<>();

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


    class SintaxeError extends Abismo{

        SintaxeError(int id, String titulo, int posicao, String ferramenta) {
            super(id, titulo, posicao);
            ferramentas.add("Ajuda Do Professor");
            ferramentas.add("IDE");
        }
    }

    class ExceptionError extends Abismo{
        public ExceptionError(int id, String titulo,int posicao, String ferramenta) {
            super(id, titulo, posicao);
            ferramentas.add("Ajuda Do Professor");
            ferramentas.add("Tratamento de Excepções");
        }
    }

    class FileNotFoundExceptionError extends Abismo{
    public FileNotFoundExceptionError(int id, String titulo,int posicao, String ferramenta) {
        super(id, titulo, posicao);
        ferramentas.add("Tratamento de Excepções");
    }
}
class CrashError extends Abismo{
    public CrashError(int id, String titulo,int posicao, String ferramenta) {
        super(id, titulo, posicao);
        ferramentas.add("Programação Funcional");
    }
}
class DuplicatedCodeError extends Abismo{
    public DuplicatedCodeError(int id, String titulo,int posicao, String ferramenta) {
        super(id, titulo, posicao);
        ferramentas.add("Herança");
    }
}
class EfeitosSecundariosError extends Abismo{
    public EfeitosSecundariosError(int id, String titulo,int posicao, String ferramenta) {
        super(id, titulo, posicao);
        ferramentas.add("Testes unitários");
    }
}
class BlueScreenOfDeathError extends Abismo{
    public BlueScreenOfDeathError(int id, String titulo,int posicao, String ferramenta) {
        super(id, titulo, posicao);
    }
}
class InfiniteCicleError extends Abismo{
    public InfiniteCicleError(int id, String titulo,int posicao, String ferramenta) {
        super(id, titulo, posicao);
        ferramentas.add("Programação Funcional");
    }
}
class SegmentationFaultError extends Abismo{
    public SegmentationFaultError(int id, String titulo,int posicao, String ferramenta) {
        super(id, titulo, posicao);
        ferramentas.add("Programação Funcional");
    }
}

