import java.io.*;
import java.util.*;

class Graph {
    private final Map<Integer, List<Edge>> adjList;

    static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + destination + ", " + weight + ")";
        }
    }

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
        adjList.get(node1).add(new Edge(node2, weight));
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

    void BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.add(start);
        visited.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (Edge edge : adjList.get(node)) {
                if (!visited.contains(edge.destination)) {
                    q.add(edge.destination);
                    visited.add(edge.destination);
                }
            }
        }
    }

    void DFS(int start) {
        Stack<Integer> s = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        s.push(start);
        visited.add(start);

        while (!s.isEmpty()) {
            int node = s.pop();
            System.out.print(node + " ");

            for (Edge edge : adjList.get(node)) {
                if (!visited.contains(edge.destination)) {
                    s.push(edge.destination);
                    visited.add(edge.destination);
                }
            }
        }
    }

    // Save the graph to a text file

}
