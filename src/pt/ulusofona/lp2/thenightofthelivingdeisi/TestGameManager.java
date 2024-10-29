package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestGameManager {


    @Test
    public void testCreaturasInfoString(){

        GameManager game = new GameManager();
        game.loadGame(new File("test-files","Jogo.txt"));
        String resultadoAtual = game.getCreatureInfoAsString(2);
        String resultadoEsperado =  "2 | Humano | Krat | +0 @ (5,2)";
        Assertions.assertEquals(resultadoEsperado,resultadoAtual);
    }

    @Test
    public void testEquipamentosInfoString(){

        GameManager game = new GameManager();
        game.loadGame(new File("test-files","Jogo.txt"));
        String resultadoAtual = game.getEquipmentInfoAsString(-2);
        String resultadoEsperado =  "-2 | Espada samurai @ (2,3)";
        Assertions.assertEquals(resultadoEsperado,resultadoAtual);
    }

    @Test
    public void testCreaturasInfo(){

        GameManager game = new GameManager();
        game.loadGame(new File("test-files","Jogo.txt"));
        String[] resultadoAtual = game.getCreatureInfo(2);
        String[] resultadoEsperado =  ;
        Assertions.assertEquals(resultadoEsperado,resultadoAtual);
    }

    @Test
    public void testEquipamentosInfo(){

        GameManager game = new GameManager();
        game.loadGame(new File("test-files","Jogo.txt"));
        String[] resultadoAtual = game.getEquipmentInfo(-2);
        String resultadoEsperado =  "-2 | Espada samurai @ (2,3)";
        Assertions.assertEquals(resultadoEsperado,resultadoAtual);
    }


}
