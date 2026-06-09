package com.example.symulacja_fire;

public abstract class Cell {
    protected int x;
    protected int y;

    public abstract boolean flammable();
    public boolean isOnFire(){
        return false;
    };
    public void ignite() {
    }
}
