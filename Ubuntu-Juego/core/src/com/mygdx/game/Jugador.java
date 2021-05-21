package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    Musica musica = new Musica();
    Animacion animacion = new Animacion(6f, true, "frost.png", "frost2.png", "frost3.png","frost4.png","frost5.png" );
    float x, y, w, h, v;
    List<Disparo> disparos = new ArrayList<>();
    int vidas = 3;
    int puntos = 0;
    float z = 1;
    boolean muerto = false;

    Temporizador temporizadorFireRate = new Temporizador(20);
    Temporizador temporizadorRespawn = new Temporizador(120, false);



    Jugador() {



        x = 100;
        y = 100;
        w = 43 * 2;
        h = 21 * 2;
        v = 5;


    }



    void update() {
        temporizadorFireRate.frecuencia = 20-z;
        for (Disparo disparo : disparos) disparo.update();

        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += v;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += v;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= v;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && temporizadorFireRate.suena() && !muerto) {
            disparos.add(new Disparo(x + w / 2 + 10, y + h -25));
            musica.soundshot();
        }

        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 550) x = 550;
        if (y > 440) y = 440;

        if (temporizadorRespawn.suena()) {
            muerto = false;
        }
    }

    void render(SpriteBatch batch) {
        if (muerto) batch.setColor(1, 1, 1, 0.25f);
        batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);
        if (muerto) batch.setColor(1, 1, 1, 1);

        for (Disparo disparo : disparos) disparo.render(batch);
    }



    public void strake(float quitar) {

        v += quitar ;
        z += 0.5;

    }

    public void morir() {
        if(vidas > 0)vidas--;

        muerto = true;
        temporizadorRespawn.activar();
    }
}
