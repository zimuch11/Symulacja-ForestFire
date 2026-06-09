package com.example.symulacja_fire;

import java.security.PublicKey;

public class ForestCell extends Cell {
    private int resources;
    private boolean onFire;


    public ForestCell(int resources) {
        this.resources = resources;
    }

    @Override
    public boolean flammable() {
        return true;
    }
    @Override
    public boolean isOnFire(){
        return onFire;
    }
    @Override
    public void ignite(){
        onFire=true;
    }

    public void burn() {
        if (onFire){
            resources--;
        }
        if (resources <= 0) {
            onFire=false;
        }
    }
}



