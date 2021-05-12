package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo {
    Texture texture = new Texture("fondo.jpg");
    int x = 0;
    int y = 0;



    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, 640, 480);
    }



}
