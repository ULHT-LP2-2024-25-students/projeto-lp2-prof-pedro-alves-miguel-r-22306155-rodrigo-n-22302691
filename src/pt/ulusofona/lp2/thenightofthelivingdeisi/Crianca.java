package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Crianca extends Creature {

    // Construtores
    public Crianca(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Criança";
    }




    // Override
    @Override
    public boolean move(int xO, int yO, int xD, int yD, Equipment equipment, boolean day){

        // Calcula a distancia
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);


        // Verefica se pode andar
        double distanciaDiagonal = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);
        return distanciaDiagonal <= 1;

    }


    @Override
    public boolean apanharEquipamento(Equipment equipamento){

        return equipamento.tipoArma();

    }


    @Override
    public String toString() {

        return id + " | Criança | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas() + textoEquipamento;

    }


    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }
}
