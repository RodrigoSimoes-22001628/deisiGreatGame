package pt.ulusofona.lp2.deisiGreatGame;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestGameManager {
    GameManager game = new GameManager();

    public void adicionarJogadores(){
        String[][] listaJogadores = new String[1][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Pedro";
        listaJogadores[0][2] = "c;kotlin";
        listaJogadores[0][3] = "Green";
        game.createInitialBoard(listaJogadores,10);
    }

    @Test
    public void testMoveCurrentPlayer1() {
        adicionarJogadores();
        game.moveCurrentPlayer(2);
        ArrayList<Programmer> programmers = game.getProgrammers();
         String obtido = programmers.get(0).toString();
         String esperada = "1 | Pedro | 3 | c; kotlin | Em Jogo";
         assertEquals("a posição não está correta: ", esperada, obtido);

    }
    @Test
    public void testMoveCurrentPlayer2() {
        adicionarJogadores();
        game.moveCurrentPlayer(5);
        ArrayList<Programmer> programmers = game.getProgrammers();
        String obtido = programmers.get(0).toString();
        String esperada = "1 | Pedro | 6 | c; kotlin | Em Jogo";
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
    }
}
