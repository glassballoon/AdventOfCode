package Day7;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day7pt1 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day7TestInput.txt");
        Path filePath = Paths.get("src/Input/Day7Input.txt");

        try {
            var lines = Files.readAllLines(filePath);
            var handList = new ArrayList<CamelCardHand>();

            for (String line: lines)
                handList.add(new CamelCardHand(line.trim()));

            ArrayList<CamelCardHand> sortedHands = new ArrayList<CamelCardHand>();

            for (HandTypeEnum type: HandTypeEnum.values()){
                ArrayList<CamelCardHand> groupedCardsByHandType = new ArrayList<CamelCardHand>();
                for (var hand: handList) {
                    if (hand.getHandType() == type)
                        groupedCardsByHandType.add(hand);
                }

                groupedCardsByHandType.sort(Comparator.comparing(CamelCardHand::getHandRank));
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