package com.example.symulacja_fire;

public class Simulation {
    private final int heigh;
    private final int width;
    private final int numFireFigh;
    private final int numHeli;
    private Board board;
    private int stepCount;
    private boolean isRunning;



    public Simulation(int width, int height, double forestation, int numFireFigh, int numHeli) {
        this.numFireFigh = numFireFigh;
        this.numHeli = numHeli;
        this.heigh = height;
        this.width = width;
        board = new Board(width, height, forestation);
    }

    public void initialize() {
        stepCount=0;
        for (int y=0; y < heigh; y++) {
            Cell fireCell = board.getCell(0, y);
            if (fireCell instanceof ForestCellv2 tree) {
                tree.ignite();
            }
        }
    }

    public void step() {
        stepCount ++;
        board.burnTrees();
        board.spreadFire();
        System.out.printf(
                "Step %d |Burning: %.2f%% | Burned: %.2f%%%n",
                stepCount,
                board.getBurningPercentage(),
                board.getBurnedPercentage());
    }
    public void run(int steps){
        for (int i = 0; i < steps; i++) {

            step();
        }
    }
}
