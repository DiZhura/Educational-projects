package org.example;

import java.util.ArrayList;


public class Lines {
    String nameOfLine = "";
    String numberOfLine = "";
    public ArrayList<String> lines = new ArrayList<>();

    public Lines() {
        lines.add(nameOfLine);
        lines.add(numberOfLine);
    }

    @Override
    public String toString() {
        return "nameOfLine " + nameOfLine +
                ", numberOfLine " + numberOfLine + "\n";
    }

    public String getNumberOfLine() {
        return numberOfLine;
    }

    public String getNameOfLine() {
        return nameOfLine;
    }

    public void setNameOfLine(String nameOfLine) {
        this.nameOfLine = nameOfLine;
        lines.add(nameOfLine);
    }

    public void setNumberOfLine(String numberOfLine) {
        this.numberOfLine = numberOfLine;
        lines.add(numberOfLine);
    }
}
