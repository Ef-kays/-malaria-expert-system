@echo off
echo ==========================================
echo Starting Malaria Expert System...
echo ==========================================

java -ea --module-path lib --add-modules javafx.controls,javafx.fxml -cp bin App
