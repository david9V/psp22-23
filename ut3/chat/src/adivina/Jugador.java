package adivina;

public class Jugador extends Thread {

    private int id;
    private Arbitro arbitro;

    public Jugador(int id, Arbitro arbitro) {
        this.id = id;
        this.arbitro = arbitro;
    }


    @Override
    public void run() {

        while (!arbitro.isFin()) {
            arbitro.compruebaJugada(id, (1 + (int) (10 * Math.random())));
        }
    }

}