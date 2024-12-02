package org.example;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SearchFile extends SimpleFileVisitor<Path> {

    public static String search(File file, String searchName) {
        List<String> result = new ArrayList<>();
        if (file.isDirectory()) {
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp, searchName);
                    } else {
                        String fileName = temp.getName();
                        if (fileName.endsWith(searchName)) {
                            result.add(temp.getAbsoluteFile().toString());

                        }
                    }
                }
            } else {
                System.out.println(file.getAbsoluteFile() + "Доступ запрещен");
            }
        }
        for (String l : result) {
            System.out.println(l);
        }

        return searchName;
    }
}


