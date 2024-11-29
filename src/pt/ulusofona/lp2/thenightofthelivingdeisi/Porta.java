package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Porta extends ItemTabuleiro{

    // Atributos
    int posicaoX;
    int posicaoY;




    // Construtores
    public Porta(int posicaoX, int posicaoY) {

        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;

    }




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
