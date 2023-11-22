package iesfranciscodelosrios.timerproject;

import iesfranciscodelosrios.timerproject.model.Cronometro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CronometroController {

    @FXML
    private Label labelTiempo;

    private Cronometro cronometro;

    public void initialize() {
        cronometro = new Cronometro();
        cronometro.setLabelTiempo(labelTiempo);
    }

    @FXML
    private void handleIniciar(ActionEvent event) {
        cronometro.iniciar();
    }

    @FXML
    private void handlePausar(ActionEvent event) {
        cronometro.pausar();
    }

    @FXML
    private void handleReanudar(ActionEvent event) {
        cronometro.reanudar();
    }

    @FXML
    private void handleDetener(ActionEvent event) {
        cronometro.detener();
    }
}
