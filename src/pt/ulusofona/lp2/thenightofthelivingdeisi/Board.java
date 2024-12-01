package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Board {

    // Atributos
    private ItemTabuleiro[][] tabuleiro;




    // Construtores
    public Board(ItemTabuleiro[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }




    // Metodos
    boolean squareVazio(int x, int y) {

        return tabuleiro[y][x] == null || tabuleiro[y][x].info().split(":")[0].equals("E");

    }

    // Verifica se a posicao é valida no tabuleiro
    public boolean positionInBoard(int x, int y){
        return x >= 0 && x < tabuleiro[0].length && y >= 0 && y < tabuleiro.length;
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

    ItemTabuleiro getItem(int x, int y){

        return tabuleiro[y][x];
    }

}
