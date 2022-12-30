package org.example;

import java.io.*;

public class Main {

    private static String ROOT_PATH = "./tests/";

    public static void main(String[] args) {
        ROOT_PATH = new File(ROOT_PATH).getAbsolutePath();
        setConsoleEncoding("utf-8");
        Utils utils = new Utils();
        utils.readFiles(ROOT_PATH);
        Graph graph = new Graph();
        if(!utils.writeDependencies(graph, ROOT_PATH)){
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