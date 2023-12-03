package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// https://adventofcode.com/2023/day/2
public class Day2pt2 {

    final static String BLUE = " blue";
    final static String GREEN = " green";
    final static String RED = " red";

    public static void main(String[] args) {
        var filePath = Paths.get("C:/Users/rowen/java/AdventOfCode2023/src/Day2/Day2Input.txt");
        try {
            var lines = Files.readAllLines(filePath);
            var games = new ArrayList<ArrayList<Handful>>();

            for (var line: lines) games.add(createHandfulsForGame(line));

            int sum = 0;

            for (int i = 0; i < games.size(); i++){
                var game = games.get(i);
                var maxBlue = game.stream()
                        .mapToInt(Handful::getNumBlue)
                        .max().orElse(0);
                var maxGreen = game.stream()
                        .mapToInt(Handful::getNumGreen)
                        .max().orElse(0);
                var maxRed = game.stream()
                        .mapToInt(Handful::getNumRed)
                        .max().orElse(0);

                sum += (maxBlue*maxGreen*maxRed);
            }
            System.out.println(sum);
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }

    private static ArrayList<Handful> createHandfulsForGame(String line){
        var game = new ArrayList<Handful>();
        var gameHandfuls = line.split(": ")[1];
        var handfuls = gameHandfuls.split("; ");

        for (var handfulString: handfuls){
            var handful = new Handful();
            var coloursArray = handfulString.split(", ");
            for (var colour: coloursArray) {
                if (colour.indexOf(BLUE) > -1)
                    handful.setNumBlue(Integer.parseInt(colour.substring(0, colour.indexOf(BLUE))));
                if (colour.indexOf(GREEN) > -1)
                    handful.setNumGreen(Integer.parseInt(colour.substring(0, colour.indexOf(GREEN))));
                if (colour.indexOf(RED) > -1)
                    handful.setNumRed(Integer.parseInt(colour.substring(0, colour.indexOf(RED))));
            }
            game.add(handful);
        }
        return game;
    }
}
