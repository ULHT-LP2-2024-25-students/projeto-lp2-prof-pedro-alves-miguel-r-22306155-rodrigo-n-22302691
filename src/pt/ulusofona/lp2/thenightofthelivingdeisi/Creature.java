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

    //Construtores
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


    //Metodos
    //O nome do tipo
    String tipoCriatura (int tipo){
        if(tipo == 1){
            return  "Humano";
        } else {
            return  "Zombie";
        }
    }

    //O equipamento do tipo se tem ou destruiu
    String tipoEquipamento (int tipo){
        if(tipo == 1){
            return "+" + equipCount;
        } else {
            return  "-" + equipCount;
        }
    }

    //Coordenadas da criatura
    String coordenadas (){
        return "(" + posicaoX + "," + posicaoY + ")";
    }

    //Obtem o id
    int obterId(){
        return this.id;
    }

    @Override
    public String toString() {
        return id + " | " + tipoCriatura(id) + " | " + nome + " | " + tipoEquipamento(id) + " @ " + coordenadas();
    }
}