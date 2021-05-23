package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Meteor {


    Animacion animacion5 = new Animacion(8f, true, "meteor1.png","meteor2.png","meteor3.png","meteor4.png","meteor5.png","meteor6.png","meteor7.png","meteor8.png");
    float x, y, w, h, vx;
    static float z = 2;


    public Meteor() {

        x = 640;
        y = Utils.random.nextInt(480);
        w = 64 * 2;
        h = 48 * 2;
        vx = -2;



    }

    public static void reiniciarMeteorito() {
        z = 2;
    }

    public void update() {



        x -= vx;

        vx = z;
        if (y < 0) y = 0;

        if (y > 390) y = 390;

    }

    void render(SpriteBatch batch) {

        batch.draw(animacion5.getFrame(Temporizador.tiempoJuego), x, y, w, h);

    }


    void strake() {

        z +=1;

    }
}
