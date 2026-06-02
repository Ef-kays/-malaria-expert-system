import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Entry point for the Malaria Expert System application.
 * This class bypasses JavaFX modular runtime requirements by serving as a non-Application launcher
 * which delegates to the {@link Launcher} class.
 *
 * @author Group 7
 * @version 1.0
 */
public class App {
    /**
     * Main method that launches the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}

/**
 * Custom exception thrown when patient data validation fails.
 */
class ValidationException extends Exception {
    /**
     * Constructs a new ValidationException with the specified message.
     *
     * @param message The detail message.
     */
    public ValidationException(String message) {
        super(message);
    }
}

/**
 * Data model storing the patient demographic profile and selected symptoms.
 * Employs input validation and assertions to guarantee consistent state.
 */
class PatientData {
    private String name;
    private int age;
    private String gender;
    private boolean recentTravel;
    private double temperature;
    
    // Mild Symptoms
    private boolean hasChills;
    private boolean hasHeadache;
    private boolean hasNauseaVomiting;
    private boolean hasMuscleJointPain;
    private boolean hasFatigue;
    
    // Severe Symptoms
    private boolean hasConfusion;
    private boolean hasConvulsions;
    private boolean hasBreathingDifficulty;
    private boolean hasJaundice;
    private boolean hasExtremeWeakness;
    private boolean hasDarkUrine;

    // Differential Diagnosis Symptom
    private boolean hasFluSymptoms;

    /**
     * Default constructor.
     */
    public PatientData() {
        this.name = "";
        this.age = -1;
        this.gender = "";
        this.recentTravel = false;
        this.temperature = 37.0;
    }

    /**
     * Validates the demographics data.
     *
     * @throws ValidationException If the name is blank or age is out of realistic bounds.
     */
    public void validateDemographics() throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Patient name cannot be empty.");
        }
        if (age < 0 || age > 120) {
            throw new ValidationException("Age must be between 0 and 120.");
        }
        if (gender == null || gender.trim().isEmpty()) {
            throw new ValidationException("Please select a gender.");
        }
    }

    /**
     * Validates the temperature input.
     *
     * @throws ValidationException If the temperature is clinically impossible or invalid.
     */
    public void validateTemperature() throws ValidationException {
        if (temperature < 34.0 || temperature > 43.0) {
            throw new ValidationException("Body temperature must be between 34.0°C and 43.0°C.");
        }
    }

    // Getters and Setters with assertions where appropriate

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        assert age >= 0 : "Age assertion failed: age is negative (" + age + ")";
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isRecentTravel() {
        return recentTravel;
    }

    public void setRecentTravel(boolean recentTravel) {
        this.recentTravel = recentTravel;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        assert temperature > 0.0 : "Temperature assertion failed: temperature must be positive";
        this.temperature = temperature;
    }

    public boolean hasChills() {
        return hasChills;
    }

    public void setHasChills(boolean hasChills) {
        this.hasChills = hasChills;
    }

    public boolean hasHeadache() {
        return hasHeadache;
    }

    public void setHasHeadache(boolean hasHeadache) {
        this.hasHeadache = hasHeadache;
    }

    public boolean hasNauseaVomiting() {
        return hasNauseaVomiting;
    }

    public void setHasNauseaVomiting(boolean hasNauseaVomiting) {
        this.hasNauseaVomiting = hasNauseaVomiting;
    }

    public boolean hasMuscleJointPain() {
        return hasMuscleJointPain;
    }

    public void setHasMuscleJointPain(boolean hasMuscleJointPain) {
        this.hasMuscleJointPain = hasMuscleJointPain;
    }

    public boolean hasFatigue() {
        return hasFatigue;
    }

    public void setHasFatigue(boolean hasFatigue) {
        this.hasFatigue = hasFatigue;
    }

    public boolean hasConfusion() {
        return hasConfusion;
    }

    public void setHasConfusion(boolean hasConfusion) {
        this.hasConfusion = hasConfusion;
    }

    public boolean hasConvulsions() {
        return hasConvulsions;
    }

    public void setHasConvulsions(boolean hasConvulsions) {
        this.hasConvulsions = hasConvulsions;
    }

    public boolean hasBreathingDifficulty() {
        return hasBreathingDifficulty;
    }

    public void setHasBreathingDifficulty(boolean hasBreathingDifficulty) {
        this.hasBreathingDifficulty = hasBreathingDifficulty;
    }

    public boolean hasJaundice() {
        return hasJaundice;
    }

    public void setHasJaundice(boolean hasJaundice) {
        this.hasJaundice = hasJaundice;
    }

    public boolean hasExtremeWeakness() {
        return hasExtremeWeakness;
    }

    public void setHasExtremeWeakness(boolean hasExtremeWeakness) {
        this.hasExtremeWeakness = hasExtremeWeakness;
    }

    public boolean hasDarkUrine() {
        return hasDarkUrine;
    }

    public void setHasDarkUrine(boolean hasDarkUrine) {
        this.hasDarkUrine = hasDarkUrine;
    }

    public boolean hasFluSymptoms() {
        return hasFluSymptoms;
    }

    public void setHasFluSymptoms(boolean hasFluSymptoms) {
        this.hasFluSymptoms = hasFluSymptoms;
    }
}

/**
 * Diagnosis result representation containing risk level, diagnostic label, and medical advice.
 */
class DiagnosisResult {
    public enum Risk {
        LOW, MODERATE, HIGH
    }

    private final Risk risk;
    private final String title;
    private final String explanation;
    private final String advice;

    /**
     * Constructs a DiagnosisResult object.
     *
     * @param risk        The evaluated risk level.
     * @param title       The summary title of the diagnosis.
     * @param explanation Medical explanation for the evaluation.
     * @param advice      Recommended medical action.
     */
    public DiagnosisResult(Risk risk, String title, String explanation, String advice) {
        this.risk = Objects.requireNonNull(risk);
        this.title = Objects.requireNonNull(title);
        this.explanation = Objects.requireNonNull(explanation);
        this.advice = Objects.requireNonNull(advice);
    }

    public Risk getRisk() {
        return risk;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getAdvice() {
        return advice;
    }
}

/**
 * Inference engine executing rule-based logic for malaria diagnostics.
 * Evaluates symptoms based on clinical risk factors.
 */
class MalariaInferenceEngine {

    /**
     * Infers the malaria status of a patient based on the provided patient data.
     *
     * @param patient Validated patient data object.
     * @return DiagnosisResult representing the diagnostic outcome.
     */
    public DiagnosisResult infer(PatientData patient) {
        Objects.requireNonNull(patient, "Patient data cannot be null for inference.");
        
        // Assert that data is validated before inference is run
        assert !patient.getName().isEmpty() : "Patient name should not be blank in inference stage";
        assert patient.getAge() >= 0 : "Patient age should be valid in inference stage";
        assert patient.getTemperature() >= 34.0 : "Patient temperature should be within clinical ranges";

        boolean fever = hasFever(patient);
        boolean anySevere = hasAnySevereSymptom(patient);
        int mildSymptomCount = countMildSymptoms(patient);

        // Rule 1: Severe/Complicated Malaria
        if (fever && anySevere) {
            return new DiagnosisResult(
                DiagnosisResult.Risk.HIGH,
                "Severe / Complicated Malaria Suspected",
                "Patient exhibits high body temperature combined with one or more severe symptoms (neurological, renal, respiratory, or hepatic distress indicators). Severe malaria is caused by Plasmodium falciparum and is a life-threatening medical emergency.",
                "IMMEDIATE Action Required:\n1. Transport the patient to a hospital emergency department immediately.\n2. Intravenous antimalarial therapy (such as Artesunate) is typically required under supervision.\n3. Do not attempt home treatment or self-medication."
            );
        }

        // Rule 2: Uncomplicated Malaria
        if (fever && (patient.isRecentTravel() || mildSymptomCount >= 2)) {
            String explanation = "Patient has a fever and " +
                (patient.isRecentTravel() ? "has recently traveled to a malaria-endemic region." : "exhibits multiple classic malaria symptoms.");
            return new DiagnosisResult(
                DiagnosisResult.Risk.MODERATE,
                "Uncomplicated Malaria Suspected",
                explanation + " Uncomplicated malaria is characterized by fever, chills, headaches, and joint pain, without organ dysfunction indicators.",
                "Recommended Action:\n1. Consult a medical doctor immediately for a confirmatory blood test (Microscopy or Rapid Diagnostic Test - RDT).\n2. If positive, complete a course of Artemisinin-based Combination Therapy (ACT) as prescribed by a healthcare provider.\n3. Rest, hydrate, and monitor for any severe symptoms."
            );
        }

        // Rule 3: Low probability of malaria
        if (!fever && mildSymptomCount < 2 && !patient.isRecentTravel()) {
            return new DiagnosisResult(
                DiagnosisResult.Risk.LOW,
                "Low Probability of Malaria",
                "Patient does not present with a fever, has not recently traveled to an endemic area, and exhibits very few or no characteristic symptoms of malaria.",
                "Recommended Action:\n1. Monitor body temperature and symptoms over the next 24-48 hours.\n2. Treat mild symptoms (e.g., headache) with standard over-the-counter medication if appropriate.\n3. Consult a physician if symptoms worsen or if a fever develops."
            );
        }

        // Rule 4: Differential diagnosis (Fever with other infection signs, no travel, few malaria symptoms)
        if (fever && patient.hasFluSymptoms() && !patient.isRecentTravel() && mildSymptomCount < 2) {
            return new DiagnosisResult(
                DiagnosisResult.Risk.LOW,
                "Other Infection Suspected (e.g. Common Cold, Flu)",
                "Patient presents with fever and respiratory symptoms (cough, runny nose, sore throat) typical of common viral respiratory infections, with no travel history to malaria areas and minimal malaria indicators.",
                "Recommended Action:\n1. Manage fever and stay hydrated.\n2. Rest and isolate if infectious symptoms persist.\n3. Visit a doctor if symptoms persist past 3-5 days or if breathing difficulties develop."
            );
        }

        // Fallback Rule (e.g., fever but no travel and only 1 mild symptom)
        if (fever) {
            return new DiagnosisResult(
                DiagnosisResult.Risk.MODERATE,
                "Fever of Undetermined Origin / Possible Early Malaria",
                "Patient presents with fever but has a borderline clinical profile for malaria (no travel history and limited secondary symptoms). Early-stage malaria cannot be fully ruled out.",
                "Recommended Action:\n1. Consult a physician to run diagnostic tests to determine the cause of the fever.\n2. Monitor for chills, sweating, or headaches.\n3. Take paracetamol for fever reduction, but avoid taking antimalarials before a lab test."
            );
        }

        // Catch-all
        return new DiagnosisResult(
            DiagnosisResult.Risk.LOW,
            "Malaria Unlikely - Symptom Review Suggests Monitoring",
            "The combination of symptoms does not align with active malaria. Current clinical presentation suggests a low probability of infection.",
            "Recommended Action:\n1. Monitor for changes in health.\n2. Consult a primary care physician if general malaise or fatigue persists."
        );
    }

    private boolean hasFever(PatientData patient) {
        return patient.getTemperature() >= 38.0;
    }

    private boolean hasAnySevereSymptom(PatientData patient) {
        return patient.hasConfusion() ||
               patient.hasConvulsions() ||
               patient.hasBreathingDifficulty() ||
               patient.hasJaundice() ||
               patient.hasExtremeWeakness() ||
               patient.hasDarkUrine();
    }

    private int countMildSymptoms(PatientData patient) {
        int count = 0;
        if (patient.hasChills()) count++;
        if (patient.hasHeadache()) count++;
        if (patient.hasNauseaVomiting()) count++;
        if (patient.hasMuscleJointPain()) count++;
        if (patient.hasFatigue()) count++;
        return count;
    }
}

/**
 * JavaFX application orchestrator containing the wizard screens and user interface flow.
 */
class Launcher extends Application {

    private final PatientData patient = new PatientData();
    private final MalariaInferenceEngine engine = new MalariaInferenceEngine();

    private Stage primaryStage;
    private BorderPane mainLayout;
    private ProgressBar progressBar;
    private Label stepTitleLabel;

    private int currentStep = 1;
    private final int totalSteps = 5;

    // UI Nodes for referencing inputs
    private TextField nameField;
    private TextField ageField;
    private ToggleGroup genderGroup;
    private CheckBox travelCheckBox;
    private TextField tempField;

    // Checkboxes for Mild Symptoms
    private CheckBox chillsBox;
    private CheckBox headacheBox;
    private CheckBox nauseaBox;
    private CheckBox jointPainBox;
    private CheckBox fatigueBox;

    // Checkboxes for Severe Symptoms
    private CheckBox confusionBox;
    private CheckBox convulsionsBox;
    private CheckBox breathingBox;
    private CheckBox jaundiceBox;
    private CheckBox weaknessBox;
    private CheckBox darkUrineBox;
    private CheckBox fluBox;

    // Navigation buttons
    private Button btnBack;
    private Button btnNext;

    /**
     * Entry method invoked by the JavaFX runtime environment.
     *
     * @param primaryStage Primary stage.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Group 7 - Malaria Expert System");

        // Main Layout Structure
        mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("root-layout");

        // Create Top Header
        VBox topHeader = createHeader();
        mainLayout.setTop(topHeader);

        // Create Bottom Navigation
        HBox bottomNav = createNavigation();
        mainLayout.setBottom(bottomNav);

        // Show Step 1 Initially
        showStep(1);

        Scene scene = new Scene(mainLayout, 650, 550);
        
        // Load external stylesheet
        try {
            String cssPath = getClass().getResource("style.css").toExternalForm();
            scene.getStylesheets().add(cssPath);
        } catch (Exception e) {
            System.err.println("Could not load style.css. Falling back to default JavaFX styling. Details: " + e.getMessage());
        }

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Builds the top navigation header containing title and progress bar.
     *
     * @return VBox layout container for the header.
     */
    private VBox createHeader() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(25, 25, 10, 25));
        container.setAlignment(Pos.CENTER_LEFT);

        Label appTitle = new Label("MALARIA CLINICAL EXPERT SYSTEM");
        appTitle.getStyleClass().add("app-title");

        stepTitleLabel = new Label("Step 1: Patient Information");
        stepTitleLabel.getStyleClass().add("step-title");

        progressBar = new ProgressBar(0.2);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressBar.getStyleClass().add("progress-bar");

        container.getChildren().addAll(appTitle, stepTitleLabel, progressBar);
        return container;
    }

    /**
     * Builds the bottom navigation bar with back/next controls.
     *
     * @return HBox layout container for navigation buttons.
     */
    private HBox createNavigation() {
        HBox navBar = new HBox();
        navBar.setPadding(new Insets(15, 25, 25, 25));
        navBar.setAlignment(Pos.CENTER_RIGHT);

        btnBack = new Button("Back");
        btnBack.getStyleClass().add("btn-secondary");
        btnBack.setDisable(true); // initially disabled on step 1
        btnBack.setOnAction(e -> navigateBack());

        btnNext = new Button("Next");
        btnNext.getStyleClass().add("btn-primary");
        btnNext.setOnAction(e -> navigateNext());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        navBar.getChildren().addAll(btnBack, spacer, btnNext);
        return navBar;
    }

    /**
     * Handles displaying the screen matching the step number.
     *
     * @param step Step number to display.
     */
    private void showStep(int step) {
        currentStep = step;
        double progress = (double) currentStep / totalSteps;
        progressBar.setProgress(progress);

        switch (step) {
            case 1:
                stepTitleLabel.setText("Step 1: Patient Demographics");
                mainLayout.setCenter(createStep1Demographics());
                btnBack.setDisable(true);
                btnNext.setText("Next");
                break;
            case 2:
                stepTitleLabel.setText("Step 2: Risk Factors & Temperature");
                mainLayout.setCenter(createStep2Vitals());
                btnBack.setDisable(false);
                btnNext.setText("Next");
                break;
            case 3:
                stepTitleLabel.setText("Step 3: Mild Symptoms Checklist");
                mainLayout.setCenter(createStep3MildSymptoms());
                btnBack.setDisable(false);
                btnNext.setText("Next");
                break;
            case 4:
                stepTitleLabel.setText("Step 4: Severe Symptoms & Differential Indicators");
                mainLayout.setCenter(createStep4SevereSymptoms());
                btnBack.setDisable(false);
                btnNext.setText("Run Diagnostic");
                break;
            case 5:
                stepTitleLabel.setText("Step 5: Diagnostic Inference Result");
                mainLayout.setCenter(createStep5Results());
                btnBack.setDisable(true);
                btnNext.setText("Start New Analysis");
                break;
            default:
                break;
        }
    }

    /**
     * Navigates to the next page, validating current inputs beforehand.
     */
    private void navigateNext() {
        try {
            if (currentStep == 1) {
                // Save and validate Step 1
                patient.setName(nameField.getText());
                
                String ageStr = ageField.getText();
                if (ageStr == null || ageStr.trim().isEmpty()) {
                    throw new ValidationException("Age cannot be empty.");
                }
                
                int ageVal;
                try {
                    ageVal = Integer.parseInt(ageStr.trim());
                } catch (NumberFormatException e) {
                    throw new ValidationException("Age must be a valid whole number.");
                }
                patient.setAge(ageVal);

                RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
                patient.setGender(selectedGender != null ? selectedGender.getText() : "");
                
                patient.validateDemographics();
                showStep(2);
            } else if (currentStep == 2) {
                // Save and validate Step 2
                patient.setRecentTravel(travelCheckBox.isSelected());
                
                String tempStr = tempField.getText();
                if (tempStr == null || tempStr.trim().isEmpty()) {
                    throw new ValidationException("Temperature cannot be empty.");
                }

                double tempVal;
                try {
                    tempVal = Double.parseDouble(tempStr.trim());
                } catch (NumberFormatException e) {
                    throw new ValidationException("Temperature must be a valid decimal number (e.g. 37.5).");
                }
                patient.setTemperature(tempVal);
                
                patient.validateTemperature();
                showStep(3);
            } else if (currentStep == 3) {
                // Save Step 3
                patient.setHasChills(chillsBox.isSelected());
                patient.setHasHeadache(headacheBox.isSelected());
                patient.setHasNauseaVomiting(nauseaBox.isSelected());
                patient.setHasMuscleJointPain(jointPainBox.isSelected());
                patient.setHasFatigue(fatigueBox.isSelected());
                showStep(4);
            } else if (currentStep == 4) {
                // Save Step 4 and run inference
                patient.setHasConfusion(confusionBox.isSelected());
                patient.setHasConvulsions(convulsionsBox.isSelected());
                patient.setHasBreathingDifficulty(breathingBox.isSelected());
                patient.setHasJaundice(jaundiceBox.isSelected());
                patient.setHasExtremeWeakness(weaknessBox.isSelected());
                patient.setHasDarkUrine(darkUrineBox.isSelected());
                patient.setHasFluSymptoms(fluBox.isSelected());
                
                showStep(5);
            } else if (currentStep == 5) {
                // Reset patient data and go to Step 1
                resetSystem();
                showStep(1);
            }
        } catch (ValidationException ex) {
            showAlert("Input Error", ex.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception ex) {
            showAlert("Unexpected Error", "An unexpected system error occurred: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Resets patient model and inputs back to default state.
     */
    private void resetSystem() {
        patient.setName("");
        patient.setAge(-1);
        patient.setGender("");
        patient.setRecentTravel(false);
        patient.setTemperature(37.0);
        patient.setHasChills(false);
        patient.setHasHeadache(false);
        patient.setHasNauseaVomiting(false);
        patient.setHasMuscleJointPain(false);
        patient.setHasFatigue(false);
        patient.setHasConfusion(false);
        patient.setHasConvulsions(false);
        patient.setHasBreathingDifficulty(false);
        patient.setHasJaundice(false);
        patient.setHasExtremeWeakness(false);
        patient.setHasDarkUrine(false);
        patient.setHasFluSymptoms(false);
    }

    /**
     * Navigates back one step in the wizard.
     */
    private void navigateBack() {
        if (currentStep > 1) {
            showStep(currentStep - 1);
        }
    }

    /**
     * Displays a customized GUI dialog message alert.
     */
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        // Try styling the dialog pane slightly using custom styles
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStyleClass().add("dialog-pane");
        try {
            String cssPath = getClass().getResource("style.css").toExternalForm();
            dialogPane.getStylesheets().add(cssPath);
        } catch (Exception e) {
            // ignore stylesheet load fail for alert popup
        }

        alert.showAndWait();
    }

    // --- Screen Generators ---

    /**
     * Screen 1 UI container: Patient demographic information.
     */
    private ScrollPane createStep1Demographics() {
        VBox mainBox = new VBox(20);
        mainBox.setPadding(new Insets(20, 25, 20, 25));
        mainBox.getStyleClass().add("step-card");

        Label introLabel = new Label("Welcome to the Malaria Diagnostic Expert System. Please enter the patient's information to begin.");
        introLabel.setWrapText(true);
        introLabel.getStyleClass().add("intro-text");

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER_LEFT);

        Label lblName = new Label("Full Name:");
        lblName.getStyleClass().add("form-label");
        nameField = new TextField(patient.getName());
        nameField.setPromptText("Enter patient name");
        nameField.getStyleClass().add("text-field");

        Label lblAge = new Label("Age (Years):");
        lblAge.getStyleClass().add("form-label");
        ageField = new TextField(patient.getAge() >= 0 ? String.valueOf(patient.getAge()) : "");
        ageField.setPromptText("Enter patient age");
        ageField.getStyleClass().add("text-field");

        Label lblGender = new Label("Gender:");
        lblGender.getStyleClass().add("form-label");

        HBox genderBox = new HBox(15);
        genderGroup = new ToggleGroup();

        RadioButton rbMale = new RadioButton("Male");
        rbMale.setToggleGroup(genderGroup);
        rbMale.getStyleClass().add("radio-button");

        RadioButton rbFemale = new RadioButton("Female");
        rbFemale.setToggleGroup(genderGroup);
        rbFemale.getStyleClass().add("radio-button");

        RadioButton rbOther = new RadioButton("Other");
        rbOther.setToggleGroup(genderGroup);
        rbOther.getStyleClass().add("radio-button");

        if ("Male".equals(patient.getGender())) {
            rbMale.setSelected(true);
        } else if ("Female".equals(patient.getGender())) {
            rbFemale.setSelected(true);
        } else if ("Other".equals(patient.getGender())) {
            rbOther.setSelected(true);
        } else {
            rbMale.setSelected(true); // default option
        }

        genderBox.getChildren().addAll(rbMale, rbFemale, rbOther);

        grid.add(lblName, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(lblAge, 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(lblGender, 0, 2);
        grid.add(genderBox, 1, 2);

        // Styling layout widths
        ColumnConstraints col1 = new ColumnConstraints(120);
        ColumnConstraints col2 = new ColumnConstraints(350);
        grid.getColumnConstraints().addAll(col1, col2);

        mainBox.getChildren().addAll(introLabel, grid);
        return wrapInScrollPane(mainBox);
    }

    /**
     * Screen 2 UI container: Risk Factors (Travel) and Vitals (Body Temperature).
     */
    private ScrollPane createStep2Vitals() {
        VBox mainBox = new VBox(20);
        mainBox.setPadding(new Insets(20, 25, 20, 25));
        mainBox.getStyleClass().add("step-card");

        Label sectionLabel = new Label("Enter critical epidemiological risks and patient vitals.");
        sectionLabel.getStyleClass().add("section-header");

        // Travel Checkbox Row
        VBox travelBox = new VBox(8);
        Label travelLabel = new Label("1. Epidemiological Travel History:");
        travelLabel.getStyleClass().add("form-label");
        travelCheckBox = new CheckBox("Patient has traveled to a malaria-endemic region in the last 12 months.");
        travelCheckBox.setSelected(patient.isRecentTravel());
        travelCheckBox.setWrapText(true);
        travelCheckBox.getStyleClass().add("checkbox");
        travelBox.getChildren().addAll(travelLabel, travelCheckBox);

        // Temperature Input Row
        VBox tempBox = new VBox(8);
        Label tempLabel = new Label("2. Measured Body Temperature (°C):");
        tempLabel.getStyleClass().add("form-label");

        HBox tempInputRow = new HBox(10);
        tempInputRow.setAlignment(Pos.CENTER_LEFT);
        tempField = new TextField(String.valueOf(patient.getTemperature()));
        tempField.setPrefWidth(120);
        tempField.getStyleClass().add("text-field");

        Label tempGuide = new Label("Normal is ~37.0°C. Fever is diagnosed at >=38.0°C.");
        tempGuide.getStyleClass().add("field-guide");

        tempInputRow.getChildren().addAll(tempField, tempGuide);
        tempBox.getChildren().addAll(tempLabel, tempInputRow);

        mainBox.getChildren().addAll(sectionLabel, travelBox, tempBox);
        return wrapInScrollPane(mainBox);
    }

    /**
     * Screen 3 UI container: Mild symptoms list.
     */
    private ScrollPane createStep3MildSymptoms() {
        VBox mainBox = new VBox(15);
        mainBox.setPadding(new Insets(20, 25, 20, 25));
        mainBox.getStyleClass().add("step-card");

        Label title = new Label("Check all mild/general symptoms currently reported by the patient:");
        title.getStyleClass().add("form-label");
        title.setWrapText(true);

        chillsBox = new CheckBox("Chills and Rigors (Sudden shivering and feeling very cold)");
        chillsBox.setSelected(patient.hasChills());
        chillsBox.getStyleClass().add("checkbox");

        headacheBox = new CheckBox("Severe Headache");
        headacheBox.setSelected(patient.hasHeadache());
        headacheBox.getStyleClass().add("checkbox");

        nauseaBox = new CheckBox("Nausea, Vomiting, or Diarrhea");
        nauseaBox.setSelected(patient.hasNauseaVomiting());
        nauseaBox.getStyleClass().add("checkbox");

        jointPainBox = new CheckBox("Muscle and Joint Aches / Body Pains");
        jointPainBox.setSelected(patient.hasMuscleJointPain());
        jointPainBox.getStyleClass().add("checkbox");

        fatigueBox = new CheckBox("General Fatigue, Muscle Weakness, and Malaise");
        fatigueBox.setSelected(patient.hasFatigue());
        fatigueBox.getStyleClass().add("checkbox");

        mainBox.getChildren().addAll(title, chillsBox, headacheBox, nauseaBox, jointPainBox, fatigueBox);
        return wrapInScrollPane(mainBox);
    }

    /**
     * Screen 4 UI container: Severe symptoms (red warnings) and alternative diagnosis flags.
     */
    private ScrollPane createStep4SevereSymptoms() {
        VBox mainBox = new VBox(12);
        mainBox.setPadding(new Insets(20, 25, 20, 25));
        mainBox.getStyleClass().add("step-card");

        Label warningLabel = new Label("WARNING: Select any critical symptoms that indicate complicated/severe systemic failure:");
        warningLabel.getStyleClass().add("warning-header");
        warningLabel.setWrapText(true);

        confusionBox = new CheckBox("Confusion, Disorientation, or Extreme Sleepiness");
        confusionBox.setSelected(patient.hasConfusion());
        confusionBox.getStyleClass().add("checkbox-severe");

        convulsionsBox = new CheckBox("Convulsions, Fits, or Multiple Seizures");
        convulsionsBox.setSelected(patient.hasConvulsions());
        convulsionsBox.getStyleClass().add("checkbox-severe");

        breathingBox = new CheckBox("Severe Difficulty Breathing (Fast, labored, or gasping)");
        breathingBox.setSelected(patient.hasBreathingDifficulty());
        breathingBox.getStyleClass().add("checkbox-severe");

        jaundiceBox = new CheckBox("Jaundice (Yellow coloration of the eyes or skin)");
        jaundiceBox.setSelected(patient.hasJaundice());
        jaundiceBox.getStyleClass().add("checkbox-severe");

        weaknessBox = new CheckBox("Extreme Weakness (Patient is unable to stand, sit, or walk)");
        weaknessBox.setSelected(patient.hasExtremeWeakness());
        weaknessBox.getStyleClass().add("checkbox-severe");

        darkUrineBox = new CheckBox("Hemoglobinuria (Very dark, brownish, or cola-colored urine)");
        darkUrineBox.setSelected(patient.hasDarkUrine());
        darkUrineBox.getStyleClass().add("checkbox-severe");

        Separator separator = new Separator();
        separator.setPadding(new Insets(10, 0, 5, 0));

        Label diffLabel = new Label("Differential Diagnosis Checklist:");
        diffLabel.getStyleClass().add("form-label");

        fluBox = new CheckBox("Patient has respiratory symptoms (Sore throat, runny nose, or cough)");
        fluBox.setSelected(patient.hasFluSymptoms());
        fluBox.getStyleClass().add("checkbox");

        mainBox.getChildren().addAll(
            warningLabel, confusionBox, convulsionsBox, breathingBox, 
            jaundiceBox, weaknessBox, darkUrineBox, separator, diffLabel, fluBox
        );
        return wrapInScrollPane(mainBox);
    }

    /**
     * Screen 5 UI container: Formats and prints the expert system inference diagnosis outcome.
     */
    private ScrollPane createStep5Results() {
        VBox mainBox = new VBox(20);
        mainBox.setPadding(new Insets(20, 25, 20, 25));
        mainBox.getStyleClass().add("step-card");

        // Run inference
        DiagnosisResult result = engine.infer(patient);

        // Render diagnosis card
        VBox resultCard = new VBox(15);
        resultCard.setPadding(new Insets(20));
        
        // Choose class depending on risk level
        String riskClass = "result-card-low";
        if (result.getRisk() == DiagnosisResult.Risk.HIGH) {
            riskClass = "result-card-high";
        } else if (result.getRisk() == DiagnosisResult.Risk.MODERATE) {
            riskClass = "result-card-med";
        }
        resultCard.getStyleClass().addAll("result-card", riskClass);

        Label patientSummary = new Label(
            "Patient: " + patient.getName() + " | Age: " + patient.getAge() + " | Gender: " + patient.getGender() +
            "\nRecorded Temp: " + patient.getTemperature() + "°C | Travel: " + (patient.isRecentTravel() ? "Yes" : "No")
        );
        patientSummary.getStyleClass().add("result-patient-summary");

        Label diagnosisLabel = new Label(result.getTitle());
        diagnosisLabel.getStyleClass().add("result-diagnosis-title");

        Label riskBadge = new Label("RISK ASSESSMENT: " + result.getRisk().name());
        riskBadge.getStyleClass().add("result-risk-badge");

        VBox.setVgrow(patientSummary, Priority.NEVER);
        VBox.setVgrow(diagnosisLabel, Priority.NEVER);

        resultCard.getChildren().addAll(patientSummary, diagnosisLabel, riskBadge);

        // Narrative details
        Label explanationHeader = new Label("Clinical Explanation:");
        explanationHeader.getStyleClass().add("form-label");
        Label explanationText = new Label(result.getExplanation());
        explanationText.getStyleClass().add("explanation-text");
        explanationText.setWrapText(true);

        Label recommendationHeader = new Label("Recommended Action Plan:");
        recommendationHeader.getStyleClass().add("form-label");
        Label recommendationText = new Label(result.getAdvice());
        recommendationText.getStyleClass().add("advice-text");
        recommendationText.setWrapText(true);

        mainBox.getChildren().addAll(resultCard, explanationHeader, explanationText, recommendationHeader, recommendationText);
        return wrapInScrollPane(mainBox);
    }

    /**
     * Wrap helper to add standard scroll bars to UI containers.
     */
    private ScrollPane wrapInScrollPane(VBox content) {
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.getStyleClass().add("scroll-pane-card");
        return scrollPane;
    }

    /**
     * Standard entry method invoked when Java launches.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
