import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int rows = 20;
        int cols = 20;
        int numMines = 10;

        SwingUtilities.invokeLater(() -> new MinesweeperGUI(rows, cols, numMines));
    }
}
