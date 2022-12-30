package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Concatenator {
    private final Graph fileGraph;
    private final List<File> files;

    public Concatenator(Graph fileGraph, List<File> files) {
        this.fileGraph = fileGraph;
        this.files = files;
    }

    public String concat() {
        List<Integer> cycle = fileGraph.searchCycle();
        if (!cycle.isEmpty()) {
            System.out.println("В файлах есть циклические зависимости.");
            return "";
        }

        StringBuilder result = new StringBuilder();
        //TODO сделать топологическую сортировку и конкатенацию файлов в порядке сортировки
        return result.toString();
    }

    private void concatFileToString(File file, StringBuilder result) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + file.getPath());
        }
    }
}
