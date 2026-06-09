package com.example.symulacja_fire;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final int height;
    private final int width;
    private final int numFireFigh;
    private final int numHeli;
    private int resources;
    private Board board;
    private Fire fire;
    private int stepCount;
    boolean isRunning;
    private List<int[]> firefighs = new ArrayList<>();



    public Simulation(int width, int height, double forestation, int numFireFigh, int numHeli, int resources) {
        this.numFireFigh = numFireFigh;
        this.numHeli = numHeli;
        this.height = height;
        this.width = width;
        this.resources = resources;
        board = new Board(width, height, forestation,resources);
        fire = new Fire();
    }

    public void initialize() {
        stepCount=0;
        isRunning=true;
        for (int y=0; y < numFireFigh; y++){
            int fightx = (int) (width);
            int fighty = (int) (height*y/numFireFigh);
            firefighs.add(new int[]{y, fightx,fighty});
        }

        for (int[] i : firefighs){
            System.out.println("id: "+i[0]+", x: "+i[1]+", y: "+i[2]);
        } //Wypisuje wszystkich strazakow którym zostały przydzielone pola na plaszy

        for (int y=0; y < height; y++) {
            Cell fireCell = board.getCell(0, y);
            if (fireCell instanceof ForestCell tree) {
                tree.ignite();
            }
        }
    }


    public void step() {
        stepCount ++;
        board.fireFighlogic(firefighs);
        fire.burnTrees(board);
        fire.spreadFire(board);
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
    public double getBurningPercentage() {

        double burning = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Cell cell = board.getCell(x, y);

                if (cell instanceof Cell forest &&
                        forest.isOnFire()) {

                    burning++;
                }
            }
        }
        return burning / board.getNumForest() *100;
    }
    public double getBurntPercentage() {

        double burnt = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (board.getCell(x, y) instanceof BurntCell) {
                    burnt++;
                }
            }
        }
        return burnt / board.getNumForest() * 100;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isRunning(){
        return isRunning;
    }

    public List<int[]> getFirefighters() {
        return this.firefighs;
    }
}
