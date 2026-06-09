package com.example.symulacja_fire;

public class Firefighter extends Agent {
    private final int capacity;
    private int currentVolume;

    public Firefighter(int capacity) {
        this.capacity = capacity;
        this.currentVolume = capacity;
    }
}

