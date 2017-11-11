/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 *
 * @author pkc11430
 */

public class ScreenWorld implements Screen {

    private World world;
    private Renderer renderer;
    private float runTime;
    
    public ScreenWorld(){
        Cell[][] cells = GenerationLavels.generationArray(4, 4);
        world = new World(cells);
        renderer = new Renderer(world);
        runTime = 0;
    }

    @Override
    public void render(float f) {
        //Gdx.app.log("GameScreen", "render");
        runTime+=f;
        world.update();
        renderer.render(runTime,f);
    }

     @Override
    public void resize(int i, int i1) {
        Gdx.app.log("GameScreen", "resizing");
    }
    
    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }
    
    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {
        
    }
    
}
