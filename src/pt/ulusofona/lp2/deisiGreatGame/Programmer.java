package pt.ulusofona.lp2.deisiGreatGame;
import java.util.ArrayList;

public class Programmer {
    String name = "";
    ArrayList<String> languages;
    int iD;
    ProgrammerColor colorAvatar;
    int posicao = 1; // 1 é a casa de Partida
    int turnoJogador; //turno do jogador teste
    String estado = "Em Jogo";

    public Programmer() {

    }

    int getId(){
        return iD;
    }

    String getName(){
        return name;
    }

    ProgrammerColor getColor(){
        return colorAvatar;
    }

    int getPosicao(){
        return posicao;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        int count = 0;
        for (int i = 0; i < languages.size(); i++) {
            if (count != languages.size() -1) {
                count++;
               texto.append(languages.get(i));
            }else {
                count++;
                texto.append(languages.get(i)).append(";");
            }
        }
        return iD+" | "+name+" | "+posicao+" | "+texto+" | "+estado;
    }
}
