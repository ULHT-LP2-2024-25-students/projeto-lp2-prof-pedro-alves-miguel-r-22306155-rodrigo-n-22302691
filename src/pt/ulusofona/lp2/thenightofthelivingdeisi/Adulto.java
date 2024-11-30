package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Adulto extends Creature {

    // Construtores
    public Adulto(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Adulto";
    }

    public boolean move(int xO, int yO, int xD, int yD, Equipment equipment){

        return (Math.abs(xD - xO) <= 2 && Math.abs(yD - yO) == 0) ||
                (Math.abs(yD - yO) <= 2 && Math.abs(xD - xO) == 0) ||
                (Math.abs(xD - xO) <= 2 && Math.abs(xD - xO) == Math.abs(yD - yO));
    }

    // Override
    @Override
    public String toString() {

        if(equipment == null){

            return id + " | Adulto | " + tipoCriatura(tipo) + " | " + nome + " | "
                    + tipoEquipamento(tipo) + " @ " + coordenadas();

        }

        return id + " | Adulto | " + tipoCriatura(tipo) + " | " + nome + " | "
                + tipoEquipamento(tipo) + " @ " + coordenadas() + " | " + equipment;

    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }
}
