import javax.swing.*;

/**
 * Activity 4 - Bug 1 Fix: Offload blocking work from the EDT.
 *
 * @author Michael Kilonzo
 */
public class Bug1_BlockingEDT extends JFrame {

    private JLabel statusLabel;
    private JButton processButton;

    public Bug1_BlockingEDT() {
        setTitle("Bug 1 - Blocking EDT (Fixed)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        statusLabel = new JLabel("Ready");
        processButton = new JButton("Process");

        processButton.addActionListener(e -> {
            // Update UI immediately on the EDT before starting background work
            processButton.setEnabled(false);
            statusLabel.setText("Processing...");

            // Run the long-running task on a background thread so the EDT stays responsive
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText("Done!");
                        processButton.setEnabled(true);
                    });
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText("Interrupted");
                        processButton.setEnabled(true);
                    });
                }
            }).start();
        });

        add(statusLabel);
        add(processButton);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug1_BlockingEDT().setVisible(true);
        });
    }
}
