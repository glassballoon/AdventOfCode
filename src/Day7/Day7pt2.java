package Day7;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day7pt2 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day7TestInput.txt");
        Path filePath = Paths.get("src/Input/Day7Input.txt");

        try {
            var lines = Files.readAllLines(filePath);
            var handList = new ArrayList<CamelCardHandpt2>();

            for (String line: lines)
                handList.add(new CamelCardHandpt2(line.trim()));

            ArrayList<CamelCardHandpt2> sortedHands = new ArrayList<CamelCardHandpt2>();

            for (HandTypeEnum type: HandTypeEnum.values()){
                ArrayList<CamelCardHandpt2> groupedCardsByHandType = new ArrayList<CamelCardHandpt2>();
                for (var hand: handList) {
                    if (hand.getHandType() == type)
                        groupedCardsByHandType.add(hand);
                }

                groupedCardsByHandType.sort(Comparator.comparing(CamelCardHandpt2::getHandRank));
                sortedHands.addAll(groupedCardsByHandType);
            }

            Collections.reverse(sortedHands);

            int sum = 0;

            for (int i = 0; i < sortedHands.size(); i++)
                sum += sortedHands.get(i).getBidAmount() * (i+1);

            System.out.println(sum);

        } catch (Exception e){
            System.out.println(e);
        }
    }

}