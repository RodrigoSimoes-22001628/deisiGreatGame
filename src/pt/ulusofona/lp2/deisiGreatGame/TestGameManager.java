package pt.ulusofona.lp2.deisiGreatGame;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestGameManager {
    GameManager game = new GameManager();
    Programmer programmer = new Programmer();
    @Test
    public void testaJogo() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[9][3];
        //Ferramenta
        abismoEFerramentas[0][0] = "1";
        abismoEFerramentas[0][1] = "5";  //Ajuda Professor
        abismoEFerramentas[0][2] = "2";
        abismoEFerramentas[1][0] = "1";
        abismoEFerramentas[1][1] = "3";  //Tratamento de Excepções
        abismoEFerramentas[1][2] = "3";
        abismoEFerramentas[2][0] = "1";
        abismoEFerramentas[2][1] = "0";  //Herança
        abismoEFerramentas[2][2] = "4";
        abismoEFerramentas[3][0] = "1";
        abismoEFerramentas[3][1] = "1";  //Programação Funcional
        abismoEFerramentas[3][2] = "5";
        //Abismos
        abismoEFerramentas[4][0] = "0";
        abismoEFerramentas[4][1] = "1";  //Erro de Logica
        abismoEFerramentas[4][2] = "6";
        abismoEFerramentas[5][0] = "0";
        abismoEFerramentas[5][1] = "2";  //Exception
        abismoEFerramentas[5][2] = "7";
        abismoEFerramentas[6][0] = "0";
        abismoEFerramentas[6][1] = "3";  //File Not Found
        abismoEFerramentas[6][2] = "8";
        abismoEFerramentas[7][0] = "0";
        abismoEFerramentas[7][1] = "5";  //Duplicated Code
        abismoEFerramentas[7][2] = "9";
        abismoEFerramentas[8][0] = "0";
        abismoEFerramentas[8][1] = "8";  //Ciclo Infinito
        abismoEFerramentas[8][2] = "10";

        game.createInitialBoard(listaJogadores,15,abismoEFerramentas);
        game.moveCurrentPlayer(1); //Rodrigo posicao = 2 //Ajuda Professor
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Gonçalo posicao = 2 //Ajuda Professor
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Rodrigo posicao = 3 //Tratamento de Excepções
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Gonçalo posicao = 3 //Tratamento de Excepções
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Rodrigo posicao = 4 //Herança
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Gonçalo posicao = 4 //Herança
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Rodrigo posicao = 5 //Programação Funcional
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Gonçalo posicao = 5 //Programação Funcional
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 5 | Ajuda Do Professor;Tratamento de Excepções;Herança;Programação Funcional | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
        String obtido2 = programmers.get(1).toString();
        String esperada2 = "2 | Goncalo | 5 | Ajuda Do Professor;Tratamento de Excepções;Herança;Programação Funcional | java; phyton | Em Jogo";
        assertEquals(esperada2, obtido2);
        game.moveCurrentPlayer(1); //Rodrigo posicao = 6 //Caí no Abismo ErroLogica é salvo pela ferramenta Ajuda Professor
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 8 //Caí no Abismo File not Found é salvo pela ferramenta Ajuda Professor
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Rodrigo posicao = 7 //Caí no Abismo Exception é salvo pela ferramenta Tratamento de Excepções
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Gonçalo posicao = 12
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Rodrigo posicao = 9 //Caí no Abismo Duplicated Code é salvo pela ferramenta Herança
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Gonçalo posicao = 13
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Rodrigo posicao = 10 //Caí no Abismo Ciclo Infinito é salvo pela ferramenta Programação funcional
        game.reactToAbyssOrTool();
        game.getTitle(1);
        List<Programmer> programmers2 = game.getProgrammers(false);
        String obtido3 = programmers2.get(0).toString();
        String esperada3 = "1 | Rodrigo | 10 | No tools | java; kotlin | Em Jogo";
        assertEquals(esperada3, obtido3);
        String obtido4 = programmers2.get(1).toString();
        String esperada4 = "2 | Goncalo | 13 | Tratamento de Excepções;Herança;Programação Funcional | java; phyton | Em Jogo";
        assertEquals(esperada4, obtido4);
    }

    @Test
    public void gameManageTabuleiroMenorTamahoArrayJogadores() throws InvalidInitialBoardException {
        String menssagem = "Erro : Tabuleiro menor que o dobro do numero de jogadores";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            game.createInitialBoard(listaJogadores, 3);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    public void adicionarJogadores() throws InvalidInitialBoardException {
        game.abismoPorId(10);
        game.ferramentaPorId(6);
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[15][3];
        abismoEFerramentas[0][0] = "0"; // abismo
        abismoEFerramentas[0][1] = "4";  // id Crash
        abismoEFerramentas[0][2] = "10"; // posição 10 tabuleiro
        abismoEFerramentas[1][0] = "0"; // abismo
        abismoEFerramentas[1][1] = "0";  // id erro de Sintaxe
        abismoEFerramentas[1][2] = "6"; // posição 6 tabuleiro
        abismoEFerramentas[2][0] = "1"; // ferramenta
        abismoEFerramentas[2][1] = "5";  // id ajuda Professor
        abismoEFerramentas[2][2] = "5"; //posição 5 tabuleiro
        abismoEFerramentas[3][0] = "1"; // ferramenta
        abismoEFerramentas[3][1] = "1";  // id Programação Funcional
        abismoEFerramentas[3][2] = "9"; //posição 9 tabuleiro
        abismoEFerramentas[4][0] = "0"; // abismo
        abismoEFerramentas[4][1] = "7";  // id Blue Screen
        abismoEFerramentas[4][2] = "12"; // posição 12 tabuleiro
        abismoEFerramentas[5][0] = "1"; // Ferramenta
        abismoEFerramentas[5][1] = "2";  // id Testes Unitarios
        abismoEFerramentas[5][2] = "14"; // posição 14 tabuleiro
        abismoEFerramentas[6][0] = "0"; //Abismo
        abismoEFerramentas[6][1] = "5";  // id Duplicated Code
        abismoEFerramentas[6][2] = "13"; // posição 13 tabuleiro
        abismoEFerramentas[7][0] = "1"; //Ferramenta
        abismoEFerramentas[7][1] = "4";  // id IDE
        abismoEFerramentas[7][2] = "16"; // posição 16 tabuleiro
        abismoEFerramentas[8][0] = "0"; //Abismo
        abismoEFerramentas[8][1] = "2";  // id Exception
        abismoEFerramentas[8][2] = "3"; // posição 3 tabuleiro
        abismoEFerramentas[9][0] = "0"; //Abismo
        abismoEFerramentas[9][1] = "8";  // id Ciclo infinito
        abismoEFerramentas[9][2] = "2"; // posição 2 tabuleiro
        abismoEFerramentas[10][0] = "0"; //Abismo
        abismoEFerramentas[10][1] = "6";  // id Efeitos Secundários
        abismoEFerramentas[10][2] = "19"; // posição 19 tabuleiro
        abismoEFerramentas[11][0] = "0"; //Abismo
        abismoEFerramentas[11][1] = "9";  // id Segmentation Fault
        abismoEFerramentas[11][2] = "20"; // posição 20 tabuleiro
        abismoEFerramentas[12][0] = "0"; //Abismo
        abismoEFerramentas[12][1] = "1";  // id Segmentation Fault
        abismoEFerramentas[12][2] = "23"; // posição 20 tabuleiro
        abismoEFerramentas[13][0] = "1"; //Abismo
        abismoEFerramentas[13][1] = "0";  // id Segmentation Fault
        abismoEFerramentas[13][2] = "22"; // posição 20 tabuleiro
        abismoEFerramentas[14][0] = "1"; //Abismo
        abismoEFerramentas[14][1] = "3";  // id Segmentation Fault
        abismoEFerramentas[14][2] = "24"; // posição 20 tabuleiro
        game.createInitialBoard(listaJogadores,25,abismoEFerramentas);
    }
    @Test
    public void blueScreenTest() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[4][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        listaJogadores[2][0] = "3";
        listaJogadores[2][1] = "Pedro";
        listaJogadores[2][2] = "java;phyton";
        listaJogadores[2][3] = "Brown";
        listaJogadores[3][0] = "4";
        listaJogadores[3][1] = "Alberto";
        listaJogadores[3][2] = "java;phyton";
        listaJogadores[3][3] = "Purple";

        String[][] abismoEFerramentas = new String[1][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "7"; //Blue Screen
        abismoEFerramentas[0][2] = "3";
        game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        game.moveCurrentPlayer(2); //Rodrigo posicao = 2 Caí no Blue screen
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Gonçalo posicao = 2 Caí no Blue screen
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Pedro posicao
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Alberto posicao 2
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1);
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1);
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1);
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1);
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(true);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 3 | No tools | java; kotlin | Derrotado";
        assertEquals(esperada1, obtido1);

    }

    @Test
    public void getImageFerramentasEAbismos() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[3][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "0"; //Erro Sintaxe
        abismoEFerramentas[0][2] = "2";
        abismoEFerramentas[1][0] = "1";
        abismoEFerramentas[1][1] = "1"; //Programação Funcional
        abismoEFerramentas[1][2] = "4";
        abismoEFerramentas[2][0] = "0";
        abismoEFerramentas[2][1] = "3"; //Exception
        abismoEFerramentas[2][2] = "3";
        game.createInitialBoard(listaJogadores,5,abismoEFerramentas);
        String abismo = game.getImagePng(2);
        String ferramenta = game.getImagePng(4);
        String casaFinal = game.getImagePng(5);
        String esperada1 = "abismo0.png";
        String esperada2 = "ferramenta1.png";
        String esperada3 = "glory.png";
        assertEquals(esperada1, abismo);
        assertEquals(esperada2, ferramenta);
        assertEquals(esperada3,casaFinal);
    }
    @Test
    public void jogo() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[3][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "0"; //Erro Sintaxe
        abismoEFerramentas[0][2] = "2";
        abismoEFerramentas[1][0] = "0";
        abismoEFerramentas[1][1] = "1"; //Erro de Logica
        abismoEFerramentas[1][2] = "4";
        abismoEFerramentas[2][0] = "0";
        abismoEFerramentas[2][1] = "3"; //Exception
        abismoEFerramentas[2][2] = "3";
        game.createInitialBoard(listaJogadores,5,abismoEFerramentas);
        game.moveCurrentPlayer(1); //Rodrigo posicao = 2 Erro de Sintaxe recua 1 casa
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(5); //Gonçalo posicao = 6
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Rodrigo posicao = 4 Erro de Logica recua 2 casas posicao = 2
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Gonçalo posicao = 7
        game.reactToAbyssOrTool();
        boolean obtido = game.gameIsOver();
        assertFalse(obtido);
        game.moveCurrentPlayer(1); //Rodrigo posicao = 3 Exception recua 2 casas posicao = 2
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 1 | No tools | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
        String obtido2 = programmers.get(1).toString();
        String esperada2 = "2 | Goncalo | 1 | No tools | java; phyton | Em Jogo";
        assertEquals(esperada2, obtido2);
    }

    ArrayList<String> languages = new ArrayList<>();
    Programmer programmer1 = new Programmer(1,"Rodrigo",languages,ProgrammerColor.GREEN);

    @Test
    public void gameIsOver() throws InvalidInitialBoardException {
        Abismo abismo = new Abismo();
        abismo.setTitulo("ola");
        game.verificaAbismos(abismo);
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[2][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "1";
        abismoEFerramentas[0][2] = "2";
        abismoEFerramentas[1][0] = "1";
        abismoEFerramentas[1][1] = "3";
        abismoEFerramentas[1][2] = "1";
        game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        game.moveCurrentPlayer(6);
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6);
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3);
        boolean obtido1 = game.gameIsOver();
        assertTrue(obtido1);
        String obtido2 = game.getGameResults().toString();
        String esperada1 = "[O GRANDE JOGO DO DEISI, , NR. DE TURNOS, 3, , VENCEDOR, Rodrigo, , RESTANTES, Goncalo 7]";
        assertEquals(esperada1, obtido2);

    }
    @Test
    public void getAuthorsPanel() {
        int width = game.getAuthorsPanel().getWidth();
        int height =game.getAuthorsPanel().getHeight();
        int widthEsperado = 300;
        int heightEsperado =300;
        assertEquals(widthEsperado, width);
        assertEquals(heightEsperado, height);
    }

    @Test
    public void gameIsOverDerrotado() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[1][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "7";
        abismoEFerramentas[0][2] = "2";
        game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        game.moveCurrentPlayer(1);
        game.getCurrentPlayerID();
        game.reactToAbyssOrTool();
        boolean obtido1 = game.gameIsOver();
        assertTrue(obtido1);
    }

    @Test
    public void apanhaFerramentaRepetida() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[2][3];
        abismoEFerramentas[0][0] = "1";
        abismoEFerramentas[0][1] = "1";
        abismoEFerramentas[0][2] = "2";
        abismoEFerramentas[1][0] = "1";
        abismoEFerramentas[1][1] = "1";
        abismoEFerramentas[1][2] = "4";
        game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        game.moveCurrentPlayer(1); //Rodrigo posicao = 2
        game.reactToAbyssOrTool();
        game.turnoAtual = 2;
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Gonçalo posicao = 3
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2);
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 4 | Programação Funcional | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
        String obtido2 = programmers.get(1).toString();
        String esperada2 = "2 | Goncalo | 3 | No tools | java; phyton | Em Jogo";
        assertEquals(esperada2, obtido2);
    }

    @Test
    public void getTitle() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[2][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "1";
        abismoEFerramentas[0][2] = "10";
        abismoEFerramentas[1][0] = "1";
        abismoEFerramentas[1][1] = "3";
        abismoEFerramentas[1][2] = "5";
        game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        String obtido1 = game.getTitle(5);
        String esperada1 ="Tratamento de Excepções" ;
        assertEquals(esperada1, obtido1);
        String obtido2= game.getTitle(10);
        String esperada2 ="Erro de lógica" ;
        assertEquals(esperada2, obtido2);
    }

   @Test
    public void getProgrammerInfoImprimir() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[2][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "1";
        abismoEFerramentas[0][2] = "10";
        abismoEFerramentas[1][0] = "1";
        abismoEFerramentas[1][1] = "3";
        abismoEFerramentas[1][2] = "5";
        game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        String obtido1 = game.getProgrammersInfo();
        String esperada1 ="Rodrigo : No tools | Goncalo : No tools" ;
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void getProgrammers() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[2][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "1";
        abismoEFerramentas[0][2] = "10";
        abismoEFerramentas[1][0] = "1";
        abismoEFerramentas[1][1] = "3";
        abismoEFerramentas[1][2] = "5";
        game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        int obtido1 = game.getProgrammers(1).size();
        int esperada1 = 2;
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void getProgrammersIncludeDefeated() throws InvalidInitialBoardException {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[2][3];
        abismoEFerramentas[0][0] = "0";
        abismoEFerramentas[0][1] = "1";
        abismoEFerramentas[0][2] = "10";
        abismoEFerramentas[1][0] = "1";
        abismoEFerramentas[1][1] = "3";
        abismoEFerramentas[1][2] = "5";
        game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        int obtido1 = game.getProgrammers(false).size();
        int esperada1 = 2;
        assertEquals(esperada1, obtido1);
        int obtido2 = game.getProgrammers(true).size();
        int esperada2 = 0;
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void gameManagerCarregarJogadoresComErros() {
        programmer.getColor();
        String menssagem = "Erro : Id do jogador inválido";
        String[][] listaJogadores = new String[2][4];
        try {
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "0";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            game.createInitialBoard(listaJogadores, 10);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
        String menssagem1 = "Erro : Id do jogador inválido";
        try {
            String[][] abismoEFerramentas = new String[2][3];
            abismoEFerramentas[0][0] = "0";
            abismoEFerramentas[0][1] = "1";
            abismoEFerramentas[0][2] = "10";
            abismoEFerramentas[1][0] = "1";
            abismoEFerramentas[1][1] = "3";
            abismoEFerramentas[1][2] = "5";
            game.createInitialBoard(listaJogadores, 10, abismoEFerramentas);
        }
        catch (Exception ex) {
            assertEquals(menssagem1, ex.getMessage());
        }
    }

    @Test
    public void moveCurrentPlayerValorNegativoZeroEMaiorQue6() throws InvalidInitialBoardException { //Caí no Abismo Efeitos Secundários Recua 2 jogadas
        adicionarJogadores();
        boolean obtido1 = game.moveCurrentPlayer(0);
        assertFalse(obtido1);
        boolean obtido2 = game.moveCurrentPlayer(-2);
        assertFalse(obtido2);
        boolean obtido3 = game.moveCurrentPlayer(7);
        assertFalse(obtido3);
    }


    @Test
    public void getImagePosicaoMaiorQueTabuleiro() throws InvalidInitialBoardException {
        Abismo abismo = new Abismo(1,"Erro Lógica",2);
        abismo.getId();
        abismo.setId(2);
        abismo.setPosicao(3);
        Ferramenta ferramenta = new Ferramenta(2,1,"IDE");
        ferramenta.getId();
        ferramenta.setId(2);
        ferramenta.setPosicao(3);
        ferramenta.setTitulo("Ajuda Do Professor");
        adicionarJogadores();
        String obtido= game.getImagePng(30);
        String esperada = null;
        assertEquals(esperada, obtido);
    }

    @Test
    public void getImagePosicaoNegativa() throws InvalidInitialBoardException {
        adicionarJogadores();
        String obtido= game.getImagePng(-1);
        String esperada = null;
        assertEquals(esperada, obtido);
    }

    @Test
    public void getTituloPosicaoMaiorQueTabuleiro() throws InvalidInitialBoardException {
        adicionarJogadores();
        String obtido= game.getTitle(30);
        String esperada = null;
        assertEquals(esperada, obtido);
    }
    @Test
    public void getTituloPosicaoNegativa() throws InvalidInitialBoardException {
        adicionarJogadores();
        String obtido= game.getTitle(-1);
        String esperada = null;
        assertEquals(esperada, obtido);
    }

    @Test
    public void gameManagerNaoEAbismoNemFerramenta() {
        String menssagem = "Erro : Foi passado um número diferente de 1 e de 0";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            String[][] abismoEFerramentas = new String[2][3];
            abismoEFerramentas[0][0] = "-2"; // abismo
            abismoEFerramentas[0][1] = "4";  // id Crash
            abismoEFerramentas[0][2] = "10"; // posição 10 tabuleiro
            abismoEFerramentas[1][0] = "1"; // ferramenta
            abismoEFerramentas[1][1] = "5";  // id ajuda Professor
            abismoEFerramentas[1][2] = "5"; //posição 5 tabuleiro
            game.createInitialBoard(listaJogadores, 10, abismoEFerramentas);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerIdFerramentaMaiorQueCinco() {
        String menssagem = "Erro : Abismo Invalido7";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            String[][] abismoEFerramentas = new String[2][3];
            abismoEFerramentas[0][0] = "0"; // abismo
            abismoEFerramentas[0][1] = "4";  // id Crash
            abismoEFerramentas[0][2] = "10"; // posição 10 tabuleiro
            abismoEFerramentas[1][0] = "1"; // ferramenta
            abismoEFerramentas[1][1] = "7";  // id ajuda Professor
            abismoEFerramentas[1][2] = "5"; //posição 5 tabuleiro
            game.createInitialBoard(listaJogadores, 10, abismoEFerramentas);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerIdAbismoNegativo() {
        String menssagem = "Erro : Abismo Invalido";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            String[][] abismoEFerramentas = new String[2][3];
            abismoEFerramentas[0][0] = "0"; // abismo
            abismoEFerramentas[0][1] = "-2";  // id Crash
            abismoEFerramentas[0][2] = "10"; // posição 10 tabuleiro
            abismoEFerramentas[1][0] = "1"; // ferramenta
            abismoEFerramentas[1][1] = "7";  // id ajuda Professor
            abismoEFerramentas[1][2] = "5"; //posição 5 tabuleiro
            game.createInitialBoard(listaJogadores, 10, abismoEFerramentas);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerNomeAbismoVazio() {
        String menssagem = "Erro : Abismo Invalido7";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            String[][] abismoEFerramentas = new String[2][3];
            abismoEFerramentas[0][0] = "0"; // abismo
            abismoEFerramentas[0][1] = "3";  // id Crash
            abismoEFerramentas[0][2] = "10"; // posição 10 tabuleiro
            abismoEFerramentas[1][0] = "1"; // ferramenta
            abismoEFerramentas[1][1] = "7";  // id ajuda Professor
            abismoEFerramentas[1][2] = "5"; //posição 5 tabuleiro
            game.createInitialBoard(listaJogadores, 10, abismoEFerramentas);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerNomeFerramentaVazio() {
        String menssagem = "Erro : Abismo Invalido";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            String[][] abismoEFerramentas = new String[2][3];
            abismoEFerramentas[0][0] = "0"; // abismo
            abismoEFerramentas[0][1] = "10";  // id Crash
            abismoEFerramentas[0][2] = "10"; // posição 10 tabuleiro
            abismoEFerramentas[1][0] = "1"; // ferramenta
            abismoEFerramentas[1][1] = "5";  // id ajuda Professor
            abismoEFerramentas[1][2] = "5"; //posição 5 tabuleiro
            game.createInitialBoard(listaJogadores, 10, abismoEFerramentas);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManager1Jogador() {
        String menssagem = "Erro : Numero de jogadores inválidos";
        try {
            String[][] listaJogadores = new String[1][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            game.createInitialBoard(listaJogadores, 10);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerMaisDe4Jogadores() {
        String menssagem  = "Erro : Jogadores com cores repetidas";
        try {
            String[][] listaJogadores = new String[5][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            listaJogadores[2][0] = "3";
            listaJogadores[2][1] = "Rodrigo";
            listaJogadores[2][2] = "java;kotlin";
            listaJogadores[2][3] = "Purple";
            listaJogadores[3][0] = "4";
            listaJogadores[3][1] = "Goncalo";
            listaJogadores[3][2] = "java;phyton";
            listaJogadores[3][3] = "Brown";
            listaJogadores[4][0] = "5";
            listaJogadores[4][1] = "Goncalo";
            listaJogadores[4][2] = "java;phyton";
            listaJogadores[4][3] = "Blue";
            game.createInitialBoard(listaJogadores, 10);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerTamanhoTabuleiroNulo() {
        String menssagem = "Erro : Tabuleiro com posições inválidas";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            game.createInitialBoard(listaJogadores, 0);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerTamanhoTabuleironegativo() {
        String menssagem =  "Erro : Tabuleiro com posições inválidas";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "1";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            game.createInitialBoard(listaJogadores, -5);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }
    @Test
    public void gameManagerIdNegativo() throws InvalidInitialBoardException {
        String menssagem = "Erro : Id do jogador inválido";
        try {

            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "-3";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            game.createInitialBoard(listaJogadores, 10);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerNomeVazio() {
        String menssagem = "Erro : Nome do jogador inválido";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "3";
            listaJogadores[0][1] = "";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "2";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            game.createInitialBoard(listaJogadores, 10);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameManagerIdRepetido() throws InvalidInitialBoardException {
        String menssagem = "Erro : Id do jogador inválido";
        try {
            String[][] listaJogadores = new String[2][4];
            listaJogadores[0][0] = "3";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            listaJogadores[1][0] = "3";
            listaJogadores[1][1] = "Goncalo";
            listaJogadores[1][2] = "java;phyton";
            listaJogadores[1][3] = "Blue";
            game.createInitialBoard(listaJogadores, 10);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void gameIsOver1Player() throws InvalidInitialBoardException {
        String menssagem = "Erro : Numero de jogadores inválidos";
        try {
            String[][] listaJogadores = new String[1][4];
            listaJogadores[0][0] = "3";
            listaJogadores[0][1] = "Rodrigo";
            listaJogadores[0][2] = "java;kotlin";
            listaJogadores[0][3] = "Green";
            game.gameIsOver();
            game.createInitialBoard(listaJogadores, 10);
        }
        catch (Exception ex) {
            assertEquals(menssagem, ex.getMessage());
        }
    }

    @Test
    public void eSalvoEfeitosSecundarios() throws InvalidInitialBoardException { //E Salvo pela Ferramenta Programação Funcional
        adicionarJogadores();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 7 Caí no Ciclo Infinito
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Gonçalo posicao = 7 Caí no Ciclo Infinito
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Rodrigo posicao = 9 Apanha a ferramenta Programação Funcional
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Gonçalo posicao = 9 Apanha a ferramenta Programação Funcional
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 15
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Gonçalo posicao = 15
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 19 Caí no Abismo Efeitos Secundários é salvo pela ferramenta
        game.reactToAbyssOrTool();

        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 19 | No tools | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
        String obtido2 = programmers.get(1).toString();
        String esperada2 = "2 | Goncalo | 15 | Programação Funcional | java; phyton | Em Jogo";
        assertEquals(esperada2, obtido2);
    }

    @Test
    public void caiNoAbismoSegmentationFault() throws InvalidInitialBoardException { //Caí no Abismo Segmentation Fault tu e o teu adversário recuam 3 casas
        adicionarJogadores();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 7
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 11
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Gonçalo posicao = 5
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 17
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Gonçalo posicao = 8
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Rodrigo posicao = 20 Caí no Abismo Segmentation Fault
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 20 Caí no Abismo Segmentation Fault
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 17 | No tools | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
        String obtido2 = programmers.get(1).toString();
        String esperada2 = "2 | Goncalo | 17 | No tools | java; phyton | Em Jogo";
        assertEquals(esperada2, obtido2);
        String obtido3 = game.getProgrammersInfo();
        String esperada3="Rodrigo : No tools | Goncalo : No tools" ;
    }

    @Test
    public void caiNoAbismoEfeitosSecundarios() throws InvalidInitialBoardException { //Caí no Abismo Efeitos Secundários Recua 2 jogadas
        adicionarJogadores();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 7
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 11
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Gonçalo posicao = 5
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 17
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 8
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Rodrigo posicao = 19 Caí no Abismo Efeitos Secundários
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 11 | No tools | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void caiNoAbismoCicloInfinito() throws InvalidInitialBoardException { //Caí no Ciclo Infinito
        adicionarJogadores();
        game.moveCurrentPlayer(1); //Rodrigo posicao = 2 Caí no Ciclo Infinito
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 2  Não se pode mover Está no ciclo infinito
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 2 | No tools | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void eSalvoCicloInfinito() throws InvalidInitialBoardException { //E salvo pelo adversario
        adicionarJogadores();
        game.moveCurrentPlayer(1); //Rodrigo posicao = 2 Caí no Ciclo Infinito
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Gonçalo posicao = 2 Caí no Ciclo Infinito
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 8
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Gonçalo posicao = 2 Não se pode mover Está no ciclo infinito
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 8 | No tools | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
        String obtido2 = programmers.get(1).toString();
        String esperada2 = "2 | Goncalo | 2 | No tools | java; phyton | Em Jogo";
        assertEquals(esperada2, obtido2);
    }

    @Test
    public void caiNoAbismoCrash() throws InvalidInitialBoardException { //Caí no Crash
        adicionarJogadores();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 5
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Gonçalo posicao = 5
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 9
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(5); //Gonçalo posicao = 10
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Rodrigo posicao = 11
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 11 | Ajuda Do Professor;Programação Funcional | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido1);
        String obtido2 = programmers.get(1).toString();
        String esperada2 = "2 | Goncalo | 1 | Ajuda Do Professor | java; phyton | Em Jogo";
        assertEquals(esperada2, obtido2);
    }

    @Test
    public void defendeDoAbismoErroSintaxe() throws InvalidInitialBoardException { //defende do erro Sintaxe
        adicionarJogadores();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 5 Apanha Ajuda Do Professor
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 5 | Ajuda Do Professor | java; kotlin | Em Jogo"; // Foi salvo pela Ferramenta Ajuda do Professor
        assertEquals(esperada1, obtido1);
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(1); //Rodrigo posicao = 6 Caí no Abismo Erro Sintaxe
        game.reactToAbyssOrTool();
        String obtido2 = programmers.get(0).toString();
        String esperada2 = "1 | Rodrigo | 6 | No tools | java; kotlin | Em Jogo"; // Foi salvo pela Ferramenta Ajuda do Professor
        assertEquals(esperada2, obtido2);
    }

    @Test
    public void caiNoAbismoException() throws InvalidInitialBoardException { //caí no Abismo Exception
        adicionarJogadores();
        game.moveCurrentPlayer(2); //Rodrigo posicao = 3
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 1 | No tools | java; kotlin | Em Jogo"; // Foi salvo pela Ferramenta Ajuda do Professor
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void caiNoAbismoBlueScreen() throws InvalidInitialBoardException { //Caí no Blue Screen
        adicionarJogadores();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 7
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(5); //Rodrigo posicao = 12 Caí no Abismo Blue Screen
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(true);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 12 | No tools | java; kotlin | Derrotado"; // Foi salvo pela Ferramenta Ajuda do Professor
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void caiAbismoDuplicatedCode() throws InvalidInitialBoardException { //Caí no Abismo Duplicated Code
        adicionarJogadores();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 7
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(6); //Rodrigo posicao = 13 Caí no Abismo Duplicated Code
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(true);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 7 | No tools | java; kotlin | Em Jogo"; // Foi salvo pela Ferramenta Ajuda do Professor
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void testApanhaFerramentas() throws InvalidInitialBoardException { //Apanha todas a ferramentas Ajuda Do Professor;Programação Funcional;Testes unitários;IDE
        adicionarJogadores();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 5 Apanha a ferramenta Ajuda Do Professor
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 9 Apanha a ferramenta Programação Funcional
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(5); //Gonçalo posicao = 7
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(5); //Rodrigo posicao = 14 Apanha a ferramenta Teste Unitários
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Gonçalo posicao = 11
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(2); //Rodrigo posicao = 16 Apanha a ferramenta IDE
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(true);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 16 | Ajuda Do Professor;Programação Funcional;Testes unitários;IDE | java; kotlin | Em Jogo"; // Foi salvo pela Ferramenta Ajuda do Professor
        assertEquals(esperada1, obtido1);
        String obtido3 = game.getProgrammersInfo();
        String esperada3="Rodrigo : Ajuda Do Professor;Programação Funcional;Testes unitários;IDE | Goncalo : Programação Funcional" ;
        assertEquals(esperada3, obtido3);
    }

    @Test
    public void getPlayer() throws InvalidInitialBoardException{
        /*
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";

         */
         adicionarJogadores();
      //  game.createInitialBoard(listaJogadores,15);
        game.moveCurrentPlayer(4); //Rodrigo posicao = 5 Apanha a ferramenta Ajuda Do Professor
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        List<String> comandos = new ArrayList<>();
        comandos.add("GET PLAYER Rodrigo");
        String obtido = FunctionsKt.getPlayer(game,comandos);
        String esperada1 = "1 | Rodrigo | 16 | Ajuda Do Professor;Programação Funcional;Testes unitários;IDE | java; kotlin | Em Jogo";
        assertEquals(esperada1, obtido);
    }

    @Test
    public void getPolyglots() throws InvalidInitialBoardException{
        adicionarJogadores();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 5 Apanha a ferramenta Ajuda Do Professor
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        List<String> comandos = new ArrayList<>();
        comandos.add("GET POLYGLOTS");
        String obtido = FunctionsKt.getPolyGlots(game,comandos);
        String esperada1 = "Rodrigo:2\n" + "Goncalo:2";
        assertEquals(esperada1, obtido);
    }

}
