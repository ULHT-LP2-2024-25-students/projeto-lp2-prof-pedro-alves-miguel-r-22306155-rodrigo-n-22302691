package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Espada extends Equipment {

    public Espada(int id, int tipo, int posicaoX, int posicaoY){
        super(id, tipo, posicaoX, posicaoY);
    }





    // Override
    @Override
    public boolean tipoArma(){

        return false;

    }

    @Override
    public boolean usarArma() {
        return true;
    }

    @Override
    public String info() {

        return "E:" + id;

    }


    // To String
    @Override
    public String toString() {
        return id + " | Espada samurai @ " + coordenadas();
    }

}
