package org.example.datastructures;

import java.util.*;
//Реализация графа
public final class Graph {
    //Хеш таблица представляющая смежный лист
    //Состоит из ключа - вершины и масива где будут хранится рёбра графа
    private Map<String, List<String>> adjacentList;
    //Инициализация листа
    public Graph() {
        this.adjacentList = new HashMap<>();
    }

    //Создание новой вершины графа
    public void addVertex(String vertex) {
        //Если вершины не существует в смежном листе
        if (!adjacentList.containsKey(vertex)) {
            //Помещаем новую вершину и создаём пустое ребро
            adjacentList.put(vertex, new ArrayList<>());
        }
    }

    //Добавление рёбер передаём две вершины
    public void addEdge(String vertex1, String vertex2) {
        //Если вершины не существует в смежном листе
        if (!adjacentList.containsKey(vertex1)) {
            //Добавляем новую вершину
            addVertex(vertex1);
        }
        //Если второй вершины не существует в смежном листе
        if (!adjacentList.containsKey(vertex2)) {
            //Добавляем новую
            addVertex(vertex2);
        }
        //Получаем первую вершину из смежного листа и добавляем вторую в качевстве ребра
        adjacentList.get(vertex1).add(vertex2);
        //Получаем вторую вершину из смежного листа и добавляем первую в качевстве ребра
        adjacentList.get(vertex2).add(vertex1);
    }

    //Распечатаем граф
    public void printGraph() {
        //Перебераем каждую вершину в цикле
        for (String vertex : adjacentList.keySet()) {
            //Выводим их на экран
            System.out.println(vertex + ": ");
            //Перебераем соседние вершины в цикле
            for (String neighbor : adjacentList.get(vertex)) {
                //Выводим их на экран
                System.out.println(neighbor + " ");
            }
            System.out.println();
        }
    }

}