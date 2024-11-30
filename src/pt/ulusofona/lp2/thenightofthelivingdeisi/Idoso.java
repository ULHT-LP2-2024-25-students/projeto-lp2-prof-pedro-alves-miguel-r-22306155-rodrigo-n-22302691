package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Idoso extends Creature {

    // Construtores
    public Idoso(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Idoso";
    }


    public boolean move(int xO, int yO, int xD, int yD, Equipment equipment){

        // Só se pode movimentar na diagonal
        return Math.abs(xD - xO) == 1 && Math.abs(yD - yO) == 1;
    }

    // Override
    @Override
    public String toString() {

        if(equipment == null){

            return id + " | Idoso | " + tipoCriatura(tipo) + " | " + nome + " | "
                    + tipoEquipamento(tipo) + " @ " + coordenadas();

        }

        return id + " | Idoso | " + tipoCriatura(tipo) + " | " + nome + " | "
                + tipoEquipamento(tipo) + " @ " + coordenadas() + " | " + equipment;

    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }

}
