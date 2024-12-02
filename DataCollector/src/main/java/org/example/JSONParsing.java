package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public  class JSONParsing {
    static StringBuilder nameOfStationsAndDepth= new StringBuilder();

    public static void JSONParsing(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
            String jsonFile = Files.readString(Paths.get(path));
            JsonNode jsonData = mapper.readTree(jsonFile);
        System.out.println("\n" + "Парсинг JSON данные по станциям");
            for (JsonNode node : jsonData) {
                HashMap<String, String> info = new HashMap();
                String stationName = String.valueOf(node.get("station_name"));
                String stationDepth = String.valueOf(node.get("depth"));
                info.put(stationName, stationDepth);
                for (Map.Entry<String, String> stations : info.entrySet()) {
                    nameOfStationsAndDepth.append("Name of stations: " + stations.getKey() + " " + "Date: " + stations.getValue() + "\n");
//                    System.out.println("Станция: " + stations.getKey() + " - " + "глубина станции: " + stations.getValue());
                }
        }
        }
}







