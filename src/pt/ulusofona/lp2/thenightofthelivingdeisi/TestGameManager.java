package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestGameManager {


    @Test
    public void testCreaturesInfoString() {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String resultadoAtual = game.getCreatureInfoAsString(2);
        String resultadoEsperado = "2 | Humano | Krat | +0 @ (5, 2)";
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);

        String resultAtual = game.getCreatureInfoAsString(3);
        String resultEsperado = "3 | Zombie | DrMonengue | -0 @ (4, 2)";
        Assertions.assertEquals(resultEsperado, resultAtual);
    }

    @Test
    public void testEquipmentsInfoString() {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String resultadoAtual = game.getEquipmentInfoAsString(-2);
        String resultadoEsperado = "-2 | Espada samurai @ (2,3)";
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);

        String resultAtual = game.getEquipmentInfoAsString(-1);
        String resultEsperado = "-1 | Escudo de madeira @ (1,1)";
        Assertions.assertEquals(resultEsperado, resultAtual);
    }

    @Test
    public void testCreaturesInfo() {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String[] resultadoAtual = game.getCreatureInfo(3);
        String[] resultadoEsperado = {"3", "0", "DrMonengue", "4", "2", ""};
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testEquipmentsInfo() {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String[] resultadoAtual = game.getEquipmentInfo(-1);
        String[] resultadoEsperado = {"-1", "0", "1", "1", ""};
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testHasEquipment() {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        boolean resultadoAtual = game.hasEquipment(2, -1);
        boolean resultadoEsperado = false;
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testSquareInfo() {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String resultadoAtual = game.getSquareInfo(5, 2);
        String resultadoEsperado = "H:2";
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }
}
