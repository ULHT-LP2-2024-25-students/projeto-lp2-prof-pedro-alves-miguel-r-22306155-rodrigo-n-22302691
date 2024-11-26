package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Cao extends Creature{

    public Cao(int id, int tipo, String nome, int posicaoX, int posicaoY) {
        super(id,tipo,nome,posicaoX,posicaoY);
    }

    @Override
    public String toString() {
        return id + " | Cão | " + nome + " @ " + coordenadas();
    }
}
