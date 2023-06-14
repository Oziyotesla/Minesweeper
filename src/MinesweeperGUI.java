import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MinesweeperGUI extends JFrame {

    private JButton[][] buttons;
    private int[][] board;
    private int rows;
    private int cols;

    private ImageIcon flagIcon;

    private ImageIcon[] numberIcons;

    private ImageIcon bombIcon;


    public MinesweeperGUI(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;


        // Load the flag image
        try {
            flagIcon = new ImageIcon(getClass().getResource("flag.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bombIcon = new ImageIcon(getClass().getResource("bomb.png"));
        } catch (Exception ee) {
            ee.printStackTrace();
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
                    //button.setIcon(null);
                    Main.clickBomb(row, col, true);
                } else {
                    //button.setIcon(flagIcon);
                    Main.clickBomb(row, col, true);
                }
            } else {
                if (Main.clickBomb(row, col, false) == true) {
                    // Game over - a mine was clicked
                    //button.setText("X");
                    button.setIcon(bombIcon);
                    JOptionPane.showMessageDialog(null, "Game Over!");

                    //revealMines(int i, int j);
                    disableButtons();
                } else {
                    // Show the number of adjacent mines
                    // toDO

                    int count = Main.getBombNeibourXY(row, col);
                    if (count == 0) {
                        //button.setText("");
                        //revealEmptyCells(row, col);
                    } else {
                        //button.setText(String.valueOf(count));
                        buttons[row][col].setIcon(numberIcons[count]);
                    }
                    button.setEnabled(false);
                }
            }
        }
    }

    private void revealMines(int i, int j) {

                    buttons[i][j].setIcon(bombIcon);
                    //buttons[i][j].setText("X");
                    buttons[i][j].setEnabled(false);
                }


    public void revealEmptyCells(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || buttons[row][col].isEnabled() == false)
            return;

        buttons[row][col].setEnabled(false);
        int count = Main.getBombNeibourXY(row, col);

        if (count != 0) {
            buttons[row][col].setIcon(numberIcons[count]); // Set the appropriate image icon
            return;
        }

        //buttons[row][col].setText("");
        buttons[row][col].setIcon(null); // Remove any existing image icon
        /*
        revealEmptyCells(row - 1, col - 1);
        revealEmptyCells(row - 1, col);
        revealEmptyCells(row - 1, col + 1);
        revealEmptyCells(row, col - 1);
        revealEmptyCells(row, col + 1);
        revealEmptyCells(row + 1, col - 1);
        revealEmptyCells(row + 1, col);
        revealEmptyCells(row + 1, col + 1);
         */
    }


    private void disableButtons() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
     public void victory() {
         JOptionPane.showMessageDialog(null, "Congratulations! You Win!");
         disableButtons();
     }
    public void defeat() {
        JOptionPane.showMessageDialog(null, "Game Over!");
        disableButtons();
    }

    public void changeFlagStatus(int x, int y, boolean flag) {
        buttons[x][y].setIcon(flag ? flagIcon : null);
    }
}




