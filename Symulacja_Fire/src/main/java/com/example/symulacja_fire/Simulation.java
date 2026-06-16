package com.example.symulacja_fire;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final int height;
    private final int width;
    private final int numFireFigh;
    private final int numHeli;
    private  final double spredChance;
    private int resources;
    private Board board;
    private Firefighter firefighter;
    private Helicopter helicopter;
    private Fire fire;
    private int stepCount;
    boolean isRunning;
    private List<int[]> firefighs = new ArrayList<>();
    private List<int[]> helis = new ArrayList<>();

    public Simulation(int width, int height, double forestation, int numFireFigh, int numHeli, int resources, double spredChance) {
        this.numFireFigh = numFireFigh;
        this.numHeli = numHeli;
        this.height = height;
        this.width = width;
        this.resources = resources;
        this.spredChance = spredChance;
        board = new Board(width, height, forestation,resources);
        firefighter = new Firefighter(width, height);
        helicopter = new Helicopter(width, height);
        fire = new Fire(spredChance);
    }

    /**
     * <h1>initialize</h1>
     * Metoda tworząca strażaków i helikopterów (inicjuje ich położenie itp.) oraz zapala
     * las z lewej strony plaszy
     */
    public void initialize() {
        stepCount=0;
        isRunning=true;
        for (int y=0; y < numFireFigh; y++){
            int type = -1;
            int fuel = 15;
            int fightx = (int) (width*0.9);
            int fighty = (int) (height*y/numFireFigh)+(height/numFireFigh/2);
            firefighs.add(new int[]{y, fightx, fighty, fuel, type, fightx, fighty});
        } //Strazak initialize

        for (int y=0; y < numHeli; y++){
            int type = -2;
            int fuel = 50;
            int fightx = (int) (width*0.95);
            int fighty = (int) (height*y/numHeli)+(height/numHeli/2);
            helis.add(new int[]{y, fightx, fighty, fightx, fighty, fuel, type});
        } //Helis initialize

        for (int y=0; y < height; y++) {
            Cell fireCell = board.getCell(0, y);
            if (fireCell instanceof ForestCell tree) {
                tree.ignite();
            }
        }
    }

    /**
     * <h1>step</h1>
     * Metoda dysponująca zachowaniem ognia, strażaków i helikopterów w jednym kroku
     */
    public void step() {
        stepCount ++;
        for(int i=0;i<=1;i++){ ///Część logiki strażaków i helikopterów
            firefighter.fireFighlogic(firefighs);
            helicopter.helislogic(helis);
        }
        fire.burnTrees(board);
        fire.spreadFire(board);
//        System.out.printf(
//                "Step %d |Burning: %.2f%% | Burned: %.2f%%%n",
//                stepCount,
//                getBurningPercentage(),
//                getBurntPercentage());
    }
    /**
     * <h1>run</h1>
     * Metoda dokonywująca kroków symulacji
     * @param steps liczba kroków symulacji
     */
    public void run(int steps){
        for (int i = 0; i < steps; i++) {

            step();
        }
    }

    /**
     * <h1>getBurningPercentage</h1>
     * Metoda licząca procent płonącego się lasu.
     * @return procent palącego się lasu
     */
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

    /**
     * <h1>getBurnedPercentage</h1>
     * Metoda licząca procent spalonego lasu.
     * @return procent spalonego lasu
     */
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
    public List<int[]> getHelicopters() {
        return this.helis;
    }
}
