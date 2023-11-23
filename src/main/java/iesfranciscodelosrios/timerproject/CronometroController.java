package iesfranciscodelosrios.timerproject;

import iesfranciscodelosrios.timerproject.model.Cronometro;
import iesfranciscodelosrios.timerproject.model.EstadoCronometro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CronometroController {

    @FXML
    private Text textHours;
    @FXML
    private Text textMinutes;
    @FXML
    private Text textSeconds;
    @FXML
    private Button iniciarButton;
    @FXML
    private Button detenerButton;
    @FXML
    private Button reiniciarButton;

    private Cronometro cronometro;
    private EstadoCronometro estadoCronometro;

    public void initialize() {
        cronometro = new Cronometro(textHours, textMinutes, textSeconds);
        estadoCronometro = EstadoCronometro.DETENIDO;
        actualizarBotones();
    }

    private void actualizarBotones() {
        switch (estadoCronometro) {
            case CONTANDO:
                iniciarButton.setText("CONTANDO");
                detenerButton.setText("DETENER");
                reiniciarButton.setText("REINICIAR");
                detenerButton.setDisable(false);
                iniciarButton.setDisable(true);
                break;
            case PAUSADO:
                iniciarButton.setText("DETENIDO");
                detenerButton.setText("REANUDAR");
                detenerButton.setDisable(false);
                iniciarButton.setDisable(true);
                reiniciarButton.setDisable(false);
                break;
            case DETENIDO:
                iniciarButton.setText("INICIAR");
                detenerButton.setText("DETENER");
                reiniciarButton.setText("REINICIAR");
                iniciarButton.setDisable(false);
                detenerButton.setDisable(false);
                reiniciarButton.setDisable(false);
                break;
            case REINICIADO:
                iniciarButton.setText("INICIAR");
                detenerButton.setText("DETENIDO");
                detenerButton.setDisable(true);
                iniciarButton.setDisable(false);
                reiniciarButton.setDisable(true);
                estadoCronometro = EstadoCronometro.DETENIDO;
                break;
        }
    }

    @FXML
    private void handleIniciar(ActionEvent event) {
        if (estadoCronometro == EstadoCronometro.DETENIDO || estadoCronometro == EstadoCronometro.REINICIADO) {
            cronometro.iniciar();
            estadoCronometro = EstadoCronometro.CONTANDO;
        } else if (estadoCronometro == EstadoCronometro.CONTANDO) {
            cronometro.pausar();
            estadoCronometro = EstadoCronometro.PAUSADO;
        } else if (estadoCronometro == EstadoCronometro.PAUSADO) {
            cronometro.reanudar();
            estadoCronometro = EstadoCronometro.CONTANDO;
        }
        actualizarBotones();
    }

    @FXML
    private void handleDetener(ActionEvent event) {
        if (estadoCronometro == EstadoCronometro.CONTANDO) {
            cronometro.pausar();
            estadoCronometro = EstadoCronometro.PAUSADO;
        } else if (estadoCronometro == EstadoCronometro.PAUSADO) {
            cronometro.reanudar();
            estadoCronometro = EstadoCronometro.CONTANDO;
        }
        actualizarBotones();
    }

    @FXML
    private void handleReiniciar(ActionEvent event) {
        cronometro.reiniciar();
        estadoCronometro = EstadoCronometro.REINICIADO;
        System.out.println("entra");
        actualizarBotones();
    }
}