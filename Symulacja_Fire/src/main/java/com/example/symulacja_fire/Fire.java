package com.example.symulacja_fire;

import java.util.List;
import java.util.Random;

public class Fire {

    private Position position;
    private Random random = new Random();

    public Fire(Position position) {
        this.position = position;
    }


    public void update(Board board) {

        int x = position.getX();
        int y = position.getY();

        List<Cell> neighbours = board.getNeighbours(x, y);

        for (Cell cell : neighbours) {
            if (random.nextDouble()<0.5){
                if (cell.flammable()&&!cell.isOnFire()) {
                    }
                }
            }
        }
    }