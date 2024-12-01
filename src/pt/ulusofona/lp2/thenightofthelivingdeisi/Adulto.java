package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class Adulto extends Creature {

    // Construtores
    public Adulto(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Adulto";
    }




    // Override
    public boolean move(int xO, int yO, int xD, int yD, Equipment equipment, boolean day){

        // Calcula a distancia
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);


        // Verefica se pode andar
        double distanciaDiagonal = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);

        // Verefica se anda em xadres
        if(distanciaDiagonal <= 4){

            return true;

        }
        if(distanciaDiagonal > 4 && distanciaY <= 8){

            return distanciaX == distanciaY;

        }

        return false;

    }


    @Override
    public boolean apanharEquipamento(Equipment equipamento){

        return true;

    }

    @Override
    public boolean podeIrParaSafeHaven(int x, int y, ArrayList<Porta> portas) {

        // Se tiver o tipo correto entra no safeHaven
        if (tipo == 20) {

            for (Porta porta : portas) {

                if (porta.getX() == x && porta.getY() == y) {
                    return true;

                }
            }
        }

        return false;
    }


    @Override
    public String toString() {

        return id + " | Adulto | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas() + textoEquipamento;

    }


    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }

}
