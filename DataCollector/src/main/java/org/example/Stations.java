package org.example;

import java.util.ArrayList;


public class Stations {
    String nameOfStations = "";
    String numberOfLines = "";
    public ArrayList<String> stations = new ArrayList<>();

    public void stationsMetro() {
        stations.add(nameOfStations);
        stations.add(numberOfLines);
    }


    public String getNameOfStations(String nameOfStations) {
        this.nameOfStations = nameOfStations;
        return nameOfStations;
    }

    @Override
    public String toString() {

        return "Name of stations: " + nameOfStations + ", number of lines: " + numberOfLines + ";" + "\n";
    }

    public void setNameOfStations(String nameOfStations) {
        this.nameOfStations = nameOfStations;
        stations.add(nameOfStations);

    }

    public ArrayList<String> getStations() {
        return stations;
    }

    public String getNumberOfLines(String numberOfLines) {
        this.numberOfLines = numberOfLines;
        return numberOfLines;
    }

    public void setNumberOfLines(String numberOfLines) {
        this.numberOfLines = numberOfLines;
        stations.add(numberOfLines);
    }
}
