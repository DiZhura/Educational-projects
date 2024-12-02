package org.example;

import java.util.ArrayList;

public class StationsForWrite {
    ArrayList<String> stationsForWrite = new ArrayList<>();
    private String name;
    private String line;
    private String date;
    private String depth;
    private boolean haConnections;

    public StationsForWrite(String name, String line, String date, String depth, boolean haConnections) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.haConnections = haConnections;
    }

    public void setName(String name) {
        stationsForWrite.add(name);
        this.name = name;
    }

    public void setLine(String line) {
        stationsForWrite.add(line);
        this.line = line;
    }

    public void setDate(String date) {
        stationsForWrite.add(date);
        this.date = date;
    }

    public void setDepth(String depth) {
        stationsForWrite.add(depth);
        this.depth = depth;
    }

    public void setHaConnections(boolean haConnections) {
        this.haConnections = haConnections;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public String getDate() {
        return date;
    }

    public String getDepth() {
        return depth;
    }

    public boolean isHaConnections() {
        return haConnections;
    }

    @Override
    public String toString() {
        return "StationsForWrite{" +
                "name='" + name + "\\n" +
                ", line='" + line + '\'' +
                ", date='" + date + '\'' +
                ", depth='" + depth + '\'' +
                ", haConnections=" + haConnections +
                '}';
    }
}
