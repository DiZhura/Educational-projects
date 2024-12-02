package org.example;
import java.io.File;
import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException {
        HTMLParsing.parseHTML("https://skillbox-java.github.io/");
        System.out.println("Parsing HTML Данные по станциям " + "\n" + HTMLParsing.parseStations()); //Парсинг из HTML станций
        System.out.println("Parsing HTML Данные по линиям " + "\n" + HTMLParsing.parseLines()); //Парсинг из HTML линий
        File file = new File("src/main/java/Data"); // для парсинга из файлов
        SearchFile.search(file, ".json"); //поиски  json
        SearchFile.search(file, "csv"); //поиск csv
        JSONParsing.JSONParsing("src/main/java/Data/7/1/depths-2.json");//парсинг из json
        JSONParsing.JSONParsing("src/main/java/Data/4/6/depths-3.json");//парсинг из json
        JSONParsing.JSONParsing("src/main/java/Data/2/4/depths-1.json");//парсинг из json
        System.out.println(JSONParsing.nameOfStationsAndDepth); //парсинг из JSON
        CSVParsing.CSVParsing("src/main/java/Data/9/6/dates-3.csv");
        CSVParsing.CSVParsing("src/main/java/Data/0/5/dates-2.csv");
        CSVParsing.CSVParsing("src/main/java/Data/4/6/dates-1.csv");
        System.out.println(CSVParsing.nameOfStationsAndDate); //парсинг из csv
        ParsingData parsingData = new ParsingData();
        parsingData.entryInJson();
    }
}

