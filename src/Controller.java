import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Controller {
    String loadedGraphFilename = null;
    Graph loadedGraph = null;

    GraphSearch graphSearch = new GraphSearch();

    void addNewGraph(int nodes, List<Edge> edge, String fileName) {
        Graph graph = new Graph();

        for (int i = 0; i < nodes; i++) {
            graph.addNode(i);
        }

        for (Edge e : edge) {
            graph.addEdge(e.source, e.destination, e.weight);
        }

        GraphIO.saveGraph(graph, "graphs/" + fileName + ".txt");
        loadedGraphFilename = "graphs/" + fileName + ".txt";
        loadedGraph = graph;
    }

    File[] getGraphs() {
        return GraphIO.getGraphsFromDirectory("graphs");
    }

    void loadGraph(int choice, File[] graphs) {
        loadedGraphFilename = graphs[choice - 1].getName();

        loadedGraph = GraphIO.loadGraph("graphs/" + loadedGraphFilename);
    }

    void printGraph() {
        loadedGraph.printGraph();
    }

    void BFS(int startNode) {
        graphSearch.BFS(startNode, loadedGraph.getAdjList());
    }

    void DFS(int startNode) {
        graphSearch.DFS(startNode, loadedGraph.getAdjList());
    }
}
