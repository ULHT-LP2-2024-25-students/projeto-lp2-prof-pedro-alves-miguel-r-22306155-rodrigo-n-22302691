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
    public String coordenadas (){
        return "(" + posicaoX + ", " + posicaoY + ")";
    }




    // Gets
    public int getTipo(){
        return this.tipo;
    }

    public int getId(){
        return this.id;
    }

    public int getX(){
        return this.posicaoX;
    }

    public int getY(){
        return this.posicaoY;
    }




    // Sets
    public void setX(int x){

        this.posicaoX = x;

    }


    public void setY(int y){

        this.posicaoY = y;

    }


    public String[] getInfo(){

        String[] partes = new String[5];

        //Poe as insformacoes do equipamento na string
        partes[0] = String.valueOf(id);
        partes[1] = String.valueOf(tipo);
        partes[2] = String.valueOf(posicaoX);
        partes[3] = String.valueOf(posicaoY);
        partes[4] = null;

        //Final (;
        return partes;
    }

    public boolean isEscudo(){
        return this.tipo == 0;
    }

    public boolean isEspada(){
        return this.tipo == 1;
    }

    public boolean isPistola(){
        return this.tipo == 2;
    }

    public boolean isLixivia(){
        return this.tipo == 3;
    }



    // To String
    abstract public String toString();

    abstract public String info();

    abstract boolean tipoArma();

    public abstract boolean usarArma();
}
