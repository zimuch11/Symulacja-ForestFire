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

