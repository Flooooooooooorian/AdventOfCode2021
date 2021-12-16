package de.flooooooooooorian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFive {
    public static void main(String[] args) {
        task1();
        task2();
    }

    private static void task2() {
        List<String> lines = new ArrayList<>(Resource.getInput("/DayFiveInput.txt"));
        int[][] field = new int[1000][1000];

        List<Vent> vents = getAllVents(lines);

        for (Vent vent : vents) {
            markAll(field, vent);
        }

        System.out.println("Dangerous Spots: " + getDangerousSpotCount(field));
    }

    public static void task1() {
        List<String> lines = new ArrayList<>(Resource.getInput("/DayFiveInput.txt"));
        int[][] field = new int[1000][1000];

        List<Vent> vents = getAllVents(lines);
        vents.removeIf(vent -> vent.getStart()[0] != vent.getEnd()[0] && vent.getStart()[1] != vent.getEnd()[1]);

        for (Vent vent : vents) {
            markVentOnField(field, vent);
        }

        System.out.println("Dangerous Spots: " + getDangerousSpotCount(field));
    }

    public static int getDangerousSpotCount(int[][] field) {
        int dangerousCount = 0;
        for (int[] ints : field) {
            for (int anInt : ints) {
                if (anInt > 1) {
                    dangerousCount++;
                }
            }
        }
        return dangerousCount;
    }

    private static void markVentOnField(int[][] field, Vent vent) {
        if (vent.getStart()[0] == vent.getEnd()[0]) {
            for (int y = vent.getStart()[1]; y <= vent.getEnd()[1]; y++) {
                field[vent.getStart()[0]][y]++;
            }
        } else {
            for (int x = vent.getStart()[0]; x <= vent.getEnd()[0]; x++) {
                field[x][vent.getStart()[1]]++;
            }
        }
    }

    private static void markAll(int[][] field, Vent vent) {
        if (vent.getStart()[0] == vent.getEnd()[0]) {
            for (int y = vent.getStart()[1]; y <= vent.getEnd()[1]; y++) {
                field[vent.getStart()[0]][y]++;
            }
        } else if (vent.getStart()[1] == vent.getEnd()[1]) {
            for (int x = vent.getStart()[0]; x <= vent.getEnd()[0]; x++) {
                field[x][vent.getStart()[1]]++;
            }

        } else {
            int xFactor = 1;
            int yFactor = 1;

            if (vent.getStart()[0] > vent.getEnd()[0]) {
                xFactor = -1;
            }

            if (vent.getStart()[1] > vent.getEnd()[1]) {
                yFactor = -1;
            }

            int x = vent.getStart()[0];
            int y = vent.getStart()[1];

            while (x * xFactor <= vent.getEnd()[0]  && y * yFactor <= vent.getEnd()[1]) {

                field[x][y]++;
                x = x + (xFactor);
                y = y + (yFactor) ;
            }
        }
    }

    public static List<Vent> getAllVents(List<String> lines) {
        List<Vent> vents = new ArrayList<>();
        for (String line : lines) {
            line = line.replace(" ", "");
            List<Integer> coords = Arrays.stream(line.split("->")).flatMap(pos -> Arrays.stream(pos.split(","))).mapToInt(Integer::valueOf).boxed().toList();
            for (int i = 0; i < coords.size(); i += 4) {
                vents.add(new Vent(coords.get(i), coords.get(i + 1), coords.get(i + 2), coords.get(i + 3)));
            }
            for (Vent vent : vents) {
                if (vent.getStart()[0] > vent.getEnd()[0] || vent.getStart()[1] > vent.getEnd()[1]) {
                    int[] tmp = vent.getStart();
                    vent.setStart(vent.getEnd());
                    vent.setEnd(tmp);
                }
            }
        }
        return vents;
    }

    public static String fieldToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[x][y] >= 10) {
                    sb.append(board[x][y]).append(" ");
                } else {
                    sb.append(" ").append(board[x][y]).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static class Vent {
        private int[] start;
        private int[] end;

        public Vent(int startX, int startY, int endX, int endY) {
            this.start = new int[]{startX, startY};
            this.end = new int[]{endX, endY};
        }

        public void setStart(int[] start) {
            this.start = start;
        }

        public void setEnd(int[] end) {
            this.end = end;
        }

        public int[] getStart() {
            return start;
        }

        public int[] getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "Vent{" +
                    "start=" + Arrays.toString(start) +
                    ", end=" + Arrays.toString(end) +
                    '}';
        }
    }
}
