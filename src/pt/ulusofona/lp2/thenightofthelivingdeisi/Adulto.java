package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class Adulto extends Creature {

    // Construtores
    public Adulto(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Adulto";
    }


    // Override
    @Override
    public boolean move(int xO, int yO, int xD, int yD, boolean day) {

        // Calcula a distancia
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);


        // Verefica se pode andar
        double distanciaDiagonal = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);

        // Verefica se anda em xadres
        if (distanciaDiagonal <= 4) {

            return true;

        }
        if (distanciaDiagonal > 4 && distanciaY <= 8) {

            return distanciaX == distanciaY;

        }

        return false;

    }


    // Criatura Ataca
    @Override
    public boolean atacar(Creature alvo, Board board) {

        if (this.isHumano() && equipment != null) {
            return atacarComoHumano(alvo, board);
        }

        if (this.isZombie()) {
            return atacarComoZombie(alvo);
        }

        return false;
    }

    public boolean atacarComoHumano(Creature alvo, Board board){

        if(alvo.isHumano()){
            return false;
        }

        if(alvo.isZombie()) {

            if (equipment.isEspada() && equipment.usarArma()) {

                // Mata o zombie e remove do tabuleiro
                matarZombie(alvo, board);
                // Ataca com a espada
                return equipment.usarArma();
            }

            if (equipment.isPistola()) {

                if (equipment.usarArma()) {

                    // Mata o zombie e remove do tabuleiro
                    matarZombie(alvo, board);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean atacarComoZombie(Creature alvo){

        if(alvo.isZombie()){
            return false;
        }

        if(alvo.isCao()){
            return false;
        }

        if(alvo.isHumano() && equipment != null){

            // Defense com qualquer tipo de equipamento
            if(equipment.usarArma()){
                return true;
            }

            // Se tiver com as pistola ou a lixivia, e nao tiver balas ou litros é tranformado em zombie
            alvo.transformarEmZombie();
            return true;
        }

        alvo.transformarEmZombie();
        return true;
    }



    // Criatura Defende
    @Override
    public boolean defender(Creature creature) {
        return false;
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

        // Se nao tiver transformado
        if (!transformado) {
            return id + " | Adulto | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas() + textoEquipamento;
        }

        // Se tiver transformado
        return id + " | Adulto | Zombie (Transformado) | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas();
    }
}
