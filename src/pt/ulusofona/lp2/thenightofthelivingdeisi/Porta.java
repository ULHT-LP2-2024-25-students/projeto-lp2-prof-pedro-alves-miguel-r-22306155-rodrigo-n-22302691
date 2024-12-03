package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Porta extends ItemTabuleiro{

    // Atributos
    private int posicaoX;
    private int posicaoY;




    // Construtores
    public Porta(int posicaoX, int posicaoY) {

        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;

    }




    // Gets
    int getX() { return posicaoX; }

    int getY() { return posicaoY; }

    // Metodos

    @Override
    public String info() {

        return "SH";

    }

    // To String
    @Override
    public String toString() {
        return "";
    }


}
