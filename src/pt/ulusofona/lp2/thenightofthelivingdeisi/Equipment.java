package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;

public abstract class Equipment extends ItemTabuleiro {

    // Atributos
    protected int id;
    protected int tipo;
    protected int posicaoX;
    protected int posicaoY;
    protected ImageIcon png;




    // Construtores
    public Equipment(int id, int tipo, int posicaoX, int posicaoY, ImageIcon png) {
        this.id = id;
        this.tipo = tipo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.png = png;
    }

    public Equipment(int id, int tipo, int posicaoX, int posicaoY) {
        this.id = id;
        this.tipo = tipo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }




    // Metodos
    // Coordenadas do equipamento
    String coordenadas (){
        return "(" + posicaoX + ", " + posicaoY + ")";
    }




    // Gets
    int getTipo(){
        return this.tipo;
    }

    int getId(){
        return this.id;
    }

    int getX(){
        return this.posicaoX;
    }

    int getY(){
        return this.posicaoY;
    }




    // Sets
    void setX(int x){

        this.posicaoX = x;

    }


    void setY(int y){

        this.posicaoY = y;

    }




    // To String
    abstract public String toString();

    abstract public String info();

    abstract boolean tipoArma();

}
