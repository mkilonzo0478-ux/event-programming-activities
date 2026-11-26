import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Activity 3: Demonstrates BoxLayout (vertical stacking with struts).
 */
public class BoxLayoutDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BoxLayout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 360);
            frame.setLocationRelativeTo(null);

            JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            container.setBorder(new EmptyBorder(20, 20, 20, 20));

            JLabel header = new JLabel("Vertical BoxLayout Stack");
            header.setFont(new Font("Arial", Font.BOLD, 18));
            header.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(header);
            container.add(Box.createVerticalStrut(15));

            String[] items = {"First Item", "Second Item", "Third Item", "Fourth Item"};
            for (String item : items) {
                JButton btn = new JButton(item);
                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                btn.setMaximumSize(new Dimension(220, 36));
                container.add(btn);
                container.add(Box.createVerticalStrut(10));
            }

            frame.add(container);
            frame.setVisible(true);
            System.out.println("BoxLayoutDemo created on: " + Thread.currentThread().getName());
        });
    }
}
