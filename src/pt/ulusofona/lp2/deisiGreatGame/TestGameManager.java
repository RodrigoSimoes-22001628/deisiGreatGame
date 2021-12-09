package pt.ulusofona.lp2.deisiGreatGame;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class TestGameManager {
    GameManager game = new GameManager();
    public void adicionarJogadores(){
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismoEFerramentas = new String[12][3];
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


        game.createInitialBoard(listaJogadores,25,abismoEFerramentas);
    }

    @Test
    public void gameIsOver() { //Caí no Abismo Efeitos Secundários Recua 2 jogadas

    }

    @Test
    public void moveCurrentPlayerValorNegativoZeroEMaiorQue6() { //Caí no Abismo Efeitos Secundários Recua 2 jogadas
        adicionarJogadores();
        boolean obtido1 = game.moveCurrentPlayer(0);
        assertFalse(obtido1);
        boolean obtido2 = game.moveCurrentPlayer(-2);
        assertFalse(obtido2);
        boolean obtido3 = game.moveCurrentPlayer(7);
        assertFalse(obtido3);
    }

    @Test
    public void getImagePosicaoMaiorQueTabuleiro() {
        adicionarJogadores();
        String obtido= game.getImagePng(30);
        String esperada = null;
        assertEquals(esperada, obtido);
    }

    @Test
    public void getImagePosicaoNegativa() {
        adicionarJogadores();
        String obtido= game.getImagePng(-1);
        String esperada = null;
        assertEquals(esperada, obtido);
    }

    @Test
    public void getTituloPosicaoMaiorQueTabuleiro() {
        adicionarJogadores();
        String obtido= game.getTitle(30);
        String esperada = null;
        assertEquals(esperada, obtido);
    }
    @Test
    public void getTituloPosicaoNegativa() {
        adicionarJogadores();
        String obtido= game.getTitle(-1);
        String esperada = null;
        assertEquals(esperada, obtido);
    }

    @Test
    public void gameManagerNaoEAbismoNemFerramenta() {
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
        boolean jogo = game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        assertFalse(jogo);
    }

    @Test
    public void gameManagerIdFerramentaMaiorQueCinco() {
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
        boolean jogo = game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        assertFalse(jogo);
    }

    @Test
    public void gameManagerIdAbismoNegativo() {
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
        boolean jogo = game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        assertFalse(jogo);
    }

    @Test
    public void gameManagerNomeAbismoVazio() {
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
        boolean jogo = game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        assertFalse(jogo);
    }

    @Test
    public void gameManagerNomeFerramentaVazio() {
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
        boolean jogo = game.createInitialBoard(listaJogadores,10,abismoEFerramentas);
        assertFalse(jogo);
    }

    @Test
    public void gameManager1Jogador() {
        String[][] listaJogadores = new String[1][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        boolean jogo = game.createInitialBoard(listaJogadores,10);
        assertFalse(jogo);
    }
    @Test
    public void gameManagerMaisDe4Jogadores() {
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
        boolean jogo = game.createInitialBoard(listaJogadores,10);
        assertFalse(jogo);
    }

    @Test
    public void gameManagerTamanhoTabuleiroNulo() {
         String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        boolean jogo = game.createInitialBoard(listaJogadores,0);
        assertFalse(jogo);
    }

    @Test
    public void gameManagerTamanhoTabuleironegativo() {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        boolean jogo = game.createInitialBoard(listaJogadores,-5);
        assertFalse(jogo);
    }
    @Test
    public void gameManagerIdNegativo() {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "-3";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        boolean jogo = game.createInitialBoard(listaJogadores,10);
        assertFalse(jogo);
    }

    @Test
    public void gameManagerNomeVazio() {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "3";
        listaJogadores[0][1] = "";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        boolean jogo = game.createInitialBoard(listaJogadores,10);
        assertFalse(jogo);
    }

    @Test
    public void gameManagerIdRepetido() {
        String[][] listaJogadores = new String[2][4];
        listaJogadores[0][0] = "3";
        listaJogadores[0][1] = "Rodrigo";
        listaJogadores[0][2] = "java;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "3";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        boolean jogo = game.createInitialBoard(listaJogadores,10);
        assertFalse(jogo);
    }

    @Test
    public void eSalvoEfeitosSecundarios() { //E Salvo pela Ferramenta Programação Funcional
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
    public void caiNoAbismoSegmentationFault() { //Caí no Abismo Segmentation Fault tu e o teu adversário recuam 3 casas
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
    }

    @Test
    public void caiNoAbismoEfeitosSecundarios() { //Caí no Abismo Efeitos Secundários Recua 2 jogadas
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
    public void caiNoAbismoCicloInfinito() { //Caí no Ciclo Infinito
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
    public void eSalvoCicloInfinito() { //E salvo pelo adversario
        adicionarJogadores(); adicionarJogadores();
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
    public void caiNoAbismoCrash() { //Caí no Crash
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
    public void defendeDoAbismoErroSintaxe() { //defende do erro Sintaxe
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
    public void caiNoAbismoException() { //caí no Abismo Exception
        adicionarJogadores();
        game.moveCurrentPlayer(2); //Rodrigo posicao = 3
        game.reactToAbyssOrTool();
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido1 = programmers.get(0).toString();
        String esperada1 = "1 | Rodrigo | 1 | No tools | java; kotlin | Em Jogo"; // Foi salvo pela Ferramenta Ajuda do Professor
        assertEquals(esperada1, obtido1);
    }

    @Test
    public void caiNoAbismoBlueScreen() { //Caí no Blue Screen
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
    public void caiAbismoDuplicatedCode() { //Caí no Abismo Duplicated Code
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
    public void testApanhaFerramentas() { //Apanha todas a ferramentas Ajuda Do Professor;Programação Funcional;Testes unitários;IDE
        adicionarJogadores();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 5 Apanha a ferramenta Ajuda Do Professor
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 4
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(4); //Rodrigo posicao = 9 Apanha a ferramenta Programação Funcional
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(3); //Gonçalo posicao = 7
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
    }
}
