package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class Vampiro extends Creature{

    public Vampiro(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Vampiro";
    }




    // Override
    @Override
    public boolean move(int xO, int yO, int xD, int yD, boolean day){

        if(day){

            return false;

        }

        // Calcula a distancia
        int distanciaX = Math.abs(xD - xO);
        int distanciaY = Math.abs(yD - yO);


        // Verefica se pode andar
        double distanciaDiagonal = Math.pow(distanciaX, 2.0) + Math.pow(distanciaY, 2.0);
        return distanciaDiagonal <= 2;

    }


    // Criatura Ataca
    @Override
    public boolean atacar(Creature alvo, Board board){

        if(alvo.isZombie()){
            return false;
        }

        if(alvo.isHumano()){

            alvo.transformado = true;
            alvo.tipo = 10;
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

        return true;

    }


    @Override
    public boolean podeIrParaSafeHaven(int x, int y, ArrayList<Porta> portas) {

        // Nao pode entrar no safeHaven
        return false;
    }


    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }


    @Override
    public String toString() {

        return id + " | Vampiro | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas();

    }

}
