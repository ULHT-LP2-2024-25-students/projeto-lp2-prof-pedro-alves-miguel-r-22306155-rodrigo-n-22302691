package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestGameManager {


    @Test
    public void testCreaturesInfoString() throws InvalidFileException, FileNotFoundException {

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
    public void testEquipmentsInfoString() throws InvalidFileException, FileNotFoundException {

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
    public void testHasEquipment() throws InvalidFileException, FileNotFoundException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        boolean resultadoAtual = game.hasEquipment(2, -1);
        boolean resultadoEsperado = false;
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testSquareInfo() throws InvalidFileException, FileNotFoundException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String resultadoAtual = game.getSquareInfo(5, 2);
        String resultadoEsperado = "H:2";
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }
}
