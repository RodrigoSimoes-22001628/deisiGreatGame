package pt.ulusofona.lp2.deisiGreatGame;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        String[][] abismo = new String[12][3];
        abismo[0][0] = "0"; // abismo
        abismo[0][1] = "4";  // id Crash
        abismo[0][2] = "10"; // posição 10 tabuleiro
        abismo[1][0] = "0"; // abismo
        abismo[1][1] = "0";  // id erro de Sintaxe
        abismo[1][2] = "6"; // posição 6 tabuleiro
        abismo[2][0] = "1"; // ferramenta
        abismo[2][1] = "5";  // id ajuda Professor
        abismo[2][2] = "5"; //posição 5 tabuleiro
        abismo[3][0] = "1"; // ferramenta
        abismo[3][1] = "1";  // id Programação Funcional
        abismo[3][2] = "9"; //posição 9 tabuleiro
        abismo[4][0] = "0"; // abismo
        abismo[4][1] = "7";  // id Blue Screen
        abismo[4][2] = "12"; // posição 12 tabuleiro
        abismo[5][0] = "1"; // Ferramenta
        abismo[5][1] = "2";  // id Testes Unitarios
        abismo[5][2] = "14"; // posição 14 tabuleiro
        abismo[6][0] = "0"; //Abismo
        abismo[6][1] = "5";  // id Duplicated Code
        abismo[6][2] = "13"; // posição 13 tabuleiro
        abismo[7][0] = "1"; //Ferramenta
        abismo[7][1] = "4";  // id IDE
        abismo[7][2] = "16"; // posição 16 tabuleiro
        abismo[8][0] = "0"; //Abismo
        abismo[8][1] = "2";  // id Exception
        abismo[8][2] = "3"; // posição 3 tabuleiro
        abismo[9][0] = "0"; //Abismo
        abismo[9][1] = "8";  // id Ciclo infinito
        abismo[9][2] = "2"; // posição 2 tabuleiro
        abismo[10][0] = "0"; //Abismo
        abismo[10][1] = "6";  // id Efeitos Secundários
        abismo[10][2] = "19"; // posição 19 tabuleiro
        abismo[11][0] = "0"; //Abismo
        abismo[11][1] = "9";  // id Segmentation Fault
        abismo[11][2] = "20"; // posição 20 tabuleiro


        game.createInitialBoard(listaJogadores,25,abismo);
    }

    @Test
    public void eSalvoEfeitosSecundarios() { //E Salvo pela Ferramenta Programação Funcional
        adicionarJogadores(); adicionarJogadores();
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
