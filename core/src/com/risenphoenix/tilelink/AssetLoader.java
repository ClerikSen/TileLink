/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 *
 * @author pkc11430
 */
public class AssetLoader {
    
    public static Texture cell;
    public static Texture pl,kirpich,plUp1,plUp2,plUp3;
    public static Texture[] plitka;
    public static Animation plitkaRotate;
    public static BitmapFont font;
    public static BitmapFont fontText;
    public static BitmapFont fontLEVEL,fontGive;
    public static Texture leftUp,rightUp,leftDown,rightDown,center;
    public static Texture zagolovok,best,level,score,LEVEL,giv,giveup;
    public static Texture inter;
    public static TextureRegion region,regLevel,GIVEUP;
    public static FreeTypeFontGenerator generator;
    public static FreeTypeFontGenerator.FreeTypeFontParameter param;
    public static Preferences prefs;
    
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
    
    public static void load(){
        cell = new Texture(Gdx.files.internal("data/Cell.png"));
        cell.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        plitka = new Texture[20];
        for(int i=0;i<20;i++){
            plitka[i] = new Texture(Gdx.files.internal("data/"+(i+1)+".png"));
            plitka[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        pl = new Texture(Gdx.files.internal("data/PLN.png"));
        pl.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        
        //plitkaF = new Texture(Gdx.files.internal("data/plitkaF1.png"));
        //plitkaF.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        
        kirpich = new Texture(Gdx.files.internal("data/00.png"));
        kirpich.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        
        plUp1 = new Texture(Gdx.files.internal("data/plitkaNUp.png"));
        plUp1.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        plUp2 = new Texture(Gdx.files.internal("data/plitkaNUp2.png"));
        plUp2.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        plUp3 = new Texture(Gdx.files.internal("data/plitkaNUp3.png"));
        plUp3.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        leftUp = new Texture(Gdx.files.internal("data/pl1.png"));
        leftUp.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rightUp = new Texture(Gdx.files.internal("data/pl2.png"));
        rightUp.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        leftDown = new Texture(Gdx.files.internal("data/pl3.png"));
        leftDown.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rightDown = new Texture(Gdx.files.internal("data/pl4.png"));
        rightDown.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        center = new Texture(Gdx.files.internal("data/pl5.png"));
        center.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("data/1.ttf"));
        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 100;
        font = generator.generateFont(param);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //font.getData().setScale(1.0f,-1.0f);
        float scale = 1.0f;
        font.getData().setScale(scale,-scale);
        font.setColor(Color.WHITE);

        fontText = generator.generateFont(param);
        fontText.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //font.getData().setScale(1.0f,-1.0f);
        float ychas = Gdx.graphics.getHeight()/3840.0f;
        scale = ychas*2.0f-0.1f;
        fontText.getData().setScale(scale,-scale);
        fontText.setColor(Color.WHITE);

        fontLEVEL = generator.generateFont(param);
        fontLEVEL.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //font.getData().setScale(1.0f,-1.0f);
        //ychas = Gdx.graphics.getHeight()/3840.0f;
        scale = Gdx.graphics.getHeight()/8.0f/100.0f;
        fontLEVEL.getData().setScale(scale,-scale);
        fontLEVEL.setColor(Color.WHITE);


        fontGive = generator.generateFont(param);
        fontGive.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //font.getData().setScale(1.0f,-1.0f);
        //ychas = Gdx.graphics.getHeight()/3840.0f;
        scale = Gdx.graphics.getHeight()/10.0f/100.0f;
        fontGive.getData().setScale(scale,-scale);
        fontGive.setColor(Color.WHITE);


        Texture[] pl = {plUp1,plUp2,plUp3};
        plitkaRotate = new Animation(0.06f,pl);
        plitkaRotate.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        zagolovok = new Texture(Gdx.files.internal("data/zagolovok.png"));
        zagolovok.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        best = new Texture(Gdx.files.internal("data/best1.png"));
        best.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        score = new Texture(Gdx.files.internal("data/best1.png"));
        score.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        level = new Texture(Gdx.files.internal("data/level1.png"));
        level.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        LEVEL = new Texture(Gdx.files.internal("data/LEVEL.png"));
        LEVEL.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        regLevel = new TextureRegion(LEVEL);
        regLevel.flip(false, true);

        inter = new Texture(Gdx.files.internal("data/inter1.png"));
        inter.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        region = new TextureRegion(inter);
        region.flip(false, true);

        giv = new Texture(Gdx.files.internal("data/GIV.png"));
        giv.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        giveup = new Texture(Gdx.files.internal("data/giveup.png"));
        giveup.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        GIVEUP = new TextureRegion(giveup);
        GIVEUP.flip(false, true);

        prefs = Gdx.app.getPreferences("felx");
        if(!prefs.contains("felx")){
            prefs.putInteger("felx", 0);
        }
    }
    
    public static void dispose(){
        for(int i=0;i<20;i++)
            plitka[i].dispose();

        pl.dispose();
        //pl.dispose();
        kirpich.dispose();
        plUp1.dispose();
        plUp2.dispose();
        plUp3.dispose();
        leftUp.dispose();
        leftDown.dispose();
        rightUp.dispose();
        rightDown.dispose();
        center.dispose();
        inter.dispose();
        giv.dispose();
        giveup.dispose();
        cell.dispose();
        generator.dispose();
        font.dispose();
        fontText.dispose();
        fontLEVEL.dispose();

    }

    public static void setHighScore(int val){
        prefs.putInteger("felx",val);
        prefs.flush();
    }

    public static int getHighScore(){
        return prefs.getInteger("felx");
    }
    
}
