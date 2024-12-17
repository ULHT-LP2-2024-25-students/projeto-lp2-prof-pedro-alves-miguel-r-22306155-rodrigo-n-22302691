package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Lixivia extends Equipment{

    // Atributos
    private float litros;




    // Construtores
    public Lixivia(int id, int tipo, int posicaoX, int posicaoY){
        super(id, tipo, posicaoX, posicaoY);
        this.litros = 1.0f;
    }

    // Verifica se a arma é defensiva
    @Override
    public boolean tipoArma(){ return true; }

    @Override
    public boolean usarArma() {

        if (litros > 0) {

            litros -= 0.3f;
            litros = Math.round(litros * 10) / 10.0f;

            if (litros < 0) {
                litros = 0;
            }

            return true;
        }

        return false;
    }



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
