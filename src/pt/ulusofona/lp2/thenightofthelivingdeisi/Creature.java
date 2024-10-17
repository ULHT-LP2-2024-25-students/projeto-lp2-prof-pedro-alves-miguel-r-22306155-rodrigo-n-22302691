package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Creature {

    int id;
    String tipo;
    String nome;
    int posicaoX;
    int posicaoY;
    String png;
    int equipCount;
    Equipment equipment;

    public Creature(int id, String tipo, String nome, int posicaoX, int posicaoY, String png) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.png = png;
    }

    @Override
    public String toString() {

        String valor = "";

        if(tipo.equals("Humano")){
            valor = "-" + equipCount;
        } else {
            valor = "+" + equipCount;
        }

        return id + " | " + tipo + " | " + nome + " | " + valor + " @ " + "(" + posicaoX + "," + posicaoY + ")";
        }
}