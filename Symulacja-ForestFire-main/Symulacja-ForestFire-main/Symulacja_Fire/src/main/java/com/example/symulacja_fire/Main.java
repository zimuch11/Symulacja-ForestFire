package com.example.symulacja_fire;

public class Main {
    public static void main(String args[]){
        Simulation sim = new Simulation(500,200,0.7,1,0);
        sim.initialize();
        sim.run(300);
    }
}
