package iesfranciscodelosrios.timerproject;

import iesfranciscodelosrios.timerproject.model.Cronometro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class CronometroController {

    @FXML
    private Text textHours;
    @FXML
    private Text textMinutes;
    @FXML
    private Text textSeconds;

    private Cronometro cronometro;

    public void initialize() {
        cronometro = new Cronometro(textHours, textMinutes, textSeconds);
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
