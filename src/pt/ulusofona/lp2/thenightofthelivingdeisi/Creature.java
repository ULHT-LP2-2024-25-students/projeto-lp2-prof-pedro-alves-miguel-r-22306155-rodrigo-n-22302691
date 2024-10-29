package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.HashMap;

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
        }
        return  "Zombie";
    }

    //O equipamento do tipo se tem ou destruiu
    String tipoEquipamento (int tipo){
        if(tipo == 1){
            return "+" + equipCount;
        }
        return  "-" + equipCount;
    }

    //Coordenadas da criatura
    String coordenadas (){
        return "(" + posicaoX + ", " + posicaoY + ")";
    }


    //gets e setters
    //Obtem todos dados
    HashMap<String, String> getInfo(){
        //Cria o hashMap
        HashMap<String, String> dados = new HashMap<>();

        //Poe os valores da info no hashMap
        dados.put("id", String.valueOf(id));
        dados.put("tipo", String.valueOf(tipo));
        dados.put("nome" , nome);
        dados.put("posicaoX", String.valueOf(posicaoX));
        dados.put("posicaoY", String.valueOf(posicaoY));
        dados.put("imagem", png);
        dados.put("NumeroEquipamento", String.valueOf(equipCount));

        //Finalizar (;
        return dados;
    }

    //Obter equipamento
    Equipment getEquipment (){
        return equipment;
    }


    //To String
    @Override
    public String toString() {
        return id + " | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas();
    }
}