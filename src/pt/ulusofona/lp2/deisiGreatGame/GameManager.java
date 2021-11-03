package pt.ulusofona.lp2.deisiGreatGame;
import javax.swing.*;
import java.util.*;

public class GameManager {
     ArrayList<Programmer> jogadoresEmJogo = new ArrayList<>(); //jogadores em jogo;
     int turnoAtual = 1; //turno em que se encontra
     int tamanhoTabuleiro;
     int nrTotalJogadas = 0;
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
        for (int i = 0; i < playerInfo.length; i++) {
            Programmer programador = new Programmer();
            ArrayList<String> linguagens = new ArrayList<>();
            System.out.println(playerInfo[i].length);
            for (int j = 0; j < playerInfo[i].length; j++) {
                if (j == 0) { //adicionar o id
                    programador.setID(Integer.parseInt(playerInfo[i][j]));
                } else if (j == 1) { //adicionar o nome
                    programador.setName(playerInfo[i][j]);
                } else if (j == 2) { //adicionar as linguagens
                    linguagens.addAll(List.of(playerInfo[i][j].split(";")));
                    programador.setLanguages(linguagens);
                } else if (j == 3) { //adicionar a cor
                    if (Objects.equals(playerInfo[i][j], "Purple")){
                        programador.setColorAvatar(ProgrammerColor.PURPLE);
                    }else if (Objects.equals(playerInfo[i][j], "Blue")) {
                        programador.setColorAvatar(ProgrammerColor.BLUE);
                    }else if (Objects.equals(playerInfo[i][j], "Green")){
                        programador.setColorAvatar(ProgrammerColor.GREEN);
                    }else if (Objects.equals(playerInfo[i][j], "Brown")){
                        programador.setColorAvatar(ProgrammerColor.BROWN);
                    }else {
                        return false;
                    }
                }
            }
            jogadoresEmJogo.add(programador);
        }

        //total de jogadores não pode haver menos de 2 jogadores nem mais de 4
        if (jogadoresEmJogo.size() < 2 || jogadoresEmJogo.size() > 4) {
            return false;
        }

        //ordenar a lista de jogadores por id
        jogadoresEmJogo.sort(Comparator.comparingInt((Programmer programmer) -> programmer.id));

        //O tabuleiro tem de ter, pelo menos duas posições por cada jogador que esteja em jogo.
        if (2 * jogadoresEmJogo.size() <= boardSize){
            return false;
        }

        //uso de Hashset para evitar duplicados (id e cor)
        HashSet<Integer> idRepetidos = new HashSet<>();
        HashSet<ProgrammerColor> coresRepetidas = new HashSet<>();

        for(Programmer jogadores : jogadoresEmJogo){
            if (jogadores.getId() != ' ' ||jogadores.getId() < 1 || !idRepetidos.add(jogadores.getId())) {
                //se o id é repetido ou inferior a 1
                jogadoresEmJogo.clear();
                return false;
            }
            if ( jogadores.getName() == null || Objects.equals(jogadores.getName(), "")) {
                //o nome é vazio ou null
                jogadoresEmJogo.clear();
                return false;
            }
            if (!coresRepetidas.add(jogadores.getColor())) {
                // a cor é repetida,  ou null
                jogadoresEmJogo.clear();
                return false;
            }
            idRepetidos.add(jogadores.getId());
            coresRepetidas.add(jogadores.getColor());
        }
        return true;
    }

    public String getImagePng(int position) {
        if (position > tamanhoTabuleiro) {
            return null;
        }
//        switch (getJogadorAtual(position).getColor()) {
//            case BLUE -> {
//                return "playerBlue.png";
//            }
//            case BROWN -> {
//                return "playerBrown.png";
//            }
//            case GREEN -> {
//                return "playerGreen.png";
//            }
//            case PURPLE -> {
//                return "playerPurple.png";
//            }
//        }
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
        for (Programmer programmer : jogadoresEmJogo) {
            //verifica qual e o turno atual e a associa à posição do arrayList de jogadores
            return switch (turnoAtual) {
                case 1 -> jogadoresEmJogo.get(0).getId();
                case 2 -> jogadoresEmJogo.get(1).getId();
                case 3 -> jogadoresEmJogo.get(2).getId();
                case 4 -> jogadoresEmJogo.get(3).getId();
                default -> 0;
            };
        }
        return 0;
    }

    public boolean moveCurrentPlayer(int nrPositions) {
         //o dado so vai de 1..6 logo valores ou inferiores a estes são excluidos
        if (nrPositions < 1 || nrPositions > 6) {
            return false;
        }else {
                switch (turnoAtual) {
                    //incrementa a posicao do jogador numero de posicoes passada
                    case 1 :
                        jogadoresEmJogo.get(0).incrementaPosicao(nrPositions,tamanhoTabuleiro);
                        break;
                    case 2 :
                        jogadoresEmJogo.get(1).incrementaPosicao(nrPositions,tamanhoTabuleiro);
                        break;
                    case 3 :
                        jogadoresEmJogo.get(2).incrementaPosicao(nrPositions,tamanhoTabuleiro);
                        break;
                    case 4 :
                        jogadoresEmJogo.get(3).incrementaPosicao(nrPositions,tamanhoTabuleiro);
                        break;
                }
            nrTotalJogadas++; // contador para saber quantas jogadas houve no jogo
            turnoAtual++; //passa ao proximo jogador
            if (turnoAtual > jogadoresEmJogo.size()){ // os turnos vão de 1-4
                turnoAtual = 1;
            }
            return true;
        }
    }

    public boolean gameIsOver() {
        //o jogo acaba quando um jogador chegar à casa de patida
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
        /*

        StringBuilder colocacoes = new StringBuilder();

        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("O GRANDE JOGO DO DEISI" + "\n" + "\n");
        resultados.add("NR. DE TURNOS" + "\n");
        resultados.add("" + turnos + "\n"+ "\n");
        resultados.add("VENCEDOR");
        if(programmer.position == meta) {
            resultados.add(programmer.name + "\n" + "\n");
        } else {
            resultados.add("RESTANTES");
            colocacoes.append(programmer.name).append(" ").append(programmer.position).append("\n");
        }
         */
    }

    public JPanel getAuthorsPanel(){
        return null;
    }
}
