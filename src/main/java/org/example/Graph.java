package org.example;

import java.util.*;

public class Graph {
    private int vertexNum;
    private final HashMap<Integer, ArrayList<Integer>> dependencies;
    private final ArrayList<Integer> parent;
    private final ArrayList<Integer> incomingDegrees;

    /**
     * Конструктор для создания пустого графа
     */
    public Graph() {
        vertexNum = 0;
        dependencies = new HashMap<>();
        incomingDegrees = new ArrayList<>();
        parent = new ArrayList<>();
    }

    /**
     * Добавление ребра между двумя вершинами в графе
     * @param start начальная вершина ребра
     * @param finish конечная вершина ребра
     */
    public void addEdge(int start, int finish) {
        dependencies.get(start).add(finish);
        incomingDegrees.set(finish, incomingDegrees.get(finish) + 1);
    }

    /**
     * Добавление новой вершины в граф
     */
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

    /**
     * Поиск цикла в графе с помощью алгоритма поиска в глубину
     * @return список вершин, образующих цикл в графе (пустой список, если циклов нет)
     */
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

    /**
    * Рекурсивная функция для топологической сортировки графа
    * @param vertex текущая вершина
    * @param color массив цветов вершин
    * @param topologicalSortOrder список вершин в порядке топологической сортировки
     */
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

    /**
     * Получение отсортированного списка вершин графа в порядке топологической сортировки
     * @return отсортированный список вершин графа
     */
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