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
                                Integer.parseInt(equipment[3])
                        ));
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
        //Verefica se existe a criatura
        if(creatures.get(id) == null){
            return null;
        }

        //Guarda a criatura
        Creature criatura = creatures.get(id);

        //Obtem os dados da criatura
        HashMap<String, String> dados = criatura.getInfo();

        //Cria a String
        String[] partes = new String[6];

        //Poe as insformacoes da criatura na string
        partes[0] = String.valueOf(id);
        partes[1] = String.valueOf(criatura.tipoCriatura(parseInt(dados.get("tipo"))));
        partes[2] = String.valueOf(dados.get("nome"));
        partes[3] = String.valueOf(dados.get("posicaoX"));
        partes[4] = String.valueOf(dados.get("posicaoY"));
        partes[5] = String.valueOf(dados.get("imagem"));

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

        //Obtem os dados da criatura
        HashMap<String, String> dados = equipamento.getInfo();

        //Cria a String
        String[] partes = new String[5];

        //Poe as insformacoes do equipamento na string
        partes[0] = String.valueOf(id);
        partes[1] = String.valueOf(parseInt(dados.get("tipo")));
        partes[2] = String.valueOf(dados.get("posicaoX"));
        partes[3] = String.valueOf(dados.get("posicaoY"));
        partes[4] = String.valueOf(dados.get("imagem"));

        //Final (;
        return partes;
    }

    public String getEquipmentInfoAsString(int id){
        return equipments.get(id).toString();
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {

        Creature criatura = creatures.get(creatureId);

        if (criatura != null && criatura.getEquipment() != null) {
            return criatura.getEquipment().getId() == equipmentTypeId;
        }
        return false;
    }

    public boolean move(int xO, int yO, int xD, int yD) {

        if ((xO == xD && (yD == yO + 1 || yD == yO - 1)) ||
                (yO == yD && (xD == xO + 1 || xD == xO - 1))) {

            return xD >= 0 && xD < worldSize[0] && yD >= 0 && yD < worldSize[1];
        }
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