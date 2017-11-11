/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/**
 *
 * @author pkc11430
 */
public class World {
    
    private ArrayList<Cord> cords;
    private Cord tecCord;
    private Platform platform;
    private int ZNACH = Gdx.graphics.getWidth()/5;
    private int PROM = ZNACH/8;
    private int vitZnach;
    private float prevDistance;
    private int score,scorePrev;
    private int best;
    private int level;
    private Cell[][] cells;
    private int newLevel,GIVEUP;
    private boolean giv;
    public float xBut,yBut,widthBut,heightBut;

    public float getPrevDistance(){
        return prevDistance;
    }

    public void setPrevDistance(float D){
        prevDistance = D;
    }

    public int getProm(){
        return (ZNACH+vitZnach)/8;
    }

    public void setVitZnach(int vit){
        vitZnach = vit;
    }

    public int getVitZnach(){
        return vitZnach;
    }

    public int getZnach(){
        return ZNACH+vitZnach;
    }
    public void setZnach(int zn){
        ZNACH = zn;
    }

    public World(Cell[][] cells){
        this.cells = cells;
        cords = new ArrayList<Cord>();
        platform = new Platform(cells,this);
        prevDistance = 0;
        vitZnach = 0;
        score = 0;
        scorePrev = 0;
        best = AssetLoader.getHighScore();
        level = 1;
        newLevel = 60;
        GIVEUP =120;
        giv = false;
        float xchas = Gdx.graphics.getWidth()/2160.0f;
        float ychas = Gdx.graphics.getHeight()/3840.0f;
        xBut = 800*xchas;
        yBut = 150*ychas;
        widthBut = 550*xchas;
        heightBut = 300*ychas;
    }

    public void update(){
        
    }




    public void setScore(int s){
        score = s;
    }

    public int getScore(){
        return score;
    }
    
    public void setTecCord(Cord c){
        tecCord = c;
    }
    
    public Cord getTecCord(){
        return tecCord;
    }

    public ArrayList<Cord> getCords(){
        return cords;
    }
    
    public Platform getPlatform(){
        return platform;
    }

    public int getBest() {
        return best;
    }

    public void setBest(int best) {
        this.best = best;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(int newLevel) {
        this.newLevel = newLevel;
    }

    public boolean isGiv() {
        return giv;
    }

    public void setGiv(boolean giv) {
        this.giv = giv;
    }

    public int getGIVEUP() {
        return GIVEUP;
    }

    public void setGIVEUP(int GIVEUP) {
        this.GIVEUP = GIVEUP;
    }

    public int getScorePrev() {
        return scorePrev;
    }

    public void setScorePrev(int scorePrev) {
        this.scorePrev = scorePrev;
    }
}
