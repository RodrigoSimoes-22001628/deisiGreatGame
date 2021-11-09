package pt.ulusofona.lp2.deisiGreatGame;
import javax.swing.*;
import java.util.*;

public class GameManager {
    ArrayList<Programmer> jogadoresEmJogo = new ArrayList<>(); //jogadores em jogo;
    int turnoAtual = 1; //turno em que se encontra
    int tamanhoTabuleiro;
    int nrTotalJogadas = 0;

    public GameManager() {

    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {
        tamanhoTabuleiro = boardSize;

        if (boardSize <= 1) {
            return false;
        }

        HashSet<Integer> idRepetidos = new HashSet<>();
        //Adicionar Jogadores
        for (int i = 0; i < playerInfo.length; i++) {
            Programmer programador = new Programmer();
            ArrayList<String> linguagens = new ArrayList<>();
            for (int j = 0; j < playerInfo[i].length; j++) {
                switch (j){
                    case 0 :
                        if (Integer.parseInt(playerInfo[i][j]) < 1 ||!idRepetidos.add((Integer.parseInt(playerInfo[i][j])))){
                            jogadoresEmJogo = new ArrayList<>();
                            return false;
                        }else{
                            programador.setID(Integer.parseInt(playerInfo[i][j]));
                            //adiciono no Hashset para evitar repetidos
                            idRepetidos.add(Integer.parseInt(playerInfo[i][j]));
                        }
                        break;
                    case 1:
                        if (playerInfo[i][j] == null || Objects.equals(playerInfo[i][j], "")){
                            jogadoresEmJogo.clear();
                            return false;
                        }else{
                            programador.setName(playerInfo[i][j]);
                        }
                        break;
                    case 2:
                        linguagens.addAll(List.of(playerInfo[i][j].split(";")));
                        programador.setLanguages(linguagens);
                        break;
                    case 3:
                        if (Objects.equals(playerInfo[i][j], "Purple")) {
                            programador.setColorAvatar(ProgrammerColor.PURPLE);
                        } else if (Objects.equals(playerInfo[i][j], "Blue")) {
                            programador.setColorAvatar(ProgrammerColor.BLUE);
                        } else if (Objects.equals(playerInfo[i][j], "Green")) {
                            programador.setColorAvatar(ProgrammerColor.GREEN);
                        } else if (Objects.equals(playerInfo[i][j], "Brown")) {
                            programador.setColorAvatar(ProgrammerColor.BROWN);
                        }
                        break;
                }
            }
            jogadoresEmJogo.add(programador);
        }

        //total de jogadores não pode haver menos de 2 jogadores nem mais de 4
        if (jogadoresEmJogo.size() < 2 || jogadoresEmJogo.size() > 4) {
            return false;
        }

        //ordenar a lista de jogadores por id
        jogadoresEmJogo.sort(Comparator.comparingInt(Programmer::getId));

        //O tabuleiro tem de ter, pelo menos duas posições por cada jogador que esteja em jogo.
        if (2 * jogadoresEmJogo.size() > boardSize){
            return false;
        }
        return true;
    }

    public String getImagePng(int position) {
        if (position > tamanhoTabuleiro) {
            return null;
        }

        if (position == tamanhoTabuleiro) {
            return "glory.png";
        }
        return "";
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
        return  jogadoresEmJogo.get(turnoAtual-1).getId();
    }

    public boolean moveCurrentPlayer(int nrPositions) {
        //o dado so vai de 1..6 logo valores ou inferiores a estes são excluidos
        if (nrPositions < 1 || nrPositions > 6) {
            return false;
        }else {
            jogadoresEmJogo.get(turnoAtual-1).incrementaPosicao(nrPositions, tamanhoTabuleiro);
            nrTotalJogadas++; // contador para saber quantas jogadas houve no jogo
            turnoAtual++; //passa ao proximo jogador

            if (turnoAtual > jogadoresEmJogo.size()){ // os turnos vão de 1-4
                turnoAtual = 1;
            }
            return true;
        }
    }

    public boolean gameIsOver() {
        //o jogo acaba quando um jogador chegar à meta
        for (int i = 0; i < jogadoresEmJogo.size(); i++) {
            if (jogadoresEmJogo.get(i).getPosicao() == tamanhoTabuleiro){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getGameResults() {
        //ordenar a lista de jogadores por posição
        jogadoresEmJogo.sort(Comparator.comparingInt(Programmer::getPosicao).reversed());
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("O GRANDE JOGO DO DEISI");
        resultados.add("");
        resultados.add("NR. DE TURNOS");
        resultados.add(""+nrTotalJogadas);
        resultados.add("");
        resultados.add("VENCEDOR");
        resultados.add(jogadoresEmJogo.get(0).getName());
        resultados.add("");
        resultados.add("RESTANTES");
        for (int i = 1 ; i < jogadoresEmJogo.size() ; i++) {
            resultados.add(jogadoresEmJogo.get(i).getName() + " " + jogadoresEmJogo.get(i).getPosicao());
        }
        return resultados;
    }

    public JPanel getAuthorsPanel(){
        return null;
    }
}