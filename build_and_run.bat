@echo off
REM --------------------------------------
REM Compile all Java files
REM --------------------------------------
echo Compiling Java files...
javac chess\*.java chess\pieces\*.java chess\player\*.java

IF %ERRORLEVEL% NEQ 0 (
    echo Compilation failed. Fix errors and try again.
    pause
    exit /b
)

REM --------------------------------------
REM Create manifest.txt automatically
REM --------------------------------------
echo Creating manifest.txt...
(
    echo Main-Class: chess.ConsoleChess
    echo.
) > manifest.txt

REM --------------------------------------
REM Build the JAR
REM --------------------------------------
echo Building ChessGame.jar...
jar cfm ChessGame.jar manifest.txt -C . chess

IF %ERRORLEVEL% NEQ 0 (
    echo Failed to create JAR.
    pause
    exit /b
)

REM --------------------------------------
REM Run the game
REM --------------------------------------
echo Running Chess game...
java -jar ChessGame.jar

pause
