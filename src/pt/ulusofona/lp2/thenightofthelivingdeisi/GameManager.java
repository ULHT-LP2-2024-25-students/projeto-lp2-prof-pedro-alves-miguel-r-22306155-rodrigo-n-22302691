package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameManager {

    int[] worldSize = new int[2];
    int initialID;
    int currentID;
    ArrayList<Creature> creatures;
    ArrayList<Equipment> equipments;

    public boolean parseGame(File game) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(game));
        } catch (FileNotFoundException e) {
            return false;
        }

        String linha = null;
        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] tabuleiro = linha.split(" ");

        if (tabuleiro.length == 2) {

            worldSize[0] = Integer.parseInt(tabuleiro[0]);
            worldSize[1] = Integer.parseInt(tabuleiro[1]);
        } else {
            return false;
        }

        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int teamID = Integer.parseInt(linha);

        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int nrCreatures = Integer.parseInt(linha);

        for (int i = 0; i < nrCreatures; i++) {

            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] creature = linha.split(" : ");

            if (creature.length == 5) {
                creatures.add(new Creature(Integer.parseInt(creature[0]), Integer.parseInt(creature[1]),
                        creature[2], Integer.parseInt(creature[3]), Integer.parseInt(creature[4])));
            } else {
                return false;
            }
        }

            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int nrEquipamentos = Integer.parseInt(linha);

            for(int i = 0; i < nrEquipamentos; i++){

                try {
                    linha = reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String[] equipment = linha.split(" : ");

                if(equipment.length == 4){

                    equipments.add(new Equipment(Integer.parseInt(equipment[0]),Integer.parseInt(equipment[1]),
                            Integer.parseInt(equipment[2]),Integer.parseInt(equipment[3])));
                } else {
                    return false;
                }
            }

        return true;
        }


    public boolean loadGame(File file){
        return false;
    }

    public int[] getWorldSize(){
        return null;
    }

    public int getInitialTeamId(){
        return  0;
    }

    public int getCurrentTeamId(){
        return 0;
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
        return "";
    }

    public String[] getEquipmentInfo(int id){
        return null;
    }

    public String getEquipmentInfoAsString(int id){
        return "";
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




    //Main
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}