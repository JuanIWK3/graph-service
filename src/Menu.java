import java.io.File;
import java.util.Scanner;

public class Menu {
    String loadedGraphFilename = null;
    Graph loadedGraph = null;

    void continueQuestion() {
        System.out.println("\nPress enter to continue...");

        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
    }

    void displayMainMenu() {
        System.out.println("\nWelcome to the Graph Algorithms program!");

        if (loadedGraph != null) {
            System.out.println("\nLoaded graph: " + loadedGraphFilename + "\n");
        }

        System.out.println("1. Add a new graph");
        System.out.println("2. Load a graph from a file");

        if (loadedGraph != null) {
            System.out.println("3. Print the graph");
            System.out.println("4. Breadth-first search");
            System.out.println("5. Depth-first search");
        }

        System.out.println("6. Exit");

        System.out.print("\nEnter your choice: ");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        if (loadedGraph == null && choice > 2 && choice != 6) {
            System.out.println("You must load a graph first");
            displayMainMenu();
            return;
        }

        switch (choice) {
            case 1:
                this.addNewGraph();
                break;
            case 2:
                this.loadGraph();
                break;
            case 3:
                this.printGraph();
                break;
            case 4:
                this.BFS();
                break;
            case 5:
                this.DFS();
                break;
            case 6:
                this.exit();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    void addNewGraph() {
        System.out.println("Add a new graph");

        System.out.println("Enter the number of nodes: ");

        Scanner scanner = new Scanner(System.in);

        int numNodes = scanner.nextInt();

        Graph graph = new Graph();

        for (int i = 0; i < numNodes; i++) {
            graph.addNode(i);
        }

        System.out.println("Enter the number of edges: ");

        int numEdges = scanner.nextInt();

        for (int i = 0; i < numEdges; i++) {
            System.out.println("Enter the source node: ");
            int source = scanner.nextInt();
            System.out.println("Enter the destination node: ");
            int destination = scanner.nextInt();
            System.out.println("Enter the weight: ");
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        System.out.println("What name would you like to save this graph as?");

        String filename = scanner.next();

        GraphIO.saveGraph(graph, "graphs/" + filename + ".txt");

        System.out.println("Graph saved as graphs/" + filename + ".txt");

        loadedGraphFilename = "graphs/" + filename + ".txt";
        loadedGraph = graph;

        continueQuestion();

        displayMainMenu();
    }

    void loadGraph() {
        System.out.println("Select a graph to load:");

        File[] graphs = GraphIO.getGraphsFromDirectory("graphs");

        for (int i = 0; i < graphs.length; i++) {
            System.out.println((i + 1) + ". " + graphs[i].getName());
        }

        System.out.print("\nEnter your choice: ");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        if (choice < 1 || choice > graphs.length) {
            System.out.println("Invalid choice");
            loadGraph();
            return;
        }

        loadedGraphFilename = graphs[choice - 1].getName();

        loadedGraph = GraphIO.loadGraph("graphs/" + loadedGraphFilename);

        System.out.println("Graph loaded from graphs/" + loadedGraphFilename);

        continueQuestion();

        displayMainMenu();
    }

    void printGraph() {
        loadedGraph.printGraph();

        continueQuestion();

        displayMainMenu();
    }

    void BFS() {
        loadedGraph.BFS(0);
    }

    void DFS() {
        loadedGraph.DFS(0);
    }

    void exit() {
        System.out.println("Goodbye!");
    }
}
