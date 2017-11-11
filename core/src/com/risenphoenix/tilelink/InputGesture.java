package com.risenphoenix.tilelink;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Fentom on 03.05.2017.
 */

public class InputGesture implements GestureDetector.GestureListener {

    private World world;

    public InputGesture(World world){
        this.world = world;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        //System.out.println(x+" , "+y);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        //float prev = world.getPrevDistance();
        float f;
        //if(prev<1&&prev>-1){
        //    world.setPrevDistance(initialDistance);
        //    prev = initialDistance;
        //}else{
        //    world.setPrevDistance(prev);
            //prev = distance;
        //}
        //f = distance - prev;
        f = distance - initialDistance;
        if(f<-150)
            f = -150.0f;
        else if(f>0)
            f = 0;
        world.setVitZnach(Math.round(f));
        System.out.println(f);
        //int zn = world.getZnach();
        //world.setZnach(zn+Math.round(f/100));
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
