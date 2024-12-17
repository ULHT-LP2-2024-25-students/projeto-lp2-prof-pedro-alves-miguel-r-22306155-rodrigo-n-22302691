package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;
import java.util.HashMap;

public class Adulto extends Creature {

    // Construtores
    public Adulto(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Adulto";
    }


    // Override
    @Override
    public boolean move(int xO, int yO, int xD, int yD, boolean day, Board board) {

        // Calcula a distância
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);

        // Calcula a distância diagonal
        double distancia = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);

        // Verifica se o movimento é válido
        if (distancia <= 4 || (distancia > 4 && distancia <= 8 && distanciaX == distanciaY)) {


            if (this.equipment != null) {

                this.equipment.atualizaPosicao(xD, yD);
            }

            return true;
        }

        return false;
    }


    // Criatura Ataca
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

        // Verifica se o alvo já é um zombie ou um cão
        if (alvo.isZombie() || alvo.isCao()) {
            return false;
        }

        // Se o alvo é humano com equipamento
        if (alvo.isHumano() && alvo.equipment != null) {

            // Usa o equipamento para se defender mas se nao tiver conseguir denfender é transformado em zombie
            if (!alvo.equipment.usarArma()) {

                alvo.transformarEmZombie(board);

            }

            alvo.defendeu = true;
            return true;
        }

        // Transforma em zombie
        alvo.transformarEmZombie(board);
        return true;
    }


    @Override
    public boolean apanharEquipamento(Equipment equipamento) {

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

        } else {

            textoEquipamento = "";
        }

        return id + " | Adulto | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas() + textoEquipamento;

    }
}
