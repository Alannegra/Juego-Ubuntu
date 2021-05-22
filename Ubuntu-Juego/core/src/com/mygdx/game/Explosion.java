package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion {

    Animacion animacion4 = new Animacion(8f, true, "nimbus_0.png","shoot.png");


    float x, y,w, h;
    Temporizador temporizadorRespawn = new Temporizador(60, false);

    Explosion(float lolx, float loly) {

        x = lolx;
        y = loly;

        w = 64 * 2;
        h = 48 * 2;

    }


    void render(SpriteBatch batch) {



        batch.draw(animacion4.getFrame(Temporizador.tiempoJuego), x, y, w, h);

    }




}
