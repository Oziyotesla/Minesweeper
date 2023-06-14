import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow extends JFrame {

    private JTextField textField;

    public MainMenuWindow() {
        setTitle("Minesweeper - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create the main menu panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // Create the Difficulty player button
        JButton Difficulty = new JButton("Difficulty");
        Difficulty.setPreferredSize(new Dimension(100, 50));
        Difficulty.setFont(new Font("Arial", Font.PLAIN, 20));
        Difficulty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                //System.out.println("2 Player selected. Text: " + text);
                // Open the Minesweeper GUI when the button is clicked
                //openMinesweeperGUI();
                //new MultiPlayerWindow();
                new DifficultyWindow();

            }
        });

        // Create the 1 Player button
        JButton startButton = new JButton("1 Player");
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                System.out.println("1 Player selected. Text: " + text);
                // Open the Minesweeper GUI when the button is clicked
                //openMinesweeperGUI();
                Main.clickGame(false);
                Main.createTimer();
            }
        });

        // Create the start 2 player button
        JButton Player2 = new JButton("2 Player");
        Player2.setPreferredSize(new Dimension(100, 50));
        Player2.setFont(new Font("Arial", Font.PLAIN, 20));
        Player2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                System.out.println("2 Player selected. Text: " + text);
                // Open the Minesweeper GUI when the button is clicked
                //openMinesweeperGUI();
                Main.setMultiWindowRef(new MultiPlayerWindow()) ;
            }
        });

        // Create the Connect button
        JButton ConnectButton = new JButton("Connect");
        ConnectButton.setPreferredSize(new Dimension(100, 50));
        ConnectButton.setFont(new Font("Arial", Font.PLAIN, 20));
        ConnectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                System.out.println("Connect selected. Text: " + text);
                // Connect
                //openMinesweeperGUI();
                String ip = textField.getText();
                Main.IPConnect(ip);

            }
        });

        textField = new JTextField();

        // Add the buttons to the panel
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(Difficulty);
        panel.add(startButton);
        panel.add(Player2);
        panel.add(ConnectButton);
        panel.add(textField);
        panel.add(new JLabel()); // Empty label for spacing

        // Add the panel to the frame
        setContentPane(panel);
        pack();
        setSize(300, getHeight()); // Set a wider width for the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private void openMinesweeperGUI() {
        int rows = 10;
        int cols = 10;
        int numMines = 10;

        // Close the main menu window
        dispose();

        // Open the Minesweeper GUI
        SwingUtilities.invokeLater(() -> new MinesweeperGUI(rows, cols));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuWindow());
    }
}
