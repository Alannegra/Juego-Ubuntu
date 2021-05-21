package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;



public class Musica {


    Music music,music2  ;
    Sound sound,sound2,sound3  ;



    public Musica() {
        music  = Gdx.audio.newMusic(Gdx.files.internal("cave themeb4.ogg"));
        music2  = Gdx.audio.newMusic(Gdx.files.internal("forest.ogg"));
        sound  = Gdx.audio.newSound(Gdx.files.internal("pistol.wav"));
        sound2  = Gdx.audio.newSound(Gdx.files.internal("shotgun.wav"));
        sound3 = Gdx.audio.newSound(Gdx.files.internal("death.ogg"));
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
        long id = sound3.play(1.0f);
        sound3.setPitch(id,1);
        sound3.setLooping(id,false);

    }

    public void soundshot(){
        long id = sound.play(0.1f);
        sound.setPitch(id,1);
        sound.setLooping(id,false);

    }
}
