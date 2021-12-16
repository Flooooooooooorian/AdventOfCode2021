package de.flooooooooooorian;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DayOne {

    public static String getInput() {
        return new BufferedReader(new InputStreamReader(DayOne.class.getResourceAsStream("/DayOneInput.txt")))
                .lines().collect(Collectors.joining("\n"));
    }

    public static int[] getNumbers() {
        return getInput().lines().mapToInt(Integer::valueOf).toArray();
    }

    public static void day11() {
        int[] numbers = getNumbers();

        int count = 0;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[i - 1]) {
                count++;
            }
        }
        System.out.println(numbers.length);
        System.out.println(count);
    }

    public static void day12() {
        int[] numbers = getNumbers();

        int count = 0;
        for (int i = 2; i < numbers.length - 1; i++) {
            if (numbers[i - 1] + numbers[i] + numbers[i + 1] > numbers[i - 2] + numbers[i - 1] + numbers[i]) {
                count++;
            }
        }
        System.out.println(numbers.length);
        System.out.println(count);
    }

}

