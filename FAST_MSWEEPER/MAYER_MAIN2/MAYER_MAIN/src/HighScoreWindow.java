import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HighScoreWindow extends JFrame {

    public HighScoreWindow() {
        setTitle("Minesweeper - High Scores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create the high scores table
        String[] columnNames = {"Rank", "Player", "Score"};
        Object[][] data = {
                {1, "Player 1", 100},
                {2, "Player 2", 90},
                {3, "Player 3", 80},
                {4, "Player 4", 70},
                {5, "Player 5", 60},
                {6, "Player 6", 50},
                {7, "Player 7", 40},
                {8, "Player 8", 30},
                {9, "Player 9", 20},
                {10, "Player 10", 10},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        setContentPane(scrollPane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HighScoreWindow());
    }
}
