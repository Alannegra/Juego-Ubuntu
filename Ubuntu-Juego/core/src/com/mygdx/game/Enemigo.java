package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemigo {
    Animacion animacion = new Animacion(8f, true, "walk_1.png", "walk_2.png","walk_3.png","walk_4.png","walk_5.png");
    Animacion animacion2 = new Animacion(8f, true, "attack_1.png","attack_2.png","attack_3.png","attack_4.png","attack_5.png","attack_6.png","attack_7.png");
    Animacion animacion3 = new Animacion(8f, true, "idle_1.png","idle_2.png","idle_3.png","idle_4.png","idle_5.png","idle_6.png");
    Animacion animacion4 = new Animacion(8f, true, "nimbus_0.png","shoot.png");



    float x, y, w, h, vx, vy;
    Temporizador cambioVelocidad = new Temporizador(30);

    boolean muerto,ani = false;
    boolean boom = false;

    Temporizador temporizadorRespawn = new Temporizador(60, false);

    Enemigo() {
        x = 640;
        y = Utils.random.nextInt(480);
        w = 64 * 2;
        h = 48 * 2;
        vx = -2;
        vy = 0;
    }

    public void update() {
        y += vy;
        x += vx;

        if (cambioVelocidad.suena()) {
            vy = Utils.random.nextInt(3) - 1;
            vx = -(Utils.random.nextInt(3)+1);
        }


        if (temporizadorRespawn.suena()) {
            muerto = false;
        }

        if (y < 0) y = 0;

        if (y > 390) y = 390;
    }

    void render(SpriteBatch batch) {

        if (muerto){
            //X y Y DEL JUGADOR
            batch.draw(animacion2.getFrame(Temporizador.tiempoJuego), x, y, w, h);
        }else if(boom){

            batch.draw(animacion4.getFrame(Temporizador.tiempoJuego), x, y, w, h);
        }else{
            batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);
        }


        //batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);
    }


    public void morir(Jugador jugador) {
        muerto = true;
        temporizadorRespawn.activar();

        if (muerto){
            y = jugador.y;
            x = jugador.x;
        }
    }


    public void boomir() {
        boom = true;
        temporizadorRespawn.activar();
    }



}
