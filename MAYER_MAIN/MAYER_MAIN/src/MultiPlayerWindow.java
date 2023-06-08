import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiPlayerWindow extends JFrame {

    private boolean isConnected;
    private boolean isServerActive;

    public MultiPlayerWindow() {
        setTitle("Minesweeper - Multiplayer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create the multiplayer panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the create server button
        JButton createServerButton = new JButton("Create Server");
        createServerButton.setPreferredSize(new Dimension(150, 50));
        createServerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        createServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Server button clicked");
                // Create server logic here
                setServerActive(true);
            }
        });

        // Create the connected indicator (green LED)
        JLabel connectedIndicator = new JLabel();
        connectedIndicator.setPreferredSize(new Dimension(20, 20));
        updateConnectedIndicator();

        // Create the server active indicator (LED)
        JLabel serverActiveIndicator = new JLabel();
        serverActiveIndicator.setPreferredSize(new Dimension(20, 20));
        updateServerActiveIndicator();

        // Create the start game button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.setPreferredSize(new Dimension(150, 50));
        startGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Game button clicked");
                // Start game logic here
            }
        });

        // Add the components to the panel
        panel.add(createServerButton);
        panel.add(new JLabel("Connected:"));
        panel.add(connectedIndicator);
        panel.add(new JLabel("Server Active:"));
        panel.add(serverActiveIndicator);
        panel.add(startGameButton);

        // Add the panel to the frame
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateConnectedIndicator() {
        if (isConnected) {
            // Set the connected indicator to green
            // Example code to set a green LED icon
            ImageIcon icon = new ImageIcon(getClass().getResource("green_led.png"));
            JLabel connectedIndicator = (JLabel) getContentPane().getComponent(2);
            connectedIndicator.setIcon(icon);
        } else {
            // Set the connected indicator to red
            // Example code to set a red LED icon
            ImageIcon icon = new ImageIcon(getClass().getResource("red_led.png"));
            JLabel connectedIndicator = (JLabel) getContentPane().getComponent(2);
            connectedIndicator.setIcon(icon);
        }
    }

    private void updateServerActiveIndicator() {
        if (isServerActive) {
            // Set the server active indicator to green
            // Example code to set a green LED icon
            ImageIcon icon = new ImageIcon(getClass().getResource("green_led.png"));
            JLabel serverActiveIndicator = (JLabel) getContentPane().getComponent(4);
            serverActiveIndicator.setIcon(icon);
        } else {
            // Set the server active indicator to red
            // Example code to set a red LED icon
            ImageIcon icon = new ImageIcon(getClass().getResource("red_led.png"));
            JLabel serverActiveIndicator = (JLabel) getContentPane().getComponent(4);
            serverActiveIndicator.setIcon(icon);
        }
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
        updateConnectedIndicator();
    }

    public void setServerActive(boolean active) {
        isServerActive = active;
        updateServerActiveIndicator();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MultiPlayerWindow());
    }
}
