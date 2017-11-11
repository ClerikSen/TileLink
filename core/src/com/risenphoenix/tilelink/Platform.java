/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 *
 * @author pkc11430
 */
public class Platform {
    
    private Cell[][] cells;
    private int xPlatform = 50;
    private int yPlatform = 1;
    private int xText = 52;
    private int yText = 18;
    private int genLevX,genLevY;
    private World world;
    private Timer timer;
    
    public Platform(Cell[][] cells,World world){
        float width = Gdx.graphics.getWidth()/2;
        xPlatform = Math.round(width-(cells[0].length*35/2));
        xText = xPlatform+2;
        genLevX = 4;
        genLevY=4;
        float height = Gdx.graphics.getHeight()/2;
        yPlatform = Math.round(height-(cells.length*35/2));
        yText = yPlatform+18;
        this.cells = cells;
        this.world = world;
        //timer = new Timer(1000,new ActionListener(
        //public void actionPerformed(ActionEvent ev) {
        //    System.out.println("WOW!");
        //}
        //));
    }

    public Cell[][] giveUp(){
        genLevX = 4;
        genLevY=4;
        world.setLevel(1);
        world.setScorePrev(world.getScore());
        world.setScore(0);
        world.setGIVEUP(0);
        AssetLoader.setHighScore(world.getBest());
        //best
        cells = GenerationLavels.generationArray(genLevX, genLevY);
        return cells;
    }


    public Cell[][] newGener(){
        boolean flagCel=false;
        for(int k=0;k<cells.length;k++) {
            for (int j = 0; j < cells[k].length; j++) {
                if(cells[k][j].getRealValue()!=0&&cells[k][j].getLengthOfLine()>1)
                    flagCel = true;

            }
        }
        if(!flagCel) {
            //timer.start();
            world.setNewLevel(0);
            world.setLevel(world.getLevel()+1);
            if(world.getLevel()%2!=0){
                genLevX++;
            }else {
                genLevY++;
            }
                cells = GenerationLavels.generationArray(genLevX, genLevY);


        }
        return cells;

    }

    public Cell[][] getCells(){
        return cells;
    }
    
    public Vector2 getCoordinats(){
        return new Vector2(xPlatform,yPlatform);
    }
    
    public Vector2 getCoordinatsText(){
        return new Vector2(xText,yText);
    }
    
    public void update(){
        
    }
    
    public void setCoordinats(int x,int y){
        xPlatform=x;
        yPlatform=y;
        xText = x+2;
        yText = y+17;
    }
    
    public void updateCoordinats(int x_raz,int y_raz){
        xPlatform -=x_raz;
        yPlatform -=y_raz;
        xText = xPlatform+2;
        yText = yPlatform+17;
    }
    
    public void setCoordinatsText(int x, int y){
        xText=x;
        yText=y;
    }
    
    
}
