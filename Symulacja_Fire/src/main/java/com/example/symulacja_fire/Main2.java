package com.example.symulacja_fire;

public class Main2 {
    public static void main(String args[]){
        Simulation sim = new Simulation(50,50,0.7,3,1,20,0.2);
        sim.initialize();
        sim.run(250);
    }
}
