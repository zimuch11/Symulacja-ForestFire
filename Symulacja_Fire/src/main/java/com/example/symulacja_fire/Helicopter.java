package com.example.symulacja_fire;

public class Helicopter extends Agent {
    private final int capacity;
    private final int range;
    private int currentVolume;

    public Helicopter(int capacity,int range) {
        this.capacity = capacity;
        this.range = range;
        this.currentVolume = capacity;
    }
}

