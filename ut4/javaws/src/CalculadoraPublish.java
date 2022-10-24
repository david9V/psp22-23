import javax.xml.ws.Endpoint;

public class CalculadoraPublish {
    public static void main(String args[]){
        Endpoint.publish("http://localhost:1516/WS/Calculadora", new CalculadoraImpl());
    }
}
