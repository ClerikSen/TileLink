/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;


import com.badlogic.gdx.math.Vector2;


/**
 *
 * @author denchik
 */
public class Cell {
    
    public enum Stats{
        ACTIVE,PASSIVE
    };
    private Stats stats;
    private boolean start;
    private boolean finish;
    private int realValue = 0;
    private int estimatedValue;
    private int indexInLine;
    private int lengthOfLine;
    
    private Vector2 xyScreen;
    
    private boolean anim;
    
    public boolean getAnimFlag(){
        return anim;
    }
    
    public void setAnimFlag(boolean flag){
        anim = flag;
    }
    
    public void setXY(float x,float y){
        xyScreen.x = x;
        xyScreen.y = y;
    }
    
    public Vector2 getXY(){
        return xyScreen;
    }
    
    public Cell(int realV,int index,int length){
        stats = Stats.PASSIVE;
        if(index==1)
            start = true;
        else
            start = false;
        if(index==length)
            finish = true;
        else
            finish = false;
        xyScreen = new Vector2();
        realValue = realV;
        estimatedValue = -1;
        indexInLine = index;
        lengthOfLine = length;
        anim= false;
    }
    

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public int getRealValue() {
        return realValue;
    }

    public void setRealValue(int realValue) {
        this.realValue = realValue;
    }

    public int getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(int estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public int getIndexInLine() {
        return indexInLine;
    }

    public void setIndexInLine(int indexInLine) {
        this.indexInLine = indexInLine;
    }

    public int getLengthOfLine() {
        return lengthOfLine;
    }

    public void setLengthOfLine(int lengthOfLine) {
        this.lengthOfLine = lengthOfLine;
    }
    
    
    
    
}
