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
    public boolean move(int xO, int yO, int xD, int yD, boolean day){

        // Calcula a distancia
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);


        // Verefica se pode andar
        double distancia = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);
        return distancia <= 1;

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

        if(alvo.isZombie()){

            if(!equipment.tipoArma()){

                if(equipment.isEspada()){

                    // Mata o zombie e remove do tabuleiro
                    board.removeItem(alvo.getX(), alvo.getY());
                    board.removeItem(getX(),getY());
                    board.setItem(alvo.getX(),alvo.getY(),this);
                    return true;
                }

                if (equipment.isPistola()) {

                    // Mata o zombie e remove do tabuleiro
                    board.removeItem(alvo.getX(), alvo.getY());
                    board.removeItem(getX(),getY());
                    board.setItem(alvo.getX(),alvo.getY(),this);
                    // Decrementa o numero de balas se existir municao
                    Pistola pistola = (Pistola) equipment;
                    return pistola.decrementaBalas();
                }
            }

            return false;
        }

        return false;
    }

    public boolean atacarComoZombie(Creature alvo){

        if(alvo.isZombie()){
            return false;
        }

        if(alvo.isHumano()){

            alvo.transformado = true;
            alvo.tipo = 10;
            alvo.equipment = null;
            return true;
        }

        return false;
    }

    // Criatura Defende
    @Override
    public boolean defender(Creature creature){
        return false;
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

        // Se nao tiver transformado
        if (!transformado) {
            return id + " | Adulto | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas() + textoEquipamento;
        }

        // Se tiver transformado
        return id + " | Adulto | Zombie (Transformado) | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas();
    }

}
