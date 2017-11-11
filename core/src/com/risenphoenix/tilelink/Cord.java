/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 *
 * @author pkc11430
 */
public class Cord {


    private ArrayList<Vector2> index; 
    private Texture texture;
    private int indexP;
    

    //private boolean activite;

    public Cord(){
        index = new ArrayList<Vector2>();
        //activite = false;
        texture = null;
        indexP=-1;
    }
    
    public int getIndexCord(){
        return indexP;
    }
    
    public void setIndexCord(int index){
        indexP = index;
    }
    
    public int size(){
        return index.size();
    }
    
    public void add(Vector2 vec){
        index.add(vec);
    }
    
    public void set(int ind,Vector2 vec){
        index.set(ind, vec);
    }
    
    public Vector2 get(int ind){
        return index.get(ind);
    }

    public void setTexture(Texture texture){
        this.texture = texture;
    }

    public Texture getTexture(){
        return texture;
    }




}
