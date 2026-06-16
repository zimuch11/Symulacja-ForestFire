package com.example.symulacja_fire;

import java.util.List;

public class Helicopter{
    private final int width;
    private final int height;
    public Helicopter(int width, int height){
        this.width = width;
        this.height = height;
    }
    ///helislogic - Metoda która obejmuje wszystkie zachowania dla wygenerowanych na planszy Helikopterów
    public void helislogic(List<int[]> helis) {
        for (int[] h : helis){
            int fuelloss=0;
            int offset=6;
            List <int[]> pyt=Board.getClosestfire(h[1],h[2],h[6],h[5],h[3],h[4]);
            ;
            if (h[1] == h[3] && h[2] == h[4]) {
                h[1] -= offset;
                h[5] = 1;
            }
            fuelloss = Board.extinguishfire(h,pyt);
            if(pyt.get(0)[0]>=h[3]-200){
                int[] nextPosition = Board.moveFirefigh(pyt,h);
                h[0] = nextPosition[0];
                h[1] = nextPosition[1];
                h[5]+=fuelloss;
            }
        }
    }
}

