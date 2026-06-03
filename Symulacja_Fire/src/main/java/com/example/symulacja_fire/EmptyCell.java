package com.example.symulacja_fire;

public class EmptyCell extends Cell {

    @Override
    public boolean flammable() {
        return false;
    }
    @Override
    public boolean isOnFire(){
        return false;
    }
    @Override
    public void ignite(){
    }
}