package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Board {

    String[][] tabuleiro;
    Creature creature;
    Equipment equipment;

    public Board(String[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
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
