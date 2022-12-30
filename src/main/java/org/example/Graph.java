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
        boolean isCycled = false;
        color[vertex] = 1;
        if (dependencies.containsKey(vertex)) {
            for (var finish : dependencies.get(vertex)) {
                if (color[finish] == 0) {
                    parent.set(finish, vertex);
                    isCycled = dfs(finish, color, cycle) || isCycled;
                } else if (color[finish] == 1) {
                    cycle.clear();
                    int current = vertex;
                    cycle.add(current);
                    current = parent.get(current);
                    cycle.add(current);
                    while (current != finish) {
                        if (current == -1) {
                            return true;
                        }
                        current = parent.get(current);
                        cycle.add(current);
                    }
                    Collections.reverse(cycle);
                    return true;
                }
            }
        }
        color[vertex] = 2;
        return isCycled;
    }

    public ArrayList<Integer> searchCycle() {
        Integer[] color = new Integer[vertexNum];
        Arrays.fill(color, 0);
        for (int vertex = 0; vertex < vertexNum; vertex++) {
            if (color[vertex] == 0) {
                var cycle = new ArrayList<Integer>();
                if (dfs(vertex, color, cycle)) {
                    return cycle;
                }
            }
        }
        return new ArrayList<>();
    }

    private void sortDFS(int vertex, Integer[] color, ArrayList<Integer> topologicalSortOrder) {
        color[vertex] = 1;
        if (dependencies.containsKey(vertex)) {
            for (var to : dependencies.get(vertex)) {
                if (color[to] == 0) {
                    sortDFS(to, color, topologicalSortOrder);
                }
            }
        }
        color[vertex] = 2;
        topologicalSortOrder.add(vertex);
    }

    public ArrayList<Integer> getSortedList() {
        Integer[] color = new Integer[vertexNum];
        Arrays.fill(color, 0);
        var sortedList = new ArrayList<Integer>();
        for (int v = 0; v < vertexNum; v++) {
            if (color[v] == 0 && incomingDegrees.get(v) == 0) {
                sortDFS(v, color, sortedList);
            }
        }
        Collections.reverse(sortedList);
        return sortedList;
    }
}