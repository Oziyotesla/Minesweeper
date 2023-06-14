import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiPlayerWindow extends JFrame {

    private boolean isConnected;
    private JLabel connectedIndicator;

    public MultiPlayerWindow() {
        setTitle("Minesweeper - Multiplayer");
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton createServerButton = new JButton("Create Server");
        createServerButton.setPreferredSize(new Dimension(150, 50));
        createServerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        createServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Server button clicked");
                Main.CreateServer();
            }
        });

        connectedIndicator = new JLabel();
        connectedIndicator.setPreferredSize(new Dimension(10, 10));
        updateConnectedIndicator();

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setPreferredSize(new Dimension(150, 50));
        startGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Game button clicked");
                Main.clickGame(true);
                Main.createTimer();
                dispose();
            }
        });

        panel.add(createServerButton);
        panel.add(new JLabel("Connected:"));
        panel.add(connectedIndicator);
        panel.add(startGameButton);

        setContentPane(panel);
        pack();
        int newWidth = 400;
        setSize(newWidth, getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MultiPlayerWindow());
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
        updateConnectedIndicator();
    }

    private void updateConnectedIndicator() {
        if (isConnected) {
            connectedIndicator.setBackground(Color.GREEN);
        } else {
            connectedIndicator.setBackground(Color.RED);
        }
        connectedIndicator.setOpaque(true);
    }
}
