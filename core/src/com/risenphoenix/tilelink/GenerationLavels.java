/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risenphoenix.tilelink;

/**
 *
 * @author pkc11430
 */
public class GenerationLavels {
    
    private static final int VERH=0;
    private static final int VNIZ=1;      
    private static final int VLEVO=2;
    private static final int VPRAVO=3;
            
    private static int viborStoron(int x,int y,int width,int height,int[][] ar){
        int tim = 0;
        while(true){
            tim++;
            if(tim>200000)
                break;
            int k = (int)Math.round(Math.random()*3);
            if(x>=width&&k==VPRAVO)
                continue;
            if(x<=0&&k==VLEVO)
                continue;
            if(y>=height&&k==VNIZ)
                continue;
            if(y<=0&&k==VERH)
                continue;
            int a = x;
            int b = y;
            if(k == VERH){
                b--;
            }else if(k==VNIZ){
                b++;
            }else if(k==VLEVO){
                a--;
            }else if(k==VPRAVO){
                a++;
            }
            if(ar[b][a]>0)
                continue;
            
            return k;
        }
        return -1;
    }

    //public static int cellValue=1;

    public static Cell[][] generationArray(int width,int height){
        int dl = width;
        int vn = height;
        int zn = dl*vn;
        int[][] array = new int[vn][dl];
        Cell[][] cells = new Cell[vn][dl];
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                cells[i][j] = new Cell(0,0,0);
            }
        }
        for(int r=0;r<zn;r++){
            int t = 1+r;
            int a = (int)Math.round(Math.random()*(dl-1));
            int b = (int)Math.round(Math.random()*(vn-1));
            if(array[b][a]>0)
                continue;
            int dlina = (int)Math.round(2+Math.random()*10);
            for(int i=0;i<dlina;i++){
                array[b][a] = t;
                cells[b][a] = new Cell(t,i+1,dlina);
                int nap = viborStoron(a,b,dl-1,vn-1,array);
                if(nap == VERH){
                    b--;
                }else if(nap==VNIZ){
                    b++;
                }else if(nap==VLEVO){
                    a--;
                }else if(nap==VPRAVO){
                    a++;
                }else if(nap == -1){
                    //System.out.println("Слишком долго");
                    cells[b][a].setFinish(true);
                    cells[b][a].setLengthOfLine(i + 1);
                    break;
                }
            }       
        }
        return cells;
    }
    
}
