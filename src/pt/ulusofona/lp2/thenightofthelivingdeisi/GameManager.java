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

    //Informaçoes do ficheiro
    int[] worldSize = new int[2];
    int initialID;
    int nrCriaturas;
    HashMap<Integer,Creature> creatures = new HashMap<>();
    int nrEquipamentos;
    HashMap<Integer,Equipment> equipments = new HashMap<>();
    int nrJogadas;
    int nrPortas;
    ArrayList<Porta> portas = new ArrayList<Porta>();

    // tabuleiro
    Board board;

    //Informaçoes do jogo
    int currentID;


    //Vai ler o ficheiro
    public void parseGame(File game) throws InvalidFileException, FileNotFoundException {

        nrJogadas = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(game);
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("Não encontrou ficheiro");
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
                    throw new InvalidFileException("Formato invalido para tamanho do tabuleiro.");
                }
            } else {
                throw new InvalidFileException("Formato incorreto na linha do tamanho do tabuleiro.");
            }
        } else {
            throw new InvalidFileException("Erro na leitura do tamanho do tabuleiro.");
        }

        // Lê o ID inicial
        if (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            try {
                initialID = Integer.parseInt(linha);
                currentID = initialID;
            } catch (NumberFormatException e) {
                throw new InvalidFileException("ID inicial invalido.");
            }
        } else {
            throw new InvalidFileException("Erro na leitura do ID inicial.");
        }

        // Lê o número de criaturas
        if (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            try {
                nrCriaturas = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                throw new InvalidFileException("Numero de criaturas invalido.");
            }
        } else {
            throw new InvalidFileException("Erro na leitura do numero de criaturas.");
        }

        // Loop para ler as criaturas
        for (int i = 0; i < nrCriaturas; i++) {
            if (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] creature = linha.split(" : ");

                if (creature.length == 6) {
                    try {

                        int id = Integer.parseInt(creature[0]);
                        int tipo = Integer.parseInt(creature[1]);
                        int categoria = Integer.parseInt(creature[2]);
                        String nome = creature[3];
                        int posX = Integer.parseInt(creature[4]);
                        int posY = Integer.parseInt(creature[5]);

                        Creature criatura = switch (categoria) {
                            case 0 -> new Crianca(id, tipo, nome, posX, posY); // Criança
                            case 1 -> new Adulto(id, tipo, nome, posX, posY);  // Adulto
                            case 2 -> new Idoso(id, tipo, nome, posX, posY);   // Idoso
                            case 3 -> {
                                if (id == 20) {
                                    yield new Cao(id, tipo, nome, posX, posY); // Cão
                                } else {
                                    throw new IllegalArgumentException("Cão só pode ser criado com ID 20.");
                                }
                            }
                            case 4 -> {
                                if (id == 10) {
                                    yield new Vampiro(id, tipo, nome, posX, posY); // Vampiro
                                } else {
                                    throw new IllegalArgumentException("Vampiro só pode ser criado com ID 10.");
                                }
                            }
                            default -> throw new IllegalArgumentException("Categoria desconhecida: " + categoria);
                        };


                    } catch (NumberFormatException e) {
                        throw new InvalidFileException("Formato invalido para criatura na linha " + (i + 1));
                    }

                    // Remove criatura caso esteja fora
                    if (Integer.parseInt(creature[4]) < 0 || Integer.parseInt(creature[4]) >= worldSize[1] || Integer.parseInt(creature[5]) < 0 || Integer.parseInt(creature[5]) >= worldSize[0]) {
                        creatures.remove(Integer.parseInt(creature[0]));
                    }
                } else {
                    throw new InvalidFileException("Formato incorreto para criatura na linha " + (i + 1));
                }
            } else {
                throw new InvalidFileException("Faltam dados para criatura na linha " + (i + 1));
            }
        }

        // Lê o número de equipamentos
        if (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            try {
                nrEquipamentos = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                throw new InvalidFileException("Numero de equipamentos inválido.");
            }
        } else {
            throw new InvalidFileException("Erro na leitura do numero de equipamentos.");
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
                        throw new InvalidFileException("Formato invalido para equipamento na linha " + (i + 1));
                    }

                    // Remove equipamento caso esteja fora
                    if (Integer.parseInt(equipment[2]) < 0 || Integer.parseInt(equipment[2]) >= worldSize[1] || Integer.parseInt(equipment[3]) < 0 || Integer.parseInt(equipment[3]) >= worldSize[0]) {
                        equipments.remove(Integer.parseInt(equipment[0]));
                    }
                } else {
                    throw new InvalidFileException("Formato incorreto para equipamento na linha " + (i + 1));
                }
            } else {
                throw new InvalidFileException("Faltam dados para equipamento na linha " + (i + 1));
            }
        }

        if (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            try {
                nrPortas = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                throw new InvalidFileException("Numero de portas invalido.");
            }
        } else {
            throw new InvalidFileException("Erro na leitura do numero de portas.");
        }

        for (int i = 0; i < nrPortas; i++) {
            if (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] porta = linha.split(" : ");

                if (porta.length == 2) {
                    try {
                        portas.add(new Porta(Integer.parseInt(porta[0]), Integer.parseInt(porta[1])));
                    } catch (NumberFormatException e) {
                        throw new InvalidFileException("Formato invalido para porta na linha " + (i+1));
                    }

                    // Remove porta caso esteja fora
                    if (Integer.parseInt(porta[1]) < 0 || Integer.parseInt(porta[1]) >= worldSize[1] || Integer.parseInt(porta[0]) < 0 || Integer.parseInt(porta[0]) >= worldSize[0]) {
                        portas.remove(new Porta(Integer.parseInt(porta[0]), Integer.parseInt(porta[1])));
                    }
                } else {
                    throw new InvalidFileException("Formato incorreto para porta na linha " + (i + 1));
                }
            } else {
                throw new InvalidFileException("Faltam dados para porta na linha " + (i + 1));
            }
        }
        // Finaliza
    }

    public void loadGame(File file) throws InvalidFileException, FileNotFoundException {

        try {
            parseGame(file);
        } catch (InvalidFileException e) {
            throw new InvalidFileException("Erro ao ler o ficheiro de jogo");
        }

        // Cria o tabuleiro na memória
        board = new Board(new String[worldSize[0]][worldSize[1]]);

        // Adiciona as criaturas no tabuleiro
        for (Creature creature : creatures.values()) {
            board.adicionaCreature(creature);
        }

        // Itens a serem adicionados aos humanos
        ArrayList<Equipment> itemsAdicionar = new ArrayList<>();
        ArrayList<Integer> criaturaAdicionar = new ArrayList<>();

        // Itens a serem removidos pelos zumbis
        ArrayList<Equipment> itemsRemover = new ArrayList<>();
        ArrayList<Integer> criaturaRemover = new ArrayList<>();

        for (Equipment equipment : equipments.values()) {

            for (Creature creature : creatures.values()) {

                // Verifica se a criatura remove ou pega o equipamento ou se o equipamento é colocado no mapa
                if (creature.getX() == equipment.getX() && creature.getY() == equipment.getY()) {
                    if (creature.getTipo() == 20) {
                        itemsAdicionar.add(equipment);
                        criaturaAdicionar.add(creature.id);
                    } else {
                        itemsRemover.add(equipment);
                        criaturaRemover.add(creature.id);
                    }
                } else {
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

    //Get WorldSize
    public int[] getWorldSize(){
        return worldSize;
    }

    //get InitialTeamId
    public int getInitialTeamId(){
        return  initialID;
    }

    //get getCurrentTeamId
    public int getCurrentTeamId(){
        return currentID;
    }

    //Em cada 2 jogadas troca o dia
    public boolean isDay() {
        // Verifica se esta de dia ou de noite
        return (nrJogadas / 2) % 2 == 0;
    }

    //Devolve uma string com a informaçao do item na posiçao dada
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

    //Devolve info da criatura em um array
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

    //Devolve info da criatura em uma string
    public String getCreatureInfoAsString(int id){
        return creatures.get(id).toString();
    }

    //Devolve info do equipamento em um array
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

    //Devolve info do equipamento em uma string
    public String getEquipmentInfoAsString(int id) {
        Equipment equipamento = equipments.get(id);

        if (equipamento == null) {
            return null;
        }

        return equipamento.toString();
    }

    //Verefica se uma criatura tem um certo equipamento
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

    //get currentID
    public void currentID() {

        if (currentID == 0){
            currentID = 1;
        } else {
            currentID = 0;
        }
    }

    //Verefica se a posiçao esta dentro do tabuleiro de memoria
    public boolean positionInBoard(int x, int y){
        return x >= 0 && x < worldSize[1] && y >= 0 && y < worldSize[0];
    }

    // Verifica se a criatura andou uma casa na lateral
    public boolean movimentoRestrito(int xO, int yO, int xD, int yD){
        return (xO == xD && (yD == yO + 1 || yD == yO - 1)) || (yO == yD && (xD == xO + 1 || xD == xO - 1));
    }

    //Move item do tabuleiro
    public boolean move(int xO, int yO, int xD, int yD) {

        // Verifica se a movimentacao é valida
        if (movimentoRestrito(xO, yO, xD, yD)) {

            Equipment equipment = existeEquipamento(xD,yD);
            // Verfica se as coordenadas de destino estao dentro do tabuleiro e se estao vazias
            if (positionInBoard(xD,yD) && board.squareVazio(xD,yD,equipment)) {

                // Atualiza posicao das criaturas
                for (Creature creature : creatures.values()) {

                    if(creature.getX() == xO && creature.getY() == yO && creature.getTipo() == currentID){

                        if (equipment != null) {
                            // Se for um humano, pega o equipamento e se for zumbi, destrói o equipamento
                            if (creature.getTipo() == 1) {
                                creature.adicionaEquipamento(equipment, equipments);
                            } else if(creature.getTipo() == 0){
                                creature.destroiEquipamento(equipment, equipments);
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

    //Faz retorno ao equipamento em uma certa coordenada se existir
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

    //Verefica se o jogo acabou
    public boolean gameIsOver(){
        return nrJogadas >= 12;
    }

    //get Survivors
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

    public static void main(String[] args) {

    }

    public void saveGame(File file) throws IOException {

    }

    public List<Integer> getIdsInSafeHaven(){

        return null;
    }
}