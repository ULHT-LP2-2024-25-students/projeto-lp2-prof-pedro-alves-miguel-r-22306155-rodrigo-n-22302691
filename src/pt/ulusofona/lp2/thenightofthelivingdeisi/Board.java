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

        if (creature != null && square != null && square.equals(creature.toString())) {

            if (creature.getTipo() == 0) {
                return creature.tipoCriaturaChar(0) + creature.getId();
            }

            if (creature.getTipo() == 1) {
                return creature.tipoCriaturaChar(1) + creature.getId();
            }
        }

        if (equipment != null && square != null && square.equals(equipment.toString())) {
            return "E" + equipment.getId();
        }

        return "";
    }

    void setItem(int x, int y, String item) {
        tabuleiro[y][x] = item;
    }

    public boolean squareVazio(int x, int y) {
        return tabuleiro[y][x] == null;
    }

    public void adicionaCreature(Creature creature) {
        if (creature != null) {
            setItem(creature.getX(), creature.getY(), creature.toString());
        }
    }

    public void adicionaEquipment(Equipment equipment) {
        if (equipment != null) {
            setItem(equipment.getX(), equipment.getY(), equipment.toString());
        }
    }

    void removeItem(int x, int y){
        tabuleiro[y][x] = null;
    }
}
