package com.example.symulacja_fire;

public class Main2 {
    public static void main(String args[]){
                Simulation sim = new Simulation(200,200,0.65,10,2,35,0.2);
                sim.initialize();
                sim.run(250);
    }
}
