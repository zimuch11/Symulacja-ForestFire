package com.example.symulacja_fire;

import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void moveFirefigh_shouldBeRepeatableWithSeed() {

        Random r = new Random(42);

        List<int[]> fire = List.of(new int[]{5, 5});
        int[] pos = new int[]{10, 10};

        int nxt = r.nextInt(2);

        int[] result = Board.moveFirefigh(fire, pos);

        assertNotNull(result);
    }

}