package com.example.symulacja_fire;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int width;
    private final int height;
    private final double forestation;

    private Cell[][] cells;
    private Random random = new Random();

    public Board(int width, int height, double forestation) {
        this.width = width;
        this.height = height;
        this.forestation = forestation;

        cells = new Cell[width][height];

        createBoard();
    }

    private void createBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x >= width - width / 4) {
                    cells[x][y] = new CityCell();
                } else {
                    if (random.nextDouble() < forestation) {
                        cells[x][y] = new ForestCellv2(10);
                    } else {
                        cells[x][y] = new EmptyCell();
                    }
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public List<Cell> getNeighbours(int x, int y) {

        List<Cell> neighbours = new ArrayList<>();

        int[][] directions = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1, 0}, {1, 0},
                {-1, 1}, {0, 1}, {1, 1}
        };

        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx >= 0 && nx < width &&
                    ny >= 0 && ny < height) {

                neighbours.add(cells[nx][ny]);
            }
        }

        return neighbours;
    }

    public List<int[]> getClosestfire(int pos_x, int pos_y){
        List<int[]> closestFirelist = new ArrayList<>();
        double pyt_final=1000000;
        int x_final=0;
        int y_final=0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Cell cell = cells[x][y];

                if (cell.isOnFire()) {
                    double dx = pos_x - x;
                    dx = Math.abs(dx);
                    double dy = pos_y - y;
                    dy = Math.abs(dy);
                    double pyt = Math.sqrt((dx*dx)+(dy*dy));
                    if(pyt_final>pyt){
                        pyt_final=pyt;
                        x_final=x;
                        y_final=y;
                    }
                }
            }
        }
        if(pyt_final==1000000){
            System.out.println("No fire");
        } else {
            System.out.printf("Closest fire: Distance: "+"%.2f"+", Fire |x:"+"%d"+", y:"+"%d"+"| <__> Firefighter|x: "+"%d"+", y:"+"%d"+"|\n",pyt_final, x_final, y_final, pos_x, pos_y);
        }
        closestFirelist.add(new int[]{x_final, y_final});
        return closestFirelist;
    }

    public int[] moveFirefigh(List<int[]>closestFirelist, int[] i){
        int x_fire = closestFirelist.get(0)[0];
        int y_fire = closestFirelist.get(0)[1];
        if(i[1]>x_fire){
            i[1]-=1;
        } else if (i[1]<x_fire){
            i[1]+=1;
        }
        if(i[2]>y_fire){
            i[2]-=1;
        } else if (i[2]<y_fire){
            i[2]+=1;
        }
        return i;
    }

    public void fireFighlogic(List<int[]> firefighs) {
        for (int[] i : firefighs){
//            System.out.println("id: "+i[0]+", x: "+i[1]+", y: "+i[2]);
            int[] nextPosition = moveFirefigh(getClosestfire(i[1],i[2]),i);
            i[0] = nextPosition[0];
            i[1] = nextPosition[1];
//            moveFirefigh(getClosestfire(i[1],i[2]),i);
        }
    }

    public void burnTrees() {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Cell cell = cells[x][y];

                if (cell instanceof ForestCellv2 tree) {

                    if (tree.isOnFire()) {
                        tree.burn();

                        if (tree.isBurnt() == true) {
                            cells[x][y] = new BurntCell();
                        }
                    }
                }
            }
        }
    }
    public void spreadFire() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Cell cell = cells[x][y];

                if (cell.isOnFire()) {
                    for (Cell neighbour : getNeighbours(x, y)) {
                        if(random.nextDouble() < 0.3) {
                            if (neighbour.flammable() && !neighbour.isOnFire()) {
                                neighbour.ignite();
                            }
                        }
                    }
                }
            }
        }
    }
    public double getBurningPercentage() {

        int burning = 0;
        int total = width * height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Cell cell = cells[x][y];

                if (cell instanceof Cell forest &&
                        forest.isOnFire()) {

                    burning++;
                }
            }
        }

        return 100.0 * burning / total;
    }
    public double getBurnedPercentage() {

        int burned = 0;
        int total = width * height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (cells[x][y] instanceof BurntCell) {
                    burned++;
                }
            }
        }

        return 100.0 * burned / total;
    }

}

