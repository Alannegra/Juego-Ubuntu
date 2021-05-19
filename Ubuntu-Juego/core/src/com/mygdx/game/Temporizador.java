package com.mygdx.game;

public class Temporizador {
    static float tiempoJuego;
    float alarma;
    float frecuencia;
    boolean repetir = true;
    boolean activo = true;

    Temporizador(float frecuencia) {
        this.frecuencia = frecuencia;
        alarma = tiempoJuego + frecuencia;
    }

    Temporizador(float frecuencia, boolean repetir) {
        this.frecuencia = frecuencia;
        alarma = tiempoJuego + frecuencia;
        this.repetir = repetir;
    }

    public boolean suena() {
        if (activo && tiempoJuego >= alarma) {
            alarma = tiempoJuego + frecuencia;
            if (!repetir) activo = false;
            return true;
        }
        return false;
    }

    public void activar() {
        activo = true;
        alarma = tiempoJuego + frecuencia;
    }

    public void finalizar() {

        activo = false;
        alarma = tiempoJuego + frecuencia;

    }


}
