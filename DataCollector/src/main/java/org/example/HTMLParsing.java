package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLParsing {
    static Document doc;
    static StringBuilder lines = new StringBuilder();
    static StringBuilder stations = new StringBuilder();

    public static void parseHTML(String url) {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static StringBuilder parseStations() {
        Elements elementsStations;
        elementsStations = doc.select("p.single-station");
        for (Element station : elementsStations) {
            Stations allStations = new Stations();
            allStations.setNameOfStations(station.select("span.name").text());
            allStations.setNumberOfLines(station.parent().attr("data-line"));
            stations.append(allStations);

        }
        return stations;
    }


    public static StringBuilder parseLines() {
        Elements elementsLines;
        elementsLines = doc.select("span.js-metro-line");
        for (Element line : elementsLines) {
            Lines allLines = new Lines();
            allLines.setNameOfLine(line.select("span.js-metro-line").text());
            allLines.setNumberOfLine(line.attr("data-line"));
            lines.append(allLines);
        }
        return lines;
    }
}





