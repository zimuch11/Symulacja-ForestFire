package com.example.symulacja_fire;

import java.util.Random;

public class Fire {

    private final double spreadChance;

    public Fire(double spreadChance) {
        this.spreadChance = spreadChance;
    }

    private final Random random = new Random();

    /**
     * <h1>burnTrees</h1>
     * Metoda wypalająca drzewa na plaszy.
     * Jeśli ogień wyczerpał zasoby w komórce, drzewo zostaje spalone.
     * @param board wygenerowana plansza
     */
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
    /**
     * <h1>spreadFire</h1>
     * Metoda rozprzestrzeniająca ogień na plaszy.
     * <li>Ogień ma random.nextDouble() < spreadChance na rozprzestrzenienie się
     * @param board wygenerowana plansza
     */
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