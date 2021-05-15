package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    BitmapFont bitmapFont;

    Jugador jugador;
    List<Fondo> fondos = new ArrayList<>();
    Fondo fondo;

    List<Enemigo> enemigos = new ArrayList<>();
    List<Disparo> disparosAEliminar = new ArrayList<>();
    List<Enemigo> enemigosAEliminar = new ArrayList<>();

    Temporizador temporizadorNuevoAlien = new Temporizador(120);
    private ScoreBoard scoreboard;
    private boolean gameover;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bitmapFont.getData().setScale(2f);



        jugador = new Jugador();
        scoreboard = new ScoreBoard();

        fondos.add(new Fondo());
    }

    void update() {


        Temporizador.tiempoJuego += 1;


        for (Fondo fondo : fondos) fondo.update();


        if (fondos.get(0).z <= 0){

            fondos.remove(0);
            fondos.add(new Fondo());


        }





        if (temporizadorNuevoAlien.suena()) enemigos.add(new Enemigo());

        if(!gameover) {
            jugador.update();
        }

        for (Enemigo enemigo : enemigos) enemigo.update();              // enemigos.forEach(Enemigo::update);

        for (Enemigo enemigo : enemigos) {
            for (Disparo disparo : jugador.disparos) {
                if (Utils.solapan(disparo.x, disparo.y, disparo.w, disparo.h, enemigo.x, enemigo.y, enemigo.w, enemigo.h)) {
                    disparosAEliminar.add(disparo);
                    enemigosAEliminar.add(enemigo);
                    jugador.puntos+= 10;

                    for (Fondo fondo : fondos) fondo.quitar +=1;

                    break;
                }
            }

            if (!jugador.muerto && Utils.solapan(enemigo.x, enemigo.y, enemigo.w, enemigo.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
                jugador.morir();
                enemigo.morir(jugador);
                if (jugador.vidas == 0){
                    gameover = true;
                    scoreboard.guardarPuntuacion(jugador.puntos);
                }
            }

            if (enemigo.x < -enemigo.w) enemigosAEliminar.add(enemigo);
        }

        for (Disparo disparo : jugador.disparos)
            if (disparo.x > 640)
                disparosAEliminar.add(disparo);

        for (Disparo disparo : disparosAEliminar) jugador.disparos.remove(disparo);       // disparosAEliminar.forEach(disparo -> jugador.disparos.remove(disparo));
        for (Enemigo enemigo : enemigosAEliminar){



            //----------------------ANIMACION-MUERTE-----------------------------------
            if(enemigo.boom){


                enemigos.remove(enemigo);

            }
            enemigo.boomir();
            //------------------------ANIMACION-MUERTE---------------------------------


        }

        // enemigosAEliminar.forEach(enemigo -> enemigos.remove(enemigo));
        disparosAEliminar.clear();
        enemigosAEliminar.clear();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        batch.begin();

        for (Fondo fondo : fondos) fondo.render(batch);

        jugador.render(batch);

        for (Enemigo enemigo : enemigos){

            if(enemigo.boom){
                bitmapFont.draw(batch, "+10" , enemigo.x + 35, enemigo.y);
            }
            enemigo.render(batch);  // enemigos.forEach(e -> e.render(batch));

        }
        bitmapFont.draw(batch, "" + jugador.vidas, 590, 440);
        bitmapFont.draw(batch, "" + jugador.puntos, 30, 440);

        if (gameover){

            scoreboard.render(batch, bitmapFont);
        }

        if(jugador.vidas == 0) bitmapFont.draw(batch, "GAME OVER" , 240, 200);

        for (Enemigo enemigo : enemigosAEliminar){


            //------------------------ANIMACION-MUERTE---------------------------------
            if(!enemigo.ani){
                enemigo.ani = true;

            }
            //------------------------ANIMACION-MUERTE---------------------------------

        }




        batch.end();
    }
}

/*

init();

create();

while(true) render();

 */