package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Pistola extends Equipment{

    // Atributos
    private int municao;




    // Construtores
    public Pistola(int id, int tipo, int posicaoX, int posicaoY){
        super(id, tipo, posicaoX, posicaoY);
        municao = 3;
    }


    // Verifica se a arma é defensiva
    @Override
    boolean tipoArma(){ return false; }

    @Override
    public String info() {

        return "E:" + id;

    }

    // To String
    @Override
    public String toString() {
        return id + " | Pistola Walther PPK @ " + coordenadas() + " | " + municao + " balas";
    }


}
