package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.util.HashMap;

public class Equipment {

    int id;
    int tipo;
    int posicaoX;
    int posicaoY;
    ImageIcon png;


    //Construtores
    public Equipment(int id, int tipo, int posicaoX, int posicaoY, ImageIcon png) {
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

    //Metodos
    //Coordenadas do equipamento
    String coordenadas (){
        return "(" + posicaoX + "," + posicaoY + ")";
    }

    //Nome do equipamento
    String equipamentoNome (int tipo) {

        String nome = "";

        if (tipo == 0) {
            nome = "Escudo de madeira";
        }

        if (tipo == 1) {
            nome = "Espada samurai";
        }
        return nome;
    }

    //gets e setters
    //Obtem todos dados
    HashMap<String, String> getInfo(){
        //Cria o hashMap
        HashMap<String, String> dados = new HashMap<>();

        //Poe os valores da info no hashMap
        dados.put("id", String.valueOf(id));
        dados.put("tipo", String.valueOf(tipo));
        dados.put("posicaoX", String.valueOf(posicaoX));
        dados.put("posicaoY", String.valueOf(posicaoY));
        dados.put("imagem", null);

        //Finalizar (;
        return dados;
    }

    int getTipo(){
        return this.tipo;
    }

    int getId(){
        return this.id;
    }

    int getX(){
        return this.posicaoX;
    }

    int getY(){
        return this.posicaoY;
    }

    //To String
    @Override
    public String toString() {
        return id + " | " + equipamentoNome(tipo) + " @ " + coordenadas();
    }
}
