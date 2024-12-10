package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;
import java.util.HashMap;

public class Cao extends Creature {

    // Construtores
    public Cao(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Cão";
    }




    // Override
    @Override
    public boolean move(int xO, int yO, int xD, int yD, boolean day) {

        // Calcula a distancia
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);


        // Verefica se tentou andar na diagonal
        if(distanciaX != 0 && distanciaY != 0){

            return false;

        }


        // Verefica se pode andar
        double distancia = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);
        return distancia <= 4;

    }


    // Criatura Ataca
    @Override
    public boolean atacarDefender(Creature creature, Board board, HashMap<Integer, Creature> creatures){
        return false;
    }



    @Override
    public boolean apanharEquipamento(Equipment equipamento){

        return false;

    }

    @Override
    public boolean podeIrParaSafeHaven(int x, int y, ArrayList<Porta> portas) {

        for (Porta porta : portas) {

            if (porta.getX() == x && porta.getY() == y) {
                return true;

            }
        }
        return false;
    }


    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }


    @Override
    public String toString() {

        return id + " | Cão | " + nome + " @ " + coordenadas();

    }

}
