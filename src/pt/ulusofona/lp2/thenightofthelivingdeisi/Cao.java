package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Cao extends Creature{

    // Construtores
    public Cao(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Cão";
    }



    @Override
    public boolean move(int xO, int yO, int xD, int yD, Equipment equipment) {

        // Cao so pode andar 2 casas na vertical ou na horizontal
        boolean movimentoHorizontal = (xO == xD) && (Math.abs(yD - yO) <= 2);
        boolean movimentoVertical = (yO == yD) && (Math.abs(xD - xO) <= 2);

        // Caso nao haja equipamento pode se mover
        if(equipment == null){

            return movimentoHorizontal || movimentoVertical;
        }
        // Caso haja equipamento na posicao de destino é invalido
        return false;
    }

    // Override
    @Override
    public String toString() {
        return id + " | Cão | " + nome + " @ " + coordenadas();
    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }

}
