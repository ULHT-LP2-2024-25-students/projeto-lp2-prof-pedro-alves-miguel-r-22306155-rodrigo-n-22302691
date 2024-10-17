package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Equipment {

    int id;
    int tipo;
    int posicaoX;
    int posicaoY;
    String png;

    public Equipment(int id, int tipo, int posicaoX, int posicaoY, String png) {
        this.id = id;
        this.tipo = tipo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.png = png;
    }

    public Equipment(int id, int tipo, int posicaoX, int posicaoY) {
        this.id = id;
        this.tipo = tipo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    @Override
    public String toString() {

        String equipamento = "";

        equipamento = switch (tipo){
            case 0 -> "Escudo de madeira";
            case 1 -> "Espada samurai";
            default -> "";
        };

        return id + " | " + equipamento + " @ " + "(" + posicaoX + "," + posicaoY + ")";
    }

    int getId(){
        return this.id;
    }
}
