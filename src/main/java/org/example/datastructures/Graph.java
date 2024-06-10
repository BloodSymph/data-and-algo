package org.example.datastructures;

import java.util.*;

public final class Graph {
    private Map<String, List<String>> adjacentList;

    public Graph() {
        this.adjacentList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        if (!adjacentList.containsKey(vertex)) {
            adjacentList.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(String vertex1, String vertex2) {
        if (!adjacentList.containsKey(vertex1)) {
            addVertex(vertex1);
        }
        if (!adjacentList.containsKey(vertex2)) {
            addVertex(vertex2);
        }
        adjacentList.get(vertex1).add(vertex2);
        adjacentList.get(vertex2).add(vertex1);
    }

    public void printGraph() {
        for (String vertex : adjacentList.keySet()) {
            System.out.println(vertex + ": ");
            for (String neighbor : adjacentList.get(vertex)) {
                System.out.println(neighbor + " ");
            }
            System.out.println();
        }
    }

}