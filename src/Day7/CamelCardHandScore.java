package Day7;

import java.util.HashMap;

public class CamelCardHandScore {

    private HashMap<Character, String> cardToScore = new HashMap<Character, String>();

    public CamelCardHandScore() {
        cardToScore.put('A', "A");
        cardToScore.put('K', "B");
        cardToScore.put('Q', "C");
        cardToScore.put('J', "D");
        cardToScore.put('T', "E");
        cardToScore.put('9', "F");
        cardToScore.put('8', "G");
        cardToScore.put('7', "H");
        cardToScore.put('6', "I");
        cardToScore.put('5', "J");
        cardToScore.put('4', "K");
        cardToScore.put('3', "L");
        cardToScore.put('2', "M");
    }

    public HashMap<Character, String> getCardToScore() {
        return cardToScore;
    }
}
