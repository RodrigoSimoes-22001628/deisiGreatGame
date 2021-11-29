package pt.ulusofona.lp2.deisiGreatGame;
import java.util.ArrayList;
import java.util.Collections;

public class Programmer {
    String name = "";
    ArrayList<String> languages;
    int id;
    ProgrammerColor colorAvatar;
    int posicao = 1; // 1 é a casa de Partida
    int turnoJogador; //turno do jogador teste
    String estado = "Em Jogo";
    String bloqueado = "Desbloqueado";
    ArrayList<Ferramenta> ferramentas = new ArrayList<>();
    ArrayList<Integer> gravadorDePosicoes = new ArrayList<>();

    public Programmer() {

    }

    public Programmer(int id ,String nome, ArrayList<String> languages, ProgrammerColor colorAvatar) {
        this.id = id;
        this.name = nome;
        this.languages = languages;
        this.colorAvatar = colorAvatar;
        ArrayList<Integer> gravadorDePosicoes = new ArrayList<>();
    }


// set atribuir um valor
    // get ir buscar esse valor

    void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }
    public int getId(){
        return id;
    }

    void setID(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    void setName(String nome) {
        this.name = nome;
    }

    public ProgrammerColor getColor(){
        return colorAvatar;
    }

    void setColorAvatar(ProgrammerColor color) {
        this.colorAvatar = color;
    }

    public int getPosicao(){
        return posicao;
    }

    public void setPosicao(int posicao){
        this.posicao = posicao;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public ArrayList<Ferramenta> getFerramentas() {
        return ferramentas;
    }
    public void setFerramentas(Ferramenta ferramentaAdicionada) {
       this.ferramentas.add(ferramentaAdicionada);
    }

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }


    void incrementaPosicao(int posicao, int tamanhoTabuleiro) {
        if (posicao + this.posicao > tamanhoTabuleiro){
            int conta = (tamanhoTabuleiro - this.posicao);
            conta = posicao - conta;
            this.posicao = tamanhoTabuleiro - conta;
            gravadorDePosicoes.add(posicao); //gravar a posição para usar no abismo Efeitos secundários
        }else {
            this.posicao += posicao;
            gravadorDePosicoes.add(posicao); //gravar a posição para usar no abismo Efeitos secundários
        }
    }

    void subtraiPosicao(int posicao) {
        if (this.posicao - posicao < 1){
            this.posicao = 1;
        }else {
            this.posicao -= posicao;
        }
    }

    String criarFerramentas(ArrayList<Ferramenta> ferramentasJogador){
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < ferramentasJogador.size(); i++) {
            if (i == ferramentasJogador.size()-1) {
                texto.append(ferramentasJogador.get(i).titulo);
            } else {
                texto.append(ferramentasJogador.get(i).titulo).append(";");
            }
        }
        return texto.toString();
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        Collections.sort(languages);
        for (int i = 0; i < languages.size(); i++) {
            if (i == languages.size() -1) {
                texto.append(languages.get(i));
            }else {
                texto.append(languages.get(i)).append("; ");
            }
        }
        if(ferramentas.size() == 0){
            return id +" | "+name+" | "+posicao+" | "+ "No tools" + " | " +texto+" | "+estado;
        }else {
            return id + " | " + name + " | " + posicao + " | " + criarFerramentas(ferramentas) + " | " + texto + " | " + estado;
        }
    }
}