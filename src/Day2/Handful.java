package Day2;

public class Handful {

    private int numBlue = 0;
    private int numGreen = 0;
    private int numRed = 0;

    public Handful() {
    }

    public void setNumBlue(int numBlue) {
        this.numBlue = numBlue;
    }

    public void setNumGreen(int numGreen) {
        this.numGreen = numGreen;
    }

    public void setNumRed(int numRed) {
        this.numRed = numRed;
    }

    public int getNumBlue() {
        return numBlue;
    }

    public int getNumGreen() {
        return numGreen;
    }

    public int getNumRed() {
        return numRed;
    }

    @Override
    public String toString() {
        return "Handful{" +
                "numBlue=" + numBlue +
                ", numGreen=" + numGreen +
                ", numRed=" + numRed +
                '}';
    }
}
