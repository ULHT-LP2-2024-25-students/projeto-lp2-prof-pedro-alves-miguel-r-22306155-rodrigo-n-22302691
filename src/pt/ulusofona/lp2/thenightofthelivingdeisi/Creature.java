package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Creature {

    int id;
    int tipo;
    String nome;
    int posicaoX;
    int posicaoY;
    String png;
    int equipCount;
    Equipment equipment;

    public Creature(int id, int tipo, String nome, int posicaoX, int posicaoY, String png) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.png = png;
    }

    public Creature(int id, int tipo, String nome, int posicaoX, int posicaoY) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    @Override
    public String toString() {

        String valor = "";
        String tipoDeCreatura = "";

        if(tipo == 1){
            valor = "+" + equipCount;
            tipoDeCreatura = "Humano";
        } else {
            valor = "-" + equipCount;
            tipoDeCreatura = "Zombie";
        }

        return id + " | " + tipoDeCreatura + " | " + nome + " | " + valor + " @ " + "(" + posicaoX + "," + posicaoY + ")";
        }
}