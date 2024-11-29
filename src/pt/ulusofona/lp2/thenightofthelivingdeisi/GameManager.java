package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GameManager {

    // Informaçoes do ficheiro
    // Tamanho do tabuleiro
    private int[] worldSize = new int[2];

    // ID da criatura inicial
    private int initialID;

    // Dados de cada criatura do tabuleiro
    private HashMap<Integer,Creature> creatures = new HashMap<>();
    private int nrCriaturas;

    // Dados de cada equipamento do tabuleiro
    private HashMap<Integer,Equipment> equipments = new HashMap<>();
    private int nrEquipamentos;

    // Numero de jogadas
    private int nrJogadas;

    // Dados de cada porta do tabuleiro
    private ArrayList<Porta> portas = new ArrayList<Porta>();
    private int nrPortas;

    // tabuleiro
    private Board board;

    //Informaçoes do jogo
    private int currentID;




    // Metodos
    // Vai ler o ficheiro
    public void parseGame(File game) throws InvalidFileException {

        // Cada vez que le um novo ficheiro reinicia o numero de jogadas
        nrJogadas = 0;

        // Vai reiniciar o numero de linhas lidas
        int numeroDaLinha = 0;


        // Vai criar o scanner
        Scanner scanner = GameReader.criarScanner(game, numeroDaLinha);


        // Lê a primeira linha (tamanho do tabuleiro)
        numeroDaLinha++;
        worldSize = GameReader.lerTamanhoTabuleiro(scanner, numeroDaLinha);


        // Lê a segunda linha (ID inicial)
        numeroDaLinha++;
        initialID = GameReader.lerIdInicial(scanner, numeroDaLinha);
        currentID = initialID;


        // Lê o número de criaturas
        numeroDaLinha++;
        nrCriaturas = GameReader.lerNumeroDeCriaturas(scanner, numeroDaLinha);


        // Lê as informaçoes das criaturas e as cria
        creatures = GameReader.lerCriaturas(scanner, numeroDaLinha, nrCriaturas, worldSize);
        numeroDaLinha += nrCriaturas;


        // Lê o número de equipamentos
        numeroDaLinha++;
        nrEquipamentos = GameReader.lerNumeroDeEquipamento(scanner, numeroDaLinha);


        // Se nao houver equipamentos vai começar o jogo
        if(nrEquipamentos == 0){
            return;
        }


        // Loop para ler os equipamentos
        equipments = GameReader.lerEquipamentos(scanner, numeroDaLinha, nrEquipamentos, worldSize);
        numeroDaLinha += nrEquipamentos;


        // Le numero de portas para o safe Haven
        nrPortas = GameReader.lerNumeroDePortas(scanner, numeroDaLinha);
        numeroDaLinha++;


        // Vai ler o numero de linhas aonde tem as portas
        portas = GameReader.lerPortas(scanner, numeroDaLinha, nrPortas, worldSize);
        numeroDaLinha += nrPortas;

    }


    public void loadGame(File file) throws InvalidFileException{

        try {

            parseGame(file);

        }
        catch (InvalidFileException e) {

            throw e;

        }


        // Cria o tabuleiro na memória
        board = new Board(new ItemTabuleiro[worldSize[0]][worldSize[1]]);


        // Adiciona as criaturas no tabuleiro
        for (Creature creature : creatures.values()) {
            board.adicionaCreature(creature);
        }

        // Adiciona portas
        for (Porta porta: portas){
            board.setItem(porta.getPosicaoX(), porta.getPosicaoY(), porta);
        }


        // Itens a serem adicionados aos humanos
        ArrayList<Equipment> itemsAdicionar = new ArrayList<>();
        ArrayList<Integer> criaturaAdicionar = new ArrayList<>();


        // Itens a serem removidos pelos zumbis
        ArrayList<Equipment> itemsRemover = new ArrayList<>();
        ArrayList<Integer> criaturaRemover = new ArrayList<>();


        // Vai ver se alguma criatura foi iniciada em cima de um equipamento
        for (Equipment equipment : equipments.values()) {

            for (Creature creature : creatures.values()) {

                // Verifica se a criatura remove ou pega o equipamento ou se o equipamento é colocado no mapa
                if (creature.getX() == equipment.getX() && creature.getY() == equipment.getY()) {

                    if (creature.getTipo() == 20) {

                        itemsAdicionar.add(equipment);
                        criaturaAdicionar.add(creature.id);

                    }
                    else {

                        itemsRemover.add(equipment);
                        criaturaRemover.add(creature.id);

                    }
                }
                else {

                    board.adicionaEquipment(equipment);

                }

            }

        }


        // Adiciona ou remove os equipamentos
        for (int i = 0; i < itemsAdicionar.size(); i++) {
            creatures.get(criaturaAdicionar.get(i)).adicionaEquipamento(itemsAdicionar.get(i), equipments);
        }

        for (int i = 0; i < itemsRemover.size(); i++) {
            creatures.get(criaturaRemover.get(i)).destroiEquipamento(itemsRemover.get(i), equipments);
        }

    }




    // Gets
    // Get WorldSize
    public int[] getWorldSize(){
        return worldSize;
    }


    // Get InitialTeamId
    public int getInitialTeamId(){
        return  initialID;
    }


    // Get getCurrentTeamId
    public int getCurrentTeamId(){
        return currentID;
    }


    // Devolve uma string com a informaçao do item na posiçao dada
    public String getSquareInfo(int x, int y) {

        // Pega o item do tabuleiro
        ItemTabuleiro tile = board.getTabuleiro()[y][x];

        // Verefica se o tile esta empty
        if (tile == null){

            return "";

        }

        // Faz return
        return tile.info();

    }


    // Devolve info da criatura em um array
    public String[] getCreatureInfo(int id){

        //Verefica se existe a criatura
        if(creatures.get(id) == null){
            return null;
        }

        //Guarda a criatura
        Creature criatura = creatures.get(id);

        //Cria a String
        String[] partes = new String[7];

        //Poe as insformacoes da criatura na string
        partes[0] = String.valueOf(id); // ID
        partes[1] = criatura.getNomeDaCategoria(); // Categoria
        partes[2] = String.valueOf(criatura.tipoCriatura(criatura.getTipo())); // Tipo
        partes[3] = String.valueOf(criatura.getNome()); // Nome
        partes[4] = String.valueOf(criatura.getX()); // X
        partes[5] = String.valueOf(criatura.getY()); // Y
        partes[6] = null;

        //Final (;
        return partes;

    }


    // Devolve info da criatura em uma string
    public String getCreatureInfoAsString(int id){
        return creatures.get(id).toString();
    }


    // Devolve info do equipamento em um array
    public String[] getEquipmentInfo(int id){
        //Verefica se existe o item
        if(equipments.get(id) == null){
            return null;
        }

        //Guarda a criatura
        Equipment equipamento = equipments.get(id);

        //Cria a String
        String[] partes = new String[5];

        //Poe as insformacoes do equipamento na string
        partes[0] = String.valueOf(id);
        partes[1] = String.valueOf(equipamento.getTipo());
        partes[2] = String.valueOf(equipamento.getX());
        partes[3] = String.valueOf(equipamento.getY());
        partes[4] = null;

        //Final (;
        return partes;
    }


    // Devolve info do equipamento em uma string
    public String getEquipmentInfoAsString(int id) {
        Equipment equipamento = equipments.get(id);

        if (equipamento == null) {
            return null;
        }

        return equipamento.toString();
    }




    // Jogo
    // Em cada 2 jogadas troca o dia
    public boolean isDay() {
        // Verifica se esta de dia ou de noite
        return (nrJogadas / 2) % 2 == 0;
    }

    // Verefica se uma criatura tem um certo equipamento
    public boolean hasEquipment(int creatureId, int equipmentTypeId) {

        //Verefica se a criatura existe
        if(creatures.get(creatureId) == null){
            return false;
        }

        //Atribui a criatura segundo o id dado por parametro
        Creature criatura = creatures.get(creatureId);

        //Verefica se a criatura nao é um zombie
        if(criatura.getTipo() == 0){
            return false;
        }

        //Verefica se o Humano tem o equipamento
        return criatura.temEquipamento(equipmentTypeId);
    }

    // get currentID
    public void currentID() {

        if (currentID == 20){
            currentID = 10;
        } else {
            currentID = 20;
        }
    }

    // Verefica se a posiçao esta dentro do tabuleiro de memoria
    public boolean positionInBoard(int x, int y){
        return x >= 0 && x < worldSize[1] && y >= 0 && y < worldSize[0];
    }

    // Verifica se a criatura andou uma casa na lateral
    public boolean movimentoRestrito(int xO, int yO, int xD, int yD){
        return (xO == xD && (yD == yO + 1 || yD == yO - 1)) || (yO == yD && (xD == xO + 1 || xD == xO - 1));
    }

    // Move item do tabuleiro
    public boolean move(int xO, int yO, int xD, int yD) {

        // Verifica se a movimentacao é valido
        if (movimentoRestrito(xO, yO, xD, yD)) {

            Equipment equipment = existeEquipamento(xD,yD);
            // Verfica se as coordenadas de destino estao dentro do tabuleiro e se estao vazias
            if (positionInBoard(xD,yD) && board.squareVazio(xD,yD,equipment)) {

                // Atualiza posicao das criaturas
                for (Creature creature : creatures.values()) {

                    if(creature.getX() == xO && creature.getY() == yO && creature.getTipo() == currentID){

                        if (equipment != null) {
                            // Se for um humano, pega o equipamento e se for zumbi, destrói o equipamento
                            if (creature.getTipo() == 20) {
                                creature.adicionaEquipamento(equipment, equipments);
                            } else if(creature.getTipo() == 10){
                                creature.destroiEquipamento(equipment, equipments);
                            }
                        }

                        creature.atualizaPosicao(xD,yD);
                        board.setItem(xD,yD,creature);
                        board.removeItem(xO,yO);
                        nrJogadas++;
                        currentID();
                        return true;

                    }
                }
            }
        }
        return false;
    }

    // Faz retorno ao equipamento em uma certa coordenada se existir
    public Equipment existeEquipamento(int x, int y){

        //Inicialiaza equipamento a NULL
        Equipment equipment = null;

        //Verefica se o equipamento existe
        for(Equipment equip : equipments.values()){

            if(equip.getX() == x && equip.getY() == y){
                equipment = equip;
            }
        }

        //Faz retorno ao equipamento (;
        return equipment;
    }

    // Verefica se o jogo acabou
    public boolean gameIsOver(){
        return nrJogadas >= 12;
    }




    // Personalizaçao de menus
    public ArrayList<String> getSurvivors(){

        ArrayList<String> survivors = new ArrayList<>();

        for(Creature creature : creatures.values()){

            if(creature.getTipo() == 1){
                survivors.add(creature.toString());
            }
        }
        return survivors;
    }

    public JPanel getCreditsPanel(){
        return null;
    }

    public HashMap<String,String> customizeBoard(){
        return new HashMap<>();
    }

    public List<Integer> getIdsInSafeHaven(){

        return null;
    }




    // Salva o jogo
    public void saveGame(File file) throws IOException {

    }




    // Main (;
    public static void main(String[] args) {

    }
}