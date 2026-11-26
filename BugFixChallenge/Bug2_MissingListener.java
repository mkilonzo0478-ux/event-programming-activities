import javax.swing.*;
import java.awt.*;

/**
 * Activity 4 - Bug 2 Fix: Register an ActionListener on the increment button.
 *
 * @author Michael Kilonzo
 */
public class Bug2_MissingListener extends JFrame {

    private JLabel countLabel;
    private JButton incrementButton;
    private int count = 0;

    public Bug2_MissingListener() {
        setTitle("Bug 2 - Missing Listener (Fixed)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        countLabel = new JLabel("Count: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 20));

        incrementButton = new JButton("Increment");

        // Register an ActionListener to handle button clicks
        incrementButton.addActionListener(e -> {
            count++;
            countLabel.setText("Count: " + count);
        });

        add(countLabel);
        add(incrementButton);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug2_MissingListener().setVisible(true);
        });
    }
}
