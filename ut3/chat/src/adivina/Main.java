package adivina;

public class Main {

    public static void main(String[] args) {

        Arbitro arbitro = new Arbitro(3,1);
        Jugador j1 = new Jugador(1,arbitro);
        Jugador j2 = new Jugador(2,arbitro);
        Jugador j3 = new Jugador(3,arbitro);


        System.out.println("N?MERO A ADIVINAR: " + arbitro.getNumero());


        j1.start();
        j2.start();
        j3.start();



    }

}
