package Day7;

import java.util.*;
import java.util.stream.Collectors;

public class CamelCardHand {

    private String cardCombination;
    private HandTypeEnum handType;
    private String handRank = "";

    private int bidAmount = 0;

    public CamelCardHand(String input) {

        this.cardCombination = input.substring(0, 5);
        this.bidAmount = Integer.valueOf(input.substring(6));

        var cardsInHandList = new ArrayList<Character>();
        char[] cardsInHand = cardCombination.toCharArray();

        for (char c: cardsInHand)
            cardsInHandList.add(c);

        Map<Character, Integer> charCountMap = cardsInHandList.stream()
                .collect(Collectors.groupingBy(
                        character -> character,
                        Collectors.summingInt(character -> 1)
                ));

        List<Character> distinctChars = charCountMap.keySet().stream().toList();

        if (charCountMap.size() == 1)
            handType = HandTypeEnum.FIVE_OF_A_KIND;
        else if (charCountMap.size() == 5)
            handType = HandTypeEnum.HIGH_CARD;
        else if (charCountMap.size() == 2) {
            if (charCountMap.get(distinctChars.get(0)) == 4 || charCountMap.get(distinctChars.get(1)) == 4)
                handType = HandTypeEnum.FOUR_OF_A_KIND;
            else handType = HandTypeEnum.FULL_HOUSE;
        } else if (charCountMap.size() == 3){ // can be 3 of a kind or two pair
            if (charCountMap.get(distinctChars.get(0)) == 3 || charCountMap.get(distinctChars.get(1)) == 3 || charCountMap.get(distinctChars.get(2)) == 3)
                handType = HandTypeEnum.THREE_OF_A_KIND;
            else handType = HandTypeEnum.TWO_PAIR;
        } else handType = HandTypeEnum.ONE_PAIR;

        var cardRank = new CamelCardHandScore().getCardToScore();
        for (Character c: cardCombination.toCharArray())
            handRank = handRank + cardRank.get(c);

    }

    public String getCardCombination() {
        return cardCombination;
    }

    public HandTypeEnum getHandType() {
        return handType;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public String getHandRank() {
        return handRank;
    }

    @Override
    public String toString() {
        return "CamelCardHand{" +
                "cardCombination='" + cardCombination + '\'' +
                ", handType=" + handType +
                ", handRank='" + handRank + '\'' +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
