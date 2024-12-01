package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Idoso extends Creature {

    // Construtores
    public Idoso(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Idoso";
    }




    // Override
    @Override
    public boolean move(int xO, int yO, int xD, int yD, Equipment equipment, boolean day){

        if(!day){

            return false;

        }

        // Calcula a distancia
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);


        // Verefica se pode andar
        double distanciaDiagonal = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);
        return distanciaDiagonal == 2;

    }


    @Override
    public boolean apanharEquipamento(Equipment equipamento){

        return true;

    }


    @Override
    public String toString() {

        return id + " | Idoso | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas() + textoEquipamento;

    }


    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }

}
