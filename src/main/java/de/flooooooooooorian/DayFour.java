package de.flooooooooooorian;

import java.util.*;
import java.util.function.Predicate;

public class DayFour {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>(Resource.getInput("/DayFourInput.txt"));
        List<Integer> numbersToDraw = Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::valueOf).boxed().toList();

        lines.remove(0);
        lines.removeIf(String::isBlank);

        List<int[][]> boards = getBoards(lines);

        System.out.println("Score: " + squidBingo(boards, numbersToDraw));

        System.out.println("Score: " + squidBingoGetLoosingBoardScore(boards, numbersToDraw));
    }

    public static int squidBingoGetLoosingBoardScore(List<int[][]> boards, List<Integer> numbers) {
        List<Integer> numbersDrawn = new ArrayList<>();
        for (Integer number : numbers) {
            numbersDrawn.add(number);

            List<int[][]> boardsToRemove = new ArrayList<>();
            for (int[][] board: boards) {
                if (checkBoardForWin(board, numbersDrawn)){
                    if (boards.size() == 1) {
                        return getScore(boards.get(0), numbersDrawn);
                    }
                    else {
                        boardsToRemove.add(board);
                    }
                }
            }
            boards.removeAll(boardsToRemove);
        }
        return -1;
    }

    public static int squidBingo(List<int[][]> boards, List<Integer> numbers) {
        List<Integer> numbersDrawn = new ArrayList<>();
        for (Integer number : numbers) {
            numbersDrawn.add(number);

            for (int[][] board : boards) {
                if (checkBoardForWin(board, numbersDrawn)) {
                    return getScore(board, numbersDrawn);
                }
            }
        }
        return -1;
    }

    public static boolean checkBoardForWin(int[][] board, List<Integer> numbers) {
        for (int[] row : board) {
            if (numbers.containsAll(Arrays.stream(row).boxed().toList())) {
                return true;
            }
        }

        for (int i = 0; i < board.length; i++) {
            int finalI = i;
            int[] coloumn = Arrays.stream(board).mapToInt(ints -> ints[finalI]).toArray();

            if (numbers.containsAll(Arrays.stream(coloumn).boxed().toList())) {
                return true;
            }
        }
        return false;
    }

    public static int getScore(int[][] board, List<Integer> numbersDrawn) {
        int score = 0;
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (!numbersDrawn.contains(anInt)) {
                    score += anInt;
                }
            }
        }
        score *= numbersDrawn.get(numbersDrawn.size() - 1);
        return score;
    }

    public static String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt >= 10) {
                    sb.append(anInt).append(" ");
                } else {
                    sb.append(" ").append(anInt).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static List<int[][]> getBoards(List<String> lines) {
        List<Integer> numbers = lines.stream().flatMap(line -> Arrays.stream(line.split(" "))).filter(Predicate.not(String::isBlank)).mapToInt(Integer::parseInt).boxed().toList();
        List<int[][]> boards = new ArrayList<>();

        for (int i = 0; i < numbers.size() / 25; i++) {
            int[][] board = new int[5][5];

            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    board[x][y] = numbers.get(i * 25 + x * 5 + y);
                }
            }

            boards.add(board);
        }
        return boards;
    }
}
