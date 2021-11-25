package pt.ulusofona.lp2.deisiGreatGame;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameManager {
    ArrayList<Programmer> jogadoresEmJogo = new ArrayList<>(); //jogadores em jogo;
    ArrayList<Ferramenta> ferramentas = new ArrayList<>();
    ArrayList<Abismo> abismos = new ArrayList<>();
    int turnoAtual = 1; //turno em que se encontra
    int tamanhoTabuleiro;
    int nrTotalJogadas = 1;
    int valorDado = 0;

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

    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools){
        createInitialBoard(playerInfo,worldSize);
        if (!createInitialBoard(playerInfo, worldSize)){
            return false;
        }
        Abismo abismo = new Abismo();
        Ferramenta ferramenta = new Ferramenta();

        for(int i = 0; i < abyssesAndTools.length; i++){
            if (Integer.parseInt(abyssesAndTools[i][1]) < 0 || Integer.parseInt(abyssesAndTools[i][1]) > 9
                    ||abyssesAndTools[i][2].equals("")){ //o  id tem de ser entre 0..9 e o título não pode ser vazio
                return false;
            }
            if (Integer.parseInt(abyssesAndTools[i][0]) == 0){
                abismo.setId(Integer.parseInt(abyssesAndTools[i][1])); //adiciono o id
                abismo.setTitulo(abismoPorId(Integer.parseInt(abyssesAndTools[i][1]))); //adiciono o titulo correspondente ao id
                abismo.setPosicao(Integer.parseInt(abyssesAndTools[i][2])); //adiciono a posicao
                abismos.add(abismo); //adicionar na lista
            }else if (Integer.parseInt(abyssesAndTools[i][0]) == 1){
                ferramenta.setId(Integer.parseInt(abyssesAndTools[i][1])); //adiciono o id
                ferramenta.setTitulo(ferramentaPorId(Integer.parseInt(abyssesAndTools[i][1]))); //adiciono o titulo correspondente ao id
                ferramenta.setPosicao(Integer.parseInt(abyssesAndTools[i][2])); //adiciono a posicao
                ferramentas.add(ferramenta); //adicionar na lista
            }
        }
        return true;
    }

    String abismoPorId(int id){
        return switch (id) {
            case 0 -> "Erro de sintaxe";
            case 1 -> "Erro de lógica";
            case 2 -> "Exception";
            case 3 -> "File Not Found Exception";
            case 4 -> "Crash (aka Rebentanço)";
            case 5 -> "Duplicated Code";
            case 6 -> "Efeitos secundários";
            case 7 -> "Blue Screen of Death";
            case 8 -> "Ciclo infinito";
            case 9 -> "Segmentation Fault";
            default -> null;
        };
    }

    String ferramentaPorId(int id) {
        return switch (id) {
            case 0 -> "Herança";
            case 1 -> "Programação funcional";
            case 2 -> "Testes unitários";
            case 3 -> "Tratamento de Excepções";
            case 4 -> "IDE";
            case 5 -> "Ajuda Do Professor";
            default -> null;
        };
    }

    public String getImagePng(int position) {
        if (position > tamanhoTabuleiro) {
            return null;
        }

        for (Abismo abismo : abismos){
            if (abismo.getPosicao() == position){
               return abismo.getTitulo() + ".png";
            }
        }

        for (Ferramenta ferramenta : ferramentas){
            if (ferramenta.getPosicao() == position){
                return ferramenta.getTitulo() + ".png";
            }
        }

        if (position == tamanhoTabuleiro) {
            return "glory.png";
        }
        return null;
    }

    public List<Programmer> getProgrammers(boolean includeDefeated){
        List<Programmer> jogadoresSemDefeated = new ArrayList<>();
        if(includeDefeated){ //se includeDefeated == true, deve incluir os jogadores derrotados.
            jogadoresSemDefeated.addAll(jogadoresEmJogo);
        }else { //se includeDefeated == false, deve incluir todos os jogadores
            for (Programmer jogadores : jogadoresEmJogo){
                if (!jogadores.estado.equals("Derrotado")){
                    jogadoresSemDefeated.add(jogadores);
                }
            }
        }
        return jogadoresSemDefeated;
    }

    public List<Programmer> getProgrammers(int position) {
        //retorna a lista dos jogadores em jogo numa certa posição
        List<Programmer> jogadoresNaPosicao = new ArrayList<>();
        for (Programmer jogadores : jogadoresEmJogo){
            if (jogadores.getPosicao() == position){
                jogadoresNaPosicao.add(jogadores);
            }
        }
        return jogadoresNaPosicao;
    }

    public String getProgrammersInfo(){
        for (Programmer programmer : jogadoresEmJogo){
            if (programmer.getFerramenta().size() == 0){
                return programmer.getName()+": No tools";
            }else {
                return programmer.getName() +" : "+ programmer.criarFerramentas(ferramentas);
            }
        }
        return "";
    }

    public int getCurrentPlayerID() {
        return  jogadoresEmJogo.get(turnoAtual-1).getId();
    }

    public String getTitle(int position){
        if (position < 1 || position > tamanhoTabuleiro) { // verifica se a posicão esta dentro do tabuleiro
            return null;
        }
        for (Abismo abismo : abismos){ // retorna nome do abismo nessa posição
            if (abismo.getPosicao() == position){
                return abismo.getTitulo();
            }
        }
        for (Ferramenta ferramenta : ferramentas){ // retorna nome da ferramenta nessa posição
            if (ferramenta.getPosicao() == position){
                return ferramenta.getTitulo();
            }
        }
        return null;
    }

    public boolean moveCurrentPlayer(int nrPositions) {
        //o dado so vai de 1..6 logo valores ou inferiores a estes são excluidos
        valorDado = nrPositions; //atribuir à variável o valor que calhou no dado
        if (nrPositions < 1 || nrPositions > 6) {
            return false;
        }else {
            if (jogadoresEmJogo.get(turnoAtual - 1).getEstado().equals("Derrotado")) {
                //verifica se o jogador já perdeu ou não
                turnoAtual++;
            } else {
                jogadoresEmJogo.get(turnoAtual - 1).incrementaPosicao(nrPositions, tamanhoTabuleiro);
                nrTotalJogadas++; // contador para saber quantas jogadas houve no jogo
                turnoAtual++; //passa ao proximo jogador
                if (turnoAtual > jogadoresEmJogo.size()) { // os turnos vão de 1-4
                    turnoAtual = 1;
                }
                return true;
            }
        }
        return true;
    }

    public String reactToAbyssOrTool(){
        for (Abismo abismo : abismos){ //verifica se é um abismo
            if (abismo.getPosicao() == jogadoresEmJogo.get(turnoAtual-1).getPosicao()){
                verificaAbismos(abismo.getTitulo());
                return abismo.getTitulo();
            }
        }
        for (Ferramenta ferramenta : ferramentas){ //adicionar ferramenta ao joagador
            if (ferramenta.getPosicao() == jogadoresEmJogo.get(turnoAtual-1).getPosicao()){
               jogadoresEmJogo.get(turnoAtual-1).setFerramenta(ferramenta); // adiciono a ferramenta ao jogador
                return ferramenta.getTitulo();
            }
        }
        return null;
    }

    boolean verificaSeTemFerramenta(String ajuda){
        for (Ferramenta ferramenta :jogadoresEmJogo.get(turnoAtual-1).ferramenta) {
            if (ferramenta.getTitulo().equals(ajuda)) {
                return true;
            }
        }
        return false;
    }

    String verificaAbismos(String nome){
        switch (nome) {
            case "Erro de sintaxe": //recua 1 casa
                if (!verificaSeTemFerramenta("Ajuda Professor") || !verificaSeTemFerramenta("IDE")) {
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(1);
                    return "Erro de sintaxe : Que azar! Recua 1 casa.";
                }else {
                    return "Foste salvo pela tua ferramenta!";
                }

            case "Erro de lógica":  //recua o valor dos dados a dividir por 2
                if (!verificaSeTemFerramenta("Ajuda Professor")) {
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(valorDado / 2);
                    return "Erro de lógica : Que azar! Recua " + valorDado / 2 + "casas";
                }else {
                    return "Foste pela tua ferramenta!";
                }

            case "Exception":  //recua 2 casas
                if (!verificaSeTemFerramenta("Ajuda Professor")
                    || !verificaSeTemFerramenta("Tratamento de Excepções")){
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(2);
                    return "Exception : Que azar! Recua 2 casas";
                }else {
                    return "Foste pela tua ferramenta!";
                }

            case "File Not Found Exception":  //recua 3 casas
                if ( !verificaSeTemFerramenta("Tratamento de Excepções")) {
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(3);
                    return "File Not Found Exception : Que azar! Recua 3 casas";
                }else {
                    return "Foste pela tua ferramenta!";
                }

            case "Crash (aka Rebentanço)":  //volta à casa de partida
                if (!verificaSeTemFerramenta("Programação funcional")) {
                    jogadoresEmJogo.get(turnoAtual - 1).setPosicao(1);
                    return "Crash (aka Rebentanço) : Já Foste voltas ao início";
                }else {
                    return "Foste pela tua ferramenta!";
                }

            case "Duplicated Code":  //O programador recua até à casa onde estava antes de chegar a esta casa.
                if (!verificaSeTemFerramenta("Herança")) {
                    jogadoresEmJogo.get(turnoAtual - 1).setPosicao(valorDado);
                    return "Duplicated Code : Que azar! Volta para onde vieste";
                }else {
                    return "Foste pela tua ferramenta!";
                }

            case "Efeitos secundários":  //O programador recua para a posição onde estava há 2 movimentos atrás.
                if (!verificaSeTemFerramenta("Testes unitários")) {
                    int ultimas2jogadas = 0;
                    ultimas2jogadas += jogadoresEmJogo.get(turnoAtual - 1).gravadorDePosicoes.get(jogadoresEmJogo.get(turnoAtual - 1).gravadorDePosicoes.size() - 1);
                    ultimas2jogadas += jogadoresEmJogo.get(turnoAtual - 1).gravadorDePosicoes.get(jogadoresEmJogo.get(turnoAtual - 1).gravadorDePosicoes.size() - 2);
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(ultimas2jogadas);
                    return "Efeitos secundários : Que azar! Volta 2 jogadas atrás";
                }else {
                    return "Foste pela tua ferramenta!";
                }

            case "Blue Screen of Death":  // O programador perde imediatamente o jogo
                if (!verificaSeTemFerramenta("")) {
                    jogadoresEmJogo.get(turnoAtual - 1).setEstado("Derrotado"); //altera o estado do jogador para perdeu
                    return "Game Over";
                }else {
                    return "Foste pela tua ferramenta!";
                }

            case "Ciclo infinito":  //O programador fica preso na casa onde está até que lá apareça outro programador para o ajudar
               if (!verificaSeTemFerramenta("")) {
                   return "Aguarda por ajuda";
               }else{
                   return "Foste pela tua ferramenta!";
               }

            case "Segmentation Fault":  // caso existam 2 ou mais jogadores nessa casa todos os jogadores nessa casa recuam 3 casas
                if (!verificaSeTemFerramenta("Programação funcional")) {
                    return "Tu e o teu parceiro recuam 3 casas";
                }else {
                    return "Foste pela tua ferramenta!";
                }
        }
        return null;
    }

    public boolean gameIsOver() {
        //o jogo acaba quando um jogador chegar à meta

        if (jogadoresEmJogo.size() == 1){ //caso so exista um jogador a jogar
            return true;
        }
        for (Programmer emJogo : jogadoresEmJogo) {
            if (emJogo.getPosicao() == tamanhoTabuleiro) {
                return true;
            }
        }
        return false;
    }

    public List<String> getGameResults() {
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