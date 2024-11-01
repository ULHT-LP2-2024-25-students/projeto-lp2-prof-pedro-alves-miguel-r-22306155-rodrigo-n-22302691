package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class GameManager {

    //Informaçoes do ficheiro
    int[] worldSize = new int[2];
    int initialID;
    int nrCriaturas;
    HashMap<Integer,Creature> creatures = new HashMap<>();
    int nrEquipamentos;
    HashMap<Integer,Equipment> equipments = new HashMap<>();
    int nrJogadas;

    // tabuleiro
    Board board;

    //Informaçoes do jogo
    int currentID;

    public boolean parseGame(File game) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(game);
        } catch (FileNotFoundException e) {
            return false;
        }

        // Lê a primeira linha (tamanho do tabuleiro)
        if (scanner.hasNextLine()) {

            String linha = scanner.nextLine();
            String[] tabuleiro = linha.split(" ");

            if (tabuleiro.length == 2) {

                try {
                    worldSize[0] = Integer.parseInt(tabuleiro[0]);
                    worldSize[1] = Integer.parseInt(tabuleiro[1]);
                } catch (NumberFormatException e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

        // Lê o ID inicial
        if (scanner.hasNextLine()) {

            String linha = scanner.nextLine();

            try {
                initialID = Integer.parseInt(linha);
                currentID = initialID;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }

        // Lê o número de criaturas
        if (scanner.hasNextLine()) {

            String linha = scanner.nextLine();

            try {
                nrCriaturas = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }

        // Loop para ler as criaturas
        for (int i = 0; i < nrCriaturas; i++) {

            if (scanner.hasNextLine()) {

                String linha = scanner.nextLine();
                String[] creature = linha.split(" : ");

                if (creature.length == 5) {

                    try {
                        creatures.put(Integer.parseInt(creature[0]), new Creature(
                                Integer.parseInt(creature[0]),
                                Integer.parseInt(creature[1]),
                                                  creature[2],
                                Integer.parseInt(creature[3]),
                                Integer.parseInt(creature[4])));

                    } catch (NumberFormatException e) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        // Lê o número de equipamentos
        if (scanner.hasNextLine()) {

            String linha = scanner.nextLine();

            try {
                nrEquipamentos = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }

        // Loop para ler os equipamentos
        for (int i = 0; i < nrEquipamentos; i++) {

            if (scanner.hasNextLine()) {

                String linha = scanner.nextLine();
                String[] equipment = linha.split(" : ");

                if (equipment.length == 4) {

                    try {
                        equipments.put(Integer.parseInt(equipment[0]), new Equipment(
                                Integer.parseInt(equipment[0]),
                                Integer.parseInt(equipment[1]),
                                Integer.parseInt(equipment[2]),
                                Integer.parseInt(equipment[3])));
                    } catch (NumberFormatException e) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        // Finaliza
        return true;
    }

    public boolean loadGame(File file){

        if(parseGame(file)){
            board = new Board(new String[worldSize[1]][worldSize[0]]);

            for(Creature creature : creatures.values()){
                board.adicionaCreature(creature);
            }

            for(Equipment equipment : equipments.values()){
                board.adicionaEquipment(equipment);
            }
            return true;
        }
        return false;
    }

    public int[] getWorldSize(){
        return worldSize;
    }

    public int getInitialTeamId(){
        return  initialID;
    }

    public int getCurrentTeamId(){
        return currentID;
    }

    public boolean isDay() {
        // Verifica se esta de dia ou de noite
        return (nrJogadas / 2) % 2 == 0;
    }

    public String getSquareInfo(int x, int y) {

        String square = board.tabuleiro[y][x];
        if (square != null) {

            // Verificar se a célula tem uma criatura
            for (Creature creature : creatures.values()) {
                if (creature.getX() == x && creature.getY() == y) {
                    return creature.tipoCriaturaChar(creature.getTipo()) + ":" + creature.getId();
                }
            }

            // Verificar se a célula tem um equipamento
            for (Equipment equipment : equipments.values()) {
                if (equipment.getX() == x && equipment.getY() == y) {
                    return "E:" + equipment.getId();
                }
            }
        }
        return "";
    }

    public String[] getCreatureInfo(int id){
        //Verefica se existe a criatura
        if(creatures.get(id) == null){
            return null;
        }

        //Guarda a criatura
        Creature criatura = creatures.get(id);

        //Cria a String
        String[] partes = new String[6];

        //Poe as insformacoes da criatura na string
        partes[0] = String.valueOf(id);
        partes[1] = String.valueOf(criatura.tipoCriatura(criatura.getTipo()));
        partes[2] = String.valueOf(criatura.getNome());
        partes[3] = String.valueOf(criatura.getX());
        partes[4] = String.valueOf(criatura.getY());
        partes[5] = null;

        //Final (;
        return partes;
    }

    public String getCreatureInfoAsString(int id){
        return creatures.get(id).toString();
    }

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

    public String getEquipmentInfoAsString(int id){
        return this.equipments.get(id).toString();
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {

        // atribui a criatura segundo o id dado por parametro
        Creature criatura = creatures.get(creatureId);

        /* verifica se a criatura existe e se tem equipamento
        e verifica se o equipamento da criatura é igual ao dado
        por parametro
         */
        if (criatura != null && criatura.getEquipment() != null) {
            return criatura.getEquipment().getId() == equipmentTypeId;
        }
        return false;
    }

    public void currentID() {

        if (nrJogadas % 2 == 0) {
            currentID = 1;
        } else {
            currentID = 0;
        }
    }

    public boolean positionInBoard(int x, int y){

        return x >= 0 && x < worldSize[1] && y >= 0 && y < worldSize[0];
    }

    public boolean move(int xO, int yO, int xD, int yD) {

        // Verifica se a movimentacao é valida
        if ((xO == xD && (yD == yO + 1 || yD == yO - 1)) ||
                (yO == yD && (xD == xO + 1 || xD == xO - 1))) {

            Equipment equipment = existeEquipamento(xD,yD);
            // Verfica se as coordenadas de destino estao dentro do tabuleiro e se estao vazias
            if (positionInBoard(xD,yD) && board.squareVazio(xD,yD,equipment)) {

                // Atualiza posicao das criaturas
                for (Creature creature : creatures.values()) {

                    if(creature.getX() == xO && creature.getY() == yO && creature.getTipo() == currentID){

                        if (equipment != null) {
                            // Se for um humano, pega o equipamento e se for zumbi, destrói o equipamento
                            if (creature.getTipo() == 1) {
                                creature.adicionaEquipamento(equipment);
                                creature.setEquipCount();
                                equipments.get(equipment.getId());
                            } else if(creature.getTipo() == 0){
                                creature.setEquipCount();
                                equipments.get(equipment.getId());
                            }
                        }

                        creature.atualizaPosicao(xD,yD);
                        board.setItem(xD,yD,creature.toString());
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

    public Equipment existeEquipamento(int x, int y){

        Equipment equipment = null;

        for(Equipment equip : equipments.values()){

            if(equip.getX() == x && equip.getY() == y){
                equipment = equip;
            }
        }
        return equipment;
    }

    public boolean gameIsOver(){
        return nrJogadas == 12;
    }

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
        return null;
    }

    public static void main(String[] args) {

    }
}