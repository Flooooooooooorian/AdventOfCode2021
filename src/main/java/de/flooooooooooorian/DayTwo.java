package de.flooooooooooorian;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class DayTwo {

    public static String getInput() {
        return new BufferedReader(new InputStreamReader(DayTwo.class.getResourceAsStream("/DayTwoInput.txt")))
                .lines().collect(Collectors.joining("\n"));
    }

    public static void day21() {
        int horizontal = 0;
        int depth = 0;

        List<String> commands = getInput().lines().toList();

        for (String s : commands) {
            String[] command = s.split(" ");

            switch (command[0]) {
                case "forward" -> {
                    horizontal += Integer.parseInt(command[1]);
                }
                case "down" -> {
                    depth += Integer.parseInt(command[1]);
                }
                case "up" -> {
                    depth -= Integer.parseInt(command[1]);
                }
                default -> {
                    throw new RuntimeException();
                }
            }
        }
        System.out.println("Horizontal: " + horizontal);
        System.out.println("Depth: " + depth);
    }

    public static void day22() {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        List<String> commands = getInput().lines().toList();

        for (String s : commands) {
            String[] command = s.split(" ");

            switch (command[0]) {
                case "forward" -> {
                    horizontal += Integer.parseInt(command[1]);
                    depth += aim * Integer.parseInt(command[1]);
                }
                case "down" -> {
                    aim += Integer.parseInt(command[1]);
                }
                case "up" -> {
                    aim -= Integer.parseInt(command[1]);
                }
                default -> {
                    throw new RuntimeException();
                }
            }
        }
        System.out.println("Horizontal: " + horizontal);
        System.out.println("Depth: " + depth);
        System.out.println("Aim: " + aim);
    }

}
