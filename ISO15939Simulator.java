import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ISO15939Simulator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;


    private JLabel profileStepLabel;
    private JLabel defineStepLabel;
    private JLabel planStepLabel;
    private JLabel collectStepLabel;
    private JLabel analyseStepLabel;

    private String username = "";
    private String school = "";
    private String sessionName = "";

    public MainFrame() {
        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(
                "ISO 15939 Measurement Process Simulator",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel indicatorPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        indicatorPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        profileStepLabel = new JLabel("1. Profile", SwingConstants.CENTER);
        defineStepLabel = new JLabel("2. Define", SwingConstants.CENTER);
        planStepLabel = new JLabel("3. Plan", SwingConstants.CENTER);
        collectStepLabel = new JLabel("4. Collect", SwingConstants.CENTER);
        analyseStepLabel = new JLabel("5. Analyse", SwingConstants.CENTER);

        profileStepLabel.setOpaque(true);
        defineStepLabel.setOpaque(true);
        planStepLabel.setOpaque(true);
        collectStepLabel.setOpaque(true);
        analyseStepLabel.setOpaque(true);

        indicatorPanel.add(profileStepLabel);
        indicatorPanel.add(defineStepLabel);
        indicatorPanel.add(planStepLabel);
        indicatorPanel.add(collectStepLabel);
        indicatorPanel.add(analyseStepLabel);

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(indicatorPanel, BorderLayout.SOUTH);


        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createProfilePanel(), "step1");
        cardPanel.add(createDefinePanel(), "step2");
        cardPanel.add(createPlanPanel(), "step3");
        cardPanel.add(createCollectPanel(), "step4");
        cardPanel.add(createAnalysePanel(), "step5");

        add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton step1Button = new JButton("Profile");
        JButton step2Button = new JButton("Define");
        JButton step3Button = new JButton("Plan");
        JButton step4Button = new JButton("Collect");
        JButton step5Button = new JButton("Analyse");

        step1Button.addActionListener(e -> showStep("step1"));
        step2Button.addActionListener(e -> showStep("step2"));
        step3Button.addActionListener(e -> showStep("step3"));
        step4Button.addActionListener(e -> showStep("step4"));
        step5Button.addActionListener(e -> showStep("step5"));

        buttonPanel.add(step1Button);
        buttonPanel.add(step2Button);
        buttonPanel.add(step3Button);
        buttonPanel.add(step4Button);
        buttonPanel.add(step5Button);

        add(buttonPanel, BorderLayout.SOUTH);
        showStep("step1");
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 1: Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel schoolLabel = new JLabel("School:");
        JTextField schoolField = new JTextField();

        JLabel sessionLabel = new JLabel("Session Name:");
        JTextField sessionField = new JTextField();

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField.setPreferredSize(new Dimension(250, 30));
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(schoolLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        schoolField.setPreferredSize(new Dimension(250, 30));
        formPanel.add(schoolField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(sessionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        sessionField.setPreferredSize(new Dimension(250, 30));
        formPanel.add(sessionField, gbc);

        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(e -> {
            if (usernameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        panel,
                        "Please enter your username to continue."
                );
                return;
            }

            if (schoolField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        panel,
                        "Please enter your school to continue."
                );
                return;
            }

            if (sessionField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        panel,
                        "Please enter your session name to continue."
                );
                return;
            }
            username = usernameField.getText().trim();
            school = schoolField.getText().trim();
            sessionName = sessionField.getText().trim();

            showStep("step2");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createDefinePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 2: Define Quality Dimensions", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JPanel qualityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        qualityPanel.setBorder(BorderFactory.createTitledBorder("2a. Quality Type Selection"));

        JRadioButton productRadio = new JRadioButton("Product Quality");
        JRadioButton processRadio = new JRadioButton("Process Quality");

        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(productRadio);
        qualityGroup.add(processRadio);

        qualityPanel.add(productRadio);
        qualityPanel.add(processRadio);

        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        modePanel.setBorder(BorderFactory.createTitledBorder("2b. Mode Selection"));

        JRadioButton healthRadio = new JRadioButton("Health");
        JRadioButton educationRadio = new JRadioButton("Education");

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(healthRadio);
        modeGroup.add(educationRadio);

        modePanel.add(healthRadio);
        modePanel.add(educationRadio);

        JPanel scenarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scenarioPanel.setBorder(BorderFactory.createTitledBorder("2c. Scenario Selection"));

        JLabel scenarioLabel = new JLabel("Scenario:");
        JComboBox<String> scenarioBox = new JComboBox<>();
        scenarioBox.setPreferredSize(new Dimension(250, 25));

        scenarioPanel.add(scenarioLabel);
        scenarioPanel.add(scenarioBox);

        educationRadio.addActionListener(e -> {
            scenarioBox.removeAllItems();
            scenarioBox.addItem("Scenario C — Team Alpha");
            scenarioBox.addItem("Scenario D — Team Beta");
        });

        healthRadio.addActionListener(e -> {
            scenarioBox.removeAllItems();
            scenarioBox.addItem("Scenario A — Clinic One");
            scenarioBox.addItem("Scenario B — Hospital Core");
        });

        JPanel buttonPanel = new JPanel();

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        backButton.addActionListener(e -> showStep("step1"));

        nextButton.addActionListener(e -> {
            if (!productRadio.isSelected() && !processRadio.isSelected()) {
                JOptionPane.showMessageDialog(panel, "Please select one quality type to continue.");
                return;
            }

            if (!healthRadio.isSelected() && !educationRadio.isSelected()) {
                JOptionPane.showMessageDialog(panel, "Please select one mode to continue.");
                return;
            }

            if (scenarioBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(panel, "Please select a scenario to continue.");
                return;
            }

            showStep("step3");
        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        mainPanel.add(qualityPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(modePanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(scenarioPanel);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(mainPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createPlanPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 3: Plan Measurement", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columnNames = {
                "Metric",
                "Coefficient",
                "Direction",
                "Range",
                "Unit"
        };

        Object[][] tableData = {
                {"SUS Score", "50", "Higher ↑", "0-100", "points"},
                {"Onboarding Time", "50", "Lower ↓", "0-60", "min"},
                {"Video Start Time", "50", "Lower ↓", "0-15", "sec"},
                {"Concurrent Exams", "50", "Higher ↑", "0-600", "users"},
                {"WCAG Compliance", "50", "Higher ↑", "0-100", "%"},
                {"Screen Reader Score", "50", "Higher ↑", "0-100", "%"},
                {"Uptime", "50", "Higher ↑", "95-100", "%"},
                {"MTTR", "50", "Lower ↓", "0-120", "min"},
                {"Feature Completion", "50", "Higher ↑", "0-100", "%"},
                {"Assignment Submit Rate", "50", "Higher ↑", "0-100", "%"}
        };

        DefaultTableModel model = new DefaultTableModel(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);

        JLabel dimensionLabel = new JLabel("Usability, Performance Efficiency, Accessibility, Reliability, Functional Suitability");
        dimensionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dimensionLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(dimensionLabel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        backButton.addActionListener(e -> showStep("step2"));
        nextButton.addActionListener(e -> showStep("step4"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createCollectPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Step 4: Collect Data", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columnNames = {
                "Metric",
                "Direction",
                "Range",
                "Value",
                "Score (1-5)",
                "Coeff / Unit"
        };

        Object[][] tableData = {
                {"SUS Score", "Higher ↑", "0-100", "89", String.valueOf(calculateScore(89, 0, 100, true)), "50 / points"},
                {"Onboarding Time", "Lower ↓", "0-60", "5", String.valueOf(calculateScore(5, 0, 60, false)), "50 / min"},
                {"Video Start Time", "Lower ↓", "0-15", "3", String.valueOf(calculateScore(3, 0, 15, false)), "50 / sec"},
                {"Concurrent Exams", "Higher ↑", "0-600", "520", String.valueOf(calculateScore(520, 0, 600, true)), "50 / users"},
                {"WCAG Compliance", "Higher ↑", "0-100", "82", String.valueOf(calculateScore(82, 0, 100, true)), "50 / %"},
                {"Screen Reader Score", "Higher ↑", "0-100", "76", String.valueOf(calculateScore(76, 0, 100, true)), "50 / %"},
                {"Uptime", "Higher ↑", "95-100", "98.7", String.valueOf(calculateScore(98.7, 95, 100, true)), "50 / %"},
                {"MTTR", "Lower ↓", "0-120", "28", String.valueOf(calculateScore(28, 0, 120, false)), "50 / min"},
                {"Feature Completion", "Higher ↑", "0-100", "90", String.valueOf(calculateScore(90, 0, 100, true)), "50 / %"},
                {"Assignment Submit Rate", "Higher ↑", "0-100", "84", String.valueOf(calculateScore(84, 0, 100, true)), "50 / %"}
        };

        DefaultTableModel model = new DefaultTableModel(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);

        JTextArea formulaArea = new JTextArea();
        formulaArea.setEditable(false);
        formulaArea.setLineWrap(true);
        formulaArea.setWrapStyleWord(true);
        formulaArea.setText(
                "Higher is better: score = 1 + (value - min) / (max - min) × 4\n\n" +
                        "Lower is better: score = 5 - (value - min) / (max - min) × 4\n\n" +
                        "Scores are rounded to the nearest 0.5."
        );

        formulaArea.setBorder(BorderFactory.createTitledBorder("Score Formula"));

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        backButton.addActionListener(e -> showStep("step3"));
        nextButton.addActionListener(e -> showStep("step5"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(formulaArea, BorderLayout.EAST);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createAnalysePanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));

        JLabel titleLabel = new JLabel("Step 5: Analyse", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        double susScore = calculateScore(89, 0, 100, true);
        double onboardingScore = calculateScore(5, 0, 60, false);

        double videoScore = calculateScore(3, 0, 15, false);
        double concurrentScore = calculateScore(520, 0, 600, true);

        double wcagScore = calculateScore(82, 0, 100, true);
        double screenReaderScore = calculateScore(76, 0, 100, true);

        double uptimeScore = calculateScore(98.7, 95, 100, true);
        double mttrScore = calculateScore(28, 0, 120, false);

        double featureScore = calculateScore(90, 0, 100, true);
        double submitScore = calculateScore(84, 0, 100, true);

        double usability = calculateWeightedAverage(
                new double[]{susScore, onboardingScore},
                new int[]{50, 50}
        );

        double performance = calculateWeightedAverage(
                new double[]{videoScore, concurrentScore},
                new int[]{50, 50}
        );

        double accessibility = calculateWeightedAverage(
                new double[]{wcagScore, screenReaderScore},
                new int[]{50, 50}
        );

        double reliability = calculateWeightedAverage(
                new double[]{uptimeScore, mttrScore},
                new int[]{50, 50}
        );

        double suitability = calculateWeightedAverage(
                new double[]{featureScore, submitScore},
                new int[]{50, 50}
        );

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBorder(BorderFactory.createTitledBorder("Dimension-Based Weighted Average"));

        scorePanel.add(createScoreRow("Usability", usability));
        scorePanel.add(createScoreRow("Performance Efficiency", performance));
        scorePanel.add(createScoreRow("Accessibility", accessibility));
        scorePanel.add(createScoreRow("Reliability", reliability));
        scorePanel.add(createScoreRow("Functional Suitability", suitability));

        String lowestDimension = "Usability";
        double lowestScore = usability;

        if (performance < lowestScore) {
            lowestScore = performance;
            lowestDimension = "Performance Efficiency";
        }
        if (accessibility < lowestScore) {
            lowestScore = accessibility;
            lowestDimension = "Accessibility";
        }
        if (reliability < lowestScore) {
            lowestScore = reliability;
            lowestDimension = "Reliability";
        }
        if (suitability < lowestScore) {
            lowestScore = suitability;
            lowestDimension = "Functional Suitability";
        }

        double gapValue = 5.0 - lowestScore;

        JTextArea gapArea = new JTextArea();
        gapArea.setEditable(false);
        gapArea.setLineWrap(true);
        gapArea.setWrapStyleWord(true);
        gapArea.setFont(new Font("Arial", Font.PLAIN, 14));
        gapArea.setBorder(BorderFactory.createTitledBorder("Gap Analysis"));
        gapArea.setText(
                "Lowest Dimension: " + lowestDimension + "\n\n" +
                        "Score: " + String.format("%.2f", lowestScore) + "\n" +
                        "Gap Value: " + String.format("%.2f", gapValue) + "\n" +
                        "Quality Level: " + getQualityLevel(lowestScore) + "\n\n" +
                        "This dimension has the lowest score and requires the most improvement."
        );

        JPanel radarPlaceholderPanel = new JPanel(new BorderLayout());
        radarPlaceholderPanel.setBorder(BorderFactory.createTitledBorder("Radar Chart (Bonus)"));

        JTextArea radarText = new JTextArea();
        radarText.setEditable(false);
        radarText.setLineWrap(true);
        radarText.setWrapStyleWord(true);
        radarText.setText(
                "Bonus part:\n\n" +
                        "You can later replace this area with a custom-drawn radar chart\n" +
                        "using Graphics / Graphics2D."
        );

        radarPlaceholderPanel.add(radarText, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        centerPanel.add(scorePanel);
        centerPanel.add(radarPlaceholderPanel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showStep("step4"));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(gapArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createScoreRow(String dimensionName, double score) {
        JPanel rowPanel = new JPanel(new BorderLayout(10, 10));
        rowPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel nameLabel = new JLabel(dimensionName + " - Score: " + String.format("%.2f", score));

        JProgressBar progressBar = new JProgressBar(0, 500);
        progressBar.setValue((int) (score * 100));
        progressBar.setString(String.format("%.2f / 5.0", score));
        progressBar.setStringPainted(true);

        rowPanel.add(nameLabel, BorderLayout.WEST);
        rowPanel.add(progressBar, BorderLayout.CENTER);

        return rowPanel;
    }
    private void showStep(String stepName) {
        cardLayout.show(cardPanel, stepName);

        profileStepLabel.setText("1. Profile");
        defineStepLabel.setText("2. Define");
        planStepLabel.setText("3. Plan");
        collectStepLabel.setText("4. Collect");
        analyseStepLabel.setText("5. Analyse");

        profileStepLabel.setBackground(null);
        defineStepLabel.setBackground(null);
        planStepLabel.setBackground(null);
        collectStepLabel.setBackground(null);
        analyseStepLabel.setBackground(null);

        profileStepLabel.setForeground(Color.BLACK);
        defineStepLabel.setForeground(Color.BLACK);
        planStepLabel.setForeground(Color.BLACK);
        collectStepLabel.setForeground(Color.BLACK);
        analyseStepLabel.setForeground(Color.BLACK);

        if (stepName.equals("step1")) {
            profileStepLabel.setBackground(Color.BLUE);
            profileStepLabel.setForeground(Color.WHITE);
        }

        if (stepName.equals("step2")) {
            profileStepLabel.setText("✓ Profile");
            defineStepLabel.setBackground(Color.BLUE);
            defineStepLabel.setForeground(Color.WHITE);
        }

        if (stepName.equals("step3")) {
            profileStepLabel.setText("✓ Profile");
            defineStepLabel.setText("✓ Define");
            planStepLabel.setBackground(Color.BLUE);
            planStepLabel.setForeground(Color.WHITE);
        }

        if (stepName.equals("step4")) {
            profileStepLabel.setText("✓ Profile");
            defineStepLabel.setText("✓ Define");
            planStepLabel.setText("✓ Plan");
            collectStepLabel.setBackground(Color.BLUE);
            collectStepLabel.setForeground(Color.WHITE);
        }

        if (stepName.equals("step5")) {
            profileStepLabel.setText("✓ Profile");
            defineStepLabel.setText("✓ Define");
            planStepLabel.setText("✓ Plan");
            collectStepLabel.setText("✓ Collect");
            analyseStepLabel.setBackground(Color.BLUE);
            analyseStepLabel.setForeground(Color.WHITE);
        }
    }
    private double roundToNearestHalf(double value) {
        return Math.round(value * 2.0) / 2.0;
    }

    private double calculateScore(double value, double min, double max, boolean higherIsBetter) {
        double score;

        if (higherIsBetter) {
            score = 1 + ((value - min) / (max - min)) * 4;
        } else {
            score = 5 - ((value - min) / (max - min)) * 4;
        }

        if (score < 1.0) score = 1.0;
        if (score > 5.0) score = 5.0;

        return roundToNearestHalf(score);
    }

    private double calculateWeightedAverage(double[] scores, int[] coefficients) {
        double total = 0;
        int coeffSum = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i] * coefficients[i];
            coeffSum += coefficients[i];
        }

        return total / coeffSum;
    }

    private String getQualityLevel(double score) {
        if (score >= 4.5) return "Excellent";
        if (score >= 3.5) return "Good";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor";
    }


}