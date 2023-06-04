import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static sun.security.x509.OIDMap.getClass;

public class MinesweeperGUI extends JFrame {

    private JButton[][] buttons;
    private int[][] board;
    private int rows;
    private int cols;
    private int numMines;

    private ImageIcon flagIcon;

    private ImageIcon[] numberIcons;





    public MinesweeperGUI(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;

        // Load the flag image
        try {
            flagIcon = new ImageIcon(getClass().getResource("flag.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load the number images
        try {
            numberIcons = new ImageIcon[9]; // Assuming you have images for numbers 1 to 8
            for (int i = 1; i <= 8; i++) {
                String imagePath = "images/" + i + ".png";
                numberIcons[i] = new ImageIcon(getClass().getResource(imagePath));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initializeBoard();
        initializeGUI();
    }

    private void initializeBoard() {
        board = new int[rows][cols];

        // Place mines randomly
        for (int i = 0; i < numMines; i++) {
            int randRow = (int) (Math.random() * rows);
            int randCol = (int) (Math.random() * cols);
            board[randRow][randCol] = -1; // -1 represents a mine
        }

        // Calculate adjacent mine counts
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != -1) {
                    int count = 0;
                    if (i > 0 && j > 0 && board[i - 1][j - 1] == -1) count++; // top-left
                    if (i > 0 && board[i - 1][j] == -1) count++; // top
                    if (i > 0 && j < cols - 1 && board[i - 1][j + 1] == -1) count++; // top-right
                    if (j > 0 && board[i][j - 1] == -1) count++; // left
                    if (j < cols - 1 && board[i][j + 1] == -1) count++; // right
                    if (i < rows - 1 && j > 0 && board[i + 1][j - 1] == -1) count++; // bottom-left
                    if (i < rows - 1 && board[i + 1][j] == -1) count++; // bottom
                    if (i < rows - 1 && j < cols - 1 && board[i + 1][j + 1] == -1) count++; // bottom-right
                    board[i][j] = count;
                }
            }
        }
    }

    private void initializeGUI() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols, 2, 2));
        panel.setBackground(Color.GRAY);

        buttons = new JButton[rows][cols];
        ButtonListener listener = new ButtonListener();

        // Create buttons for each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(30, 30));
                button.setFont(new Font("Arial", Font.BOLD, 14));
                button.setFocusPainted(false);
                button.addMouseListener(listener);
                panel.add(button);
                buttons[i][j] = button;
            }
        }

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            // Find the button's position
            int row = -1;
            int col = -1;
            outerloop:
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (button == buttons[i][j]) {
                        row = i;
                        col = j;
                        break outerloop;
                    }
                }
            }
            if (SwingUtilities.isRightMouseButton(e)) {
                // Right-click: Place flag
                if (button.getIcon() == flagIcon) {
                    button.setIcon(null);
                } else {
                    button.setIcon(flagIcon);
                }
            } else {
                if (board[row][col] == -1) {
                    // Game over - a mine was clicked
                    button.setText("X");
                    JOptionPane.showMessageDialog(null, "Game Over!");
                    revealMines();
                    disableButtons();
                } else {
                    // Show the number of adjacent mines
                    int count = board[row][col];
                    if (count == 0) {
                        button.setText("");
                        revealEmptyCells(row, col);
                    } else {
                        button.setText(String.valueOf(count));
                    }
                    button.setEnabled(false);

                    // Check for win condition
                    if (checkWin()) {
                        JOptionPane.showMessageDialog(null, "Congratulations! You Win!");
                        disableButtons();
                    }
                }
            }
        }
    }

    private void revealMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1) {
                    buttons[i][j].setText("X");
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }

    private void revealEmptyCells(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || buttons[row][col].isEnabled() == false)
            return;

        buttons[row][col].setEnabled(false);

        int count = board[row][col];
        if (count != 0) {
            buttons[row][col].setIcon(numberIcons[count]); // Set the appropriate image icon
            return;
        }

        //buttons[row][col].setText("");
        buttons[row][col].setIcon(null); // Remove any existing image icon

        revealEmptyCells(row - 1, col - 1);
        revealEmptyCells(row - 1, col);
        revealEmptyCells(row - 1, col + 1);
        revealEmptyCells(row, col - 1);
        revealEmptyCells(row, col + 1);
        revealEmptyCells(row + 1, col - 1);
        revealEmptyCells(row + 1, col);
        revealEmptyCells(row + 1, col + 1);
    }






    private void disableButtons() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private boolean checkWin() {
        int totalButtons = rows * cols;
        int revealedButtons = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buttons[i][j].isEnabled())
                    revealedButtons++;
            }
        }

        return revealedButtons == totalButtons - numMines;
    }

    public static void main(String[] args) {
        int rows = 10;
        int cols = 10;
        int numMines = 10;

        SwingUtilities.invokeLater(() -> new MinesweeperGUI(rows, cols, numMines));
    }
}
