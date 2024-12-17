package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

public class TestGameManager {


    @Test
    public void testCreaturesInfoString() throws InvalidFileException {

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
    public void testEquipmentsInfoString() throws InvalidFileException {

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
    public void testHasEquipment() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        boolean resultadoAtual = game.hasEquipment(2, -1);
        boolean resultadoEsperado = false;
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testSquareInfo() throws InvalidFileException {

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
        Assertions.assertTrue(primeiroMovimento, "O zombie deveria mover de (5,3) para (5,4).");

        // Segundo movimento
        boolean segundoMovimento = game.move(4, 3, 4, 4);
        Assertions.assertTrue(segundoMovimento, "O humano deveria mover de (5,3) para (4,4).");

        // Terceiro movimento
        boolean terceiroMovimento = game.move(5, 4, 4, 4);
        Assertions.assertTrue(terceiroMovimento, "O zombie deveria mover de (5,4) para (4,4).");

        // Validação final
        String resultadoEsperadoToString = "7 | Adulto | Humano | Freddie M. | +1 @ (4, 4) | -4 | Lixívia @ (4, 4) | 0.7 litros";
        String resultadoAtualToString = game.getCreatureInfoAsString(7);
        Assertions.assertEquals(resultadoEsperadoToString,resultadoAtualToString);

        String resultadoEsperadoGetSquare = "H:7";
        String resultadoAtualGetSquare = game.getSquareInfo(4,4);
        Assertions.assertEquals(resultadoEsperadoGetSquare,resultadoAtualGetSquare);


    }

    @Test
    public void testGetWorldSize() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String worldSizeAtual = Arrays.toString(game.getWorldSize());
        String wordSizeEsperado = "[7, 7]";
        Assertions.assertEquals(wordSizeEsperado, worldSizeAtual, "O tamanho do tabuleiro deveria ser 7 por 7");
    }

    @Test
    public void testGetInicialID() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        int inicialIdAtual = game.getInitialTeamId();
        int inicialIdEsperado = 10;
        Assertions.assertEquals(inicialIdEsperado,inicialIdAtual, "Deveriam ser os zombies primeiro a jogar");
    }

    @Test
    public void testGetCurrentID() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        int currentIdAtual;
        game.move(5, 3, 5, 4);
        currentIdAtual = game.getCurrentTeamId();
        int currentIdEsperado = 20;
        Assertions.assertEquals(currentIdEsperado, currentIdAtual, "Agora deveriam ser os humanos a jogar");
    }

    @Test
    public void testgetSquareInfo() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String squareInfoAtual = game.getSquareInfo(-1,0);
        String squareInfoEsperado = null;
        Assertions.assertEquals(squareInfoEsperado,squareInfoAtual);
    }

    @Test
    public void testGameIsOver() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo2.txt"));
        boolean primeiroMove = game.move(5,3,4,3);
        Assertions.assertTrue(primeiroMove,"O zombie move-se primeiro (Turno do Zombie)");

        boolean segundoMove = game.move(3,4,4,4);
        Assertions.assertTrue(segundoMove,"Deve ser o humano a mover-se (Turno do humano");

        boolean terceiroMove = game.move(4,3,4,4);
        Assertions.assertTrue(terceiroMove,"Deve ser o zombie a mover-se (Turno do zombie");

        boolean gameOver = game.gameIsOver();
        Assertions.assertTrue(gameOver, "O jogo devia ter terminado todos os humanos foram transformados em zombie");
    }

    @Test
    public void testGetIdsSafeHaven() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        boolean primeiroMove = game.move(5,3,4,3);
        Assertions.assertTrue(primeiroMove,"O zombie move-se primeiro (Turno do Zombie)");

        boolean segundoMove = game.move(5,1,6,0);
        Assertions.assertTrue(segundoMove, "Deve ser o humano a mover-se (Turno Humano)");

        String listaAtual = String.valueOf(game.getIdsInSafeHaven());
        String listaEsparado = "[8]";
        Assertions.assertEquals(listaEsparado,listaAtual, "Deveria ser o id 8");
    }

    @Test
    public void testGetCreatureInfo() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String resultadoAtual = Arrays.toString(game.getCreatureInfo(5));
        String resultadoEsperado = "[5, Criança, Zombie, Babe, 1, 1, null]";

        Assertions.assertEquals(resultadoEsperado,resultadoAtual,
                "Deveria ter sido [5, Criança, Zombie, Babe, 1, 1, null] e foi " + resultadoAtual);

    }

    @Test
    public void testGetEquipmentInfo() throws InvalidFileException {

        GameManager game = new GameManager();
        game.loadGame(new File("test-files", "Jogo.txt"));
        String resultadoAtual = Arrays.toString(game.getEquipmentInfo(-3));
        String resultadoEsperado = "[-3, 2, 2, 1, null]";

        Assertions.assertEquals(resultadoEsperado,resultadoAtual,
                "Deveria ter sido [-3, 2, 2, 1, null] e foi " + resultadoAtual);

    }

}

