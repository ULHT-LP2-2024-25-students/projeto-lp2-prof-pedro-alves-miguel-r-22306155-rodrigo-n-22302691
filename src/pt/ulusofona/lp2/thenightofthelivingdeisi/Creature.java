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
    protected boolean transformado;



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

        if(transformado){

            textoEquipamento = "";
            return "Zombie (Transformado)";
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

        textoEquipamento = " | " + equipment;

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


    public int getX(){
        return this.posicaoX;
    }


    public int getY(){
        return this.posicaoY;
    }


    public int getTipo(){
        return this.tipo;
    }


    int getId(){
        return this.id;
    }


    int getCategoria(){ return this.categoria; }


    public String getNomeDaCategoria(){ return this.nomeDaCategoria; }


    public Equipment getEquipment(){

        return equipment;

    }

    public String[] getInfo(){

        String[] partes = new String[7];

        //Poe as insformacoes da criatura em string
        partes[0] = String.valueOf(id); // ID
        partes[1] = nomeDaCategoria; // Categoria
        partes[2] = tipoCriatura(tipo); // Tipo
        partes[3] = nome; // Nome
        partes[4] = String.valueOf(posicaoX); // X
        partes[5] = String.valueOf(posicaoY); // Y
        partes[6] = null;

        //Final (;
        return partes;
    }

    // Se for humano retorna true
    public boolean isHumano(){
        return this.tipo == 20;
    }

    // Se for zombie retorna true
    public boolean isZombie(){
        return this.tipo == 10;
    }

    public boolean isCao(){
        return this.categoria == 3;
    }

    public boolean isIdoso(){
        return this.categoria == 2;
    }

    public void transformarEmZombie(){

        this.tipo = 10;
        this.transformado = true;
        this.equipment = null;
    }

    public void matarZombie(Creature alvo, Board board){

        board.removeItem(alvo.getX(), alvo.getY());
        board.removeItem(getX(), getY());
        board.setItem(alvo.getX(), alvo.getY(), this);
    }


    abstract public boolean move(int xO, int yO, int xD, int yD, boolean day);

    // Defende
    public abstract boolean defender(Creature creature);

    // Ataca
    public abstract boolean atacar(Creature creatureDestino, Board board);

    // Apanha equipamento se for humano
    abstract public boolean apanharEquipamento(Equipment equipamento);

    // Vai para o safeHaven se for Humano
    abstract public boolean podeIrParaSafeHaven(int x, int y, ArrayList<Porta> portas);

    // Abs info
    abstract public String info();

    // Abs ToString
    abstract public String toString();



}