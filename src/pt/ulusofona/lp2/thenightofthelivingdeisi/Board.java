package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Board {

    String[][] tabuleiro;
    Creature creature;
    Equipment equipment;

    public Board(String[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    void adicionaItems(BoardItems item) {

        if (item != null && item.equals(creature)) {
            tabuleiro[creature.getY()][creature.getX()] = creature.toString();
        }

        if (item != null && item.equals(equipment)) {
            tabuleiro[equipment.getY()][equipment.getY()] = equipment.toString();
        }
    }
}
