package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVParsing {
    static StringBuilder nameOfStationsAndDate = new StringBuilder();

    public static void CSVParsing(String path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n" + "Парсинг CSV данные по станциям");
        for(String line : lines) {
            String[] fragments = line.split(",");
            HashMap<String, String> info = new HashMap();
            String stationsName = fragments[0];
            String stationsDate = fragments[1];
            info.put(stationsName, stationsDate);
            info.remove("name");
            for (Map.Entry<String, String> stations : info.entrySet()) {
                nameOfStationsAndDate.append("Name of stations: " + stations.getKey() + " " + "Date: " + stations.getValue() + "\n");
//                System.out.println("Станция: " + stations.getKey() + " - " + "Дата: " + stations.getValue());
            }
            }
//        System.out.println(nameOfStationsAndDate);
    }
    }

