package de.flooooooooooorian;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySix {
    public static void main(String[] args) {
        System.out.println(task1(readInput(), 18));
        System.out.println(task1(readInput(), 80));
        System.out.println(task1(readInput(), 256));
    }

    public static BigInteger task1(List<Laternfish> fishes, int days) {
        List<Laternfish> allFishes = new ArrayList<>(fishes);

        for (int day = 0; day < days; day++) {

            long newFishCount = 0;

            for (Laternfish fish : allFishes) {
                if (fish.getDays() == 0) {
                    fish.setDays(6);
                    newFishCount = newFishCount + fish.getCount();
                } else {
                    fish.setDays(fish.getDays() - 1);
                }
            }

            allFishes.add(new Laternfish(8, newFishCount));
            allFishes = mergeDuplicates(allFishes);
        }

        return countFishes(allFishes);
    }

    private static List<Laternfish> mergeDuplicates(List<Laternfish> fishes) {
        List<Laternfish> allFishes = new ArrayList<>(fishes);
        for (int i = 0; i < allFishes.size(); i++) {
            for (int j = i + 1; j < allFishes.size(); j++) {
                if (allFishes.get(i).getDays() == allFishes.get(j).getDays()) {
                    allFishes.get(i).setCount(allFishes.get(i).getCount() + allFishes.get(j).getCount());
                    allFishes.remove(j);
                    i--;
                    break;
                }
            }
        }
        return allFishes;
    }

    private static List<Laternfish> readInput() {
        List<String> lines = new ArrayList<>(Resource.getInput("/DaySixInput.txt")).stream().flatMap(line -> Arrays.stream(line.split(","))).toList();

        List<Laternfish> fishes = new ArrayList<>();

        lines.forEach(line -> {
            for (Laternfish fish : fishes) {
                if (fish.getDays() == Integer.parseInt(line)) {
                    fish.setCount(fish.getCount() + 1);
                    return;
                }
            }
            fishes.add(new Laternfish(Integer.parseInt(line)));
        });
        return fishes;
    }

    private static BigInteger countFishes(List<Laternfish> fishes) {
        BigInteger fishCount = BigInteger.ZERO;
        for (Laternfish fish : fishes) {
            fishCount = fishCount.add(BigInteger.valueOf(fish.getCount()));
        }
        return fishCount;
    }

    private static class Laternfish {
        private int days;
        private long count = 1;

        public Laternfish(int days) {
            this.days = days;
        }

        public Laternfish(int days, long newFishCount) {
            this.days = days;
            this.count = newFishCount;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(String.valueOf(days));
            for (int i = 1; i < count; i++) {
                builder.append(", ").append(days);
            }
            return builder.toString();
        }
    }
}
