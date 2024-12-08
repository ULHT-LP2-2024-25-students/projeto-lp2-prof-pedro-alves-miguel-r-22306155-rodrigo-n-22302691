package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Escudo extends Equipment{

    public Escudo(int id, int tipo, int posicaoX, int posicaoY){
        super(id, tipo, posicaoX, posicaoY);
    }

    // retorna true caso seja um equipamento defensivo, caso contrario retorna false
    @Override
    public boolean tipoArma(){
        return true;
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

        return id + " | Escudo de madeira @ " + coordenadas();

    }

}
