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
    int nrTotalJogadas = 0;
    int valorDado = 0;

    public GameManager() {

    }

    // Falta verificar as ferramentas
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
                switch (j) {
                    case 0:
                        if (Integer.parseInt(playerInfo[i][j]) < 1 || idRepetidos.contains((Integer.parseInt(playerInfo[i][j])))) {
                            return false;
                        } else {
                            programador.setID(Integer.parseInt(playerInfo[i][j]));
                            //adiciono no Hashset para evitar repetidos
                            idRepetidos.add(Integer.parseInt(playerInfo[i][j]));
                        }
                        break;
                    case 1:
                        if (playerInfo[i][j] == null || playerInfo[i][j].equals("")) {
                            return false;
                        } else {
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
                        } else {
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
        if (worldSize < 2 * jogadoresEmJogo.size()) {
            return false;
        }
        return true;
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools) {
        createInitialBoard(playerInfo, worldSize);
        ferramentas.clear();
        abismos.clear();

        if (!createInitialBoard(playerInfo, worldSize)) {
            return false;
        }

        for (int i = 0; i < abyssesAndTools.length; i++) {
            Ferramenta ferramenta = new Ferramenta();

            if (Integer.parseInt(abyssesAndTools[i][0]) == 0) {
                if (Integer.parseInt(abyssesAndTools[i][1]) < 0 || Integer.parseInt(abyssesAndTools[i][1]) > 9
                        || abyssesAndTools[i][2].equals("")) { //o  id tem de ser entre 0..9 e o título não pode ser vazio
                    return false;
                }
            switch (Integer.parseInt(abyssesAndTools[i][1])){
                    case 0:
                        SintaxeError abismo0= new SintaxeError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo0); //adicionar na lista
                        break;

                    case 1:
                        LogicError abismo1 = new LogicError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo1); //adicionar na lista
                        break;

                    case 2:
                        ExceptionError abismo2 = new ExceptionError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo2);
                        break;

                    case 3:
                        FileNotFoundExceptionError abismo3 = new FileNotFoundExceptionError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo3);
                        break;

                    case 4:
                        CrashError abismo4 = new CrashError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo4);
                        break;

                    case 5:
                        DuplicatedCodeError abismo5 = new DuplicatedCodeError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo5);
                        break;

                    case 6:
                        EfeitosSecundariosError abismo6 = new EfeitosSecundariosError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo6);
                        break;

                    case 7:
                        BlueScreenOfDeathError abismo7 = new BlueScreenOfDeathError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo7);
                        break;

                    case 8:
                        InfiniteCicleError abismo8 =new InfiniteCicleError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo8);
                        break;

                    case 9:
                        SegmentationFaultError abismo9 = new SegmentationFaultError(Integer.parseInt(abyssesAndTools[i][1]),abismoPorId(Integer.parseInt(abyssesAndTools[i][1])),Integer.parseInt(abyssesAndTools[i][2]));
                        abismos.add(abismo9);
                        break;
                }


            } else if (Integer.parseInt(abyssesAndTools[i][0]) == 1) {
                if (Integer.parseInt(abyssesAndTools[i][1]) < 0 || Integer.parseInt(abyssesAndTools[i][1]) > 5
                   || abyssesAndTools[i][2].equals("")) { //o  id tem de ser entre 0..9 e o título não pode ser vazio
                    return false;
                }
                ferramenta.setId(Integer.parseInt(abyssesAndTools[i][1])); //adiciono o id
                ferramenta.setTitulo(ferramentaPorId(Integer.parseInt(abyssesAndTools[i][1]))); //adiciono o titulo correspondente ao id
                ferramenta.setPosicao(Integer.parseInt(abyssesAndTools[i][2])); //adiciono a posicao
                ferramentas.add(ferramenta); //adicionar na lista
            } else {
                return false;
            }
        }
        ferramentas.sort(Comparator.comparingInt(Ferramenta::getId));
        abismos.sort(Comparator.comparingInt(Abismo::getId));
        return true;
    }

    String abismoPorId(int id) {
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
            case 1 -> "Programação Funcional";
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

        for (Abismo abismo : abismos) { //Reformular colocar por id
            if (abismo.getPosicao() == position) {
                return "abismo" + abismo.getId() + ".png";
            }
        }

        for (Ferramenta ferramenta : ferramentas) {
            if (ferramenta.getPosicao() == position) {
                return "ferramenta" + ferramenta.getId() + ".png";
            }
        }

        if (position == tamanhoTabuleiro) {
            return "glory.png";
        }

        return null;
    }

    public List<Programmer> getProgrammers(boolean includeDefeated) {
        List<Programmer> jogadoresSemDefeated = new ArrayList<>();
        if (includeDefeated) {
            jogadoresSemDefeated.addAll(jogadoresEmJogo);
        } else {
            for (Programmer jogadores : jogadoresEmJogo) {
                if (!jogadores.estado.equals("Derrotado")) {
                    jogadoresSemDefeated.add(jogadores);
                }
            }
        }
        return jogadoresSemDefeated;
    }

    public List<Programmer> getProgrammers(int position) {
        //retorna a lista dos jogadores em jogo numa certa posição
        List<Programmer> jogadoresNaPosicao = new ArrayList<>();
        for (Programmer jogadores : jogadoresEmJogo) {
            if (jogadores.getPosicao() == position) {
                jogadoresNaPosicao.add(jogadores);
            }
        }
        return jogadoresNaPosicao;
    }

    public String getProgrammersInfo() {
        StringBuilder imprimir = new StringBuilder();
        for (int i = 0; i < jogadoresEmJogo.size(); i++) {
            if (i == jogadoresEmJogo.size() - 1) {
                if (jogadoresEmJogo.get(i).getFerramentas().size() == 0) {
                    imprimir.append(jogadoresEmJogo.get(i).getName()).append(" : No tools");
                } else {
                    imprimir.append(jogadoresEmJogo.get(i).getName()).append(" : ").append(jogadoresEmJogo.get(i).criarFerramentas(jogadoresEmJogo.get(i).getFerramentas()));
                }
            } else {
                if (jogadoresEmJogo.get(i).getFerramentas().size() == 0) {
                    imprimir.append(jogadoresEmJogo.get(i).getName()).append(" : No tools").append(" | ");
                } else {
                    imprimir.append(jogadoresEmJogo.get(i).getName()).append(" : ").append(jogadoresEmJogo.get(i).criarFerramentas(jogadoresEmJogo.get(i).getFerramentas())).append(" | ");
                }
            }
        }
        return imprimir.toString();
    }

    public int getCurrentPlayerID() {
        return jogadoresEmJogo.get(turnoAtual - 1).getId();
    }

    public String getTitle(int position) {
        if (position < 1 || position > tamanhoTabuleiro) { // verifica se a posicão esta dentro do tabuleiro
            return null;
        }

        for (Abismo abismo : abismos) { // retorna nome do abismo nessa posição
            if (abismo.getPosicao() == position) {
                return abismo.getTitulo();
            }
        }

        for (Ferramenta ferramenta : ferramentas) { // retorna nome da ferramenta nessa posição
            if (ferramenta.getPosicao() == position) {
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
        }
        if (jogadoresEmJogo.get(turnoAtual - 1).getBloqueado().equals("Bloqueado") || jogadoresEmJogo.get(turnoAtual - 1).getEstado().equals("Derrotado")) {
            return false;
        } else {
            jogadoresEmJogo.get(turnoAtual - 1).incrementaPosicao(nrPositions, tamanhoTabuleiro);
            return true;
        }
    }

    public String reactToAbyssOrTool() {
        String imprimir = " ";

        for (Abismo abismo : abismos) { //verifica se é um abismo
            if (abismo.getPosicao() == jogadoresEmJogo.get(turnoAtual - 1).getPosicao()) {
                imprimir = verificaAbismos(abismo);
            }
        }

        for (Ferramenta ferramenta : ferramentas) { //adicionar ferramenta ao jogador
            if (ferramenta.getPosicao() == jogadoresEmJogo.get(turnoAtual - 1).getPosicao()) {
                if (jogadoresEmJogo.get(turnoAtual - 1).getFerramentasRepetidas().contains(ferramenta.getTitulo())) {
                    imprimir = "Já possui uma Ferramenta " + ferramenta.getTitulo();
                    break; //se já possuir a ferramenta não se adiciona
                } else {
                    jogadoresEmJogo.get(turnoAtual - 1).setFerramentas(ferramenta);// adiciono a ferramenta ao jogador
                    imprimir = "Apanhaste a Ferramenta " + ferramenta.getTitulo();
                }
            }
        }
        turnoAtual++;
        nrTotalJogadas++; //contador para saber quantas jogadas houve no jogo
        if (turnoAtual > jogadoresEmJogo.size()) { // os turnos vão de 1-4
            turnoAtual = 1;
        }

        if (jogadoresEmJogo.get(turnoAtual - 1).getBloqueado().equals("Bloqueado")) {
            imprimir = " ";
        }

        if (jogadoresEmJogo.get(turnoAtual - 1).getEstado().equals("Derrotado")) {
            imprimir = " ";
            turnoAtual++;
        }

        if (turnoAtual > jogadoresEmJogo.size()) { // os turnos vão de 1-4
            turnoAtual = 1;
        }
        if (jogadoresEmJogo.get(turnoAtual - 1).getEstado().equals("Derrotado")) {
            imprimir = " ";
            turnoAtual++;
        }

        if (turnoAtual > jogadoresEmJogo.size()) { // os turnos vão de 1-4
            turnoAtual = 1;
        }
        if (jogadoresEmJogo.get(turnoAtual - 1).getEstado().equals("Derrotado")) {
            imprimir = " ";
            turnoAtual++;
        }

        if (turnoAtual > jogadoresEmJogo.size()) { // os turnos vão de 1-4
            turnoAtual = 1;
        }

        if (imprimir.equals(" ")) {
            return null;
        }

        return imprimir;
    }

    boolean verificaSeTemFerramenta(ArrayList<String> ferramentasUteis, ArrayList<Ferramenta> ferramentasJogadorAtual) { //verifica no array de ferramentas se tem a que seja util
        for (String ferramenta : ferramentasUteis ) {
            for(Ferramenta ferramentaJogador : ferramentasJogadorAtual) {
                if (ferramenta.equals(ferramentaJogador.getTitulo())) {
                    removeTools(ferramentaJogador.getId());
                    return true;
                }
            }
        }
        return false;
    }

     String verificaAbismos(Abismo abismo) {
        switch (abismo.getTitulo()) {
            case "Erro de sintaxe": //recua 1 casa
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())) {
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(1);
                    return "Que azar! Recua 1 casa";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }

            case "Erro de lógica":  //recua o valor dos dados a dividir por 2
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())) {
                    int recuar = valorDado / 2;
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(recuar);
                    return "Que azar! Recua o valor dos dados / 2";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }


            case "Exception":  //recua 2 casas
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())) {
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(2);
                    return "Recua 2 casas";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }


            case "File Not Found Exception":  //recua 3 casas
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())) {
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(3);
                    return "Que azar! Recua 3 casas";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }


            case "Crash (aka Rebentanço)":  //volta à casa de partida
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())) {
                    jogadoresEmJogo.get(turnoAtual - 1).setPosicao(1);
                    return "Que azar! Volta à casa de Partida";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }


            case "Duplicated Code":  //O programador recua até à casa onde estava antes de chegar a esta casa.
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())) {
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(valorDado);
                    return "Que azar! Volta para onde vieste";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }


            case "Efeitos secundários":  //O programador recua para a posição onde estava há 2 movimentos atrás.
                int ultimas2jogadas = 0;
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())) {
                    ultimas2jogadas += jogadoresEmJogo.get(turnoAtual - 1).gravadorDePosicoes.get(jogadoresEmJogo.get(turnoAtual - 1).gravadorDePosicoes.size() - 1);
                    ultimas2jogadas += jogadoresEmJogo.get(turnoAtual - 1).gravadorDePosicoes.get(jogadoresEmJogo.get(turnoAtual - 1).gravadorDePosicoes.size() - 2);
                    jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(ultimas2jogadas);
                    return "Que azar! Volta 2 jogadas atrás";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }


            case "Blue Screen of Death":  // O programador perde imediatamente o jogo
                jogadoresEmJogo.get(turnoAtual - 1).setEstado("Derrotado");
                return "Game Over";


            case "Ciclo infinito":  //O programador fica preso na casa onde está até que lá apareça outro programador para o ajudar
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())) {
                    jogadoresEmJogo.get(turnoAtual - 1).setBloqueado("Bloqueado"); //fica bloqueado na casa
                    for (Programmer jogadores : jogadoresEmJogo) {
                        if (jogadores.getId() == jogadoresEmJogo.get(turnoAtual - 1).getId()) {
                            continue; //não faz nada pois ignora essa posição
                        }
                        if (jogadores.getPosicao() == jogadoresEmJogo.get(turnoAtual - 1).getPosicao()) {
                            jogadores.setBloqueado("Desbloqueado");
                        }
                    }
                    return "Que azar! Espera por Ajuda";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }



            case "Segmentation Fault":  // caso existam 2 ou mais jogadores nessa casa todos os jogadores nessa casa recuam 3 casas
                if (!verificaSeTemFerramenta(abismo.ferramentasSalvaAbismo,jogadoresEmJogo.get(turnoAtual - 1).getFerramentas())){
                    casaContestada(jogadoresEmJogo.get(turnoAtual - 1).getPosicao());
                    return "Que azar! Tu e o teu adversário  recuam 3 casas";
                }else {
                    return "Foste salvo pela tua Ferramenta";
                }

        }
        return "";
    }

    public void removeTools(int id) { //remove a ferramenta utilizada
        //remove a ferramenta utilizada
        jogadoresEmJogo.get(turnoAtual - 1).getFerramentas().removeIf(ferramenta -> ferramenta.getId() == id);
    }

    public void casaContestada(int posicao) {
        for (Programmer jogadores : jogadoresEmJogo) {
            if (jogadores.getId() == jogadoresEmJogo.get(turnoAtual - 1).getId()) { //ignora o jogador do turnoAtual
                continue;
            }
            if (jogadores.getPosicao() == posicao) {
                jogadoresEmJogo.get(turnoAtual - 1).subtraiPosicao(3);
                jogadores.subtraiPosicao(3);
            }
        }
    }

    public boolean gameIsOver() {
        //o jogo acaba quando um jogador chegar à meta
        int verificaDerrotados = 0;
        for (Programmer programmer : jogadoresEmJogo) {
            if (programmer.getEstado().equals("Derrotado")) { //se ficarem derrotados ganha o que não estiver
                verificaDerrotados++;
                if (verificaDerrotados == jogadoresEmJogo.size() - 1) {
                    return true;
                }
            }
        }

        if (jogadoresEmJogo.size() == 1) { //caso so exista um jogador a jogar
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
        jogadoresEmJogo.removeIf(programmer -> programmer.getEstado().equals("Derrotado"));
        //ordenar a lista de jogadores por
        jogadoresEmJogo.sort(Comparator.comparing(Programmer::getName));
        jogadoresEmJogo.sort(Comparator.comparingInt(Programmer::getPosicao).reversed());//ordena por posição
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("O GRANDE JOGO DO DEISI");
        resultados.add("");
        resultados.add("NR. DE TURNOS");
        resultados.add("" + nrTotalJogadas);
        resultados.add("");
        resultados.add("VENCEDOR");
        resultados.add(jogadoresEmJogo.get(0).getName());
        resultados.add("");
        resultados.add("RESTANTES");
        for (int i = 1; i < jogadoresEmJogo.size(); i++) {
            resultados.add(jogadoresEmJogo.get(i).getName() + " " + jogadoresEmJogo.get(i).getPosicao());
        }
        return resultados;
    }

    public JPanel getAuthorsPanel() {
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