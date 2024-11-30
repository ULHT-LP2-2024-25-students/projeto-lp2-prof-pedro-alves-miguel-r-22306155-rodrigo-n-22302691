package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Vampiro extends Creature{

    public Vampiro(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Vampiro";
    }



    public boolean move(int xO, int yO, int xD, int yD, Equipment equipment){

        return Math.abs(xD -xO) <= 1 && Math.abs(yD - yO) <= 1;
    }

    @Override
    public String toString() {
        return id + " | Vampiro | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas();
    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }

}
