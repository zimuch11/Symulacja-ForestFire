package com.example.symulacja_fire;

public class Main {
    public static void main(String args[]){
        Simulation sim = new Simulation(300,200,0.7,5,0);
        sim.initialize();
        sim.run(300);
    }
}
