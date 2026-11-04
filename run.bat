@echo off
chcp 65001 > nul
if not exist out mkdir out
javac -encoding UTF-8 -d out -sourcepath src src\arena\Main.java
if errorlevel 1 (
    echo.
    echo Erro na compilacao. Confira se o JDK 17 ou superior esta instalado.
    pause
    exit /b 1
)
java -cp out arena.Main
pause
