package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

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