public class HiloEjemplo1_V2 extends Thread{
    public HiloEjemplo1_V2(String nombre){

        super(nombre);
        System.out.println("CREANDO HILO:" + getName());
    }

    public void run(){
        for (int i = 0; i < 5; i++){
            System.out.println("Hilo: " +getName() + " C = " + i);
        }
    }
}
