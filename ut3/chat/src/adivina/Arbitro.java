package adivina;

public class Arbitro {

    private int nJugadores;
    private int turno;
    private int numero;
    private boolean fin;

    public Arbitro(int nJugadores, int turno) {
        this.nJugadores = nJugadores;
        this.turno = turno;
        this.numero = 1+(int) (10*Math.random());
        this.fin = false;
    }


    public synchronized void compruebaJugada(int jugador, int jugada) {

        while (turno != jugador) {  // Si un jugador (hilo) accede al Arbitro (recurso compartido) pero no es su turno
                                    // pasa a un estado de bloqueo.
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        if (fin) { //doNothing    // Otra posibilidad es que uh jugador (hilo) que estuviera en estado de bloqueo y al desbloquearse
                                    // puede acceder al recurso, pero se encuentra que ya se acabó el juego porque se adivinó el número.
        } else {


            if (turno == nJugadores) { // Si el jugador es al que le toca el turno y no se ha adivinado aún el número
                                       // actualizamos el turno para el siguiente jugador.
                turno = 1;
            } else turno++;

            System.out.println("Jugador " + jugador + " dice: " + jugada);
            if (this.numero == jugada) {   // se comprueba si el número del jugador es el número a adivinar
                                           // en cuyo caso se debe cambiar el valor de fin a true.
                System.out.println("  Jugador " + jugador + " gana, adivina el número!!!");
                fin = true;
              //  notifyAll();
            } else {
                System.out.println("        Le toca a Jugador " + turno);

            }
            notifyAll();   // se despiertan a todos los hilos que estuvieran bloqueados.
        }
    }


    public int getNumero() {
        return numero;
    }

    public int getTurno() {
        return turno;
    }

    public boolean isFin() {
        return fin;
    }
}
