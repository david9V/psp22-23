package ch.makery.address.controller;

import ch.makery.address.MyHilo;
import ch.makery.address.MyHilo_2;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class Controller {
    @FXML
    Label estado1;
    @FXML
    Label estado2;
    @FXML
    Label nombre1;
    @FXML
    Label nombre2;
    MyHilo_2 h1;
    MyHilo_2 h2;



    @FXML
    private void initialize() {
        this.h1 = new MyHilo_2();
        this.h2 = new MyHilo_2();


        h1.contadorProperty().addListener((observable, oldValue, newValue) -> {
            estado1.setText(String.valueOf(h1.getContador()));
        });
        h2.contadorProperty().addListener((observable, oldValue, newValue) -> {
            estado2.setText(String.valueOf(h2.getContador()));
        });
    }

    @FXML
    private void handleComenzar() {
        this.h1.start();
        this.h2.start();
        nombre1.setText("EN PROCESO");
        nombre2.setText("EN PROCESO");
    }

    @FXML
    private void handleReanudar1() {
        this.h1.Reanuda();
        nombre1.setText("EN PROCESO");
    }

    @FXML
    private void handleSuspender1() {
        this.h1.Suspende();
        nombre1.setText("SUSPENDIDO");
    }

    @FXML
    private void handleReanudar2() {
        this.h2.Reanuda();
        nombre2.setText("EN PROCESO");

    }

    @FXML
    private void handleSuspender2() {
        this.h2.Suspende();
        nombre2.setText("SUSPENDIDO");
    }

    @FXML
    private void handleFinalizar() {
        this.h1.fin = true;
        this.h2.fin = true;
        nombre1.setText("FIN");
        nombre2.setText("FIN");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RESULTADO");
        alert.setHeaderText("Contador del hilo 1: " + h1.getContador());
        alert.setContentText("Contador del hilo 2: " + h2.getContador());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            alert.close();
        }
    }
}
