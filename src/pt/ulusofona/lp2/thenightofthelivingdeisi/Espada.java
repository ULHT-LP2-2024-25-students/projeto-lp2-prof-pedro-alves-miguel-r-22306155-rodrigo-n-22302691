package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Espada extends Equipment {

    public Espada(int id, int tipo, int posicaoX, int posicaoY){
        super(id, tipo, posicaoX, posicaoY);
    }

    // To String
    @Override
    public String toString() {
        return id + " | Espada samurai @ " + coordenadas();
    }

    @Override
    public String info() {

        return "E:" + id;

    }

}
