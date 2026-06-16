package com.example.symulacja_fire;


public class Test {

    public static void main(String[] args) {

        testSpreadChance();
    }

    public static void testSpreadChance() {

        double[] spreadChances = {
                0.1,
                0.11,
                0.12,
                0.13,
                0.14,
                0.15,
                0.16,
                0.17,
                0.18,
                0.19,
                0.2,
        };

        int repetitions = 1;

        for (double spreadChance : spreadChances) {

            double burnedSum = 0;

            for (int i = 0; i < repetitions; i++) {

                Simulation simulation =
                        new Simulation(
                                200,
                                200,
                                0.65,
                                31,
                                0,
                                35,
                                spreadChance
                        );

                simulation.initialize();
                simulation.run(500);

                burnedSum +=
                        simulation.getBoard()
                                .getBurnedPercentage();
            }

            double averageBurned =
                    burnedSum / repetitions;

            System.out.printf(
                    "SpreadChance = %.2f | Avg Burned = %.2f%%%n",
                    spreadChance,
                    averageBurned
            );
        }
    }
}