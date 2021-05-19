package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;


public class Fondo {
    static class FondoBack {
        Texture texture = new Texture("fondo.jpg");
        Texture texture2 = new Texture("fondo.jpg");
        float x,z ;
        int y ;
        static float quitar = 1 ;
        boolean gameover = true;

        Temporizador cambioVelocidad = new Temporizador(1,true);


        FondoBack(){
            x = 0;
            y = 0;
            z = 640;
        }


        public void update(){
            if (gameover && cambioVelocidad.suena()) {
                z -= quitar;
                x -= quitar;
            }
        }

        public void render(SpriteBatch batch) {
            batch.draw(texture, x, y, 640, 480);
            batch.draw(texture, z, y, 640, 480);
        }
    }

    List<FondoBack> fondoBacks = new ArrayList<>();

    Fondo(){
        fondoBacks.add(new FondoBack());
    }

    static void reiniciarFondo (){
        FondoBack.quitar = 1;
    }

    public void update(){
        for (FondoBack fondoBack : fondoBacks) fondoBack.update();


        if (fondoBacks.get(0).z <= 0){

            fondoBacks.remove(0);
            fondoBacks.add(new FondoBack());

        }
    }

    void strake(){
        for (FondoBack fondoBack : fondoBacks){
            fondoBack.quitar +=0.5;



        }
    }

    void gameover(){
        fondoBacks.get(0).gameover = false ;
    }

    public void render(SpriteBatch batch) {
        for (FondoBack fondoBack : fondoBacks) fondoBack.render(batch);
    }
}
