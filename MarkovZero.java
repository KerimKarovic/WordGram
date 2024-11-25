import java.util.ArrayList;
import java.util.Random;

public class MarkovZero {

    private String myText;
    private Random myRandom;

    public MarkovZero() {
        myRandom = new Random();
    }

    public void setTraining(String text) {
        myText = text.trim();
    }

    public void setRandom(int seed) {
        myRandom.setSeed(seed);
    }

    public String getRandomText(int numChars) {
        if (myText == null || myText.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numChars; k++) {
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }
}
