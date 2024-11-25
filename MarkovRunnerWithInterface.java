import edu.duke.FileResource;

public class MarkovRunnerWithInterface {
    private void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("Running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');

        EfficientMarkovModel em = new EfficientMarkovModel(6);
        runModel(em, st, 500, 792);

        EfficientMarkovModel em2 = new EfficientMarkovModel(5);
        runModel(em2, st, 500, 531);
    }

    private void printOut(String s) {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public static void main(String[] args) {
        MarkovRunnerWithInterface runner = new MarkovRunnerWithInterface();
        runner.runMarkov();
    }
}
