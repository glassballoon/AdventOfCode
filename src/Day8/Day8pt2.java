package Day8;

import javax.swing.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day8pt2 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day8pt2TestInput.txt");
        Path filePath = Paths.get("src/Input/Day8Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            char[] instructions = lines.get(0).toCharArray();

            var values = new HashMap<String, List<String>>();

            for (int i = 2; i < lines.size(); i++) {
                String line = lines.get(i);
                var value = line.substring(0, 3);
                var nextValues = List.of(line.substring(7, 10), line.substring(12, 15));
                values.put(value, nextValues);
            }

            List<String> startingElements = values.keySet().stream()
                    .filter(str -> str.endsWith("A")).toList();


            int counter = 0;

            var elementCount = new ArrayList<Integer>();

            for (String e : startingElements) {
                counter = 0;
                while (!e.endsWith("Z")) {
                    for (char instruction : instructions) {
                        counter++;
                        e = getNextElementValue(values, e, instruction);
//                        System.out.println(e);
                        if (e.endsWith("Z")) {
                            elementCount.add(counter);
                            break;
                        }
                    }
                }
            }

            long currentLcm = elementCount.get(0);

            for (int i = 1; i< elementCount.size(); i++) {
                currentLcm = calcLcm(currentLcm, elementCount.get(i));
            }

            System.out.println(currentLcm);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    static String getNextElementValue(HashMap<String, List<String>> values, String element, char instruction) {

        if (instruction == 'R')
            return values.get(element).get(1);
        else
            return values.get(element).get(0);
    }

    // function to calculate LCM of two large numbers
//    public static BigInteger calcLcm(String a, String b)
//    {
//        // convert string 'a' and 'b' into BigInteger
//        BigInteger s = new BigInteger(a);
//        BigInteger s1 = new BigInteger(b);
//
//        // calculate multiplication of two bigintegers
//        BigInteger mul = s.multiply(s1);
//
//        // calculate gcd of two bigintegers
//        BigInteger gcd = s.gcd(s1);
//
//        // calculate lcm using formula: lcm * gcd = x * y
//        BigInteger lcm = mul.divide(gcd);
//        return lcm;
//    }

    public static long calcLcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    // not 2300977934640


}
