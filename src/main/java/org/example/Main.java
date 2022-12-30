package org.example;

import java.io.*;

public class Main {

    private static String rootPath = "./tests/1";

    public static void main(String[] args) {
        rootPath = new File(rootPath).getAbsolutePath();
        setConsoleEncoding("utf-8");
        Utils utils = new Utils();
        utils.readFiles(rootPath);
        Graph graph = new Graph();
        if(!utils.writeDependencies(graph, rootPath)){
            return;
        }

        Concatenator c = new Concatenator(graph, utils.getFiles());
        System.out.println(c.concat());
    }

    public static void setConsoleEncoding(String encoding) {
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, encoding));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding: " + encoding);
        }
    }
}