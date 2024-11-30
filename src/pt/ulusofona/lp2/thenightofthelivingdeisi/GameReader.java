package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameReader {

    // Cria
    // Cria o scanner
    public static Scanner criarScanner(File game, int numeroDaLinha) throws InvalidFileException{

        Scanner scanner = null;
        try {

            scanner = new Scanner(game);

        } catch (FileNotFoundException e) {

            throw new InvalidFileException("Não encontrou ficheiro", numeroDaLinha);

        }

        return scanner;
    }




    // Le uma so linha
    // Vai ler o tamanho do tabuleiro
    public static int[] lerTamanhoTabuleiro(Scanner scanner, int numeroDaLinha) throws InvalidFileException{

        // Verefica se existe uma proxima linha
        if(!scanner.hasNext()){

            throw new InvalidFileException("Erro: Falta da linha das dimensoes.", numeroDaLinha);

        }


        // Apanha a proxima linha e a divide, e verefica se ela tem as duas dimensoes
        String[] partes = scanner.nextLine().split(" ");
        if(partes.length != 2){

            throw new InvalidFileException("Erro: Numero de dimensoes errada.", numeroDaLinha);

        }


        // Verefica se as dimensoes sao numeros
        int[] dimensoes = new int [2];
        try {

            dimensoes[0] = Integer.parseInt(partes[0]);
            dimensoes[1] = Integer.parseInt(partes[1]);

        }
        catch (NumberFormatException e) {

            throw new InvalidFileException("Erro: As dimensoes do tabuleiro tem de ser numeros.", numeroDaLinha);

        }


        // Faz return ao processo
        return dimensoes;

    }


    // Vai ler o id inicial
    public static int lerIdInicial(Scanner scanner, int numeroDaLinha) throws InvalidFileException{

        // Verefica se existe uma proxima linha
        if(!scanner.hasNext()){

            throw new InvalidFileException("Erro: Falta da linha do id inicial.", numeroDaLinha);

        }


        // Verefica se o id inicial é um numero
        int id;
        try{

            id = Integer.parseInt(scanner.nextLine());

        }
        catch (NumberFormatException e){

            throw new InvalidFileException("Erro: O id inicial tem de ser um numero.", numeroDaLinha);

        }

        // Faz return ao id
        return id;

    }


    // Ler numero de criaturas que vai haver no tabuleiro
    public static int lerNumeroDeCriaturas(Scanner scanner, int numeroDaLinha) throws InvalidFileException{

        // Verefica se existe uma proxima linha
        if(!scanner.hasNext()){

            throw new InvalidFileException("Erro: Falta da linha do numero de criaturas.", numeroDaLinha);

        }


        // Verefica se o id inicial é um numero
        int nrCriaturas;
        try{

            nrCriaturas = Integer.parseInt(scanner.nextLine());

        }
        catch (NumberFormatException e){

            throw new InvalidFileException("Erro: O numero de criaturas tem de ser um numero.", numeroDaLinha);

        }

        // Faz return ao id
        return nrCriaturas;

    }


    // Ler numero de equipamento que vai haver no tabuleiro
    public static int lerNumeroDeEquipamento(Scanner scanner, int numeroDaLinha) throws InvalidFileException{

        // Verefica se existe uma proxima linha
        if(!scanner.hasNext()){

            return 0;

        }


        // Verefica se o id inicial é um numero
        int nrEquipamentos;
        try{

            nrEquipamentos = Integer.parseInt(scanner.nextLine());

        }
        catch (NumberFormatException e){

            throw new InvalidFileException("Erro: O numero de equipamento tem de ser um numero.", numeroDaLinha);

        }

        // Faz return ao id
        return nrEquipamentos;

    }


    // Ler numero de portas para o safe haven que vai haver no tabuleiro
    public static int lerNumeroDePortas(Scanner scanner, int numeroDaLinha) throws InvalidFileException{

        // Verefica se existe uma proxima linha
        if(!scanner.hasNext()){

            return 0;

        }


        // Verefica se o numero de portas é um numero
        int nrPortas;
        try{

            nrPortas = Integer.parseInt(scanner.nextLine());

        }
        catch (NumberFormatException e){

            throw new InvalidFileException("Erro: O numero de portas tem de ser um numero.", numeroDaLinha);

        }

        // Faz return ao id
        return nrPortas;

    }




    // Le varias linhas de dados
    // Vai ler as criaturas do ficheiro
    public static HashMap<Integer, Creature> lerCriaturas(Scanner scanner, int numeroDaLinha, int nrCriaturas, int[] worldSize) throws InvalidFileException{

        // Vai ler todas as linhas com as informaçoes das criaturas
        HashMap<Integer, Creature> criaturas = new HashMap<>();
        for(int count = 0; count < nrCriaturas; count++){

            numeroDaLinha++;

            // Verefica se existe uma proxima linha
            if(!scanner.hasNext()){

                throw new InvalidFileException("Erro: Falta da linha com informaçoes da criatura.", numeroDaLinha);

            }


            // Verefica o numero de informaçoes da linha
            String[] partes = scanner.nextLine().split(" : ");
            if(partes.length != 6){

                throw new InvalidFileException("Erro: Falta de informaçoes da criatura.", numeroDaLinha);

            }


            // Verefica se as informaçoes estao no formato correto
            int id;

            int tipo;
            int categoria;

            String nome;

            int posX;
            int posY;

            try{

                id = Integer.parseInt(partes[0]);

                tipo = Integer.parseInt(partes[1]);
                categoria = Integer.parseInt(partes[2]);

                nome = partes[3];

                posX = Integer.parseInt(partes[4]);
                posY = Integer.parseInt(partes[5]);

            }
            catch (NumberFormatException e){

                throw new InvalidFileException("Erro: O formato dos dados nao estao no formato correto.", numeroDaLinha);

            }


            // Verficar se a criatura esta dentro do tabuleiro
            if (posX < 0 || posX >= worldSize[1] || posY < 0 || posY >= worldSize[0]) {

                continue;

            }


            // Cria a criatura
            Creature criatura;
            // Equipa Humano
            if(tipo == 20){

                criatura = switch (categoria) {

                    case 0 -> new Crianca(id, tipo, categoria, nome, posX, posY); // Criança
                    case 1 -> new Adulto(id, tipo, categoria, nome, posX, posY);  // Adulto
                    case 2 -> new Idoso(id, tipo, categoria, nome, posX, posY);   // Idoso
                    case 3 -> new Cao(id, tipo, categoria, nome, posX, posY); // Cão
                    default -> throw new InvalidFileException("Erro: A categoria para a criatura da equipa Humano nao existe.", numeroDaLinha);

                };

            }
            // Equipa Zombie
            else if(tipo == 10){

                criatura = switch (categoria) {

                    case 0 -> new Crianca(id, tipo, categoria, nome, posX, posY); // Criança
                    case 1 -> new Adulto(id, tipo, categoria, nome, posX, posY);  // Adulto
                    case 2 -> new Idoso(id, tipo, categoria, nome, posX, posY);   // Idoso
                    case 4 -> new Vampiro(id, tipo, categoria, nome, posX, posY); // Vampiro
                    default -> throw new InvalidFileException("Erro: A categoria para a criatura da equipa Zombie nao existe.", numeroDaLinha);

                };

            }
            // Erro
            else{

                throw new InvalidFileException("Erro: O tipo da criatura nao existe", numeroDaLinha);

            }

            // Adiciona a criatura processada no hashMap
            criaturas.put(id, criatura);

        }

        // Return
        return criaturas;

    }


    // Vai ler os equipamentos do ficheiro
    public static HashMap<Integer, Equipment> lerEquipamentos(Scanner scanner, int numeroDaLinha, int nrEquipamentos, int[] worldSize) throws InvalidFileException{

        // Vai ler todas as linhas com as informaçoes das criaturas
        HashMap<Integer, Equipment> equipamentos = new HashMap<>();

        for(int count = 0; count < nrEquipamentos; count++){

            numeroDaLinha++;

            // Verefica se existe uma proxima linha
            if(!scanner.hasNext()){

                throw new InvalidFileException("Erro: Falta da linha com informaçoes do equipamento.", numeroDaLinha);

            }


            // Verefica o numero de informaçoes da linha
            String[] partes = scanner.nextLine().split(" : ");

            if(partes.length != 4){

                throw new InvalidFileException("Erro: Falta de informaçoes do equipamento.", numeroDaLinha);

            }


            // Verefica se as informaçoes estao no formato correto
            int id;

            int tipo;

            int posX;
            int posY;

            try{

                id = Integer.parseInt(partes[0]);

                tipo = Integer.parseInt(partes[1]);

                posX = Integer.parseInt(partes[2]);
                posY = Integer.parseInt(partes[3]);

            }
            catch (NumberFormatException e){

                throw new InvalidFileException("Erro: O formato dos dados nao estao no formato correto.", numeroDaLinha);

            }


            // Verficar se o equipamento esta dentro do tabuleiro
            if (posX < 0 || posX >= worldSize[1] || posY < 0 || posY >= worldSize[0]) {

                continue;

            }


            // Cria o equipamento
            Equipment equipamento;
            equipamento = switch (tipo) {

                case 0 -> new Escudo(id, tipo, posX, posY); // Escudo
                case 1 -> new Espada(id, tipo, posX, posY); // Espada
                case 2 -> new Pistola(id, tipo, posX, posY); // Pistola
                case 3 -> new Lixivia(id, tipo, posX, posY); // Lixivia
                default -> throw new InvalidFileException("Erro: O tipo para o equipamento nao existe.", numeroDaLinha);

            };


            // Adiciona a criatura processada no hashMap
            equipamentos.put(id, equipamento);

        }

        // Return
        return equipamentos;

    }


    // Vai ler as portas do ficheiro
    public static ArrayList<Porta> lerPortas(Scanner scanner, int numeroDaLinha, int nrPortas, int[] worldSize) throws InvalidFileException{

        // Vai ler todas as linhas com as informaçoes das criaturas
        ArrayList<Porta> portas = new ArrayList<>();

        for(int count = 0; count < nrPortas; count++){

            numeroDaLinha++;

            // Verefica se existe uma proxima linha
            if(!scanner.hasNext()){

                throw new InvalidFileException("Erro: Falta da linha com informaçoes da porta.", numeroDaLinha);

            }


            // Verefica o numero de informaçoes da linha
            String[] partes = scanner.nextLine().split(" : ");

            if(partes.length != 2){

                throw new InvalidFileException("Erro: Falta de informaçoes da porta.", numeroDaLinha);

            }


            // Verefica se as informaçoes estao no formato correto
            int posX;
            int posY;

            try{

                posX = Integer.parseInt(partes[0]);
                posY = Integer.parseInt(partes[1]);


            }
            catch (NumberFormatException e){

                throw new InvalidFileException("Erro: O formato dos dados nao estao no formato correto.", numeroDaLinha);

            }


            // Verficar se o equipamento esta dentro do tabuleiro
            if (posX < 0 || posX >= worldSize[1] || posY < 0 || posY >= worldSize[0]) {

                continue;

            }


            // Cria o equipamento
            Porta porta = new Porta(posX, posY);


            // Adiciona a criatura processada no hashMap
            portas.add(porta);

        }

        // Return
        return portas;

    }

}
