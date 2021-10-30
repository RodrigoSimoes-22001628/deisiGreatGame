package pt.ulusofona.lp2.deisiGreatGame;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class GameManager {
     ArrayList<Programmer> jogadoresEmJogo = new ArrayList<>(); //jogadores em jogo;
     int turnoAtual = 0; //turno em que se encontra
     int tamanhoTabuleiro;
     Programmer jogadorAtual = new Programmer();

     Programmer getJogadorAtual(int posicao){
         for (Programmer jogador : jogadoresEmJogo){
             if (Objects.equals(jogador.getPosicao(),posicao)){
                 return jogador;
             }
         }
         return null;
     }

    public GameManager() {

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {
        tamanhoTabuleiro = boardSize;
        if (boardSize <= 1) {
            return false;
        }

        //Adicionar Jogadores
        ArrayList<Programmer> listaJogadores = new ArrayList<>();
        for (int i = 0; i < playerInfo.length; i++) {
            Programmer programador = new Programmer();
            ArrayList<String> linguagens = new ArrayList<>();
            for (int j = 0; j < playerInfo[j].length; j++) {
                if (j == 0) { //adicionar o id
                    programador.iD = Integer.parseInt(playerInfo[i][j]);
                } else if (j == 1) { //adicionar o nome
                    programador.name = playerInfo[i][j];
                } else if (j == 2) { //adicionar as linguagens
                    linguagens.addAll(List.of(playerInfo[i][j].split(";")));
                    programador.languages = linguagens;
                } else if (j == 3) { //adicionar a cor
                    programador.colorAvatar = ProgrammerColor.valueOf(playerInfo[i][j]);
                }
            }
            listaJogadores.add(programador);
        }

        //total de jogadores não pode haver menos de 2 jogadores nem mais de 4
        if (listaJogadores.size() < 2 || listaJogadores.size() > 4) {
            return false;
        }

        //uso de Hashset para evitar duplicados (id e cor)
        HashSet<Integer> idRepetidos = new HashSet<>();
        HashSet<ProgrammerColor> coresRepetidas = new HashSet<>();

        for(Programmer jogadores : listaJogadores){
            if (jogadores.getId() < 1 || !idRepetidos.add(jogadores.getId())) {
                //se o id é repetido ou inferior a 1
                return false;
            }
            if (Objects.equals(jogadores.getName(), "") || jogadores.getName() == null ) {
                //o nome é vazio ou null
                return false;
            }
            if (!coresRepetidas.add(jogadores.getColor()) || jogadores.getColor() == null) {
                // a cor é repetida,  ou null
                return false;
            }
            idRepetidos.add(jogadores.getId());
            coresRepetidas.add(jogadores.getColor());
            jogadoresEmJogo.add(jogadores); //adicionar os jogadores que tem tudo certo
        }

        //O tabuleiro tem de ter, pelo menos duas posições por cada jogador que esteja em jogo.
        return 2 * jogadoresEmJogo.size() <= boardSize;

        //Para saber o turno basta fazer um collection sort por id e
        // os jogadores ficam desta forma ordenados por turnos
    }

    public String getImagePng(int position) {
        if (position > tamanhoTabuleiro) {
            return null;
        }

        switch (getJogadorAtual(position).getColor()) {
            case BLUE -> {
                return "playerBlue.png";
            }
            case BROWN -> {
                return "playerBrown.png";
            }
            case GREEN -> {
                return "playerGreen.png";
            }
            case PURPLE -> {
                return "playerPurple.png";
            }
        }
        if (position == tamanhoTabuleiro) {
            return "glory.png";
        }
        return null;
    }

    public ArrayList<Programmer> getProgrammers() {
        //retorna a lista dos jogadores em jogo
        return jogadoresEmJogo;
    }

    public ArrayList<Programmer> getProgrammers(int position) {
        //retorna a lista dos jogadores em jogo numa certa posição
       ArrayList<Programmer> jogadoresNaPosicao = new ArrayList<>();
       for (Programmer jogadores : jogadoresEmJogo){
           if (Objects.equals(jogadores.getPosicao(),position)){
               jogadoresNaPosicao.add(jogadores);
           }
       }
       return jogadoresNaPosicao;
    }

    public int getCurrentPlayerID() {
        return 0;
    }

    public boolean moveCurrentPlayer(int nrPositions) {
        if (nrPositions < 1 || nrPositions > 6) {  //pois o dado vai de 1..6
            return false;
        }else {
            turnoAtual++; //passa ao proximo jogador
            if (turnoAtual > jogadoresEmJogo.size()){
                turnoAtual = 0;
            }
            return true;
        }
    }

    public boolean gameIsOver() {
        for (Programmer jogadores : jogadoresEmJogo) {
            if (jogadores.getPosicao() == tamanhoTabuleiro){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getGameResults() {
       /* O GRANDE JOGO DO DEISI
        NR. DE TURNOS
        <NR DE TURNOS>
        VENCEDOR
        <NOME DO PROGRAMADOR VENCEDOR>
        RESTANTES
        <NOME DO SEGUNDO MELHOR CLASSIFICADO> <POSIÇãO>
        <NOME DO TERCEIRO MELHOR CLASSIFICADO> <POSIçÃO>
        <...> */
        return null;
    }

    public JPanel getAuthorsPanel(){
        return null;
    }
}
