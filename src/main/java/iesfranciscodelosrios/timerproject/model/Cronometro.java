package iesfranciscodelosrios.timerproject.model;

import javafx.scene.control.Label;

public class Cronometro extends Thread{
    private volatile boolean running = false;
    private volatile boolean paused = false;
    private long startTime;


    private Label labelTiempo; // Referencia al componente de la interfaz gráfica

    public void setLabelTiempo(Label labelTiempo) {
        this.labelTiempo = labelTiempo;
    }
    public void iniciar(){
        running = true;
        startTime = System.currentTimeMillis();
        start();
    }

    public void detener(){
        running =false;
        paused = false;
    }

    public void reanudar(){
        paused = false;
    }
    public void pausar(){
        paused = true;
    }

    public void run() {
        while (running) {
            if (!paused) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                // Puedes imprimir el tiempo transcurrido o realizar alguna otra acción
                System.out.println("Tiempo transcurrido: " + elapsedTime + " ms");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
