package de.flooooooooooorian;

import java.util.*;

public class DayEight {

    public static void main(String[] args) {
        System.out.println(task1(readInput()));
        System.out.println(task2(readInput()));
    }

    private static int task2(List<String> lines) {
        List<List<List<String>>> sequences = formatInput(lines);

        List<Map<String, String>> wirings = new ArrayList<>();
        for (List<List<String>> sequence : sequences) {
            wirings.add(calculateWiring(sequence.get(0)));
        }

        List<List<Integer>> numbers = new ArrayList<>();
        for (int i = 0; i < sequences.size(); i++) {
            numbers.add(calculateOutput(sequences.get(i).get(1), convertWiring(wirings.get(i))));
        }

        return sumNumbers(numbers);
    }

    private static int sumNumbers(List<List<Integer>> numbersList) {
        int totalSum = 0;
        for (List<Integer> numbers : numbersList) {
            StringBuilder num = new StringBuilder();
            for (int number : numbers) {
                num.append(number);
            }
            totalSum += Integer.parseInt(num.toString());
        }
        return totalSum;
    }

    private static int task1(List<String> lines) {
        List<List<List<String>>> sequences = formatInput(lines);

        List<Map<String, String>> wirings = new ArrayList<>();
        for (List<List<String>> sequence : sequences) {
            wirings.add(calculateWiring(sequence.get(0)));
        }

        List<List<Integer>> numbers = new ArrayList<>();
        for (int i = 0; i < sequences.size(); i++) {
            numbers.add(calculateOutput(sequences.get(i).get(1), convertWiring(wirings.get(i))));
        }

        return countNumbers(numbers);
    }

    private static List<List<List<String>>> formatInput(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(" \\| "))
                .map(line -> Arrays.stream(line)
                        .map(side -> Arrays.stream(side.split(" "))
                                .toList())
                        .toList())
                .toList();
    }

    private static int countNumbers(List<List<Integer>> numbersList) {
        int count = 0;
        for (List<Integer> numbers : numbersList) {
            for (int number : numbers) {
                if (number == 1 || number == 4 || number == 7 || number == 8) {
                    count++;
                }
            }
        }
        return count;
    }

    private static List<Integer> calculateOutput(List<String> line, Map<String, String> wiring) {

        List<Integer> numbers = new ArrayList<>();
        for (String digit : line) {
            char[] tempArray = digit.toCharArray();
            Arrays.sort(tempArray);
            String sortedDigit = new String(tempArray);

            for (int i = 0; i < wiring.size(); i++) {
                if (sortedDigit.equals(wiring.get(String.valueOf(i)))) {
                    numbers.add(i);
                }
            }
        }

        return numbers;
    }

    private static Map<String, String> convertWiring(Map<String, String> wiring) {
        Map<String, String> digitWiring = new HashMap<>();

        char[] tempArray = (wiring.get("A") + wiring.get("B") + wiring.get("C") + wiring.get("E") + wiring.get("F") + wiring.get("G")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("0", new String(tempArray));

        tempArray = (wiring.get("C") + wiring.get("F")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("1", new String(tempArray));

        tempArray = (wiring.get("A") + wiring.get("C") + wiring.get("D") + wiring.get("E") + wiring.get("G")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("2", new String(tempArray));

        tempArray = (wiring.get("A") + wiring.get("C") + wiring.get("D") + wiring.get("F") + wiring.get("G")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("3", new String(tempArray));

        tempArray = (wiring.get("B") + wiring.get("C") + wiring.get("D") + wiring.get("F")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("4", new String(tempArray));

        tempArray = (wiring.get("A") + wiring.get("B") + wiring.get("D") + wiring.get("F") + wiring.get("G")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("5", new String(tempArray));

        tempArray = (wiring.get("A") + wiring.get("B") + wiring.get("D") + wiring.get("E") + wiring.get("F") + wiring.get("G")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("6", new String(tempArray));

        tempArray = (wiring.get("A") + wiring.get("C") + wiring.get("F")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("7", new String(tempArray));

        tempArray = (wiring.get("A") + wiring.get("B") + wiring.get("C") + wiring.get("D") + wiring.get("E") + wiring.get("F") + wiring.get("G")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("8", new String(tempArray));

        tempArray = (wiring.get("A") + wiring.get("B") + wiring.get("C") + wiring.get("D") + wiring.get("F") + wiring.get("G")).toCharArray();
        Arrays.sort(tempArray);
        digitWiring.put("9", new String(tempArray));

        return digitWiring;
    }

    private static Map<String, String> calculateWiring(List<String> line) {
        Map<String, String> wiring = new HashMap<>();

        List<String> one = Arrays.stream(getSimpleDigitsWithLength(line, 2)).toList();
        List<String> four = Arrays.stream(getSimpleDigitsWithLength(line, 4)).toList();
        List<String> seven = Arrays.stream(getSimpleDigitsWithLength(line, 3)).toList();
        List<String> eight = Arrays.stream(getSimpleDigitsWithLength(line, 7)).toList();

        wiring.put("A", seven.stream().filter(c -> !one.contains(c)).toList().get(0));

        wiring.put("C", String.join("", one));
        wiring.put("F", String.join("", one));

        wiring.put("B", String.join("", four.stream()
                .filter(c -> !one.contains(c))
                .toList()));
        wiring.put("D", String.join("", four.stream()
                .filter(c -> !one.contains(c))
                .toList()));

        wiring.put("E", String.join("", eight.stream()
                .filter(c -> !c.equals(wiring.get("A")))
                .filter(c -> !one.contains(c))
                .filter(c -> !four.stream()
                        .filter(c1 -> !one.contains(c1)).toList().contains(c))
                .filter(c -> !one.contains(c))
                .toList()));

        wiring.put("G", String.join("", eight.stream()
                .filter(c -> !c.equals(wiring.get("A")))
                .filter(c -> !one.contains(c))
                .filter(c -> !four.stream()
                        .filter(c1 -> !one.contains(c1)).toList().contains(c))
                .toList()));

        List<String> three = Arrays.stream(line.stream()
                        .filter(c -> c.length() == 5)
                        .filter(c -> c.contains(one.get(0)))
                        .filter(c -> c.contains(one.get(1)))
                        .toList().get(0)
                        .split(""))
                .toList();

        three = three.stream()
                .filter(c -> !c.equals(wiring.get("A")))
                .filter(c -> !Arrays.stream(wiring.get("G").split("")).toList().contains(c))
                .filter(c -> Arrays.stream(wiring.get("B").split("")).toList().contains(c))
                .toList();

        wiring.put("D", three.get(0));

        wiring.put("B", Arrays.stream(wiring.get("B").split("")).toList().stream().filter(c -> !c.equals(wiring.get("D"))).toList().get(0));

        List<String> fives = Arrays.stream(line.stream()
                        .filter(c -> c.length() == 5)
                        .filter(c -> Arrays.stream(c.split("")).toList().contains(wiring.get("B")))
                        .toList().get(0).split(""))
                .filter(c -> !c.equals(wiring.get("A")))
                .filter(c -> !c.equals(wiring.get("B")))
                .filter(c -> !c.equals(wiring.get("D")))
                .toList();

        wiring.put("G", fives.stream().filter(c -> !Arrays.stream(wiring.get("C").split("")).toList().contains(c)).toList().get(0));

        wiring.put("E", Arrays.stream(wiring.get("E").split("")).filter(c -> !c.equals(wiring.get("G"))).toList().get(0));

        wiring.put("F", fives.stream().filter(c -> Arrays.stream(wiring.get("F").split("")).toList().contains(c)).toList().get(0));

        wiring.put("C", Arrays.stream(wiring.get("C").split(""))
                .filter(c -> !c.equals(wiring.get("F")))
                .toList().get(0));

        return wiring;
    }

    private static String[] getSimpleDigitsWithLength(List<String> line, int length) {
        String[] s = line.stream()
                .filter(c -> c.length() == length)
                .findFirst().get()
                .split("");
        Arrays.sort(s);
        return s;
    }

    private static List<String> readInput() {
        return new ArrayList<>(Resource.getInput("/DayEightInput.txt"));
    }

}
