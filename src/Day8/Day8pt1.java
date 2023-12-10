package Day8;

import Day5.LocationMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day8pt1 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day8TestInput.txt");
        Path filePath = Paths.get("src/Input/Day8Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            char[] instructions = lines.get(0).toCharArray();

            HashMap values = new HashMap<String, List<String>>();

            for (int i = 2; i < lines.size(); i++){
                String line = lines.get(i);
                var value = line.substring(0, 3);
                var nextValues = List.of(line.substring(7, 10), line.substring(12, 15));
                values.put(value, nextValues);
            }

            String nextElement = "AAA";
            int counter = 0;

            while (!nextElement.equals("ZZZ")) {
                for (char instruction : instructions) {
                    counter++;
                    nextElement = getNextElementValue(values, nextElement, instruction);
                    if (nextElement.equals("ZZZ"))
                        break;
                }
            }

            System.out.println(counter);

        } catch (Exception e){
            System.out.println(e);
        }

    }

    static String getNextElementValue(HashMap<String, List<String>> values, String element, char instruction){

        if (instruction == 'R')
            return values.get(element).get(1);
        else
            return values.get(element).get(0);
    }


}
