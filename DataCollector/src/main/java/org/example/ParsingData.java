package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ParsingData {

    ArrayListMultimap<String, String> objectObjectArrayListMultimap = ArrayListMultimap.create();
    HashMap<String, String> dateOfLinesFromHTMLParsing = new HashMap<>();
    HashMap<String, String> dateOfStationsFromJSON = new HashMap<>();
    HashMap<String, String> dateOfLinesFromCSV = new HashMap<>();
    List<StationsForWrite> stationsForWrites = new ArrayList<>();


    public void entryInJson() throws IOException {
        comparison();
        dateFromCSV();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("DataCollector/src/main/java/Data/export.json"), stationsForWrites);
    }

    public void comparison() {
        stationsFromHTMLParsing();
        linesFromHTMLParsing();
        dateFromCSV();
        depthFromJSON();
        String valueDateFromCSV = "";
        String valueDepthFromJSON = "";
        String nameOfStations = "";
        String nameOfLine = "";
        Map<String, Collection<String>> map = objectObjectArrayListMultimap.asMap();
        for (Map.Entry<String, Collection<String>> mapOfStations : map.entrySet()) {
            Collection<String> lineNumbers = mapOfStations.getValue();   //достали номер ил мультимап
            boolean hasConnections = lineNumbers.size() > 1;
            for (String lineNumberForMap : lineNumbers) {
                nameOfStations = mapOfStations.getKey()
                        .replaceAll(",", "");
                valueDateFromCSV = dateOfLinesFromCSV.get(nameOfStations);
                valueDepthFromJSON = dateOfStationsFromJSON.get(nameOfStations);
                nameOfLine = dateOfLinesFromHTMLParsing.get(lineNumberForMap);// достали название линии по номеру линии
                StationsForWrite stationsForWrite = new StationsForWrite(nameOfStations, nameOfLine, valueDateFromCSV, valueDepthFromJSON, hasConnections);
                stationsForWrites.add(stationsForWrite);
            }
        }

    }


    public void stationsFromHTMLParsing() {
        String datesForNameOfStations = String.valueOf(HTMLParsing.parseStations());
        String[] nw = datesForNameOfStations.split("\n");
        String nameOfStations = "";
        String numberOfLines1 = "";
        for (String words : nw) {
            nameOfStations = words
                    .replaceAll("[B-Ca-cE-Ze-z:;]", "")
                    .replaceAll("[0-9D]{2}", "")
                    .replaceAll("[\\s]{3}", "")
                    .replaceAll("[0-9]{1}", "")
                    .replaceAll("\\s+$", "");
            numberOfLines1 = words
                    .replaceAll("[^0-9D]{2}", "")
                    .replaceAll("[;\\s]", "");
            objectObjectArrayListMultimap.put(nameOfStations, numberOfLines1);
        }
    }

    public void linesFromHTMLParsing() {
        String datesForNameOfLines = "";
        datesForNameOfLines = String.valueOf(HTMLParsing.parseLines());
        String[] nw = datesForNameOfLines.split("\n");
        String nameOfLine = "";
        String numberOfLines = "";
        for (String words : nw) {
            String name = words
                    .replaceAll("[A-Za-z,]", "")
                    .replaceAll("[\\s]{3}", "");
            if (name.length() == 9) {
                nameOfLine = name.substring(1, name.length() - 1);
            } else {
                nameOfLine = name.substring(1, name.length() - 2);
            }
            numberOfLines = words
                    .replaceAll("[^0-9DA]", "");
            if (numberOfLines.length() == 3) {
                numberOfLines = numberOfLines.substring(1, numberOfLines.length());
            }
            dateOfLinesFromHTMLParsing.put(numberOfLines, nameOfLine);
        }
    }

    public void dateFromCSV() {
        String dateOfStationConstruction = "";
        dateOfStationConstruction = String.valueOf(CSVParsing.nameOfStationsAndDate);
        String[] ne = dateOfStationConstruction.split("\n");
        String nameOfStationsFromCSV = "";
        String dateOfStations = "";
        for (String wordsFromNamesAndDate : ne) {
            nameOfStationsFromCSV = wordsFromNamesAndDate
                    .replaceAll("[A-Za-z.:]", "")
                    .replaceAll("[0-9]", "")
                    .replaceAll("[\\s]{2}", "")
                    .replaceAll("^\\s+", "");
            dateOfStations = wordsFromNamesAndDate
                    .replaceAll("[A-Za-z:А-Яа-я-ё]", "")
                    .replaceAll("[\\s]{5}", "");
            dateOfLinesFromCSV.put(nameOfStationsFromCSV, dateOfStations);
        }
    }

    public void depthFromJSON() {
        String dateOfStationsDepth = "";
        dateOfStationsDepth = String.valueOf(JSONParsing.nameOfStationsAndDepth);
        String[] mp = dateOfStationsDepth.split("\n");
        String nameOfStationsFromJson = "";
        String depthOfStations = "";
        for (String wordFromNameAndDepth : mp) {
            nameOfStationsFromJson = wordFromNameAndDepth
                    .replaceAll("[A-Za-z.:\",-]", "")
                    .replaceAll("[0-9]", "")
                    .replaceAll("[\\s]{2}", "")
                    .replaceAll("^\\s+|\\s+$", "");
            depthOfStations = wordFromNameAndDepth
                    .replaceAll("[A-Za-z:А-Яа-яё\"]", "")
                    .replaceAll("^\\s+|\\s+$", "");
            dateOfStationsFromJSON.put(nameOfStationsFromJson, depthOfStations);
        }
    }
}



