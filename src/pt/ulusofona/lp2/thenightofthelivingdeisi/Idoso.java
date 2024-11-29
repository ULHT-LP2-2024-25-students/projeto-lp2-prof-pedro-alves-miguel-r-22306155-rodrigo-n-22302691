package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Idoso extends Creature {

    // Construtores
    public Idoso(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Idoso";
    }




    // Override
    @Override
    public String toString() {

        return id + " | Idoso | " + nomeDaCategoria + " | " + nome + " | "
                + tipoEquipamento(tipo) + " @ " + coordenadas();

    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }

}
