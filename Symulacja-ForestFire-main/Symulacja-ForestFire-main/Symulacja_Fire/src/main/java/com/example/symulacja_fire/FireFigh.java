package com.example.symulacja_fire;

import java.util.List;
import java.util.Random;

public class FireFigh {

    private Position position;
    private Random random = new Random();

    public FireFigh(Position position) {
        this.position = position;
    }

//    public void update(Board board) {
//
//        int x = position.getX();
//        int y = position.getY();
//        int id = position.getId();
//
//        List<Cell> getClosestfire = board.getClosestfire(x, y);
//
//        for (Cell cell : getClosestfire) {
//            if (random.nextDouble()<0.5){
//                if (cell.flammable()&&!cell.isOnFire()) {
//                }
//            }
//        }
//    }
//
//
}
