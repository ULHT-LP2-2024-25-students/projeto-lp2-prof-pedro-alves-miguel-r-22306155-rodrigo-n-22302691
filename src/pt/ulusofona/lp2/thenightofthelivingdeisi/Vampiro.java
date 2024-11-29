package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Vampiro extends Creature{

    public Vampiro(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        super(id, tipo, categoria, nome, posicaoX, posicaoY);
        nomeDaCategoria = "Vampiro";
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
