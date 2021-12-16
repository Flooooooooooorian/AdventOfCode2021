package de.flooooooooooorian;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Resource {
    public static List<String> getInput(String name) {
        return new BufferedReader(new InputStreamReader(DayFour.class.getResourceAsStream(name)))
                .lines().toList();
    }
}
