package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class GameManager {

    // Constantes
    private static final int NR_SEM_INTERACAO = 8;


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
    private ArrayList<Porta> portas = new ArrayList<>();
    private int nrPortas;

    // tabuleiro
    private Board board;

    //Informaçoes do jogo
    private int currentID;

    private ArrayList<Creature> safeHaven = new ArrayList<>();

    private int nrJogadasSemInteracao;



    // Metodos
    // Vai ler o ficheiro
    public void parseGame(File game) throws InvalidFileException {

        // Cada vez que le um novo ficheiro reinicia variaveis globais
        nrJogadas = 0;
        nrJogadasSemInteracao = 0;

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

            equipments = new HashMap<>();
            portas = new ArrayList<>();
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

            board.setItem(porta.getX(), porta.getY(), porta);

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

        if (x < 0 || x >= worldSize[1] || y < 0 || y >= worldSize[0]) {
            return null;
        }

        ItemTabuleiro tile = board.getTabuleiro()[y][x];

        return (tile == null) ? "" : tile.info();
    }



    // Devolve info da criatura em um array
    public String[] getCreatureInfo(int id) {

        Creature criatura = creatures.get(id);

        if (criatura == null) {
            return null; // Criatura não está no tabuleiro
        }

        String[] info = criatura.getInfo();

        // Verifica se a criatura está no Safe Haven
        if (safeHaven.contains(criatura)) {
            // Ajusta o índice 4 para null, conforme esperado pelo teste
            info[4] = null;
            info[5] = null;
        }

        return info;
    }



    // Devolve info da criatura em uma string
    public String getCreatureInfoAsString(int id) {

        Creature creature = creatures.get(id);

        if (creature == null) {
            return null; // Ou outra mensagem de erro adequada
        }

        return creature.toString();
    }



    // Devolve info do equipamento em um array
    public String[] getEquipmentInfo(int id){

        //Verefica se existe o item
        if(equipments.get(id) == null){
            return null;
        }

        //Guarda o equipamento
        Equipment equipamento = equipments.get(id);

        return equipamento.getInfo();

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

        // Verefica se a criatura existe
        if(creatures.get(creatureId) == null){
            return false;
        }

        // Atribui a criatura segundo o id dado por parametro
        Creature criatura = creatures.get(creatureId);

        // Verefica se a criatura nao é um zombie
        if(criatura.getTipo() == 0){
            return false;
        }

        // Verefica se o Humano tem o equipamento
        return criatura.temEquipamento(equipmentTypeId);
    }


    // get currentID
    public void currentID() {

        if (currentID == 20) {
            currentID = 10;
        } else {
            currentID = 20;
        }
    }


    public Creature getCreature(int x, int y){

        for(Creature creature : creatures.values()){

            if(creature.getX() == x && creature.getY() == y){

                return creature;
            }
        }
        return null;
    }

    public boolean move(int xO, int yO, int xD, int yD) {

        // Verifica se a posição de destino está dentro do tabuleiro
        if (!board.positionInBoard(xD, yD)) {
            return false;
        }

        // Obtem a criatura na posição de origem
        Creature creature = getCreature(xO, yO);

        // Obtem a criatura na posição de destino
        Creature creatureDestino = getCreature(xD, yD);

        // Obtém equipamento na posição de destino, se existir
        Equipment equipment = existeEquipamento(xD, yD);


        // Só se pode mover se o ID da criatura for igual ao currentID
        if (creature == null || creature.getTipo() != currentID) {
            return false;
        }

        // Verifica se a criatura pode se mover para a posição de destino
        if (!creature.move(xO, yO, xD, yD, isDay(), board)) {
            return false;
        }

        // Se não houver nenhuma criatura na posição de destino, então não vai haver ataque nem defesa
        if (creatureDestino != null && creature.atacarDefender(creatureDestino, board, creatures)) {
            // Atualiza o contador de jogadas sem interação
            nrJogadasSemInteracao = creatureDestino.getDefesa() ? nrJogadasSemInteracao + 1 : 0;

            // Atualiza as informações da jogada
            nrJogadas++;
            currentID(); // Muda o turno
            return true;
        }

        // Aumenta o numero de jogadas sem interacao se nao houver nenhum ataque
        nrJogadasSemInteracao++;

        // Se o safeHaven tiver na posição de destino, a criatura entra no esconderijo
        if (creature.podeIrParaSafeHaven(xD, yD, portas)) {

            // Remove do tabuleiro
            board.removeItem(xO, yO);

            // Adiciona a criatura ao safeHaven
            safeHaven.add(creature);

            // Atualiza jogadas e ID do jogador atual
            nrJogadas++;
            currentID();
            return true;
        }

        // Verifica se o destino está vazio ou tem equipamento interagível
        if (!board.squareVazio(xD, yD)) {
            return false;
        }

        // Interage com o equipamento se existir
        Equipment equipamentoEquipado = null;


        // Verifica se o idoso tem um equipamento
        if (creature.isIdoso() && creature.getEquipment() != null) {


            equipamentoEquipado = creature.getEquipment();
            equipamentoEquipado.atualizaPosicao(xO, yO);
            board.adicionaEquipment(equipamentoEquipado);
            equipments.put(equipamentoEquipado.getId(), equipamentoEquipado);
            creature.removeEquipamento();
        }

        if (equipment != null) {

            // Verifica se a criatura pode apanhar o equipamento
            if (creature.isHumano() && !creature.apanharEquipamento(equipment)) {
                return false;
            }

            // Verifica se tem equipamento
            if(creature.getEquipment() != null){

                creature.getEquipment().atualizaPosicao(creature.getX(), creature.getY());
                // Diz que tinha um equipamento equipado
                equipamentoEquipado = creature.getEquipment();
            }


            if (creature.isHumano()) {
                creature.adicionaEquipamento(equipment, equipments);
            }


            if (creature.isZombie()) {
                creature.destroiEquipamento(equipment, equipments);
            }
        }



        // Atualiza a posição da criatura
        creature.atualizaPosicao(xD, yD);
        board.setItem(xD, yD, creature);
        board.removeItem(xO, yO);


        // Se tinha um equipamento então vai dropar
        if (equipamentoEquipado != null) {

            board.adicionaEquipment(equipamentoEquipado);
            equipments.put(equipamentoEquipado.getId(), equipamentoEquipado);
        }


        // Atualiza jogadas e ID do jogador atual
        nrJogadas++;
        currentID();

        return true;
    }


    // Faz retorno ao equipamento em uma certa coordenada se existir
    public Equipment existeEquipamento(int x, int y) {

        // Inicializa equipamento a NULL
        Equipment equipment = null;

        // Verifica se o equipamento existe
        for (Equipment equip : equipments.values()) {
            if (equip.getX() == x && equip.getY() == y) {
                equipment = equip;
            }
        }

        // Faz retorno ao equipamento (;)
        return equipment;
    }




    // Verefica se o jogo acabou
    public boolean gameIsOver() {

        if(nrJogadasSemInteracao == NR_SEM_INTERACAO){

            return true;

        }

        boolean existemHumanos = false; // Verifica se todas as criaturas são humanas
        boolean existemZombies = false; // Verifica se todas as criaturas são zumbis

        // Percorre o tabuleiro para determinar se o jogo está terminado
        for (int y = 0; y < board.getTabuleiro().length; y++) {

            for (int x = 0; x < board.getTabuleiro()[y].length; x++) {

                ItemTabuleiro item = board.getItem(x, y);

                // Verifica se o item é uma criatura
                if (item != null && item.isCreature()) {

                    Creature criatura = (Creature) item;

                    // Se a crituras for humano entao ainda existem criaturas humanas
                    if (criatura.isHumano()) {

                        existemHumanos = true;

                    }

                    // Se a criatura for zombie entao ainda existem criaturas zombies
                    if (criatura.isZombie()) {

                        existemZombies = true;

                    }

                    // Se há mistura, o jogo não acabou
                    if (existemHumanos && existemZombies) {

                        return false;

                    }
                }
            }
        }

        return true;
    }




    public ArrayList<String> getSurvivors() {

        ArrayList<String> result = new ArrayList<>();

        ArrayList<Creature> humanos = new ArrayList<>();
        ArrayList<Creature> zumbis = new ArrayList<>();

        // Separa as criaturas em humanos e zumbis
        for (Creature creature : creatures.values()) {
            if (creature.isHumano()) {
                humanos.add(creature);
            } else if (creature.isZombie()) {
                zumbis.add(creature);
            }
        }

        // Ordena as listas pelo ID
        humanos.sort(Comparator.comparingInt(Creature::getId));
        zumbis.sort(Comparator.comparingInt(Creature::getId));

        // Adiciona o número de turnos/jogadas
        result.add("Nr. de turnos terminados:");
        result.add("" + nrJogadas);

        // Adiciona os humanos vivos
        result.add("");
        result.add("OS VIVOS");
        for (Creature humano : humanos) {
            result.add(humano.getId() + " " + humano.getNome());
        }

        // Adiciona os zumbis
        result.add("");
        result.add("OS OUTROS");
        for (Creature zombie : zumbis) {
            result.add(zombie.getId() + " (antigamente conhecido como " + zombie.getNome() + ")");
        }

        // Linha final para demarcar o fim
        result.add("-----");

        return result;
    }

    public List<Integer> getIdsInSafeHaven(){

        List<Integer> getSafeIds = new ArrayList<>();

        for(Creature creature : safeHaven){

            getSafeIds.add(creature.id);

        }

        return getSafeIds;
    }


    public void saveGame(File file) throws IOException {
        // Criar um FileWriter e BufferedWriter para escrever no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            // 1. Tamanho do tabuleiro
            writer.write(worldSize[0] + " " + worldSize[1]);
            writer.newLine();

            writer.write(String.valueOf(initialID));
            writer.newLine();

            // 2. Número de criaturas
            writer.write(String.valueOf(nrCriaturas));
            writer.newLine();

            // 3. Escrever as criaturas
            for (Creature criatura : creatures.values()) {
                writer.write(criatura.getId() + " : " +
                        criatura.getTipo() + " : " +
                        criatura.getCategoria() + " : " +
                        criatura.getNome() + " : " +
                        criatura.getX() + " : " +
                        criatura.getY());
                writer.newLine();
            }

            // 4. Número de equipamentos
            writer.write(String.valueOf(nrEquipamentos));
            writer.newLine();

            // 5. Escrever os equipamentos
            for (Equipment equipamento : equipments.values()) {
                writer.write(equipamento.getId() + " : " +
                        equipamento.getTipo() + " : " +
                        equipamento.getX() + " : " +
                        equipamento.getY());
                writer.newLine();
            }

            // 6. Número de portas
            writer.write(String.valueOf(portas.size()));
            writer.newLine();

            // 7. Escrever as portas
            for (Porta porta : portas) {
                writer.write(porta.getX() + " : " + porta.getY());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new IOException("Erro ao salvar o jogo no arquivo: " + e.getMessage());
        }
    }

    public JPanel getCreditsPanel(){
        return null;
    }


    public HashMap<String,String> customizeBoard() {

        HashMap<String, String> map = new HashMap<>();

        map.put("title", "Apocalypse in the backrooms");

        return map;


    }



    // Main (;
    public static void main(String[] args) {}

}