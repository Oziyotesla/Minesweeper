import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class DifficultyWindow extends JFrame {

    int diffnum = 1;

    public DifficultyWindow() {
        setTitle("Difficulty Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create the difficulty panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the buttons
        JButton chillButton = new JButton("Chill");
        chillButton.setPreferredSize(new Dimension(150, 50));
        chillButton.setFont(new Font("Arial", Font.PLAIN, 20));
        chillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Chill button clicked");
                diffnum = 1;
                // Chill mode logic here
            }
        });

        JButton ecoButton = new JButton("Eco");
        ecoButton.setPreferredSize(new Dimension(150, 50));
        ecoButton.setFont(new Font("Arial", Font.PLAIN, 20));
        ecoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Eco button clicked");
                diffnum = 2;
                // Eco mode logic here
            }
        });

        JButton ludicrousButton = new JButton("Ludicrous");
        ludicrousButton.setPreferredSize(new Dimension(150, 50));
        ludicrousButton.setFont(new Font("Arial", Font.PLAIN, 20));
        ludicrousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ludicrous button clicked");
                diffnum = 3;
                // Ludicrous mode logic here
            }
        });

        // Add the buttons to the panel
        panel.add(chillButton);
        panel.add(ecoButton);
        panel.add(ludicrousButton);

        // Add the panel to the frame
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DifficultyWindow());
    }
}
