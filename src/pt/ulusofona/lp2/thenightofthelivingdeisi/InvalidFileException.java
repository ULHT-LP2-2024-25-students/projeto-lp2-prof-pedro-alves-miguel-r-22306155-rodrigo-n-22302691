package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class InvalidFileException extends Exception {

    // Atributos
    int linhaDoErro;




    // Construtor
    public InvalidFileException(String message, int linhaDoErro) {
        super(message);
        this.linhaDoErro = linhaDoErro;
    }




    // Metodos
    // Vai dizer qual a linha do ficheiro do jogo o erro ocorreu
    public int getLineWithError(){
        return linhaDoErro;
    }

}