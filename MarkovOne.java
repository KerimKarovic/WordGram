import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setTraining(String text) {
        myText = text.trim();
    }

    public void setRandom(int seed) {
        myRandom.setSeed(seed);
    }

    public String getRandomText(int numChars) {
        if (myText == null || myText.length() < 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length());
        String key = myText.substring(index, index + 1);
        sb.append(key);

        for (int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }

        return sb.toString();
    }

    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while (pos < myText.length()) {
            int index = myText.indexOf(key, pos);
            if (index == -1 || index + key.length() >= myText.length()) {
                break;
            }
            String next = myText.substring(index + key.length(), index + key.length() + 1);
            follows.add(next);
            pos = index + key.length();
        }
        return follows;
    }
}
