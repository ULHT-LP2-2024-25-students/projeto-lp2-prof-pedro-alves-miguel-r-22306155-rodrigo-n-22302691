package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Crianca extends Creature {

    // Construtores
    public Crianca(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Criança";
    }




    // Override
    @Override
    public String toString() {
        return id + " | Criança | " + tipoCriatura(tipo) + " | " + nome + " | "
                + tipoEquipamento(tipo) + " @ " + coordenadas();
    }

    @Override
    public String info() {

        return tipoCriaturaChar(tipo) + ":" + id;

    }
}
