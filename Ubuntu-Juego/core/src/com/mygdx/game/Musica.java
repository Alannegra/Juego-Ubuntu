package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;


public class Musica {


    Music music,music2  ;
    Sound sound,sound2,sound3,sound4  ;
    Random random;
    Sound[] sounds,soundsX;
    int x =0;



    public Musica() {
        music  = Gdx.audio.newMusic(Gdx.files.internal("cave themeb4.ogg"));
        music2  = Gdx.audio.newMusic(Gdx.files.internal("forest.ogg"));
        sound  = Gdx.audio.newSound(Gdx.files.internal("pistol.wav"));
        sound2  = Gdx.audio.newSound(Gdx.files.internal("shotgun.wav"));
        sound3 = Gdx.audio.newSound(Gdx.files.internal("death.ogg"));
        sound4 = Gdx.audio.newSound(Gdx.files.internal("craft.ogg"));

        sounds = new Sound[7];
        soundsX = new Sound[4];

        sounds[0] = Gdx.audio.newSound(Gdx.files.internal("monster-11.ogg"));
        sounds[1] = Gdx.audio.newSound(Gdx.files.internal("monster-15.ogg"));
        sounds[2] = Gdx.audio.newSound(Gdx.files.internal("monster-16.ogg"));
        sounds[3] = Gdx.audio.newSound(Gdx.files.internal("monster-1.ogg"));
        sounds[4] = Gdx.audio.newSound(Gdx.files.internal("monster-9.ogg"));
        sounds[5] = Gdx.audio.newSound(Gdx.files.internal("monster-10.ogg"));
        sounds[6] = Gdx.audio.newSound(Gdx.files.internal("monster-13.ogg"));



        random = new Random();

        //craft.ogg

        //rifle.wav
        //shotgun.wav
        //pistol.wav
        //death.ogg
        //forest.ogg
    }

    public void musicbackground(boolean x){

        music.setVolume(1.0f);
        music.setLooping(true);
        music2.setVolume(1.0f);
        music2.setLooping(true);

        if(x){
            music.pause();
            music2.play();
        }else{
            music.play();
            music2.pause();
        }
    }





    public void sounddead(){

        x = random.nextInt(7);
        long id = sounds[x].play(3.0f);
        sounds[x].setPitch(id,1);
        sounds[x].setLooping(id,false);

        //long id = sound3.play(1.0f);
        //sound3.setPitch(id,1);
        //sound3.setLooping(id,false);

    }

    public void soundhit(){


        long id = sound4.play(3.0f);
        sound4.setPitch(id,1);
        sound4.setLooping(id,false);

    }

    public void soundshot(){
        long id = sound.play(0.5f);
        sound.setPitch(id,1);
        sound.setLooping(id,false);

    }
}
