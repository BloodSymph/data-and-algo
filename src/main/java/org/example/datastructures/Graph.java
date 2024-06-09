package org.example.datastructures;

import java.util.*;

public final class Graph {

    private List<List<Node>> adjacentList = new ArrayList<>();

    public Graph(List<Edge> edges) {
        for (int i = 0; i < edges.size(); i++) {
            adjacentList.add(i, new ArrayList<>());
            
        }
        for (Edge edge : edges) {
            adjacentList.get(edge.src).add(new Node(edge.dest, edge.weight));
        }
    }

    public static void printGraph(Graph graph)  {
        int src_vertex = 0;
        int list_size = graph.adjacentList.size();

        System.out.println("The contents of the graph:");
        while (src_vertex < list_size) {
            for (Node edge : graph.adjacentList.get(src_vertex)) {
                System.out.print("Vertex:" + src_vertex + " ==> " + edge.value +
                        " (" + edge.weight + ")\t");
            }
            System.out.println();
            src_vertex++;
        }
    }

    public static class Node {
        private int value;
        private int weight;

        public Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public static class Edge {
        private int src;
        private int dest;
        private int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
