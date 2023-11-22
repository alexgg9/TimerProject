package iesfranciscodelosrios.timerproject.model;

import javafx.application.Platform;
import javafx.scene.text.Text;

public class Cronometro extends Thread{
    private long tiempoPausado = 0;
    private final Object lock = new Object();
    private boolean running = false;
    private boolean paused = false;
    private long startTime;
    private Text textHours;
    private Text textMinutes;
    private Text textSeconds;

    public Cronometro(Text textHours, Text textMinutes, Text textSeconds) {
        this.textHours = textHours;
        this.textMinutes = textMinutes;
        this.textSeconds = textSeconds;
    }

    public void iniciar() {
        if (!running) {
            running = true;
            if (tiempoPausado == 0) {
                startTime = System.currentTimeMillis();
            } else {
                startTime = System.currentTimeMillis() - tiempoPausado;
                tiempoPausado = 0;
            }
            start();
        }
    }



    public void detener() {
        running = false;
        paused = false;
        tiempoPausado = 0;
        synchronized (lock) {
            lock.notify();
        }
        Platform.runLater(() -> {
            textHours.setText("00");
            textMinutes.setText("00");
            textSeconds.setText("00");
        });
    }

    public void pausar() {
        if (running) {
            running = false;
            paused = true;
            tiempoPausado = System.currentTimeMillis() - startTime;
        }
    }

    public void run() {
        while (running) {
            synchronized (lock) {
                try {
                    while (paused) {
                        lock.wait();
                    }
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    String tiempoFormateado = formatoTiempo(elapsedTime);
                    updateLabels(tiempoFormateado);
                    lock.wait(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        running = false;
    }



    private String formatoTiempo(long elapsedTime) {
        long segundos = elapsedTime / 1000;
        long horas = segundos / 3600;
        long minutos = (segundos % 3600) / 60;
        segundos = segundos % 60;
        String tiempoFormateado = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        return tiempoFormateado;
    }

    private void updateLabels(String tiempoFormateado) {
        String[] parts = tiempoFormateado.split(":");
        Platform.runLater(() -> {
            textHours.setText(parts[0]);
            textMinutes.setText(parts[1]);
            textSeconds.setText(parts[2]);
        });
    }

}
