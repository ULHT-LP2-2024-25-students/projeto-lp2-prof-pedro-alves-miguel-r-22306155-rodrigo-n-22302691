package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Crianca extends Creature {

    // Construtores
    public Crianca(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Criança";
    }




    public boolean move(int xO, int yO, int xD, int yD, Equipment equipment){

        boolean movimentoHorizontal = (xO == xD) && (Math.abs(yD - yO) == 1);
        boolean movimentoVertical = (yO == yD) && (Math.abs(xD - xO) == 1);
        // Se nao houver equipamento pode mover-se
        if(equipment == null){

            return movimentoHorizontal || movimentoVertical;
        }

        // Só se pode mover caso o equipamento for defensivo
        return (movimentoHorizontal || movimentoVertical) && equipment.tipoArma();
    }

    // Override
    @Override
    public String toString() {

        if(equipment == null){

            return id + " | Criança | " + tipoCriatura(tipo) + " | " + nome + " | "
                    + tipoEquipamento(tipo) + " @ " + coordenadas();

        }

        return id + " | Criança | " + tipoCriatura(tipo) + " | " + nome + " | "
                + tipoEquipamento(tipo) + " @ " + coordenadas() + " | " + equipment;

    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }
}
