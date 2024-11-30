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
    int getPosicaoX() { return posicaoX; }

    int getPosicaoY() { return posicaoY; }

    // Metodos

    // Overrides
    @Override
    public String toString() {
        return "";
    }

    @Override
    public String info() {

        return "SH";

    }

}
