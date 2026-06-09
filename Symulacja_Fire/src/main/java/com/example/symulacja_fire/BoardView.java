package com.example.symulacja_fire;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class BoardView {

    private final Board board;
    private final Canvas canvas;
    private final Simulation simulation;

    public BoardView( Simulation simulation, Board board, Canvas canvas) {
        this.simulation = simulation;
        this.board = board;
        this.canvas = canvas;

    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int cellSize = Math.min((int) canvas.getWidth() / board.getWidth(), (int) canvas.getHeight() / board.getHeight());

        for (int x = 0; x < board.getWidth(); x++) {

            for (int y = 0; y < board.getHeight(); y++) {

                Cell cell = board.getCell(x, y);

                if (cell instanceof ForestCell forest) {

                    if (forest.isOnFire()) {
                        gc.setFill(Color.RED);
                    } else {
                        gc.setFill(Color.GREEN);
                    }
                } else if (cell instanceof BurntCell) {
                    gc.setFill(Color.DARKRED);
                } else if (cell instanceof CityCell) {
                    gc.setFill(Color.GRAY);
                } else {
                    gc.setFill((Color.BLACK));
                }

                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        List<int[]> firefighters = simulation.getFirefighters();

        gc.setFill(Color.YELLOW);

        for (int[] firefighter : firefighters) {
            int fx = firefighter[1];
            int fy = firefighter[2];

            gc.fillRect(fx * cellSize, fy * cellSize, cellSize, cellSize);
        }
    }
}