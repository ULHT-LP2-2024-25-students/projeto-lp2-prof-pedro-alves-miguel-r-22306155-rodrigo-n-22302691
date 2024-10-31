package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Board {

    String[][] tabuleiro;
    Creature creature;
    Equipment equipment;

    public Board(String[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    String getSquareInfo(int x, int y) {
        String square = tabuleiro[y][x];

        if (square != null && square.equals(creature.toString())) {

            if (creature.getTipo() == 0) {
                return "Z:" + creature.getId();
            }

            if (creature.getTipo() == 1) {
                return "H:" + creature.getId();
            }
        }

        if (square != null && square.equals(equipment.toString())) {
            return "E:" + equipment.getId();
        }
        return "";
    }

    void adicionaItems(BoardItems item) {

        if (item != null && item.equals(creature)) {
            tabuleiro[creature.getY()][creature.getX()] = creature.toString();
        }

        if (item != null && item.equals(equipment)) {
            tabuleiro[equipment.getY()][equipment.getX()] = equipment.toString();
        }
    }

    public boolean squareVazio(int x, int y) {
        return tabuleiro[y][x] == null;
    }


}
