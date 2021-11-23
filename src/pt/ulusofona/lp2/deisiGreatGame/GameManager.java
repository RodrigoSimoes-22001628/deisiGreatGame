package pt.ulusofona.lp2.deisiGreatGame;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameManager {
    ArrayList<Programmer> jogadoresEmJogo = new ArrayList<>(); //jogadores em jogo;
    ArrayList<Ferramentas> ferramentas = new ArrayList<>();
    ArrayList<Abismo> abismos = new ArrayList<>();
    int turnoAtual = 1; //turno em que se encontra
    int tamanhoTabuleiro;
    int nrTotalJogadas = 1;

    public GameManager() {
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        //reset às variáveis
        jogadoresEmJogo.clear();
        turnoAtual = 1;
        nrTotalJogadas = 1;
        tamanhoTabuleiro = worldSize;

        if (tamanhoTabuleiro <= 1) {
            return false;
        }

        HashSet<Integer> idRepetidos = new HashSet<>();
        HashSet<String> corRepetida = new HashSet<>();
        //Adicionar Jogadores
        for (int i = 0; i < playerInfo.length; i++) {
            Programmer programador = new Programmer();
            ArrayList<String> linguagens = new ArrayList<>();
            for (int j = 0; j < playerInfo[i].length; j++) {
                switch (j){
                    case 0 :
                        if (Integer.parseInt(playerInfo[i][j]) < 1 || idRepetidos.contains((Integer.parseInt(playerInfo[i][j])))){
                            return false;
                        }else{
                            programador.setID(Integer.parseInt(playerInfo[i][j]));
                            //adiciono no Hashset para evitar repetidos
                            idRepetidos.add(Integer.parseInt(playerInfo[i][j]));
                        }
                        break;
                    case 1:
                        if (playerInfo[i][j] == null || playerInfo[i][j].equals("")){
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
                        if (corRepetida.contains(playerInfo[i][j])) {
                             return false;
                        }else {
                            if (playerInfo[i][j].equals("Purple")) {
                                programador.setColorAvatar(ProgrammerColor.PURPLE);
                            } else if (playerInfo[i][j].equals("Blue")) {
                                programador.setColorAvatar(ProgrammerColor.BLUE);
                            } else if (playerInfo[i][j].equals("Green")) {
                                programador.setColorAvatar(ProgrammerColor.GREEN);
                            } else if (playerInfo[i][j].equals("Brown")) {
                                programador.setColorAvatar(ProgrammerColor.BROWN);
                            }
                           corRepetida.add(playerInfo[i][j]);
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
        if (worldSize < 2 * jogadoresEmJogo.size()){
            return false;
        }
        return true;
    }

    boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools){
        createInitialBoard(playerInfo,worldSize);
        Abismo abismo = new Abismo();
        for(int i = 0; i < abyssesAndTools.length; i++){
            for(int j = 0; j < abyssesAndTools[i].length; j++){
                switch (j){
                    case 0:
                        if(j == 0){
                          //  abismo.
                        }

                    case 1:
                        abismo.setId(Integer.parseInt(abyssesAndTools[i][j]));

                    case 2:
                        abismo.setPosicao(Integer.parseInt(abyssesAndTools[i][j]));
                }

            }
        }
        return true;
    }

    String abismoPorId(int id){
        switch (id){
            case 0:
                return "Erro de sintaxe";
            case 1:
                return "Erro de lógica";

            case 2:
                return "Exception";
            case 3:
                return "File Not Found Exception";
            case 4:
                return "Crash (aka Rebentanço)";
            case 5:
                return "Duplicated Code";
            case 6:
                return "Efeitos secundários";
            case 7:
                return "Blue Screen of Death";
            case 8:
                return "Ciclo infinito";
            case 9:
                return "Segmentation Fault";
        }
        return null;
    }

    String ferramentaPorId(int id) {
        switch (id) {
            case 0:
                return "Herança";
            case 1:
                return "Programação funcional";
            case 2:
                return "Testes unitários";
            case 3:
                return "Tratamento de Excepções";
            case 4:
                return "IDE";
            case 5:
                return "Ajuda Do Professor";
        }
        return null;
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

    List<Programmer> getProgrammers(boolean includeDefeated){
        ArrayList<Programmer> jogadoresSemDefeated = new ArrayList<>();
        if(!includeDefeated){
            for (Programmer jogadores : jogadoresEmJogo){
                if (jogadores.estado.equals("Em jogo")){
                    jogadoresSemDefeated.add(jogadores);
                }
            }
        }
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

    String getTitle(int position){
        if (position > tamanhoTabuleiro) {
            return null;
        }
        return null;

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
        JPanel painelAuthors = new JPanel();
        JLabel titulo = new JLabel();
        JLabel realizador1 = new JLabel();
        JLabel realizador2 = new JLabel();

        titulo.setText("Projeto Deisi Great Game");
        realizador1.setText("Rodrigo Simões a22001628");
        realizador2.setText("Gonçalo Soares a22003736");

        painelAuthors.add(titulo);
        painelAuthors.add(realizador1);
        painelAuthors.add(realizador2);
        painelAuthors.setSize(new Dimension(300, 300));

        return painelAuthors;
    }
}