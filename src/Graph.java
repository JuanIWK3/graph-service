import java.io.*;
import java.util.*;

class Graph {
    private final Map<Integer, List<Edge>> adjList;

    public Map<Integer, List<Edge>> getAdjList() {
        return adjList;
    }

    Graph() {
        this.adjList = new HashMap<>();
    }

    void addNode(int node) {
        adjList.put(node, new LinkedList<Edge>());
    }

    void addEdge(int node1, int node2, int weight) {
        adjList.get(node1).add(new Edge(node1, node2, weight));
        // For an undirected graph, you may want to add the reverse edge as well
        // adjList.get(node2).add(new Edge(node1, weight));
    }

    void printGraph() {
        for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Edge edge : entry.getValue()) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }
}
