package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

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
        double distancia = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);
        return distancia <= 1;

    }


    @Override
    public boolean apanharEquipamento(Equipment equipamento){

        return equipamento.tipoArma();

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
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }


    @Override
    public String toString() {

        return id + " | Criança | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas() + textoEquipamento;

    }

}
