import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Activity 5: Complete Static GUI Mockup — University Student Portal Dashboard.
 * Customized with university branding, student profile, stat cards, and menu bar.
 *
 * @author Michael Kilonzo
 */
public class StudentPortal {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentPortal::createAndShow);
    }

    private static void createAndShow() {
        // ============================================================
        // 1. THE MAIN WINDOW
        // ============================================================
        JFrame frame = new JFrame("Rift Valley University — Student Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Optional Menu Bar (Step 5.3 customization)
        frame.setJMenuBar(createMenuBar());

        // ============================================================
        // 2. HEADER (NORTH) — BorderLayout inside
        // ============================================================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(25, 55, 115)); // dark blue
        header.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel title = new JLabel("Rift Valley University — Student Portal");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(200, 60, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);

        header.add(title, BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);
        frame.add(header, BorderLayout.NORTH);

        // ============================================================
        // 3. SIDEBAR (WEST) — GridLayout with 6 rows
        // ============================================================
        JPanel sidebar = new JPanel(new GridLayout(6, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(180, 0));
        sidebar.setBackground(new Color(240, 240, 245));
        sidebar.setBorder(new EmptyBorder(15, 10, 15, 10));

        String[] menuItems = {
            "Dashboard", "Courses", "Grades",
            "Fees", "Timetable", "Messages"
        };

        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            sidebar.add(btn);
        }

        frame.add(sidebar, BorderLayout.WEST);

        // ============================================================
        // 4. MAIN CONTENT AREA (CENTER)
        // ============================================================
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        content.setBackground(Color.WHITE);

        // Welcome message
        JLabel welcome = new JLabel("Welcome, Michael Kilonzo!");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(welcome);
        content.add(Box.createVerticalStrut(20));

        // ---- Stats Cards ----
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        cardsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        cardsPanel.add(createStatCard("GPA", "3.82", new Color(46, 150, 80)));
        cardsPanel.add(createStatCard("Units", "52 / 56", new Color(65, 125, 210)));
        cardsPanel.add(createStatCard("Fees", "Paid", new Color(225, 140, 50)));

        content.add(cardsPanel);
        content.add(Box.createVerticalStrut(30));

        // ---- Notifications Section ----
        JLabel notifTitle = new JLabel("* Notifications");
        notifTitle.setFont(new Font("Arial", Font.BOLD, 16));
        notifTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(notifTitle);
        content.add(Box.createVerticalStrut(10));

        String[] notifications = {
            "- Exam results for Semester 2 have been released",
            "- Fee payment deadline: 30th October 2026",
            "- Your timetable has been updated for CS304 Event-Driven Programming",
            "- Library book due for return on 12th October"
        };

        for (String note : notifications) {
            JLabel noteLabel = new JLabel(note);
            noteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            content.add(noteLabel);
            content.add(Box.createVerticalStrut(5));
        }

        frame.add(content, BorderLayout.CENTER);

        // ============================================================
        // 5. STATUS BAR (SOUTH)
        // ============================================================
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(new Color(230, 230, 235));
        statusBar.setBorder(new EmptyBorder(5, 10, 5, 10));

        statusBar.add(new JLabel("Status: Logged in as student (Michael Kilonzo)"),
                BorderLayout.WEST);
        statusBar.add(new JLabel("Last login: 26 Nov 2026"),
                BorderLayout.EAST);

        frame.add(statusBar, BorderLayout.SOUTH);

        // ============================================================
        // 6. SHOW IT!
        // ============================================================
        frame.setVisible(true);

        System.out.println("Mockup running on: "
                + Thread.currentThread().getName());
    }

    // Helper method to create a top menu bar
    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Portal");
        fileMenu.add(new JMenuItem("Profile"));
        fileMenu.add(new JMenuItem("Settings"));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Exit"));

        JMenu academicsMenu = new JMenu("Academics");
        academicsMenu.add(new JMenuItem("Register Courses"));
        academicsMenu.add(new JMenuItem("Download Transcript"));

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new JMenuItem("Student Support"));
        helpMenu.add(new JMenuItem("About Portal"));

        menuBar.add(fileMenu);
        menuBar.add(academicsMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }

    // Helper method to create a stat card
    private static JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel valueLabel = new JLabel(value, SwingConstants.RIGHT);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 22));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }
}
