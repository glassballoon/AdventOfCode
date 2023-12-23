package Day15;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Day15pt2 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day15TestInput.txt");
        Path filePath = Paths.get("src/Input/Day15Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            String[] inputs = lines.get(0).split(",");

            var lensBoxes = new HashMap<Integer, ArrayList<Lens>>();

            for (var input: inputs){
                Lens lens = new Lens(input);
                ArrayList<Lens> lensArray;
                if (lensBoxes.get(lens.hashCode()) != null){
                    lensArray = lensBoxes.get(lens.hashCode());
                } else lensArray = new ArrayList<Lens>();

                if (lens.operation == Lens.dash){
                    lensArray.removeIf(l -> l.getLabel().equals(lens.getLabel()));
                } else { // must be equals
                    if (lensArray.stream().anyMatch(l -> l.getLabel().equals(lens.getLabel()))) {
                        for (int i = 0; i < lensArray.size(); i++){
                            if (lensArray.get(i).getLabel().equals(lens.getLabel())) lensArray.set(i, lens);

                        }
                    } else lensArray.add(lens);

                }

                lensBoxes.put(lens.hashCode(), lensArray);

            }

            int sum = 0;

            // get all the keys
            var boxNumbers = lensBoxes.keySet().stream().toList();
            for (var bn: boxNumbers){
                var lensesInBox = lensBoxes.get(bn);
                for (int i = 0; i < lensesInBox.size(); i++){
                    sum += (1 + bn) * (i+1) * lensesInBox.get(i).getFocalLength();
                }

            }

            System.out.println(sum);

        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
