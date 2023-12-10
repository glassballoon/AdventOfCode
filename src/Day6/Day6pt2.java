package Day6;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day6pt2 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day6TestInput.txt");
        Path filePath = Paths.get("src/Input/Day6pt2Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            var timesLine = lines.get(0).trim().substring(10).split("\\s+");
            var distanceLine = lines.get(1).trim().substring(10).split("\\s+");

            var winningWays = new ArrayList<Integer>();
            for (int i = 1; i< timesLine.length; i++){

                long time = Long.parseLong(timesLine[i]);
                long distance = Long.parseLong(distanceLine[i]);

                int numWaysToWin = 0;

                for (int j = 0; j < time; j++){ // j is the time held
                    long distanceTravelled = j * (time-j);
                    if (distanceTravelled > distance) {
                        numWaysToWin++;
                    }
                }
                winningWays.add(numWaysToWin);
            }
            int counter = 1;
            for (int num: winningWays)
                counter = counter * num;

            System.out.println(counter);

        } catch (Exception e){
            System.out.println(e);
        }
    }
}