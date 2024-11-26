package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Idoso extends Creature {

    public Idoso(int id, int tipo, String nome, int posicaoX, int posicaoY) {
        super(id,tipo,nome,posicaoX,posicaoY);
    }

    @Override
    public String toString() {
        return id + " | Criança | " + tipoCriatura(tipo) + " | " + nome + " | "
                + tipoEquipamento(tipo) + " @ " + coordenadas();
    }
}
