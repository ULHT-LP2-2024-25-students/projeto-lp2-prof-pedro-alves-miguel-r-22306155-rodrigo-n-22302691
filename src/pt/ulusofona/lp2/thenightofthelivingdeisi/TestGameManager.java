package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestGameManager {


    @Test
    public void testCreaturas(){

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String resultadoAtual = game.getCreatureInfoAsString(2);
        String resultadoEsperado =  "2 | Humano | Krat | +0 @ (5, 2)";
        Assertions.assertEquals(resultadoEsperado,resultadoAtual);
    }
}
