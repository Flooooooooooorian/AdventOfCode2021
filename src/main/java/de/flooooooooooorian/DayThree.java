package de.flooooooooooorian;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DayThree {

    public static void main(String[] args) {
        powerConsumption();
        lifeSupport();
    }

    public static void lifeSupport() {
        List<String> lines = getInput();

        int oxygenDecimal = oxygen(lines);
        int co2Decimal =co2(lines);
        System.out.println("Oxygen: " + oxygenDecimal);
        System.out.println("CO2: " + co2Decimal);
        System.out.println("Life Support: " + oxygenDecimal * co2Decimal);
    }

    public static int oxygen(List<String> lines) {
        List<String> oxygen = new ArrayList<>(lines);

        int currentBit = -1;
        while (oxygen.size() > 1) {
            currentBit++;
            int mostCommonBit = getMostCommonBit(oxygen, currentBit);
            int finalCurrentBit = currentBit;
            oxygen.removeIf(line -> line.toCharArray()[finalCurrentBit] != Character.forDigit(mostCommonBit, 10));
        }

        return Integer.parseInt(oxygen.get(0), 2);
    }

    public static int co2(List<String> lines) {
        List<String> co2 = new ArrayList<>(lines);

        int currentBit = -1;
        while (co2.size() > 1) {
            currentBit++;
            int mostCommonBit = getMostCommonBit(co2, currentBit);
            int finalCurrentBit = currentBit;
            co2.removeIf(line -> line.toCharArray()[finalCurrentBit] == Character.forDigit(mostCommonBit, 10));
        }

        return Integer.parseInt(co2.get(0), 2);
    }

    public static int getMostCommonBit(List<String> lines, int bit) {
        int mostCommonBitCounter = 0;

        for (String line : lines) {
            char[] chars = line.toCharArray();
            switch (chars[bit]) {
                case '0' -> mostCommonBitCounter--;
                case '1' -> mostCommonBitCounter++;
                default -> System.out.println("Error: " + chars[bit]);
            }
        }
        return mostCommonBitCounter >= 0 ? 1 : 0;
    }

    public static void powerConsumption() {
        List<String> lines = getInput();

        int[] mostCommonBitCounter = new int[lines.get(0).length()];

        for (int i = 0; i < mostCommonBitCounter.length; i++) {
            mostCommonBitCounter[i] = getMostCommonBit(lines, i);
        }

        StringBuilder epsilon = new StringBuilder();
        StringBuilder gamma = new StringBuilder();
        for (int j : mostCommonBitCounter) {
            epsilon.append(j > 0 ? "1" : "0");
            gamma.append(j > 0 ? "0" : "1");
        }

        System.out.println("Epsilon: " + epsilon);
        System.out.println("Gamma: " + gamma);


        int epsilonDecimal = Integer.parseInt(epsilon.toString(), 2);
        System.out.println("Epsilon: " + epsilonDecimal);

        int gammaDecimal = Integer.parseInt(gamma.toString(), 2);
        System.out.println("Gamma: " + gammaDecimal);

        System.out.println("Power consumption: " + gammaDecimal * epsilonDecimal);
    }

    public static List<String> getInput() {
        return new BufferedReader(new InputStreamReader(DayThree.class.getResourceAsStream("/DayThreeInput.txt")))
                .lines().toList();
    }

    public static String getTestInput() {
        return "00100\n" +
                "11110\n" +
                "10110\n" +
                "10111\n" +
                "10101\n" +
                "01111\n" +
                "00111\n" +
                "11100\n" +
                "10000\n" +
                "11001\n" +
                "00010\n" +
                "01010";
    }
}
