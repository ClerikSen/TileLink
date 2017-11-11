/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

import static com.risenphoenix.tilelink.AssetLoader.*;

/**
 *
 * @author pkc11430
 */
public class Renderer {
    
    private World world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private ArrayList<Cord> cords;
    private Platform platform;
    private Cell[][] cells;
    private Texture cell;
    private Animation plitkaRotate;
    private int ZNACH;
    private int vit;
    private int PROM;
    private float xText,yText,xBest,yBest,xLevel,yLevel,xLEVEL,yLEVEL;
    
    
    private TextureRegion hz;
    
    public Renderer(World world){
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        InputMultiplexer multiplexer = new InputMultiplexer();

        multiplexer.addProcessor(new GestureDetector(new InputGesture(world)));
        multiplexer.addProcessor(new InputHandler(world));
        Gdx.input.setInputProcessor(multiplexer);
        //Gdx.input.setInputProcessor(new InputHandler(world));
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        ZNACH = this.world.getZnach();
        PROM = this.world.getProm();
        vit = this.world.getVitZnach();
        initObjects();
        initAssets();
        float xchas = Gdx.graphics.getWidth()/2160.0f;
        float ychas = Gdx.graphics.getHeight()/3840.0f;

        //System.out.println("XWidth = "+Gdx.graphics.getWidth()+"  YHeight = "+Gdx.graphics.getHeight());

        xText = (150.0f+600.0f/2)*xchas;
        yText = (150.0f+450.0f)/2*ychas;

        xBest = (1410.0f+600.0f/2)*xchas;
        yBest = (150.0f+450.0f)/2*ychas;

        xLevel = (750.0f+3*650.0f/4)*xchas-ZNACH/4.0f+10.0f;
        yLevel = (500+200/6)*ychas;



        
    }
    
    private void initObjects(){
        cords = world.getCords();
        platform = world.getPlatform();
        
    }
    
    private void initAssets(){
        cell = AssetLoader.cell;
        plitkaRotate = AssetLoader.plitkaRotate;
    }



    
    private void drawPlatform(){
        Cell[][] cells = platform.getCells();
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[i].length;j++ ){
                Vector2 point4 = cells[i][j].getXY();
                shapeRenderer.setColor(10/255.0f, 10/255.0f, 10/255.0f, 1);
                shapeRenderer.rect(point4.x+PROM, point4.y+PROM, ZNACH-(PROM*2), ZNACH-(PROM*2));
            }
        }
    }
    
    private void drawPlat(float delta){
        Cell[][] cells = platform.getCells();

        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[i].length;j++ ){
                cells[i][j].setXY(platform.getCoordinats().x+ZNACH*i, platform.getCoordinats().y+ZNACH*j);
                Vector2 point = cells[i][j].getXY();
                if(i==0&&j==0){
                    batcher.draw(leftDown, platform.getCoordinats().x+ZNACH*i, platform.getCoordinats().y+ZNACH*j,ZNACH,ZNACH);
                }else if(i==0&j==cells[i].length-1){
                    batcher.draw(leftUp, platform.getCoordinats().x+ZNACH*i, platform.getCoordinats().y+ZNACH*j,ZNACH,ZNACH);
                }else if(i==cells.length-1&&j==0){
                    batcher.draw(rightDown, platform.getCoordinats().x+ZNACH*i, platform.getCoordinats().y+ZNACH*j,ZNACH,ZNACH);
                }else if(i==cells.length-1&&j==cells[i].length-1){
                    batcher.draw(rightUp, platform.getCoordinats().x+ZNACH*i, platform.getCoordinats().y+ZNACH*j,ZNACH,ZNACH);
                }else{
                    batcher.draw(center, platform.getCoordinats().x+ZNACH*i, platform.getCoordinats().y+ZNACH*j,ZNACH,ZNACH);
                }
                boolean flag = false;
                if(cells[i][j].getRealValue()==0||cells[i][j].getLengthOfLine()==1){
                    flag = true;
                    batcher.draw(kirpich, point.x+PROM, point.y+PROM,ZNACH-(PROM*2),ZNACH-(PROM*2));
                    cells[i][j].setStart(false);
                    cells[i][j].setFinish(false);
                }

                if((cells[i][j].isStart()||cells[i][j].isFinish())&&!flag){
                    batcher.draw(plitka[cells[i][j].getRealValue()%20], point.x+PROM, point.y+PROM,ZNACH-(PROM*2),ZNACH-(PROM*2));
                    font.draw(batcher, ""+cells[i][j].getRealValue(),point.x+PROM,point.y+PROM);
                }else if(!flag){
                    if(cells[i][j].getStats()== Cell.Stats.ACTIVE){
                        if(world.getTecCord().getTexture()!=null)
                            batcher.draw(world.getTecCord().getTexture(), point.x+PROM, point.y+PROM, ZNACH - (PROM*2), ZNACH - (PROM*2));
                    }else {
                        batcher.draw(pl, platform.getCoordinats().x + PROM + ZNACH * i, platform.getCoordinats().y + PROM + ZNACH * j, ZNACH - (PROM*2), ZNACH - (PROM*2));
                    }
                }
            }
        }
        
        for(int i=0;i<cords.size();i++){
            Cord cord = cords.get(i);
            for(int j=0;j<cord.size();j++){
                Vector2 vec = cord.get(j);
                if(!(cells[(int)vec.x][(int)vec.y].isStart()||cells[(int)vec.x][(int)vec.y].isFinish()))
                    batcher.draw(cord.getTexture(), platform.getCoordinats().x + PROM + ZNACH * (int)vec.x, platform.getCoordinats().y + PROM + ZNACH * (int)vec.y, ZNACH - (PROM*2), ZNACH - (PROM*2));
            }
        }
    }
    
    public void render(float delta,float f){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //ZNACH = world.getZnach();
        vit = world.getVitZnach();
        ZNACH = world.getZnach();
        //System.out.println("ZNACH = "+ZNACH);
        PROM = world.getProm();
        //float scale = vit*-0.005f;
        font.getData().setScale(ZNACH/100.0f/2.0f,-1.0f*(ZNACH/100.0f/2.0f));
        //System.out.println(" runTime = "+delta);
        platform.update();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(252/255.0f, 248/255.0f, 239/255.0f, 1);
        //float xwidth = Gdx.graphics.getWidth();
        //float yheight = Gdx.graphics.getHeight();
        //System.out.println
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //drawPlatform();
        shapeRenderer.end();
        
        batcher.begin();
        //batcher.disableBlending();
        drawPlat(delta);
        //float height = Gdx.graphics.getHeight()/4;
        batcher.draw(AssetLoader.region,(float)0,(float)0,(float)Gdx.graphics.getWidth()/2,(float)Gdx.graphics.getHeight()/2,(float)Gdx.graphics.getWidth(),(float)Gdx.graphics.getHeight(),(float)1,(float)1,(float)0);

        float xchas = Gdx.graphics.getWidth()/2160.0f;
        float ychas = Gdx.graphics.getHeight()/3840.0f;
        float scale = (ychas*2.0f-0.1f)*100.0f;
        String sS = ""+world.getScore();
        String sB = ""+world.getBest();
        fontText.draw(batcher, sS,xText-scale/4*sS.length(),yText);
        fontText.draw(batcher, sB,xBest-scale/4*sB.length(),yBest);
        String s = ""+world.getLevel();
        fontText.draw(batcher, s,xLevel,yLevel);
        int w = world.getNewLevel();
        if(w<60) {
            float regl = Gdx.graphics.getWidth()*0.388889f;
            xLEVEL = Gdx.graphics.getWidth() / 2-(Gdx.graphics.getHeight()/8.0f/4.0f*s.length());
            yLEVEL = Gdx.graphics.getHeight() / 2;// - regl / 2 + regl / 2
            batcher.draw(AssetLoader.regLevel, (float) 0, (float) Gdx.graphics.getHeight() / 2 - regl / 2, (float) Gdx.graphics.getWidth() / 2, (float) regl / 2, (float) Gdx.graphics.getWidth(), (float) regl, (float) 1, (float) 1, (float) 0);
            fontLEVEL.draw(batcher, s,xLEVEL,yLEVEL);
            world.setNewLevel(w+1);
            //System.out.println("W = "+w);
        }
        String sW = ""+world.getScorePrev();
        int wg = world.getGIVEUP();
        if(wg<120) {
            float regl = Gdx.graphics.getWidth()*0.388889f;
            xLEVEL = Gdx.graphics.getWidth() / 2;
            yLEVEL = Gdx.graphics.getHeight() / 2;// - regl / 2 + regl / 2
            batcher.draw(AssetLoader.GIVEUP, (float) 0, (float) Gdx.graphics.getHeight() / 2 - regl / 2, (float) Gdx.graphics.getWidth() / 2, (float) regl / 2, (float) Gdx.graphics.getWidth(), (float) regl, (float) 1, (float) 1, (float) 0);
            fontGive.draw(batcher, sW,xLEVEL,yLEVEL);
            world.setGIVEUP(wg+1);
            //System.out.println("W = "+w);
        }
        if(world.isGiv()){
            batcher.draw(giv, world.xBut, world.yBut, world.widthBut, world.heightBut);
        }
        //float width = Gdx.graphics.getWidth()/4;
        //float bestWidth = width+width/2;
        //float bestHeight = bestWidth*0.5649f;
        //float zagHeight = height/4;
        //float levelWidth = width+width/2;
        //float levelHeight = levelWidth*0.2528f;
        //batcher.draw(best,width-(bestWidth/2)-10,zagHeight*2-(zagHeight/2),bestWidth,bestHeight);
        //batcher.draw(score,width*3-(bestWidth/2)+10,zagHeight*2-(zagHeight/2),bestWidth,bestHeight);
        //batcher.draw(level,width*2-(levelWidth/2),height-levelHeight-height/6,levelWidth,levelHeight);
        //if(cord.isActivite()){
        //    batcher.draw(cord.getTexture(),cord.getX()+20,cord.getY()+20,ZNACH-40,ZNACH-40);
        //}
        //AssetLoader.font.draw(batcher,"runTime = "+f,0,Gdx.graphics.getHeight()-50);
        //batcher.enableBlending();
        batcher.end();
        
        //shapeRenderer.begin(ShapeType.Line);
        //drawPlatform();
        //shapeRenderer.end();
        
    }
    
}
