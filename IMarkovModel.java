import java.util.ArrayList;

public interface IMarkovModel {
    void setTraining(String text);

    void setRandom(int seed);

    String getRandomText(int numChars);

    ArrayList<String> getFollows(String key);
}
