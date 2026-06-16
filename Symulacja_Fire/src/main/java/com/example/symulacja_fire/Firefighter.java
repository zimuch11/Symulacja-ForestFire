package com.example.symulacja_fire;

import java.util.List;

public class Firefighter{
    private final int width;
    private final int height;
    public Firefighter(int width, int height){
        this.width = width;
        this.height = height;
    }
    ///fireFighLogic - Metoda która obejmuje wszystkie zachowania dla wygenerowanych na planszy Strażaków
    public void fireFighlogic(List<int[]> firefighs) {
        for (int[] i : firefighs){
            int fuelloss=0;
            List <int[]> pyt=Board.getClosestfire(i[1],i[2],i[4],i[3],i[5],i[6]);
            if (i[1] == i[5] && i[2] == i[6]) {
                i[3] = 5;
            }
            fuelloss = Board.extinguishfire(i, pyt);
            int[] nextPosition = Board.moveFirefigh(pyt,i);
            i[1] = nextPosition[1];
            i[2] = nextPosition[2];
            i[3] += fuelloss;
        }
    }
}

