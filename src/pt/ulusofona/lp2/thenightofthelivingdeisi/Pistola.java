package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Pistola extends Equipment{

    // Atributos
    private int municao;




    // Construtores
    public Pistola(int id, int tipo, int posicaoX, int posicaoY){
        super(id, tipo, posicaoX, posicaoY);
        municao = 3;
    }


    boolean tipoArma(){ return false; }

    // To String
    @Override
    public String toString() {
        return id + " | Pistola Walther PPK @ " + coordenadas() + " | " + municao + " balas";
    }

    @Override
    public String info() {

        return "E:" + id;

    }

}
