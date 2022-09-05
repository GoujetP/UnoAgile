#!/bin/bash

javac -d UNO/bin/main UNO/src/main/*.java
java -cp UNO/bin/main UNO/src/main/Partie.java
