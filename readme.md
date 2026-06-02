# Malaria Clinical Expert System (Group 7)

A JavaFX-based clinical decision support system (expert system) that prompts users with questions about demographics, epidemiological risk factors (travel), and clinical symptoms, and then uses a rule-based inference engine to evaluate the likelihood and severity of malaria.

---

## 📋 Table of Contents
1. [Features](#-features)
2. [Expert System Rules](#-expert-system-rules)
3. [Project Directory Structure](#-project-directory-structure)
4. [Prerequisites](#-prerequisites)
5. [How to Compile and Run](#-how-to-compile-and-run)
6. [Generating Javadoc](#-generating-javadoc)
7. [How to Push to GitHub](#-how-to-push-to-github)
8. [Clinical Disclaimer](#-clinical-disclaimer)

---

## ✨ Features
* **Modern GUI Design:** Slate-dark aesthetic utilizing custom typography, gradient highlights, and responsive UI components.
* **Step-by-step Wizard Layout:** Guides the user through a 5-step structured diagnostic questionnaire with an active progress bar.
* **Robust Input Validation:** Standard and custom exceptions handle improper entries (e.g., negative ages, impossible body temperatures, empty text fields) and show alert dialogs.
* **Invariant Asserts:** Code uses Java assertions (`assert`) to enforce core clinical model consistency.
* **Completely Self-contained:** Bundled JavaFX 21 JAR files included in the `lib` folder so that it compiles and runs without setting external environment paths.

---

## 🧠 Expert System Rules

The inference logic evaluates symptoms and risk profiles under these categories:

### 1. Severe / Complicated Malaria (High Risk)
* **IF** Patient has a fever (measured body temperature $\ge 38.0^\circ\text{C}$ or reported high temperature) **AND** has *at least one* severe clinical indicator:
  * Confusion / Disorientation
  * Convulsions / Fits
  * Severe Breathing Difficulty
  * Jaundice (Yellow eyes/skin)
  * Extreme Weakness (Inability to stand/sit)
  * Dark/Cola-colored Urine
* **THEN** Classify as **HIGH RISK** and recommend immediate emergency hospitalization.

### 2. Uncomplicated Malaria (Moderate Risk)
* **IF** Patient has a fever ($\ge 38.0^\circ\text{C}$) **AND** either:
  * Traveled to a malaria-endemic region in the last 12 months, **OR**
  * Has *at least two* mild malaria symptoms (Chills, Headache, Nausea/Vomiting, Muscle/Joint Pain, Fatigue)
* **THEN** Classify as **MODERATE RISK** and recommend clinical microscopy/RDT tests.

### 3. Differential Diagnosis (Other Infection Suspected)
* **IF** Patient has a fever ($\ge 38.0^\circ\text{C}$) **AND** respiratory symptoms (cough, runny nose, sore throat) **AND** did not travel to an endemic region **AND** has fewer than two mild malaria symptoms.
* **THEN** Classify as **LOW RISK** and recommend monitoring for a cold/flu.

### 4. Low Probability of Malaria
* **IF** Patient has no fever **AND** less than two mild symptoms **AND** no recent travel history.
* **THEN** Classify as **LOW RISK** and recommend general monitoring.

---

## 📁 Project Directory Structure

```
Malaria expert system/
├── bin/                       # Compiled Java bytecode (.class) and assets
├── docs/                      # Generated HTML Javadoc documentation
├── lib/                       # Bundled JavaFX 21 library JARs
├── src/
│   ├── App.java               # Unified source file containing:
│   │                          #   - App & Launcher (JavaFX UI manager)
│   │                          #   - PatientData (Demographics & validation)
│   │                          #   - MalariaInferenceEngine (Diagnostic rules)
│   │                          #   - DiagnosisResult (Diagnostic outputs)
│   │                          #   - ValidationException (Custom exception)
│   └── style.css              # Custom styling definitions
├── .gitignore                 # Specifies intentionally untracked files
├── compile.bat                # Compiles code and copies assets
├── run.bat                    # Launches application with assertions enabled (-ea)
├── javadoc.bat                # Generates Javadoc specifications in docs/
└── readme.md                  # System manual and instructions
```

---

## 🛠️ Prerequisites
* **Java Development Kit (JDK) 21** or higher.
* To check your version, open terminal and type:
  ```powershell
  java -version
  javac -version
  ```

---

## 🚀 How to Compile and Run

We have included batch files to automate compiling and running:

1. **Compile the App:**
   Double-click `compile.bat` or run in terminal:
   ```powershell
   .\compile.bat
   ```
   *This compiles `App.java` and copies `style.css` into the build path.*

2. **Execute the App:**
   Double-click `run.bat` or run in terminal:
   ```powershell
   .\run.bat
   ```
   *This runs the app with Java assertions active.*

---

## 📖 Generating Javadoc

Requirement (d) mandates Javadoc specifications. A script is provided to automate this:

1. Double-click `javadoc.bat` or run in terminal:
   ```powershell
   .\javadoc.bat
   ```
2. Once complete, open `docs/index.html` in any web browser to view the generated Javadoc.

---

## 📤 How to Push to GitHub

To submit your project to your class representative before the deadline:

1. **Initialize Git in your project folder:**
   ```powershell
   git init
   ```

2. **Add all files to stage:**
   ```powershell
   git add .
   ```

3. **Commit files:**
   ```powershell
   git commit -m "Initial commit: Malaria Expert System JavaFX Application by Group 7"
   ```

4. **Create a new, empty repository on GitHub** (e.g., named `malaria-expert-system`).

5. **Link your local repository to GitHub:**
   Replace the URL below with your actual GitHub repository URL:
   ```powershell
   git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
   ```

6. **Push code to GitHub:**
   ```powershell
   git branch -M main
   git push -u origin main
   ```

7. Copy the repository link and send it to your class representative!

---

## ⚠️ Clinical Disclaimer
This application is a **decision support tool** for academic demonstration purposes only. It is **not** a substitute for professional medical advice, diagnosis, or treatment. Always seek the advice of a qualified healthcare professional with any questions regarding a medical condition.
