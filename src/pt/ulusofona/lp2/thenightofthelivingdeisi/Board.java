package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.HashMap;

public class Board {

    // Atributos
    private ItemTabuleiro[][] tabuleiro;




    // Construtores
    public Board(ItemTabuleiro[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }




    // Metodos
    boolean squareVazio(int x, int y, Equipment equipment) {

        return tabuleiro[y][x] == null || tabuleiro[y][x].info().split(":")[0].equals("E");

    }




    // Gets
    ItemTabuleiro[][] getTabuleiro(){ return tabuleiro; }




    // Adicionar/Remover
    void adicionaCreature(Creature creature) {

        if (creature != null) {

            setItem(creature.getX(), creature.getY(), creature);

        }

    }

    void adicionaEquipment(Equipment equipment) {

        if (equipment != null) {

            setItem(equipment.getX(), equipment.getY(), equipment);

        }

    }

    void setItem(int x, int y, ItemTabuleiro item) {

        tabuleiro[y][x] = item;

    }

    void removeItem(int x, int y){

        tabuleiro[y][x] = null;

    }
}
