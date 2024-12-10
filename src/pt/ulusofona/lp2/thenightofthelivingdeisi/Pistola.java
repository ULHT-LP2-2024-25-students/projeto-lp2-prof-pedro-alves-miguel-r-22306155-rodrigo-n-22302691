package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Pistola extends Equipment {

    // Atributos
    private int municao;

    // Construtores
    public Pistola(int id, int tipo, int posicaoX, int posicaoY) {
        super(id, tipo, posicaoX, posicaoY);
        this.municao = 3; // Inicializa com 3 balas
    }

    // Usa a arma, decrementando a munição
    @Override
    public boolean usarArma() {

        if (municao > 0) {
            municao--;
            return true; // Arma usada com sucesso
        }
        return false; // Sem munição
    }

    public int getMunicao() {
        return municao;
    }

    // Verifica se a arma é defensiva
    @Override
    public boolean tipoArma() {
        return false;
    }

    // Informação detalhada sobre a pistola
    @Override
    public String info() {
        return "E:" + id;
    }

    // Representação textual da pistola
    @Override
    public String toString() {
        return id + " | Pistola Walther PPK @ " + coordenadas() + " | " + municao + " balas";
    }
}
