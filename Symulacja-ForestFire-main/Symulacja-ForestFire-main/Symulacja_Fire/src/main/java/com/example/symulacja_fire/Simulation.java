package com.example.symulacja_fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulation {
    private final int height;
    private final int width;
    private final int numFireFigh;
    private final int numHeli;
    private Board board;
    private int stepCount;
    private boolean isRunning;
    private List<int[]> firefighs = new ArrayList<>();
    private List<int[]> helis = new ArrayList<>();



    public Simulation(int width, int height, double forestation, int numFireFigh, int numHeli) {
        this.numFireFigh = numFireFigh;
        this.numHeli = numHeli;
        this.height = height;
        this.width = width;
        board = new Board(width, height, forestation);
    }

    public void initialize() {
        stepCount=0;
        for (int y=0; y < numFireFigh; y++){
            int type = -1;
            int fuel = 10;
            int fightx = (int) (width*0.75);
            int fighty = (int) (height*y/numFireFigh);
            firefighs.add(new int[]{y, fightx, fighty, fuel, type});
        } //Strazak initialize

        for (int y=0; y < numHeli; y++){
            int type = -2;
            int fuel = 5;
            int fightx = (int) (width*0.8);
            int fighty = (int) (height*y/numHeli);
            helis.add(new int[]{y, fightx, fighty, fightx, fighty, fuel, type});
        } //Helis initialize

        for (int[] i : firefighs){
            System.out.println("id: "+i[0]+", x: "+i[1]+", y: "+i[2]);
        } //Wypisuje wszystkich strazakow którym zostały przydzielone pola na plaszy

        for (int[] h : helis){
            System.out.println("id: "+h[0]+", x: "+h[1]+", y: "+h[2]);
        } //Wypisuje wszystkie helikoptery którym zostały przydzielone pola na plaszy

        for (int y=0; y < height; y++) {
            Cell fireCell = board.getCell(0, y);
            if (fireCell instanceof ForestCellv2 tree) {
                tree.ignite();
            }
        }
    }

    public void step() {
        stepCount ++;
        board.fireFighlogic(firefighs);
        board.helislogic(helis);
        board.burnTrees();
        board.spreadFire();
        System.out.printf(
                "Step %d |Burning: %.2f%% | Burned: %.2f%%%n",
                stepCount,
                board.getBurningPercentage(),
                board.getBurnedPercentage());
//                board.getClosestfire(500,50));
    }
    public void run(int steps){
        for (int i = 0; i < steps; i++) {

            step();
        }
    }
}
