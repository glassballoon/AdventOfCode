package Day15;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day15pt1 {

    public static void main(String[] args) {

        Path filePath = Paths.get("src/Input/Day15TestInput.txt");
//        Path filePath = Paths.get("src/Input/Day15Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            String[] inputs = lines.get(0).split(",");

            ArrayList<Integer> hashCodes = new ArrayList<Integer>();

            for (var input: inputs){
                Day15 day15 = new Day15(input);
                hashCodes.add(day15.hashCode());
            }

            int sum = 0;

            for (var hashCode: hashCodes)
                sum += hashCode;

            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
