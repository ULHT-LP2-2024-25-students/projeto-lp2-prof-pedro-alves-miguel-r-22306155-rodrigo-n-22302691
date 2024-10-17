package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Equipment {

    int id;
    String tipo;
    int posicaoX;
    int posicaoY;
    String png;

    public Equipment(int id, String tipo, int posicaoX, int posicaoY, String png) {
        this.id = id;
        this.tipo = tipo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.png = png;
    }

    @Override
    public String toString() {
        return id + " | " + tipo + " @ " + "(" + posicaoX + "," + posicaoY + ")";
    }

    int getId(){
        return this.id;
    }
}
