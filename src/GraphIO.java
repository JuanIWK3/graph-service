import java.io.*;
import java.util.List;
import java.util.Map;

public class GraphIO {
    public static void saveGraph(Graph graph, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<Integer, List<Edge>> entry : graph.getAdjList().entrySet()) {
                writer.print(entry.getKey() + ":");
                for (Edge edge : entry.getValue()) {
                    writer.print("(" + edge.destination + "," + edge.weight + ") ");
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Graph loadGraph(String filename) {
        Graph graph = new Graph();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                int node = Integer.parseInt(parts[0]);
                graph.addNode(node);
                if (parts.length > 1) {
                    String[] edges = parts[1].split("\\s+");
                    for (String edge : edges) {
                        String[] edgeParts = edge.substring(1, edge.length() - 1).split(",");
                        int destination = Integer.parseInt(edgeParts[0]);
                        int weight = Integer.parseInt(edgeParts[1]);
                        graph.addEdge(node, destination, weight);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public static File[] getGraphsFromDirectory(String directory) {
        File dir = new File(directory);
        return dir.listFiles();
    }
}
