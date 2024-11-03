package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.print.DocFlavor;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Creature {

    int id;
    int tipo;
    String nome;
    int posicaoX;
    int posicaoY;
    ImageIcon png;
    int equipCount;
    HashMap<Integer, ArrayList<Equipment>> idPorEquipamento = new HashMap<>();

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

    // Atualiza posicao da criatura
    void atualizaPosicao(int posicaoX, int posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    //Adiciona o equipamento no hashMap
    void adicionaEquipamento(Equipment equipment, HashMap<Integer,Equipment> equipments){

        if(idPorEquipamento.get(equipment.tipo) == null){

            idPorEquipamento.put(equipment.tipo, new ArrayList<>());
        }

        idPorEquipamento.get(equipment.tipo).add(equipment);
        equipCount++;
        equipments.remove(equipment.getId());
    }

    //Destroi o equipamento
    void destroiEquipamento(Equipment equipment, HashMap<Integer,Equipment> equipments){
        equipCount++;
        equipments.remove(equipment.getId());
    }

    //Verefica se tem o equipamento
    boolean temEquipamento (int equipmentTypeId){
        if (idPorEquipamento.get(equipmentTypeId) == null || idPorEquipamento.get(equipmentTypeId).isEmpty()){
            return false;
        }
        return true;
    }


    //gets e setters
    //Obtem todos dados
    String getNome(){
        return this.nome;
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