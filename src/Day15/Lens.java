package Day15;

import org.jetbrains.annotations.NotNull;

public class Lens {

    String label;
    int focalLength;
    char operation;

    public static char dash = '-';
    public static char equals = '=';

    public Lens(@NotNull String input) {
        if (input.indexOf(dash) > -1) {
            label = input.substring(0, input.indexOf(dash));
            operation = input.charAt(input.indexOf(dash));
        }
        else {
            label = input.substring(0, input.indexOf(equals));
            focalLength = Integer.valueOf(input.substring(input.indexOf(equals) + 1));
            operation = input.charAt(input.indexOf(equals));
        }
    }

    public String getLabel() {
        return label;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public char getOperation() {
        return operation;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (var c: label.toCharArray()){
            hashCode += (int)c;
            hashCode *= 17;
            hashCode %= 256;
        }
        return hashCode;
    }


}
