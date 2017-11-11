/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.zip.ZipEntry;

/**
 *
 * @author pkc11430
 */
public class InputHandler implements InputProcessor {

    private World myWorld;
    private Platform platform;
    private ArrayList<Cord> cords;
    private Cord cord;
    private Cell[][] cells;
    private int ZNACH;
    private int PROM;
    
    public InputHandler(World myWorld){
        this.myWorld = myWorld;
        platform = myWorld.getPlatform();
        cords = myWorld.getCords();
        cells = platform.getCells();
        x_prev = -100;
        y_prev = -100;
        indexI=-1;
        indexJ=-1;
        ZNACH = this.myWorld.getZnach();
        PROM = this.myWorld.getProm();
    }
    
    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        flag=false;
        activ = false;
        cord= new Cord();
        myWorld.setTecCord(cord);
        ZNACH = myWorld.getZnach();
        PROM = myWorld.getProm();
        if(Intersector.overlaps(new Rectangle(myWorld.xBut,myWorld.yBut,myWorld.widthBut,myWorld.heightBut),new Rectangle(i,i1,2,2))){
            myWorld.setGiv(true);
            cells = platform.giveUp();

        }else {
            //camera.translate(i, i1);
            for (int k = 0; k < cells.length; k++) {
                for (int j = 0; j < cells[k].length; j++) {
                    Vector2 point = cells[k][j].getXY();
                    if (point != null) {
                        Rectangle rect = new Rectangle(point.x + PROM, point.y + PROM, ZNACH - (PROM * 2), ZNACH - (PROM * 2));
                        if (Intersector.overlaps(rect, new Rectangle(i, i1, 2, 2))) {
                            if (cells[k][j].isStart() || cells[k][j].isFinish()) {
                                flag = true;
                                indexI = k;
                                indexJ = j;
                                System.out.println("DOWN");
                                a = k;
                                b = j;
                                for (int r = 0; r < cords.size(); r++) {
                                    Cord cord = cords.get(r);
                                    Vector2 vecN = cord.get(0);
                                    Vector2 vecF = cord.get(cord.size() - 1);
                                    if (((int) (vecN.x) == k && (int) (vecN.y) == j) || ((int) (vecF.x) == k && (int) (vecF.y) == j)) {
                                        cords.remove(r);
                                        break;
                                    }
                                }
                                //cells[k][j].setAnimFlag(true);
                            }
                            break;
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
        
        
        //platform.setCoordinatsText()
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        myWorld.setGiv(false);
        x_prev=-100;
        y_prev=-100;
        if(indexI!=-1)
            cells[indexI][indexJ].setAnimFlag(false);
        indexI=-1;
        indexJ=-1;

        myWorld.setPrevDistance(0);
        for(int k=0;k<cells.length;k++) {
            for (int j = 0; j < cells[k].length; j++) {
                cells[k][j].setStats(Cell.Stats.PASSIVE);

            }
        }
        a=-1;
        b=-1;
        if(cord.size()>1){
            Vector2 vec = cord.get(0);
            Vector2 vecF = cord.get(cord.size()-1);
            Cell cell = cells[(int)vec.x][(int)vec.y];
            Cell cellF = cells[(int)vecF.x][(int)vecF.y];
            if(cellF.isStart()||cellF.isFinish()){
                if(cell.getRealValue()==cellF.getRealValue()){
                    boolean f=false;
                    for(int t=0;t<cord.size();t++){
                        Vector2 ve = cord.get(t);
                        if(cells[(int)ve.x][(int)ve.y].getRealValue()!=cells[(int)vec.x][(int)vec.y].getRealValue()){
                            f=true;
                            break;
                        }    
                    }
                    System.out.println("UP");
                    int length;
                    if(cell.isFinish())
                        length = cell.getLengthOfLine();
                    else
                        length = cellF.getLengthOfLine();
                    if(length==cord.size()&&!f){


                        for(int t=0;t<cord.size();t++){
                            Vector2 ve = cord.get(t);
                            if(cells[(int)ve.x][(int)ve.y].isFinish()){
                                myWorld.setScore(myWorld.getScore()+cells[(int)ve.x][(int)ve.y].getLengthOfLine());
                                int st = myWorld.getScore();
                                if(st>myWorld.getBest()){
                                    myWorld.setBest(st);
                                }
                            }
                            cells[(int)ve.x][(int)ve.y].setRealValue(0);
                            cells[(int)ve.x][(int)ve.y].setFinish(false);
                            cells[(int)ve.x][(int)ve.y].setStart(false);
                        }
                    }else{
                        cord.setIndexCord(cords.size());
                        cords.add(cord);
                    }
                }
            }
            cells = platform.newGener();

        }
        //cord.setActivite(false);
        //cord = null;
        return true;
    }

    private int x_prev;
    private int y_prev;
    private int indexI;
    private int indexJ;
    private int a;
    private int b;
    private boolean flag;
    private boolean activ;
    
    
    private void delCor(){
        boolean flag = false;
        for(int j=0;j<cords.size();j++){
            Cord cor = cords.get(j);
            if(!flag){
                for(int h=0;h<cor.size();h++){
                    Vector2 vec = cor.get(h);
                    if((int)vec.x==indexI&&(int)vec.y==indexJ){
                        cords.remove(j);
                        //break;
                        flag = true;
                        break;
                    }
                }
            }else{
                break;
                //cor.setIndexCord(cor.getIndexCord()-1);
            }
        }
    }
    
    
    @Override
    public boolean touchDragged(int i, int i1, int i2) {

            if (flag) {
                ZNACH = myWorld.getZnach();
                PROM = myWorld.getProm();
                if (indexI > -1 && indexJ > -1) {
                    if (cord.getTexture() == null) {
                        cord.setTexture(AssetLoader.plitka[cells[indexI][indexJ].getRealValue() % 20]);
                        cord.add(new Vector2(indexI, indexJ));
                    }

                    if (indexI + 1 < cells.length) {
                        Vector2 point1 = cells[indexI + 1][indexJ].getXY();
                        //if (point1 != null) {
                        //}
                        Rectangle rect1 = new Rectangle(point1.x + PROM, point1.y + PROM, ZNACH - (PROM * 2), ZNACH - (PROM * 2));
                        if (Intersector.overlaps(rect1, new Rectangle(i, i1, 2, 2))) {
                            indexI++;
                            cells[indexI][indexJ].setStats(Cell.Stats.ACTIVE);
                            cord.add(new Vector2(indexI, indexJ));
                            System.out.println("Вправо");
                            delCor();
                            //cord.setActivite(true);
                        }
                    }
                    if (indexI - 1 >= 0) {
                        Vector2 point2 = cells[indexI - 1][indexJ].getXY();
                        //if (point2 != null) {
                        //}
                        Rectangle rect2 = new Rectangle(point2.x + PROM, point2.y + PROM, ZNACH - (PROM * 2), ZNACH - (PROM * 2));
                        if (Intersector.overlaps(rect2, new Rectangle(i, i1, 2, 2))) {
                            //cord.setTexture(AssetLoader.plitka[cells[a][b].getRealValue() % 20]);
                            indexI--;
                            cells[indexI][indexJ].setStats(Cell.Stats.ACTIVE);
                            cord.add(new Vector2(indexI, indexJ));
                            System.out.println("Влево");
                            delCor();
                            //cord.setActivite(true);
                        }
                    }
                    if (indexJ + 1 < cells[0].length) {
                        Vector2 point3 = cells[indexI][indexJ + 1].getXY();
                        //if (point3 != null) {
                        //}
                        Rectangle rect3 = new Rectangle(point3.x + PROM, point3.y + PROM, ZNACH - (PROM * 2), ZNACH - (PROM * 2));
                        if (Intersector.overlaps(rect3, new Rectangle(i, i1, 2, 2))) {
                            //cord.setTexture(AssetLoader.plitka[cells[a][b].getRealValue() % 20]);
                            indexJ++;
                            cells[indexI][indexJ].setStats(Cell.Stats.ACTIVE);
                            cord.add(new Vector2(indexI, indexJ));
                            System.out.println("Вниз");
                            delCor();
                            //cord.setActivite(true);
                        }
                    }
                    if (indexJ - 1 >= 0) {
                        Vector2 point4 = cells[indexI][indexJ - 1].getXY();
                        //if (point4 != null) {
                        //}
                        Rectangle rect4 = new Rectangle(point4.x + PROM, point4.y + PROM, ZNACH - (PROM * 2), ZNACH - (PROM * 2));
                        if (Intersector.overlaps(rect4, new Rectangle(i, i1, 2, 2))) {
                            //cord.setTexture(AssetLoader.plitka[cells[a][b].getRealValue() % 20]);
                            indexJ--;
                            cells[indexI][indexJ].setStats(Cell.Stats.ACTIVE);
                            cord.add(new Vector2(indexI, indexJ));
                            System.out.println("Вверх");
                            delCor();
                            //cord.setActivite(true);
                        }
                    }


                    //if(cells[a][b].isStart()&&cells[indexI][indexJ].isFinish()){
                    //    activ = true;
                    //}
                    //if(cells[a][b].isFinish()&&cells[indexI][indexJ].isStart()){
                    //    activ = true;
                    //}
                    //cord.setX(i-100);
                    //cord.setY(i1-100);
                    //cord.setTexture(AssetLoader.plitka[cells[indexI][indexJ].getRealValue()%20]);
                    //cord.setActivite(true);
                }
                } else {
                    if (x_prev != -100) {
                        int x_raz = x_prev - i;
                        int y_raz = y_prev - i1;
                        if (x_raz > 0) {

                        } else if (x_raz < 0) {

                        }
                        if (y_raz > 0) {

                        } else if (y_raz < 0) {

                        }
                        platform.updateCoordinats(x_raz, y_raz);
                    }
                    x_prev = i;
                    y_prev = i1;
                    //System.out.println(" i=" + i + " i1=" + i1 + " i2=" + i2);
                }
            //}

        System.out.println(" i=" + i + " i1=" + i1 + " i2=" + i2);
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
    
}
