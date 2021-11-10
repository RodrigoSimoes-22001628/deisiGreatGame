package pt.ulusofona.lp2.deisiGreatGame;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestGameManager {

    public void adicionarJogadores(){
        String[][] listaJogadores = new String[1][4];
        listaJogadores[0][0] = "1";
        listaJogadores[0][1] = "Pedro";
        listaJogadores[0][2] = "c;kotlin";
        listaJogadores[0][3] = "Green";
        GameManager game = new GameManager();
        game.createInitialBoard(listaJogadores,5);
    }

//    @Test
//    public void testMoveCurrentPlayer() {
//        adicionarJogadores();
//        GameManager mover = new GameManager();
//        mover.moveCurrentPlayer(2);
//
//    }

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
