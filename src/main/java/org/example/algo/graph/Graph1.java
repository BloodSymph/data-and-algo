package org.example.algo.graph;

import java.util.*;

public final class Graph1 {
    private Map<String, List<Edge>> adjacentList;

    public Graph1() {
        this.adjacentList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        if (!adjacentList.containsKey(vertex)) {
            adjacentList.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(String vertex1, String vertex2, int weight) {
        if (!adjacentList.containsKey(vertex1)) {
            addVertex(vertex1);
        }
        if (!adjacentList.containsKey(vertex2)) {
            addVertex(vertex2);
        }
        adjacentList.get(vertex1).add(new Edge(vertex2, weight));
        adjacentList.get(vertex2).add(new Edge(vertex1, weight));
    }

    public void printGraph() {
        for (String vertex : adjacentList.keySet()) {
            System.out.print(vertex + ": ");
            for (Edge edge : adjacentList.get(vertex)) {
                System.out.print("(" + edge.destination + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    public Map<String, Integer> dijkstra(String startVertex) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Set<String> visited = new HashSet<>();

        for (String vertex : adjacentList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        priorityQueue.add(new Edge(startVertex, 0));

        while (!priorityQueue.isEmpty()) {
            Edge currentEdge = priorityQueue.poll();
            String currentVertex = currentEdge.destination;

            if (!visited.contains(currentVertex)) {
                visited.add(currentVertex);

                for (Edge edge : adjacentList.get(currentVertex)) {
                    String adjacentVertex = edge.destination;
                    int newDist = distances.get(currentVertex) + edge.weight;

                    if (newDist < distances.get(adjacentVertex)) {
                        distances.put(adjacentVertex, newDist);
                        priorityQueue.add(new Edge(adjacentVertex, newDist));
                    }
                }
            }
        }

        return distances;
    }

    private static class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

}
