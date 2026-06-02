@echo off
echo ==========================================
echo Compiling Malaria Expert System...
echo ==========================================

if not exist bin (
    mkdir bin
)

javac --module-path lib --add-modules javafx.controls,javafx.fxml -d bin src\App.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    exit /b %ERRORLEVEL%
)

echo Copying assets...
copy /Y src\style.css bin\ > nul

echo Compilation successful.
echo Run the app using run.bat
