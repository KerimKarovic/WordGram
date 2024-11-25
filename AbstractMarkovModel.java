import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    abstract public String getRandomText(int numChars);

    abstract public ArrayList<String> getFollows(String key);
}
