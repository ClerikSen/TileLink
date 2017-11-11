package com.risenphoenix.tilelink;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rope_1 extends Game {
        
        
	
    @Override
    public void create () {   
        AssetLoader.load();

        //Cell[][] cells={};
        setScreen(new ScreenWorld());
    }

    @Override
    public void render () {
        super.render();
    }
	
    @Override
    public void dispose () {
        super.dispose();
        AssetLoader.dispose();
    }
}
