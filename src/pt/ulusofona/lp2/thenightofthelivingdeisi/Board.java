package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.HashMap;

public class Board {

    String[][] tabuleiro;
    Equipment equipment;

    public Board(String[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    void setItem(int x, int y, String item) {
        tabuleiro[y][x] = item;
    }

    boolean squareVazio(int x, int y, Equipment equipment) {

        if(equipment != null && tabuleiro[y][x].equals(equipment.toString())){
            tabuleiro[y][x] = null;
            return true;
        }

        return tabuleiro[y][x] == null;
    }

    void adicionaCreature(Creature creature) {
        if (creature != null) {
            setItem(creature.getX(), creature.getY(), creature.toString());
        }
    }

    void adicionaEquipment(Equipment equipment) {
        if (equipment != null) {
            setItem(equipment.getX(), equipment.getY(), equipment.toString());
        }
    }

    void removeItem(int x, int y){
        tabuleiro[y][x] = null;
    }
}
