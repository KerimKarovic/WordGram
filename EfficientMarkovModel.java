import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel implements IMarkovModel {
    private int myOrder;
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int order) {
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<>();
    }

    @Override
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
    }

    private void buildMap() {
        map.clear();
        for (int i = 0; i <= myText.length() - myOrder; i++) {
            String key = myText.substring(i, i + myOrder);
            String next = "";
            if (i + myOrder < myText.length()) {
                next = myText.substring(i + myOrder, i + myOrder + 1);
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(next);
        }
    }

    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    @Override
    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - myOrder);
        String key = myText.substring(index, index + myOrder);
        sb.append(key);
        for (int k = 0; k < numChars - myOrder; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }

    @Override
    public ArrayList<String> getFollows(String key) {
        return map.getOrDefault(key, new ArrayList<>());
    }

    public void printHashMapInfo() {
        System.out.println("Number of keys in the map: " + map.size());
        int largestSize = 0;
        for (String key : map.keySet()) {
            if (map.get(key).size() > largestSize) {
                largestSize = map.get(key).size();
            }
        }
        System.out.println("Size of the largest value in the map: " + largestSize);
    }
}
