package de.flooooooooooorian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySeven {

    public static void main(String[] args) {
        System.out.println(task1(readInput()));
        System.out.println(task2(readInput()));
    }

    public static int task1(List<Integer> crabs) {
        Integer highestPosition = crabs.stream().max(Integer::compare).get();

        int bestPos = 0;
        int lowestFuel = -1;
        for (int position = 0; position < highestPosition; position++) {
            int fuel = calculateFuel(crabs, position);
            if (fuel < lowestFuel || lowestFuel < 0) {
                bestPos = position;
                lowestFuel = fuel;
            }
        }
        return lowestFuel;
    }

    private static int calculateFuel(List<Integer> crabs, int position) {
        int fuel = 0;

        for (Integer crab : crabs) {
            int distance = (position - crab) * Integer.signum((position - crab));
            fuel+=distance;
        }
        return fuel;
    }

    public static int task2(List<Integer> crabs) {
        Integer highestPosition = crabs.stream().max(Integer::compare).get();

        int bestPos = 0;
        int lowestFuel = -1;
        for (int position = 0; position < highestPosition; position++) {
            int fuel = calculateExpensiveFuel(crabs, position);
            if (fuel < lowestFuel || lowestFuel < 0) {
                bestPos = position;
                lowestFuel = fuel;
            }
        }
        return lowestFuel;
    }

    private static int calculateExpensiveFuel(List<Integer> crabs, int position) {
        int fuel = 0;

        for (Integer crab : crabs) {
            int distance = (position - crab) * Integer.signum((position - crab));
            for (int i = 1; i <= distance; i++) {
                fuel+=i;
            }
        }
        return fuel;
    }

    private static List<Integer> readInput() {
        return new ArrayList<>(Resource.getInput("/DaySevenInput.txt")).stream().flatMap(line -> Arrays.stream(line.split(","))).mapToInt(Integer::parseInt).boxed().toList();
    }
}
