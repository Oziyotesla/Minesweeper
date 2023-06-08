import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HighScoreTracker {
    private String[] playerNames;
    private int[] playerScores;
    private int size;

    public HighScoreTracker() {
        playerNames = new String[10];
        playerScores = new int[10];
        size = 0;
    }

    public void addScore(String playerName, int score) {
        if (size < 10) {
            playerNames[size] = playerName;
            playerScores[size] = score;
            size++;
        } else {
            // Check if the new score is higher than the lowest score
            int lowestScoreIndex = getLowestScoreIndex();
            if (score > playerScores[lowestScoreIndex]) {
                playerNames[lowestScoreIndex] = playerName;
                playerScores[lowestScoreIndex] = score;
            }
        }
    }

    private int getLowestScoreIndex() {
        int lowestScoreIndex = 0;
        int lowestScore = playerScores[0];

        for (int i = 1; i < size; i++) {
            if (playerScores[i] < lowestScore) {
                lowestScore = playerScores[i];
                lowestScoreIndex = i;
            }
        }

        return lowestScoreIndex;
    }

    public void displayHighScores() {
        System.out.println("High Scores:");
        for (int i = 0; i < size; i++) {
            System.out.println(playerNames[i] + ": " + playerScores[i]);
        }
    }

    public void saveScoresToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < size; i++) {
                writer.println(playerNames[i] + "," + playerScores[i]);
            }
            System.out.println("Scores saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving scores to file.");
            e.printStackTrace();
        }
    }

    public void loadScoresFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    addScore(playerName, score);
                }
            }
            System.out.println("Scores loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while loading scores from file.");
            e.printStackTrace();
        }
    }
}
