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

    public Programmer() {

    }
    // set atribuir um valor
    // get ir buscar esse valor

    void setLanguages(ArrayList<String> languages) {
        this.languages =languages;
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

    void incrementaPosicao(int posicao, int tamanhoTabuleiro) {
        if (posicao + this.posicao > tamanhoTabuleiro){
            int conta = (tamanhoTabuleiro - this.posicao);
            conta = posicao - conta;
            this.posicao = tamanhoTabuleiro - conta;
        }else {
            this.posicao += posicao;
        }
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        int count = 0;
        Collections.sort(languages);
        for (int i = 0; i < languages.size(); i++) {
            if (count != languages.size() -1) {
                texto.append(languages.get(i));
            }else {
                count++;
                texto.append(languages.get(i)).append(";");
            }
        }
        return id +" | "+name+" | "+posicao+" | "+texto+" | "+estado;
    }
}