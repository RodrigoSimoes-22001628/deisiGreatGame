package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.HashSet;

public class Abismo {
    int id;
    String titulo;
    int posicao;
    ArrayList<String> ferramentasSalvaAbismo = new ArrayList<>();

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

    class SintaxeError extends Abismo {
        public SintaxeError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Ajuda Do Professor");
            ferramentasSalvaAbismo.add("IDE");
        }
    }
    class LogicError extends Abismo {
        public LogicError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Ajuda Do Professor");
        }
    }

    class ExceptionError extends Abismo {
        public ExceptionError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Ajuda Do Professor");
            ferramentasSalvaAbismo.add("Tratamento de Excepções");
        }
    }

    class FileNotFoundExceptionError extends Abismo {
        public FileNotFoundExceptionError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Tratamento de Excepções");
        }
    }

    class CrashError extends Abismo {
        public CrashError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Programação Funcional");
        }
    }

    class DuplicatedCodeError extends Abismo {
        public DuplicatedCodeError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Herança");
        }
    }

    class EfeitosSecundariosError extends Abismo {
        public EfeitosSecundariosError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Testes unitários");
        }
    }

    class BlueScreenOfDeathError extends Abismo {
        public BlueScreenOfDeathError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
        }
    }

    class InfiniteCicleError extends Abismo {
        public InfiniteCicleError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Programação Funcional");
        }
    }

    class SegmentationFaultError extends Abismo {
        public SegmentationFaultError(int id, String titulo, int posicao) {
            super(id, titulo, posicao);
            ferramentasSalvaAbismo.add("Programação Funcional");
        }
    }