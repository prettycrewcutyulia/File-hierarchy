package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Utils {
    private final List<File> files;
    private final Map<String, Integer> indexes;

    public Utils() {
        files = new ArrayList<>();
        indexes = new HashMap<>();
    }

    public void readFiles(String rootPath) {
        File rootFolder = new File(rootPath);
        if (!rootFolder.exists() || !rootFolder.isDirectory()) {
            System.out.println("Папка не существует или не является папкой");
            System.exit(0);
        }
        File[] rootFiles = rootFolder.listFiles();
        if (rootFiles != null) {
            for (File file : rootFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    readFiles(file.getAbsolutePath());
                }
            }
        }
    }

    public boolean writeDependencies(Graph fileGraph, String rootPath) {
        int index = 0;
        for (File file : files) {
            indexes.put(file.getPath(), index++);
            fileGraph.addVertex();
        }
        for (File file : files) {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String current = sc.nextLine();
                    if (current.startsWith("require '")) {
                        String requirePath = current.substring(9, current.length() - 1);
                        File dependedFile = new File(rootPath + File.separator + requirePath + ".txt");
                        if (indexes.containsKey(dependedFile.getPath())) {
                            fileGraph.addEdge(indexes.get(dependedFile.getPath()), indexes.get(file.getPath()));
                        }
                    }
                }
            } catch (IOException exc) {
                System.out.println("Не удаётся открыть файл " + file.getAbsolutePath());
            }
        }
        return true;
    }

    public List<File> getFiles() {
        return files;
    }
}