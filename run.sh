#!/bin/sh
mkdir -p out
javac -encoding UTF-8 -d out -sourcepath src src/arena/Main.java
if [ $? -ne 0 ]; then
    echo "Erro na compilacao. Confira se o JDK 17 ou superior esta instalado."
    exit 1
fi
java -cp out arena.Main
