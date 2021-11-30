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
        listaJogadores[0][1] = "Pedro";
        listaJogadores[0][2] = "c;kotlin";
        listaJogadores[0][3] = "Green";
        listaJogadores[1][0] = "2";
        listaJogadores[1][1] = "Goncalo";
        listaJogadores[1][2] = "java;phyton";
        listaJogadores[1][3] = "Blue";
        String[][] abismo = new String[2][3];
        abismo[0][0] = "0"; // abismo
        abismo[0][1] = "6";  // id
        abismo[0][2] = "10"; // posicao
        abismo[1][0] = "1"; // ferramenta
        abismo[1][1] = "2";  // id
        abismo[1][2] = "5";
        //    game.createInitialBoard(listaJogadores,5);
        game.createInitialBoard(listaJogadores,20,abismo);
    }

    @Test
    public void testMoveCurrentPlayer1() {
        adicionarJogadores();
        game.moveCurrentPlayer(4);
        game.reactToAbyssOrTool();
        //    game.turnoAtual += 1;
        game.moveCurrentPlayer(5);
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(5);
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(5);
        game.reactToAbyssOrTool();
        game.moveCurrentPlayer(5);
        List<Programmer> programmers = game.getProgrammers(false);
        String obtido = programmers.get(0).toString();
        String esperada = "1 | Pedro | 15 | No tools | c; kotlin | Em Jogo";
        assertEquals("a posição não está correta: ", esperada, obtido);
    }
/*

    @Test
    public void testMoveCurrentPlayer2() {
        adicionarJogadores();
        game.moveCurrentPlayer(1);
        ArrayList<Programmer> programmers = game.getProgrammers();
        String obtido = programmers.get(1).toString();
        String esperada = "2 | Goncalo | 1 | java; phyton | Em Jogo";
        assertEquals("a posição não está correta: ", esperada, obtido);
    }

    @Test
    public void testMoveCurrentPlayer1PosicaoMaiorTabuleiro() {
        adicionarJogadores();
        game.moveCurrentPlayer(6);
        ArrayList<Programmer> programmers = game.getProgrammers();
        String obtido = programmers.get(0).toString();
        String esperada = "1 | Pedro | 3 | c; kotlin | Em Jogo";
        assertEquals("a posição não está correta: ", esperada, obtido);
    }

    @Test
    public void testMoveCurrentPlayer2PosicaoMaiorTabuleiro() {
        adicionarJogadores();
        game.moveCurrentPlayer(8);
        ArrayList<Programmer> programmers = game.getProgrammers();
        String obtido = programmers.get(1).toString();
        String esperada = "2 | Goncalo | 1 | java; phyton | Em Jogo";
        assertEquals("a posição não está correta: ", esperada, obtido);
    }

    @Test
    public void testMoveCurrentPlayer2PosicaoInvalidaTabuleiro() {
        adicionarJogadores();
        game.moveCurrentPlayer(0);
        ArrayList<Programmer> programmers = game.getProgrammers();
        String obtido = programmers.get(1).toString();
        String esperada = "2 | Goncalo | 1 | java; phyton | Em Jogo";
        assertEquals("a posição não está correta: ", esperada, obtido);
    }

    @Test
    public void testGetters() {
        ArrayList<String> languages = new ArrayList<>();
        languages.add("kotlin; c");
        Programmer jogador = new Programmer(1, "Joao", languages, ProgrammerColor.PURPLE);
        int valorEsperadoPosicao = 1;
        int valorRealPosicao = jogador.getPosicao();
        int valorEsperadoId = 1;
        int valorRealId = jogador.getId();
        String valorEsperadoNome = "Joao";
        String valorRealNome = jogador.getName();
        ProgrammerColor valorEsperadoCor = ProgrammerColor.PURPLE;
        ProgrammerColor valorRealCor = jogador.getColor();
        assertEquals(valorEsperadoPosicao, valorRealPosicao);
        assertEquals(valorEsperadoId, valorRealId);
        assertEquals(valorEsperadoNome, valorRealNome);
        assertEquals(valorEsperadoCor, valorRealCor);
    } */
}
