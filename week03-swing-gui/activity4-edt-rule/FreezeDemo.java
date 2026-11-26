import javax.swing.*;
import java.awt.*;

/**
 * Activity 4.1: Demonstrates an EDT violation that freezes the UI for 5 seconds.
 *
 * @author Michael Kilonzo
 */
public class FreezeDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("EDT Demo — BAD Practice");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout());

            JLabel status = new JLabel("Ready");
            JButton button = new JButton("Freeze the UI");

            // BAD: This blocks the EDT for 5 seconds!
            button.addActionListener(e -> {
                System.out.println("Running long task on: " + Thread.currentThread().getName());
                status.setText("Working...");
                try {
                    Thread.sleep(5000); // Simulates a long operation on the EDT
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                status.setText("Done!");
            });

            frame.add(button);
            frame.add(status);
            frame.setVisible(true);
            System.out.println("FreezeDemo created on: " + Thread.currentThread().getName());
        });
    }
}
