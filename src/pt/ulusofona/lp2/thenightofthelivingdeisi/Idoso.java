package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;
import java.util.HashMap;

public class Idoso extends Creature {

    // Construtores
    public Idoso(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Idoso";
    }




    // Override
    @Override
    public boolean move(int xO, int yO, int xD, int yD, boolean day, Board board){

        if(!day && tipo == 20){

            return false;

        }

        // Calcula a distancia
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);


        // Verefica se pode andar
        double distanciaDiagonal = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);

        return distanciaDiagonal == 2;
    }

    // Criatura Ataca / Defende
    @Override
    public boolean atacarDefender(Creature alvo, Board board, HashMap<Integer, Creature> creatures) {

        if (this.isHumano() && equipment != null) {
            return atacarComoHumano(alvo, board, creatures);
        }

        if (this.isZombie()) {
            return atacarComoZombie(alvo, board);
        }

        return false;
    }

    public boolean atacarComoHumano(Creature alvo, Board board, HashMap<Integer, Creature> creatures) {

        if (alvo.isHumano()) {
            return false;
        }

        if (alvo.isZombie()) {

            // Se tiver espada ataca
            if (this.equipment.isEspada() && this.equipment.usarArma()) {

                // Mata o zombie e remove do tabuleiro
                matarZombie(alvo, board, creatures);
                this.atualizaPosicao(alvo.getX(), alvo.getY());
                return true;
            }

            if (this.equipment.isPistola() && this.equipment.usarArma()) {

                // Mata o zombie e remove do tabuleiro
                matarZombie(alvo, board, creatures);
                this.atualizaPosicao(alvo.getX(), alvo.getY());
                return true;
            }
        }

        return false;
    }

    public boolean atacarComoZombie(Creature alvo, Board board) {

        if (alvo.isZombie()) {
            return false;
        }

        if (alvo.isCao()) {
            return false;
        }

        if (alvo.isHumano() && alvo.equipment != null) {

            // Defense com qualquer tipo de equipamento
            if (!alvo.equipment.usarArma()) {

                // Se tiver com as pistola ou a lixivia, e nao tiver balas ou litros é tranformado em zombie
                alvo.transformarEmZombie(board);
            }

            return true;
        }

            // É transformado em zombie
        alvo.transformarEmZombie(board);
        return true;
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
                    this.safeHaven = true;
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

        if(equipment != null){

            textoEquipamento = " | " + equipment;

        }

        // Se nao tiver transformado
        return id + " | Idoso | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas() + textoEquipamento;
    }

}
