package com.example.symulacja_fire;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void getClosestfireTest() {
        Board board = new Board(5, 5, 0.0, 10);

        ForestCell forestCell1 = new ForestCell(10);
        ForestCell forestCell2 = new ForestCell(10);
        board.setCell(1, 2, forestCell1);
        board.setCell(3,4, forestCell2);
        forestCell1.ignite();
        forestCell2.ignite();

        var result = Board.getClosestfire(0, 0, 0, 10, 0, 0);

        assertEquals(1, result.get(0)[0]); // x
        assertEquals(2, result.get(0)[1]); // y
    }
}


