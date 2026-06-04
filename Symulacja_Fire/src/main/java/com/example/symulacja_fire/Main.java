package com.example.symulacja_fire;

public class Main {
    public static void main(String args[]){
        Simulation sim = new Simulation(1000,100,0.7,3,1);
        sim.initialize();
        sim.run(300);
    }
}
