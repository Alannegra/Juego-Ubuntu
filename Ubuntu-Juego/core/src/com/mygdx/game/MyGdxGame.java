package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

    Musica musica;



    Jugador jugador;

    Fondo fondo;
    Explosion explosion;
    Meteor meteor ;
    List<Enemigo> enemigos = new ArrayList<>();
    List<Explosion> enemigosEx = new ArrayList<>();
    List<Explosion> enemigosExdelete = new ArrayList<>();
    List<Enemigo> enemigosDaño = new ArrayList<>();
    List<Enemigo> enemigosDañoAElimnar = new ArrayList<>();
    List<Disparo> disparosAEliminar = new ArrayList<>();
    List<Enemigo> enemigosAEliminar = new ArrayList<>();
    List<Meteor> meteors = new ArrayList<>();
    List<Meteor> meteorAEliminar = new ArrayList<>();


    Temporizador temporizadorNuevoAlien;
    Temporizador temporizadorNuevoMeteorito;
    Temporizador temporizadorEXPLOSION;
    Temporizador temporizadorDaño;
    private ScoreBoard scoreboard;
    private boolean gameover;
    public int z = 0;
    public float x = 0;
    public float y = 0;
    public boolean xd = false;
    public boolean xd2 = false;


    @Override
    public void create() {
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.WHITE);

        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bitmapFont.getData().setScale(2f);



        inicializarJuego();

    }

    void inicializarJuego(){
        fondo = new Fondo();
        Fondo.reiniciarFondo();
        jugador = new Jugador();
        meteor= new Meteor();
        musica = new Musica();
        enemigos = new ArrayList<>();
        meteors = new ArrayList<>();
        temporizadorNuevoAlien = new Temporizador(120);
        temporizadorNuevoMeteorito = new Temporizador(120);
        temporizadorEXPLOSION = new Temporizador(60);
        temporizadorDaño = new Temporizador(60);

        disparosAEliminar = new ArrayList<>();
        enemigosAEliminar = new ArrayList<>();
        scoreboard = new ScoreBoard();
        Enemigo.reiniciarEnemigo();
        Meteor.reiniciarMeteorito();
        z = 0;


       musica.musicbackground(false);


        gameover = false;

    }

    void update() {


        Temporizador.tiempoJuego += 1;

        fondo.update();


        if (temporizadorNuevoAlien.suena()) enemigos.add(new Enemigo());
        if (temporizadorNuevoMeteorito.suena()) meteors.add(new Meteor());


        if(!gameover) {
            jugador.update();

        }

        if(xd){
            for (Enemigo enemigo : enemigosDaño) {
                enemigo.morir(jugador);
                enemigosDañoAElimnar.add(enemigo);
            }
        }

        if(temporizadorDaño.suena()){
            xd= false;
            for (Enemigo enemigo : enemigosDañoAElimnar) {
                enemigosDaño.remove(enemigo);
            }

        }






        for (Enemigo enemigo : enemigos) enemigo.update();              // enemigos.forEach(Enemigo::update);
        for (Meteor meteor : meteors) meteor.update();

        for (Enemigo enemigo : enemigos) {
            for (Disparo disparo : jugador.disparos) {
                if (Utils.solapan(disparo.x, disparo.y, disparo.w, disparo.h, enemigo.x, enemigo.y, enemigo.w, enemigo.h)) {
                    disparosAEliminar.add(disparo);
                    enemigosAEliminar.add(enemigo);

                    jugador.puntos+= 10;
                    jugador.strake(0.5f);
                    enemigo.strake(0.5f);
                    fondo.strake();
                    meteor.strake();
                    z+= 2;
                    temporizadorNuevoAlien.frecuencia = 120-z ;
                    temporizadorNuevoMeteorito.frecuencia = 120-z ;

                    musica.sounddead();

                    enemigosEx.add(new Explosion(enemigo.x,enemigo.y));

                   // explosion = new Explosion(enemigo.x,enemigo.y);


                    break;
                }

            }

            if (!jugador.muerto && Utils.solapan(enemigo.x, enemigo.y, enemigo.w, enemigo.h, jugador.x, jugador.y, jugador.w, jugador.h) ) {
                jugador.morir();
                xd =true;
                enemigosDaño.add(enemigo);

                musica.soundhit();

                if (jugador.vidas == 0){
                    gameover = true;

                    fondo.gameover();
                    musica.musicbackground(true);
                }
            }



            if (enemigo.x < -enemigo.w) enemigosAEliminar.add(enemigo);
        }

        for (Meteor meteor : meteors){

            if (!jugador.muerto && Utils.solapan(meteor.x, meteor.y, meteor.w-20, meteor.h-20, jugador.x, jugador.y, jugador.w, jugador.h) ) {
                jugador.morir();

                //xdM =true;
                //meteorDaño.add(meteor);

                //musica.soundhit();

                if (jugador.vidas == 0){
                    gameover = true;

                    fondo.gameover();
                    musica.musicbackground(true);
                }

            }


        }


        for (Disparo disparo : jugador.disparos)
            if (disparo.x > 640)
                disparosAEliminar.add(disparo);

        for (Meteor meteor : meteorAEliminar){
            meteors.remove(meteor);

        }
        for (Disparo disparo : disparosAEliminar) jugador.disparos.remove(disparo);       // disparosAEliminar.forEach(disparo -> jugador.disparos.remove(disparo));
        for (Enemigo enemigo : enemigosAEliminar){



            //----------------------ANIMACION-MUERTE-----------------------------------




            enemigos.remove(enemigo);




            //------------------------ANIMACION-MUERTE---------------------------------


        }

        // enemigosAEliminar.forEach(enemigo -> enemigos.remove(enemigo));
        disparosAEliminar.clear();
        enemigosAEliminar.clear();
        meteorAEliminar.clear();
        enemigosDañoAElimnar.clear();



        if(gameover) {
            int result = scoreboard.update(jugador.puntos);
            if(result == 1) {
                inicializarJuego();
            } else if (result == 2) {
                Gdx.app.exit();
            }
        }





    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        update();





        batch.begin();

        fondo.render(batch);

        jugador.render(batch);

        for (Meteor meteor : meteors){

            meteor.render(batch);
        }


        for (Explosion explosion : enemigosEx){


                explosion.render(batch);

            Color LIGHTBLUE = new Color(0,1,9f,1);
            bitmapFont.setColor(LIGHTBLUE);
            bitmapFont.draw(batch, "+ 10" , explosion.x + 35, explosion.y);
            bitmapFont.setColor(Color.WHITE);
            enemigosExdelete.add(explosion);




        }
        if(temporizadorEXPLOSION.suena()){




        for(Explosion explosion : enemigosExdelete){

                enemigosEx.remove(explosion);


        }
        }

        enemigosExdelete.clear();




        for (Enemigo enemigo : enemigos){



            if(enemigo.boom){
                bitmapFont.draw(batch, "+10" , enemigo.x + 35, enemigo.y);
            }
            enemigo.render(batch);  // enemigos.forEach(e -> e.render(batch));

        }

       bitmapFont.setColor(Color.RED);
        bitmapFont.draw(batch, "" + jugador.vidas, 590, 440);

        Color LIGHTBLUE = new Color(0,1,9f,1);
        bitmapFont.setColor(LIGHTBLUE);
        bitmapFont.draw(batch, "" + jugador.puntos, 30, 440);
        //bitmapFont.draw(batch, "" + z, 300, 440);
        bitmapFont.setColor(Color.WHITE);
        if (gameover){

            scoreboard.render(batch, bitmapFont);
        }

        if(jugador.vidas == 0) bitmapFont.draw(batch, "GAME OVER" , 240, 200);



        for (Enemigo enemigo : enemigosAEliminar){




            //------------------------ANIMACION-MUERTE---------------------------------

            if(enemigo.boom){



                bitmapFont.draw(batch, "+10" , enemigo.x + 35, enemigo.y);
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