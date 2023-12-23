package Day15;

public class Day15 {

    String input;

    public Day15(String input) {
        this.input = input;
    }


    @Override
    public int hashCode() {
        int hashCode = 0;
        for (var c: input.toCharArray()){
            hashCode += (int)c;
            hashCode *= 17;
            hashCode %= 256;
        }
        return hashCode;
    }
}
