package org.example.algo.graph;

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

    private void DFS(String startVertex) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            String currentVertex = stack.pop();

            if (!visited.contains(currentVertex)) {
                visited.add(currentVertex);
                System.out.print(currentVertex + " ");

                for (String neighbor : adjacentList.get(currentVertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    public void dfsRecursive(String startVertex) {
        Set<String> visited = new HashSet<>();
        dfsRecursiveHelper(startVertex, visited);
        System.out.println();
    }

    private void dfsRecursiveHelper(String vertex, Set<String> visited) {
        if (visited.contains(vertex)) {
            return;
        }
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (String neighbor : adjacentList.get(vertex)) {
            dfsRecursiveHelper(neighbor, visited);
        }
    }


    public void bfs(String startVertex) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            String currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            for (String neighbor : adjacentList.get(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void bfsRecursive(String startVertex) {
        Set<String> visited = new HashSet<>();
        bfsRecursiveHelper(startVertex, visited);
        System.out.println();
    }

    private void bfsRecursiveHelper(String vertex, Set<String> visited) {
        if (visited.contains(vertex)) {
            return;
        }
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (String neighbor : adjacentList.get(vertex)) {
            bfsRecursiveHelper(neighbor, visited);
        }
    }

}