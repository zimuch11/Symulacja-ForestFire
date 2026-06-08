package com.example.symulacja_fire;

import java.util.Random;

public class Fire {

    private final double spreadChance = 0.2;

    private final Random random = new Random();

    public void burnTrees(Board board) {

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {

                Cell cell = board.getCell(x, y);

                if (cell instanceof ForestCell tree) {

                    if (tree.isOnFire()) {

                        tree.burn();

                        if (tree.isBurnt()) {
                            board.setCell(x, y, new BurntCell());
                        }
                    }
                }
            }
        }
    }

    public void spreadFire(Board board) {

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {

                Cell cell = board.getCell(x, y);

                if (cell.isOnFire()) {

                    for (Cell neighbour : board.getNeighbours(x, y)) {

                        if (random.nextDouble() < spreadChance) {

                            if (neighbour.flammable() &&
                                    !neighbour.isOnFire()) {

                                neighbour.ignite();
                            }
                        }
                    }
                }
            }
        }
    }
}