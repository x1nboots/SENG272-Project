import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ISO15939Simulator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

/*
 * Simple model class for one metric.
 * This keeps metric data separate from GUI code.
 */
class Metric {
    private final String dimensionName;
    private final int dimensionCoefficient;
    private final String name;
    private final int coefficient;
    private final boolean higherIsBetter;
    private final double min;
    private final double max;
    private final String unit;
    private final double value;

    public Metric(String dimensionName, int dimensionCoefficient, String name, int coefficient,
                  boolean higherIsBetter, double min, double max, String unit, double value) {
        this.dimensionName = dimensionName;
        this.dimensionCoefficient = dimensionCoefficient;
        this.name = name;
        this.coefficient = coefficient;
        this.higherIsBetter = higherIsBetter;
        this.min = min;
        this.max = max;
        this.unit = unit;
        this.value = value;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public int getDimensionCoefficient() {
        return dimensionCoefficient;
    }

    public String getName() {
        return name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public boolean isHigherIsBetter() {
        return higherIsBetter;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public String getRangeText() {
        if (min == (int) min && max == (int) max) {
            return (int) min + "-" + (int) max;
        }
        return min + "-" + max;
    }

    public String getDirectionText() {
        return higherIsBetter ? "Higher ↑" : "Lower ↓";
    }

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }
}

/*
 * Scenario model class.
 * Each scenario has a mode, name, and a list of metrics.
 */
class Scenario {
    private final String mode;
    private final String name;
    private final ArrayList<Metric> metrics;

    public Scenario(String mode, String name, ArrayList<Metric> metrics) {
        this.mode = mode;
        this.name = name;
        this.metrics = metrics;
    }

    public String getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Metric> getMetrics() {
        return metrics;
    }
}

/*
 * ScenarioRepository stores all hard-coded scenario data.
 * HashMap is used to group scenarios by mode.
 * ArrayList is used to store scenarios and metrics dynamically.
 */
class ScenarioRepository {
    private final HashMap<String, ArrayList<Scenario>> scenariosByMode = new HashMap<>();

    public ScenarioRepository() {
        loadData();
    }

    private void loadData() {
        scenariosByMode.put("Health", new ArrayList<>());
        scenariosByMode.put("Education", new ArrayList<>());
        scenariosByMode.put("Custom", new ArrayList<>());

        scenariosByMode.get("Education").add(new Scenario("Education", "Scenario C — Team Alpha", createEducationTeamAlphaMetrics()));
        scenariosByMode.get("Education").add(new Scenario("Education", "Scenario D — Team Beta", createEducationTeamBetaMetrics()));

        scenariosByMode.get("Health").add(new Scenario("Health", "Scenario A — Clinic One", createHealthClinicOneMetrics()));
        scenariosByMode.get("Health").add(new Scenario("Health", "Scenario B — Hospital Core", createHealthHospitalCoreMetrics()));

        scenariosByMode.get("Custom").add(new Scenario("Custom", "Custom Scenario 1 — Basic Template", createCustomTemplateOneMetrics()));
        scenariosByMode.get("Custom").add(new Scenario("Custom", "Custom Scenario 2 — Advanced Template", createCustomTemplateTwoMetrics()));
    }

    public ArrayList<Scenario> getScenariosByMode(String mode) {
        return scenariosByMode.get(mode);
    }

    public Scenario findScenario(String mode, String scenarioName) {
        ArrayList<Scenario> scenarioList = scenariosByMode.get(mode);
        if (scenarioList == null) {
            return null;
        }

        for (Scenario scenario : scenarioList) {
            if (scenario.getName().equals(scenarioName)) {
                return scenario;
            }
        }

        return null;
    }

    private ArrayList<Metric> createEducationTeamAlphaMetrics() {
        ArrayList<Metric> metrics = new ArrayList<>();
        metrics.add(new Metric("Usability", 25, "SUS Score", 50, true, 0, 100, "points", 89));
        metrics.add(new Metric("Usability", 25, "Onboarding Time", 50, false, 0, 60, "min", 5));
        metrics.add(new Metric("Performance Efficiency", 20, "Video Start Time", 50, false, 0, 15, "sec", 3));
        metrics.add(new Metric("Performance Efficiency", 20, "Concurrent Exams", 50, true, 0, 600, "users", 520));
        metrics.add(new Metric("Accessibility", 20, "WCAG Compliance", 50, true, 0, 100, "%", 82));
        metrics.add(new Metric("Accessibility", 20, "Screen Reader Score", 50, true, 0, 100, "%", 76));
        metrics.add(new Metric("Reliability", 20, "Uptime", 50, true, 95, 100, "%", 98.7));
        metrics.add(new Metric("Reliability", 20, "MTTR", 50, false, 0, 120, "min", 28));
        metrics.add(new Metric("Functional Suitability", 15, "Feature Completion", 50, true, 0, 100, "%", 90));
        metrics.add(new Metric("Functional Suitability", 15, "Assignment Submit Rate", 50, true, 0, 100, "%", 84));
        return metrics;
    }

    private ArrayList<Metric> createEducationTeamBetaMetrics() {
        ArrayList<Metric> metrics = new ArrayList<>();
        metrics.add(new Metric("Usability", 25, "SUS Score", 50, true, 0, 100, "points", 72));
        metrics.add(new Metric("Usability", 25, "Onboarding Time", 50, false, 0, 60, "min", 16));
        metrics.add(new Metric("Performance Efficiency", 20, "Video Start Time", 50, false, 0, 15, "sec", 6));
        metrics.add(new Metric("Performance Efficiency", 20, "Concurrent Exams", 50, true, 0, 600, "users", 410));
        metrics.add(new Metric("Accessibility", 20, "WCAG Compliance", 50, true, 0, 100, "%", 68));
        metrics.add(new Metric("Accessibility", 20, "Screen Reader Score", 50, true, 0, 100, "%", 70));
        metrics.add(new Metric("Reliability", 20, "Uptime", 50, true, 95, 100, "%", 97.8));
        metrics.add(new Metric("Reliability", 20, "MTTR", 50, false, 0, 120, "min", 44));
        metrics.add(new Metric("Functional Suitability", 15, "Feature Completion", 50, true, 0, 100, "%", 78));
        metrics.add(new Metric("Functional Suitability", 15, "Assignment Submit Rate", 50, true, 0, 100, "%", 76));
        return metrics;
    }

    private ArrayList<Metric> createHealthClinicOneMetrics() {
        ArrayList<Metric> metrics = new ArrayList<>();
        metrics.add(new Metric("Usability", 25, "Patient Portal SUS", 50, true, 0, 100, "points", 86));
        metrics.add(new Metric("Usability", 25, "Registration Time", 50, false, 0, 60, "min", 8));
        metrics.add(new Metric("Performance Efficiency", 20, "Record Load Time", 50, false, 0, 15, "sec", 4));
        metrics.add(new Metric("Performance Efficiency", 20, "Concurrent Patients", 50, true, 0, 600, "users", 480));
        metrics.add(new Metric("Accessibility", 20, "WCAG Compliance", 50, true, 0, 100, "%", 80));
        metrics.add(new Metric("Accessibility", 20, "Screen Reader Score", 50, true, 0, 100, "%", 74));
        metrics.add(new Metric("Reliability", 20, "Uptime", 50, true, 95, 100, "%", 99.1));
        metrics.add(new Metric("Reliability", 20, "MTTR", 50, false, 0, 120, "min", 22));
        metrics.add(new Metric("Functional Suitability", 15, "Feature Completion", 50, true, 0, 100, "%", 88));
        metrics.add(new Metric("Functional Suitability", 15, "Appointment Success Rate", 50, true, 0, 100, "%", 91));
        return metrics;
    }

    private ArrayList<Metric> createHealthHospitalCoreMetrics() {
        ArrayList<Metric> metrics = new ArrayList<>();
        metrics.add(new Metric("Usability", 25, "Staff SUS", 50, true, 0, 100, "points", 77));
        metrics.add(new Metric("Usability", 25, "Patient Search Time", 50, false, 0, 60, "min", 13));
        metrics.add(new Metric("Performance Efficiency", 20, "Lab Result Load Time", 50, false, 0, 15, "sec", 5));
        metrics.add(new Metric("Performance Efficiency", 20, "Concurrent Staff", 50, true, 0, 600, "users", 450));
        metrics.add(new Metric("Accessibility", 20, "WCAG Compliance", 50, true, 0, 100, "%", 73));
        metrics.add(new Metric("Accessibility", 20, "Screen Reader Score", 50, true, 0, 100, "%", 71));
        metrics.add(new Metric("Reliability", 20, "Uptime", 50, true, 95, 100, "%", 98.2));
        metrics.add(new Metric("Reliability", 20, "MTTR", 50, false, 0, 120, "min", 36));
        metrics.add(new Metric("Functional Suitability", 15, "Feature Completion", 50, true, 0, 100, "%", 83));
        metrics.add(new Metric("Functional Suitability", 15, "Prescription Success Rate", 50, true, 0, 100, "%", 79));
        return metrics;
    }

    private ArrayList<Metric> createCustomTemplateOneMetrics() {
        ArrayList<Metric> metrics = new ArrayList<>();
        metrics.add(new Metric("Custom Quality", 30, "User Satisfaction", 50, true, 0, 100, "points", 75));
        metrics.add(new Metric("Custom Quality", 30, "Task Completion Time", 50, false, 0, 60, "min", 18));
        metrics.add(new Metric("System Quality", 30, "System Uptime", 50, true, 95, 100, "%", 98));
        metrics.add(new Metric("System Quality", 30, "Error Recovery Time", 50, false, 0, 120, "min", 40));
        metrics.add(new Metric("Feature Quality", 40, "Feature Completion", 50, true, 0, 100, "%", 82));
        metrics.add(new Metric("Feature Quality", 40, "Task Success Rate", 50, true, 0, 100, "%", 85));
        return metrics;
    }

    private ArrayList<Metric> createCustomTemplateTwoMetrics() {
        ArrayList<Metric> metrics = new ArrayList<>();
        metrics.add(new Metric("Custom Quality", 30, "User Satisfaction", 50, true, 0, 100, "points", 84));
        metrics.add(new Metric("Custom Quality", 30, "Task Completion Time", 50, false, 0, 60, "min", 11));
        metrics.add(new Metric("System Quality", 30, "System Uptime", 50, true, 95, 100, "%", 99));
        metrics.add(new Metric("System Quality", 30, "Error Recovery Time", 50, false, 0, 120, "min", 24));
        metrics.add(new Metric("Feature Quality", 40, "Feature Completion", 50, true, 0, 100, "%", 91));
        metrics.add(new Metric("Feature Quality", 40, "Task Success Rate", 50, true, 0, 100, "%", 88));
        return metrics;
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

    private String selectedQualityType = "";
    private String selectedMode = "Education";
    private Scenario selectedScenario;

    private final ScenarioRepository scenarioRepository = new ScenarioRepository();

    public MainFrame() {
        setUIFont();

        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(1500, 950);
        setMinimumSize(new Dimension(1300, 850));
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(16, 24, 8, 24));

        JLabel titleLabel = new JLabel("ISO 15939 Measurement Process Simulator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));

        JPanel indicatorPanel = new JPanel(new GridLayout(1, 5, 14, 14));
        indicatorPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        profileStepLabel = createStepLabel("1. Profile");
        defineStepLabel = createStepLabel("2. Define");
        planStepLabel = createStepLabel("3. Plan");
        collectStepLabel = createStepLabel("4. Collect");
        analyseStepLabel = createStepLabel("5. Analyse");

        indicatorPanel.add(profileStepLabel);
        indicatorPanel.add(defineStepLabel);
        indicatorPanel.add(planStepLabel);
        indicatorPanel.add(collectStepLabel);
        indicatorPanel.add(analyseStepLabel);

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(indicatorPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        selectedScenario = scenarioRepository.getScenariosByMode("Education").get(0);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));

        cardPanel.add(createProfilePanel(), "step1");
        cardPanel.add(createDefinePanel(), "step2");
        cardPanel.add(createPlanPanel(), "step3");
        cardPanel.add(createCollectPanel(), "step4");
        cardPanel.add(createAnalysePanel(), "step5");

        add(cardPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 16, 10));





        add(navigationPanel, BorderLayout.SOUTH);

        showStep("step1");
    }

    private void setUIFont() {
        Font mainFont = new Font("Arial", Font.PLAIN, 22);
        Font boldFont = new Font("Arial", Font.BOLD, 22);

        UIManager.put("Label.font", mainFont);
        UIManager.put("Button.font", boldFont);
        UIManager.put("TextField.font", mainFont);
        UIManager.put("RadioButton.font", mainFont);
        UIManager.put("ComboBox.font", mainFont);
        UIManager.put("Table.font", mainFont);
        UIManager.put("TableHeader.font", boldFont);
        UIManager.put("TextArea.font", mainFont);
        UIManager.put("ProgressBar.font", new Font("Arial", Font.BOLD, 20));
        UIManager.put("TitledBorder.font", boldFont);
        UIManager.put("OptionPane.messageFont", mainFont);
        UIManager.put("OptionPane.buttonFont", boldFont);
    }

    private JLabel createStepLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(12, 8, 12, 8)
        ));
        return label;
    }

    private JButton createNavigationButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(160, 52));
        return button;
    }

    private JButton createLargeButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 54));
        return button;
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));

        JLabel titleLabel = new JLabel("Step 1: Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel formWrapper = new JPanel(new GridBagLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("User and Session Information"),
                BorderFactory.createEmptyBorder(35, 55, 35, 55)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(16, 16, 16, 16);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(480, 48));

        JLabel schoolLabel = new JLabel("School:");
        JTextField schoolField = new JTextField();
        schoolField.setPreferredSize(new Dimension(480, 48));

        JLabel sessionLabel = new JLabel("Session Name:");
        JTextField sessionField = new JTextField();
        sessionField.setPreferredSize(new Dimension(480, 48));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(schoolLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(schoolField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(sessionLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(sessionField, gbc);

        formWrapper.add(formPanel);

        JButton nextButton = createLargeButton("Next");

        nextButton.addActionListener(e -> {
            if (usernameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter your username to continue.");
                return;
            }

            if (schoolField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter your school to continue.");
                return;
            }

            if (sessionField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter your session name to continue.");
                return;
            }

            username = usernameField.getText().trim();
            school = schoolField.getText().trim();
            sessionName = sessionField.getText().trim();

            showStep("step2");
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 16));
        buttonPanel.add(nextButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formWrapper, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createDefinePanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));

        JLabel titleLabel = new JLabel("Step 2: Define Quality Dimensions", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 90, 30, 90));

        JPanel qualityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 24, 18));
        qualityPanel.setBorder(BorderFactory.createTitledBorder("2a. Quality Type Selection"));

        JRadioButton productRadio = new JRadioButton("Product Quality");
        JRadioButton processRadio = new JRadioButton("Process Quality");

        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(productRadio);
        qualityGroup.add(processRadio);

        qualityPanel.add(productRadio);
        qualityPanel.add(processRadio);

        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 24, 18));
        modePanel.setBorder(BorderFactory.createTitledBorder("2b. Mode Selection"));

        JRadioButton customRadio = new JRadioButton("Custom");
        JRadioButton healthRadio = new JRadioButton("Health");
        JRadioButton educationRadio = new JRadioButton("Education");

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(customRadio);
        modeGroup.add(healthRadio);
        modeGroup.add(educationRadio);

        modePanel.add(customRadio);
        modePanel.add(healthRadio);
        modePanel.add(educationRadio);

        JPanel scenarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 24, 18));
        scenarioPanel.setBorder(BorderFactory.createTitledBorder("2c. Scenario Selection"));

        JLabel scenarioLabel = new JLabel("Scenario:");
        JComboBox<String> scenarioBox = new JComboBox<>();
        scenarioBox.setPreferredSize(new Dimension(440, 48));

        scenarioPanel.add(scenarioLabel);
        scenarioPanel.add(scenarioBox);

        Runnable loadCustomScenarios = () -> {
            selectedMode = "Custom";
            loadScenarioBox(scenarioBox, selectedMode);
        };

        Runnable loadHealthScenarios = () -> {
            selectedMode = "Health";
            loadScenarioBox(scenarioBox, selectedMode);
        };

        Runnable loadEducationScenarios = () -> {
            selectedMode = "Education";
            loadScenarioBox(scenarioBox, selectedMode);
        };

        customRadio.addActionListener(e -> loadCustomScenarios.run());
        healthRadio.addActionListener(e -> loadHealthScenarios.run());
        educationRadio.addActionListener(e -> loadEducationScenarios.run());

        educationRadio.setSelected(true);
        loadEducationScenarios.run();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 16));

        JButton backButton = createLargeButton("Back");
        JButton nextButton = createLargeButton("Next");

        backButton.addActionListener(e -> showStep("step1"));

        nextButton.addActionListener(e -> {
            if (!productRadio.isSelected() && !processRadio.isSelected()) {
                JOptionPane.showMessageDialog(panel, "Please select one quality type to continue.");
                return;
            }

            if (!customRadio.isSelected() && !healthRadio.isSelected() && !educationRadio.isSelected()) {
                JOptionPane.showMessageDialog(panel, "Please select one mode to continue.");
                return;
            }

            if (scenarioBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(panel, "Please select a scenario to continue.");
                return;
            }

            selectedQualityType = productRadio.isSelected() ? "Product Quality" : "Process Quality";
            selectedScenario = scenarioRepository.findScenario(selectedMode, scenarioBox.getSelectedItem().toString());

            refreshPlanPanel();
            refreshCollectPanel();
            refreshAnalysePanel();

            showStep("step3");
        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        mainPanel.add(qualityPanel);
        mainPanel.add(Box.createVerticalStrut(22));
        mainPanel.add(modePanel);
        mainPanel.add(Box.createVerticalStrut(22));
        mainPanel.add(scenarioPanel);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(mainPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadScenarioBox(JComboBox<String> scenarioBox, String mode) {
        scenarioBox.removeAllItems();

        ArrayList<Scenario> scenarios = scenarioRepository.getScenariosByMode(mode);
        for (Scenario scenario : scenarios) {
            scenarioBox.addItem(scenario.getName());
        }
    }

    private void refreshPlanPanel() {
        cardPanel.add(createPlanPanel(), "step3");
    }

    private void refreshCollectPanel() {
        cardPanel.add(createCollectPanel(), "step4");
    }

    private void refreshAnalysePanel() {
        cardPanel.add(createAnalysePanel(), "step5");
    }

    private JPanel createPlanPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));

        JLabel titleLabel = new JLabel("Step 3: Plan Measurement", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        String[] columnNames = {"Dimension", "Metric", "Metric Coeff.", "Direction", "Range", "Unit"};

        ArrayList<Metric> metrics = selectedScenario.getMetrics();
        Object[][] tableData = new Object[metrics.size()][6];

        for (int i = 0; i < metrics.size(); i++) {
            Metric metric = metrics.get(i);
            tableData[i][0] = metric.getDimensionName() + " (" + metric.getDimensionCoefficient() + ")";
            tableData[i][1] = metric.getName();
            tableData[i][2] = metric.getCoefficient();
            tableData[i][3] = metric.getDirectionText();
            tableData[i][4] = metric.getRangeText();
            tableData[i][5] = metric.getUnit();
        }

        DefaultTableModel model = createReadOnlyTableModel(tableData, columnNames);

        JTable table = createLargeTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JLabel dimensionLabel = new JLabel(
                "Selected Quality Type: " + selectedQualityType +
                        "    |    Mode: " + selectedMode +
                        "    |    Scenario: " + selectedScenario.getName(),
                SwingConstants.CENTER
        );
        dimensionLabel.setFont(new Font("Arial", Font.BOLD, 22));
        dimensionLabel.setBorder(BorderFactory.createEmptyBorder(12, 0, 18, 0));

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(dimensionLabel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = createLargeButton("Back");
        JButton nextButton = createLargeButton("Next");

        backButton.addActionListener(e -> showStep("step2"));
        nextButton.addActionListener(e -> showStep("step4"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 16));
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCollectPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));

        JLabel titleLabel = new JLabel("Step 4: Collect Data", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        String[] columnNames = {"Metric", "Direction", "Range", "Value", "Score (1-5)", "Coeff / Unit"};

        ArrayList<Metric> metrics = selectedScenario.getMetrics();
        Object[][] tableData = new Object[metrics.size()][6];

        for (int i = 0; i < metrics.size(); i++) {
            Metric metric = metrics.get(i);
            double score = calculateScore(metric.getValue(), metric.getMin(), metric.getMax(), metric.isHigherIsBetter());

            tableData[i][0] = metric.getName();
            tableData[i][1] = metric.getDirectionText();
            tableData[i][2] = metric.getRangeText();
            tableData[i][3] = formatNumber(metric.getValue());
            tableData[i][4] = String.format("%.1f", score);
            tableData[i][5] = metric.getCoefficient() + " / " + metric.getUnit();
        }

        DefaultTableModel model = createReadOnlyTableModel(tableData, columnNames);
        JTable table = createLargeTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JTextArea formulaArea = new JTextArea();
        formulaArea.setEditable(false);
        formulaArea.setLineWrap(true);
        formulaArea.setWrapStyleWord(true);
        formulaArea.setFont(new Font("Arial", Font.PLAIN, 21));
        formulaArea.setText(
                "Higher is better:\n" +
                        "score = 1 + (value - min) / (max - min) × 4\n\n" +
                        "Lower is better:\n" +
                        "score = 5 - (value - min) / (max - min) × 4\n\n" +
                        "All scores are limited between 1.0 and 5.0.\n" +
                        "Scores are rounded to the nearest 0.5."
        );
        formulaArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Score Formula"),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        formulaArea.setPreferredSize(new Dimension(430, 250));

        JButton backButton = createLargeButton("Back");
        JButton nextButton = createLargeButton("Next");

        backButton.addActionListener(e -> showStep("step3"));
        nextButton.addActionListener(e -> showStep("step5"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 16));
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
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        HashMap<String, ArrayList<Metric>> dimensionMap = groupMetricsByDimension(selectedScenario.getMetrics());

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Dimension-Based Weighted Average"),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        String lowestDimension = "";
        double lowestScore = 6.0;

        for (String dimensionName : dimensionMap.keySet()) {
            ArrayList<Metric> metrics = dimensionMap.get(dimensionName);
            double dimensionScore = calculateDimensionScore(metrics);

            scorePanel.add(createScoreRow(dimensionName, dimensionScore));

            if (dimensionScore < lowestScore) {
                lowestScore = dimensionScore;
                lowestDimension = dimensionName;
            }
        }

        double gapValue = 5.0 - lowestScore;

        JTextArea gapArea = new JTextArea();
        gapArea.setEditable(false);
        gapArea.setLineWrap(true);
        gapArea.setWrapStyleWord(true);
        gapArea.setFont(new Font("Arial", Font.PLAIN, 21));
        gapArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Gap Analysis"),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        gapArea.setText(
                "Lowest Dimension: " + lowestDimension + "\n\n" +
                        "Score: " + String.format("%.2f", lowestScore) + "\n" +
                        "Gap Value: " + String.format("%.2f", gapValue) + "\n" +
                        "Quality Level: " + getQualityLevel(lowestScore) + "\n\n" +
                        "This dimension has the lowest score and requires the most improvement."
        );

        JPanel radarPanel = new JPanel(new BorderLayout());
        radarPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Radar Chart (Bonus Placeholder)"),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        JTextArea radarText = new JTextArea();
        radarText.setEditable(false);
        radarText.setLineWrap(true);
        radarText.setWrapStyleWord(true);
        radarText.setFont(new Font("Arial", Font.PLAIN, 21));
        radarText.setText(
                "This area is reserved for the bonus radar chart.\n\n" +
                        "If more time is available, this part can be implemented with Java 2D Graphics."
        );

        radarPanel.add(radarText, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout(18, 18));
        centerPanel.add(scorePanel, BorderLayout.CENTER);

        radarPanel.setPreferredSize(new Dimension(360, 220));
        centerPanel.add(radarPanel, BorderLayout.EAST);

        JButton backButton = createLargeButton("Back");
        JButton exitButton = createLargeButton("Exit");

        backButton.addActionListener(e -> showStep("step4"));
        exitButton.addActionListener(e -> System.exit(0));

        JPanel bottomPanel = new JPanel(new BorderLayout(15, 15));
        bottomPanel.add(gapArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 16));
        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private DefaultTableModel createReadOnlyTableModel(Object[][] tableData, String[] columnNames) {
        return new DefaultTableModel(tableData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private JTable createLargeTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setRowHeight(46);
        table.setFont(new Font("Arial", Font.PLAIN, 21));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 21));
        table.getTableHeader().setPreferredSize(new Dimension(100, 48));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }

    private JPanel createScoreRow(String dimensionName, double score) {
        JPanel rowPanel = new JPanel(new BorderLayout(16, 16));
        rowPanel.setBorder(BorderFactory.createEmptyBorder(12, 8, 12, 8));

        JLabel nameLabel = new JLabel(dimensionName + " - Score: " + String.format("%.2f", score));
        nameLabel.setPreferredSize(new Dimension(280, 40));
        rowPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 21));

        JProgressBar progressBar = new JProgressBar(0, 500);
        progressBar.setValue((int) (score * 100));
        progressBar.setString(String.format("%.2f / 5.0", score));
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(420, 38));

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

        resetStepLabel(profileStepLabel);
        resetStepLabel(defineStepLabel);
        resetStepLabel(planStepLabel);
        resetStepLabel(collectStepLabel);
        resetStepLabel(analyseStepLabel);

        if (stepName.equals("step1")) {
            activateStepLabel(profileStepLabel);
        }

        if (stepName.equals("step2")) {
            profileStepLabel.setText("✓ Profile");
            activateStepLabel(defineStepLabel);
        }

        if (stepName.equals("step3")) {
            profileStepLabel.setText("✓ Profile");
            defineStepLabel.setText("✓ Define");
            activateStepLabel(planStepLabel);
        }

        if (stepName.equals("step4")) {
            profileStepLabel.setText("✓ Profile");
            defineStepLabel.setText("✓ Define");
            planStepLabel.setText("✓ Plan");
            activateStepLabel(collectStepLabel);
        }

        if (stepName.equals("step5")) {
            profileStepLabel.setText("✓ Profile");
            defineStepLabel.setText("✓ Define");
            planStepLabel.setText("✓ Plan");
            collectStepLabel.setText("✓ Collect");
            activateStepLabel(analyseStepLabel);
        }
    }

    private void resetStepLabel(JLabel label) {
        label.setBackground(new Color(235, 235, 235));
        label.setForeground(Color.BLACK);
    }

    private void activateStepLabel(JLabel label) {
        label.setBackground(new Color(30, 90, 180));
        label.setForeground(Color.WHITE);
    }

    private HashMap<String, ArrayList<Metric>> groupMetricsByDimension(ArrayList<Metric> metrics) {
        HashMap<String, ArrayList<Metric>> dimensionMap = new HashMap<>();

        for (Metric metric : metrics) {
            String dimensionName = metric.getDimensionName();

            if (!dimensionMap.containsKey(dimensionName)) {
                dimensionMap.put(dimensionName, new ArrayList<>());
            }

            dimensionMap.get(dimensionName).add(metric);
        }

        return dimensionMap;
    }

    private double calculateDimensionScore(ArrayList<Metric> metrics) {
        double total = 0;
        int coeffSum = 0;

        for (Metric metric : metrics) {
            double score = calculateScore(metric.getValue(), metric.getMin(), metric.getMax(), metric.isHigherIsBetter());
            total += score * metric.getCoefficient();
            coeffSum += metric.getCoefficient();
        }

        return total / coeffSum;
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

    private String getQualityLevel(double score) {
        if (score >= 4.5) return "Excellent";
        if (score >= 3.5) return "Good";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor";
    }

    private String formatNumber(double value) {
        if (value == (int) value) {
            return String.valueOf((int) value);
        }
        return String.valueOf(value);
    }
}