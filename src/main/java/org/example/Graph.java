package org.example;

import java.util.*;

public class Graph {
    private int vertexNum;
    private final HashMap<Integer, ArrayList<Integer>> dependencies;
    private final ArrayList<Integer> parent;
    private final ArrayList<Integer> incomingDegrees;


    public Graph() {
        vertexNum = 0;
        dependencies = new HashMap<>();
        incomingDegrees = new ArrayList<>();
        parent = new ArrayList<>();
    }

    public void addEdge(int start, int finish) {
        dependencies.get(start).add(finish);
        incomingDegrees.set(finish, incomingDegrees.get(finish) + 1);
    }

    public void addVertex() {
        dependencies.put(dependencies.size(), new ArrayList<>());
        incomingDegrees.add(0);
        parent.add(-1);
        vertexNum++;
    }

    private boolean dfs(int vertex, Integer[] color, ArrayList<Integer> cycle) {
        //TODO
        return false;
    }

    public ArrayList<Integer> searchCycle() {
    }

    private void sortDFS(int vertex, Integer[] color, ArrayList<Integer> topologicalSortOrder) {
        //TODO
    }

    public ArrayList<Integer> getSortedList() {
        //TODO
    }
}