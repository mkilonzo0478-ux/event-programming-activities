import javax.swing.*;
import java.awt.*;

/**
 * Activity 3.2: Demonstrates BorderLayout's five regions.
 *
 * @author Michael Kilonzo
 */
public class BorderLayoutDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BorderLayout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);

            // BorderLayout has 5 regions: NORTH, SOUTH, EAST, WEST, CENTER
            frame.setLayout(new BorderLayout(5, 5));

            frame.add(new JButton("NORTH"), BorderLayout.NORTH);
            frame.add(new JButton("SOUTH"), BorderLayout.SOUTH);
            frame.add(new JButton("EAST"), BorderLayout.EAST);
            frame.add(new JButton("WEST"), BorderLayout.WEST);
            frame.add(new JButton("CENTER"), BorderLayout.CENTER);

            frame.setVisible(true);
            System.out.println("BorderLayoutDemo created on: " + Thread.currentThread().getName());
        });
    }
}
