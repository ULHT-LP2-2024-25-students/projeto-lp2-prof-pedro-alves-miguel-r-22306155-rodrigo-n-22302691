package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.print.DocFlavor;
import javax.swing.*;
import java.util.HashMap;

public class Creature {

    int id;
    int tipo;
    String nome;
    int posicaoX;
    int posicaoY;
    ImageIcon png;
    int equipCount;
    Equipment equipment;

    //Construtores
    public Creature(int id, int tipo, String nome, int posicaoX, int posicaoY, ImageIcon png) {
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

    public Creature(int id, Equipment equipment) {
        this.id = id;
        this.equipment = equipment;
    }

    //Metodos
    //O nome do tipo
    String tipoCriatura(int tipo){
        if(tipo == 1){
            return "Humano";
        }
        return "Zombie";
    }

    String tipoCriaturaChar(int tipo){

        if(tipo == 1){
            return "H";
        }
        return "Z";
    }

    //O equipamento do tipo se tem ou destruiu
    String tipoEquipamento(int tipo){
        if(tipo == 1){
            return "+" + equipCount;
        }
        return  "-" + equipCount;
    }

    //Coordenadas da criatura
    String coordenadas(){
        return "(" + posicaoX + ", " + posicaoY + ")";
    }

    //gets e setters
    //Obtem todos dados
    String getNome(){
        return this.nome;
    }

    //Obter equipamento
    Equipment getEquipment(){
        return this.equipment;
    }

    // Atualiza posicao da criatura
    void atualizaPosicao(int posicaoX, int posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    void adicionaEquipamento(Equipment equipment){
        this.equipment = equipment;
    }

    void setEquipCount(){
        this.equipCount++;
    }

    int getX(){
        return this.posicaoX;
    }

    int getY(){
        return this.posicaoY;
    }

    int getTipo(){
        return this.tipo;
    }

    int getId(){
        return this.id;
    }

    //To String
    @Override
    public String toString() {
        return id + " | " + tipoCriatura(tipo) + " | " + nome + " | " + tipoEquipamento(tipo) + " @ " + coordenadas();
    }
}