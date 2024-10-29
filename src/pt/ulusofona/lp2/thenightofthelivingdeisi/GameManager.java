package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


import static java.lang.Integer.parseInt;

public class GameManager {

    //Informaçoes do ficheiro
    int[] worldSize = new int[2];
    int initialID;
    int nrCriaturas;
    HashMap<Integer,Creature> creatures = new HashMap<>();
    int nrEquipamentos;
    HashMap<Integer,Equipment> equipments = new HashMap<>();

    //Informaçoes do jogo
    int currentID;

    public boolean parseGame(File game) {

        //Cria o reader e verfica a sua criaçao
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(game));
        } catch (FileNotFoundException e) {
            return false;
        }


        //Ler a primeira linha
        String linha = null;
        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Vai pegar no tamanho do tabuleiro
        String[] tabuleiro = linha.split(" ");

        //Vai ler o tamanho do tabuleiro
        if (tabuleiro.length == 2) {

            try {
                worldSize[0] = parseInt(tabuleiro[0]);
                worldSize[1] = parseInt(tabuleiro[1]);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }


        //Le a proxima linha
        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Pega o initial int e faz parse
        try{
            initialID = Integer.parseInt(linha);
        } catch (NumberFormatException e){
            return false;
        }


        //Le a proxima linha
        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Pega o numero de creaturas
        try{
            nrCriaturas = Integer.parseInt(linha);
        } catch (NumberFormatException e){
            return false;
        }

        //Faz um loop para ler o numero de criaturas do ficheiro
        for (int i = 0; i < nrCriaturas; i++) {

            //Le a proxima linha
            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Split
            String[] creature = linha.split(" : ");

            //Le a informaçao da criatura
            if (creature.length == 5) {

                try {
                    creatures.put(Integer.parseInt(creature[0]),new Creature(parseInt(creature[0]), parseInt(creature[1]),
                            creature[2], parseInt(creature[3]), parseInt(creature[4])));

                } catch (NumberFormatException e){
                    return false;
                }

            } else {
                return false;
            }
        }


        //Le a proxima linha
        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Le o numero de equipamentos que existem
        try {
            nrEquipamentos = Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return false;
        }


        //Vai ler todos os equipamentos do ficheiro
        for (int i = 0; i < nrEquipamentos; i++) {

            //Le a proxima linha
            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Split
            String[] equipment = linha.split(" : ");

            //Le os equipamentos
            if (equipment.length == 4) {

                try {
                    equipments.put(Integer.parseInt(equipment[0]),new Equipment(parseInt(equipment[0]), parseInt(equipment[1]),
                            parseInt(equipment[2]), parseInt(equipment[3])));

                } catch (NumberFormatException e) {
                    return false;
                }
            } else {
                return false;
            }
        }

        //Termina (;
        return true;
    }

    public boolean loadGame(File file){

        return parseGame(file);
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

    public boolean isDay(){
        return false;
    }

    public String getSquareInfo(int x, int y){
        return "";
    }

    public String[] getCreatureInfo(int id){
        return null;
    }

    public String getCreatureInfoAsString(int id){
        return creatures.get(id).toString();
    }

    public String[] getEquipmentInfo(int id){
        return null;
    }

    public String getEquipmentInfoAsString(int id){
        return equipments.get(id).toString();
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId){
        return false;
    }

    public boolean move(int xO, int yO, int xD, int yD){
        return false;
    }

    public boolean gameIsOver(){
        return false;
    }

    public ArrayList<String> getSurvivors(){
        return null;
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