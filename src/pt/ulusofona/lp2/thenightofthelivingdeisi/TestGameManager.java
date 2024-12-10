package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.desktop.AboutEvent;
import java.io.File;
import java.io.FileNotFoundException;

public class TestGameManager {


    @Test
    public void testCreaturesInfoString() throws InvalidFileException, FileNotFoundException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String resultadoAtual = game.getCreatureInfoAsString(2);
        String resultadoEsperado = "2 | Adulto | Zombie | Walker | -0 @ (5, 3)";
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);

        String resultAtual = game.getCreatureInfoAsString(6);
        String resultEsperado = "6 | Criança | Humano | Karate Kid | +0 @ (3, 4)";
        Assertions.assertEquals(resultEsperado, resultAtual);
    }

    @Test
    public void testEquipmentsInfoString() throws InvalidFileException, FileNotFoundException {

        GameManager game = new GameManager();

        game.loadGame(new File("test-files", "Jogo.txt"));


        String resultadoAtual = game.getEquipmentInfoAsString(-2);
        String resultadoEsperado = "-2 | Espada samurai @ (2, 0)";
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);

        String resultAtual = game.getEquipmentInfoAsString(-1);
        String resultEsperado = "-1 | Escudo de madeira @ (3, 5)";
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
        String resultadoAtual = game.getSquareInfo(5, 3);
        String resultadoEsperado = "Z:2";
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testHumanoDefendeZombie() throws InvalidFileException {
        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));

        // Primeiro movimento
        boolean primeiroMovimento = game.move(5, 3, 5, 4);
        Assertions.assertTrue(primeiroMovimento, "O humano deveria mover de (5,3) para (5,4).");

        // Segundo movimento
        boolean segundoMovimento = game.move(4, 3, 4, 4);
        Assertions.assertTrue(segundoMovimento, "O humano deveria mover de (5,3) para (4,4).");

        // Terceiro movimento
        boolean terceiroMovimento = game.move(5, 4, 4, 4);
        Assertions.assertTrue(terceiroMovimento, "O humano deveria mover de (5,4) para (4,4).");

        // Validação final
        boolean moveEsperado = true;
        Assertions.assertEquals(moveEsperado, terceiroMovimento,
                "O humano deveria conseguir defender-se do zumbi na posição (4,4).");
    }
    }

