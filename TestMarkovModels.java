import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestMarkovModels {

    private String getTextFromFile(String filePath, String searchText) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(filePath)));
        int startIndex = text.indexOf(searchText);
        if (startIndex == -1) return "";
        int endIndex = startIndex + searchText.length();
        return text.substring(startIndex, endIndex);
    }

    public void testMarkovZero() throws IOException {
        String st = getTextFromFile("data/confucius.txt", "The Project Gutenberg EBook of The Sayings Of Confucius, by Confucius");
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(1024);

        String text = markov.getRandomText(500);
        String[] lines = text.split("\\.");
        System.out.println("Answer for question 2: " + lines[0].trim());
    }

    public void testMarkovOne() throws IOException {
        String st = getTextFromFile("data/romeo.txt", "THE TRAGEDY OF ROMEO AND JULIET");
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(365);

        String text = markov.getRandomText(500);
        String[] lines = text.split("\\.");
        System.out.println("Answer for question 5: " + lines[0].trim());
    }

    public void testMarkovFour() throws IOException {
        String st = getTextFromFile("data/romeo.txt", "THE TRAGEDY OF ROMEO AND JULIET");
        MarkovFour markov = new MarkovFour();
        markov.setTraining(st);
        markov.setRandom(715);

        String text = markov.getRandomText(500);
        String[] lines = text.split("\\.");
        System.out.println("Answer for question 6: " + lines[0].trim());
    }

    public void testMarkovModel() throws IOException {
        String st = getTextFromFile("data/romeo.txt", "THE TRAGEDY OF ROMEO AND JULIET");
        MarkovModelConcrete markov = new MarkovModelConcrete(7);
        markov.setTraining(st);
        markov.setRandom(953);

        String text = markov.getRandomText(500);
        String[] lines = text.split("\\.");
        System.out.println("Answer for question 7: " + lines[0].trim());
    }

    public static void main(String[] args) {
        try {
            TestMarkovModels tester = new TestMarkovModels();
            tester.testMarkovZero();
            tester.testMarkovOne();
            tester.testMarkovFour();
            tester.testMarkovModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
