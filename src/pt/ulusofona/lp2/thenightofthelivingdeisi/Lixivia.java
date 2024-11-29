package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Lixivia extends Equipment{

    // Atributos
    float litros;


    // Construtores
    public Lixivia(int id, int tipo, int posicaoX, int posicaoY){
        super(id, tipo, posicaoX, posicaoY);
        litros = 1.0f;
    }

    // To String
    @Override
    public String toString() {
        return id + " | Lixivia @ " + coordenadas() + " | " + litros + " litros";
    }

    @Override
    public String info() {

        return "E:" + id;

    }

}
