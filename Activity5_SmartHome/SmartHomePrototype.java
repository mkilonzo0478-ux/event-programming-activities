import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Interactive Java Swing prototype for the Smart Home Controller (Activity 5).
 * Demonstrates multiple event sources, distinct listener types, centralized
 * state rendering, and proper EDT usage.
 *
 * @author Michael Kilonzo
 */
public class SmartHomePrototype extends JFrame {

    private static final Color NORMAL_BG = new Color(245, 247, 250);
    private static final Color ALARM_BG = new Color(255, 210, 210);

    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    private JPanel headerPanel;
    private JLabel statusLabel;
    private JLabel stateSummaryLabel;
    private JTextArea eventLog;

    private JToggleButton lightsToggle;
    private JToggleButton securityToggle;
    private JButton doorButton;
    private JButton motionButton;
    private JButton resetAlarmButton;
    private JSlider thermostatSlider;
    private JLabel thermostatLabel;

    private boolean lightsOn = false;
    private boolean securityArmed = false;
    private boolean alarmActive = false;
    private int targetTempC = 22;

    public SmartHomePrototype() {
        setTitle("Smart Home Controller - Event-Driven Prototype");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(NORMAL_BG);

        initHeaderPanel();
        initEventLogPanel();
        initControlPanel();

        updateStateDisplay("System Ready");
        logEvent("Smart Home Controller initialized");

        setSize(560, 480);
        setLocationRelativeTo(null);
    }

    private void initHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 1, 4, 4));
        headerPanel.setBackground(NORMAL_BG);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 12, 6, 12));

        statusLabel = new JLabel("System Ready");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        stateSummaryLabel = new JLabel();
        stateSummaryLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        headerPanel.add(statusLabel);
        headerPanel.add(stateSummaryLabel);
        add(headerPanel, BorderLayout.NORTH);
    }

    private void initEventLogPanel() {
        eventLog = new JTextArea(10, 40);
        eventLog.setEditable(false);
        eventLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(eventLog);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Event Log"));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initControlPanel() {
        JPanel southContainer = new JPanel(new BorderLayout(8, 8));
        southContainer.setBorder(BorderFactory.createEmptyBorder(6, 12, 12, 12));
        southContainer.setOpaque(false);

        JPanel buttonGrid = new JPanel(new GridLayout(2, 3, 8, 8));
        buttonGrid.setOpaque(false);

        // 1. Lights Toggle Handler
        lightsToggle = new JToggleButton("Lights: OFF");
        lightsToggle.addActionListener(e -> {
            lightsOn = lightsToggle.isSelected();
            lightsToggle.setText(lightsOn ? "Lights: ON" : "Lights: OFF");
            String msg = lightsOn ? "Lights turned ON" : "Lights turned OFF";
            logEvent(msg);
            updateStateDisplay(msg);
        });

        // 2. Security Toggle Handler
        securityToggle = new JToggleButton("Security: OFF");
        securityToggle.addActionListener(e -> {
            securityArmed = securityToggle.isSelected();
            securityToggle.setText(securityArmed ? "Security: ARMED" : "Security: OFF");
            if (!securityArmed && alarmActive) {
                setAlarmActive(false);
                logEvent("Security DISARMED (alarm cleared)");
                updateStateDisplay("Security disarmed");
            } else {
                String msg = securityArmed ? "Security system ARMED" : "Security system DISARMED";
                logEvent(msg);
                updateStateDisplay(msg);
            }
        });

        // 3. Door Sensor Handler
        doorButton = new JButton("Simulate Door Open");
        doorButton.addActionListener(e -> handleSensorTrigger("Door sensor triggered"));

        // 4. Motion Sensor Handler
        motionButton = new JButton("Simulate Motion");
        motionButton.addActionListener(e -> {
            if (!securityArmed && !lightsOn) {
                lightsOn = true;
                lightsToggle.setSelected(true);
                lightsToggle.setText("Lights: ON");
                logEvent("Motion detected -> Auto-turned Lights ON");
            }
            handleSensorTrigger("Motion sensor triggered");
        });

        // 5. Reset Alarm Handler
        resetAlarmButton = new JButton("Reset Alarm");
        resetAlarmButton.setEnabled(false);
        resetAlarmButton.addActionListener(e -> {
            setAlarmActive(false);
            logEvent("Alarm silenced and reset by user");
            updateStateDisplay("Alarm cleared");
        });

        // Placeholder / Quick All-Off convenience button
        JButton ecoModeButton = new JButton("Eco Mode");
        ecoModeButton.addActionListener(e -> {
            lightsOn = false;
            lightsToggle.setSelected(false);
            lightsToggle.setText("Lights: OFF");
            thermostatSlider.setValue(19);
            logEvent("Eco Mode activated: Lights OFF, Target 19 C");
            updateStateDisplay("Eco Mode active");
        });

        buttonGrid.add(lightsToggle);
        buttonGrid.add(securityToggle);
        buttonGrid.add(doorButton);
        buttonGrid.add(motionButton);
        buttonGrid.add(resetAlarmButton);
        buttonGrid.add(ecoModeButton);

        // 6. Thermostat Slider (ChangeListener)
        JPanel thermostatPanel = new JPanel(new BorderLayout(8, 0));
        thermostatPanel.setOpaque(false);
        thermostatLabel = new JLabel("Thermostat: " + targetTempC + " C");
        thermostatLabel.setFont(new Font("Arial", Font.BOLD, 13));

        thermostatSlider = new JSlider(16, 30, targetTempC);
        thermostatSlider.setMajorTickSpacing(2);
        thermostatSlider.setPaintTicks(true);
        thermostatSlider.setPaintLabels(true);
        thermostatSlider.setOpaque(false);
        thermostatSlider.addChangeListener(e -> {
            targetTempC = thermostatSlider.getValue();
            thermostatLabel.setText("Thermostat: " + targetTempC + " C");
            updateStateDisplay(statusLabel.getText());
            if (!thermostatSlider.getValueIsAdjusting()) {
                logEvent("Thermostat set to " + targetTempC + " C");
            }
        });

        thermostatPanel.add(thermostatLabel, BorderLayout.WEST);
        thermostatPanel.add(thermostatSlider, BorderLayout.CENTER);

        southContainer.add(buttonGrid, BorderLayout.CENTER);
        southContainer.add(thermostatPanel, BorderLayout.SOUTH);
        add(southContainer, BorderLayout.SOUTH);
    }

    private void handleSensorTrigger(String sensorMessage) {
        logEvent(sensorMessage);
        if (securityArmed) {
            setAlarmActive(true);
            logEvent("ALARM! Intruder detected while system armed!");
            updateStateDisplay("ALARM ACTIVE!");
        } else {
            updateStateDisplay(sensorMessage + " (security disarmed)");
        }
    }

    private void setAlarmActive(boolean active) {
        alarmActive = active;
        resetAlarmButton.setEnabled(active);
        Color bg = active ? ALARM_BG : NORMAL_BG;
        getContentPane().setBackground(bg);
        headerPanel.setBackground(bg);
        statusLabel.setForeground(active ? Color.RED.darker() : Color.BLACK);
    }

    private void updateStateDisplay(String statusText) {
        statusLabel.setText(statusText);
        stateSummaryLabel.setText(String.format(
                "Lights: %s  |  Security: %s  |  Thermostat: %d C  |  Alarm: %s",
                lightsOn ? "ON" : "OFF",
                securityArmed ? "ARMED" : "OFF",
                targetTempC,
                alarmActive ? "ACTIVE" : "Normal"
        ));
    }

    private void logEvent(String event) {
        String timestamp = timeFormat.format(new Date());
        eventLog.append("[" + timestamp + "] " + event + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SmartHomePrototype().setVisible(true);
        });
    }
}
