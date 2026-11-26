import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Activity 4 - Bug 3 Fix: Marshal UI updates from background threads onto the EDT.
 *
 * @author Michael Kilonzo
 */
public class Bug3_WrongThread extends JFrame {

    private JLabel timeLabel;
    private JButton startButton;
    private volatile boolean running = false;

    public Bug3_WrongThread() {
        setTitle("Bug 3 - Wrong Thread (Fixed)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("Time: ");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        startButton = new JButton("Start Clock");

        startButton.addActionListener(e -> {
            if (running) {
                return;
            }
            running = true;
            startButton.setEnabled(false);

            new Thread(() -> {
                while (running) {
                    String currentTime = "Time: " + new Date();
                    // Schedule GUI update on the EDT for thread safety
                    SwingUtilities.invokeLater(() -> timeLabel.setText(currentTime));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        running = false;
                    }
                }
            }).start();
        });

        add(timeLabel);
        add(startButton);
        setSize(350, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug3_WrongThread().setVisible(true);
        });
    }
}
