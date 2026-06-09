package com.example.symulacja_fire;

public class CityCell extends Cell {

    private boolean onFire;

    @Override
    public boolean flammable() {
        return true;
    }
    @Override
    public boolean isOnFire()
    {
        return onFire;
    }
    @Override
    public void ignite(){
        onFire = true;
    }
}