package pt.ulusofona.lp2.thenightofthelivingdeisi;

import pt.ulusofona.lp2.guiSimulator.AppLauncher;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class GameManager {

    int[] worldSize = new int[2];
    int initialID;
    int currentID;
    ArrayList<Creature> creatures = new ArrayList<>();
    ArrayList<Equipment> equipments = new ArrayList<>();

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

            try {

                worldSize[0] = parseInt(tabuleiro[0]);
                worldSize[1] = parseInt(tabuleiro[1]);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }

        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int teamID;

        try{
            teamID = Integer.parseInt(linha);
        } catch (NumberFormatException e){
            return false;
        }

        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int nrCreatures;

        try{
            nrCreatures = Integer.parseInt(linha);
        } catch (NumberFormatException e){
            return false;
        }

        for (int i = 0; i < nrCreatures; i++) {

            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] creature = linha.split(" : ");

            if (creature.length == 5) {

                try {
                    creatures.add(new Creature(parseInt(creature[0]), parseInt(creature[1]),
                            creature[2], parseInt(creature[3]), parseInt(creature[4])));

                } catch (NumberFormatException e){
                    return false;
                }

            } else {
                return false;
            }
        }

        try {
            linha = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int nrEquipamentos;

        try {
            nrEquipamentos = Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return false;
        }

        for (int i = 0; i < nrEquipamentos; i++) {

            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] equipment = linha.split(" : ");

            if (equipment.length == 4) {

                try {
                    equipments.add(new Equipment(parseInt(equipment[0]), parseInt(equipment[1]),
                            parseInt(equipment[2]), parseInt(equipment[3])));

                } catch (NumberFormatException e) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    public boolean loadGame(File file){

        return parseGame(file);
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

    public static void main(String[] args) {

        AppLauncher launcher = new AppLauncher();
        AppLauncher.main(null);
    }
}