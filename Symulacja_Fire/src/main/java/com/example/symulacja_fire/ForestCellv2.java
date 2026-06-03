package com.example.symulacja_fire;

public class ForestCellv2 extends Cell {
    private int resources;
    private boolean onFire;
    private boolean burnt;


    public ForestCellv2(int resources) {
        this.resources = resources;
        this.onFire = false;
        this.burnt = false;
    }

    @Override
    public boolean flammable() {
        return true;
    }
    @Override
    public void ignite(){
        onFire=true;
    }
    @Override
    public boolean isOnFire(){
        return onFire;
    }

    public void burn() {
        if (resources > 0) {
            resources--;
        }
    }
    public boolean isBurnt(){
        if (resources<=0) return true;
        else return false;
    }
}