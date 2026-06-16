package com.example.symulacja_fire;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FireTest {
    @Test
    public void burnTrees_Test() {

        Board board = new Board(1, 1,1.0,1);
        ForestCell cell = new ForestCell(1);

        cell.ignite();
        board.setCell(0, 0, cell);

        Fire fire = new Fire(1.0);

        fire.burnTrees(board);

        assertTrue(board.getCell(0, 0) instanceof BurntCell);
    }
}
