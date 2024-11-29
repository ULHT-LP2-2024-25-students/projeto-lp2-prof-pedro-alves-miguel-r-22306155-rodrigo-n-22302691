package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Adulto extends Creature {

    // Construtores
    public Adulto(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Adulto";
    }




    // Override
    @Override
    public String toString() {
        return id + " | Adulto | " + nomeDaCategoria + " | " + nome + " | "
                + tipoEquipamento(tipo) + " @ " + coordenadas();
    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }
}
