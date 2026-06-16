package com.example.symulacja_fire;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForestCellTest {

    @Test
    public void flammable_Test() {
        ForestCell cell = new ForestCell(5);

        assertTrue(cell.flammable());
    }

    @Test
    public void ignite_Test() {
        ForestCell cell = new ForestCell(5);

        cell.ignite();

        assertTrue(cell.isOnFire());
    }

    @Test
    public void isOnFire_Test() {
        ForestCell cell = new ForestCell(10);

        assertFalse(cell.isOnFire());
    }

    @Test
    public void isBurnt_Test() {
        ForestCell cell = new ForestCell(2);

        cell.burn();
        cell.burn();

        assertTrue(cell.isBurnt());
    }
}