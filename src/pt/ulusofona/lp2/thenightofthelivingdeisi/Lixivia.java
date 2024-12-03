package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Lixivia extends Equipment{

    // Atributos
    private float litros;




    // Construtores
    public Lixivia(int id, int tipo, int posicaoX, int posicaoY){
        super(id, tipo, posicaoX, posicaoY);
        litros = 1.0f;
    }


    // Verifica se a arma é defensiva
    @Override
    boolean tipoArma(){ return true; }


    @Override
    public String info() {

        return "E:" + id;

    }


    // To String
    @Override
    public String toString() {
        return id + " | Lixívia @ " + coordenadas() + " | " + litros + " litros";
    }

}
