package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo {
    Texture texture = new Texture("fondo.jpg");
    Texture texture2 = new Texture("fondo.jpg");
    float x,z ;
    int y ;
    float quitar = 10;

    Temporizador cambioVelocidad = new Temporizador(1,true);



    Fondo(){



        x = 0;
        y = 0;
        z = 640;



    }

    public void update(){




        if (cambioVelocidad.suena()) {


            z -= quitar;
            x -= quitar;


        }

    }

    public void render(SpriteBatch batch) {






            batch.draw(texture, x, y, 640, 480);



            batch.draw(texture, z, y, 640, 480);








    }






}
