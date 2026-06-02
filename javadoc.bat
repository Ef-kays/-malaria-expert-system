@echo off
echo ==========================================
echo Generating Javadoc...
echo ==========================================

if not exist docs (
    mkdir docs
)

javadoc -private --module-path lib --add-modules javafx.controls,javafx.fxml -d docs -sourcepath src src\App.java

if %ERRORLEVEL% NEQ 0 (
    echo Javadoc generation failed!
    exit /b %ERRORLEVEL%
)

echo Javadoc generated successfully in the 'docs' directory.
