package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Creature extends ItemTabuleiro {

    // Atributos
    protected int id;
    protected int tipo;
    protected String nomeDaCategoria;
    protected int categoria;
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected ImageIcon png;
    protected int equipCount;
    protected Equipment equipment;
    protected HashMap<Integer, ArrayList<Equipment>> idPorEquipamento = new HashMap<>();
    protected String textoEquipamento = "";




    // Construtores
    public Creature(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY, ImageIcon png) {
        this.id = id;
        this.tipo = tipo;
        this.categoria = categoria;
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.png = png;
    }

    public Creature(int id, int tipo, int categoria, String nome, int posicaoX, int posicaoY) {
        this.id = id;
        this.tipo = tipo;
        this.categoria = categoria;
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }




    // Metodos
    // Tipo de criatura
    String tipoCriatura(int tipo){

        if(tipo == 20){
            return "Humano";
        }
        return "Zombie";
    }


    // Tipo de criatura em char
    String tipoCriaturaChar(int tipo){

        if(tipo == 20){
            return "H";
        }
        return "Z";

    }


    //O equipamento do tipo se tem ou destruiu
    String tipoEquipamento(int tipo){
        if(tipo == 20){
            return "+" + equipCount;
        }
        return  "-" + equipCount;
    }




    // Posiçao da criatura
    // Coordenadas da criatura
    String coordenadas(){
        return "(" + posicaoX + ", " + posicaoY + ")";
    }


    // Atualiza posicao da criatura
    void atualizaPosicao(int posicaoX, int posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }


    // Ataca





    // Equipamento
    // Adiciona o equipamento no hashMap
    void adicionaEquipamento(Equipment equipment, HashMap<Integer,Equipment> equipments){

        // Atualiza o historico de equipamentos
        if(idPorEquipamento.get(equipment.tipo) == null){

            idPorEquipamento.put(equipment.tipo, new ArrayList<>());
        }
        idPorEquipamento.get(equipment.tipo).add(equipment);


        // Equipa o equipamento
        this.equipment = equipment;
        equipCount++;
        equipments.remove(equipment.getId());

        textoEquipamento = " | " + equipment.toString();

    }


    // Destroi o equipamento
    void destroiEquipamento(Equipment equipment, HashMap<Integer,Equipment> equipments){

        equipCount++;
        equipments.remove(equipment.getId());

    }


    // Verefica se tem o equipamento
    boolean temEquipamento (int equipmentTypeId){

        if(equipment == null){
            return false;
        }

        return equipment.getTipo() == equipmentTypeId;

    }





    // Gets e setters
    // Obtem todos dados
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


    int getCategoria(){ return this.categoria; }


    String getNomeDaCategoria(){ return this.nomeDaCategoria; }


    Equipment getEquipment(){

        return equipment;

    }




    // Abs
    abstract public String toString();


    abstract public boolean move(int xO, int yO, int xD, int yD, Equipment equipment, boolean day);


    abstract public boolean apanharEquipamento(Equipment equipamento);


    abstract public String info();


}