package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Cao extends Creature{

    // Construtores
    public Cao(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Cão";
    }




    // Override
    @Override
    public String toString() {
        return id + " | Cão | " + nome + " @ " + coordenadas();
    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }

}
