import javax.swing.*;
import java.awt.*;

/**
 * Activity 3.3: Demonstrates GridLayout with a 4x4 calculator button grid.
 *
 * @author Michael Kilonzo
 */
public class GridLayoutDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("GridLayout — Calculator Style");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);
            frame.setLocationRelativeTo(null);

            // GridLayout: 4 rows, 4 columns, 5px gaps
            frame.setLayout(new GridLayout(4, 4, 5, 5));

            String[] labels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
            };

            for (String label : labels) {
                JButton btn = new JButton(label);
                btn.setFont(new Font("Arial", Font.BOLD, 18));
                frame.add(btn);
            }

            frame.setVisible(true);
            System.out.println("GridLayoutDemo created on: " + Thread.currentThread().getName());
        });
    }
}
