package org.example;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {

    private static final String ROOT_PATH = "./tests/";

    public static void main(String[] args) {
        Utils utils = new Utils();
        utils.readFiles(ROOT_PATH);
        Graph graph = new Graph();
        if(!utils.writeDependencies(graph, ROOT_PATH)){
            return;
        }

        Concatenator c = new Concatenator(graph, utils.getFiles());
        System.out.println(c.concat());
    }
}