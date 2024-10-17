package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Info {

        int id;
        String tipo;
        String nome;
        int posicaoX;
        int posicaoY;
        String png;

        public Info(int id, String tipo, String nome, int posicaoX, int posicaoY, String png) {
            this.id = id;
            this.tipo = tipo;
            this.nome = nome;
            this.posicaoX = posicaoX;
            this.posicaoY = posicaoY;
            this.png = png;
        }

        @Override
        public String toString() {

            if (id > 0) {
                return id + " | " + tipo + " | " + nome + " | " + png;
            }

            return id + " | " + tipo + " " + png;
        }
    }
