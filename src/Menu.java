import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Controller controller = new Controller();

    Scanner scanner = new Scanner(System.in);

    void displayMainMenu() {
        System.out.println("\nWelcome to the Graph Algorithms program!");

        if (controller.loadedGraph != null) {
            System.out.println("\nLoaded graph: " + controller.loadedGraphFilename + "\n");
        }

        System.out.println("1. Add a new graph");
        System.out.println("2. Load a graph from a file");

        if (controller.loadedGraph != null) {
            System.out.println("3. Print the graph");
            System.out.println("4. Breadth-first search");
            System.out.println("5. Depth-first search");
        }

        System.out.println("6. Exit");

        System.out.print("\nEnter your choice: ");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        if (controller.loadedGraph == null && choice > 2 && choice != 6) {
            System.out.println("You must load a graph first");
            displayMainMenu();
            return;
        }

        switch (choice) {
            case 1:
                System.out.println("Add a new graph");

                System.out.println("Enter the number of nodes: ");

                int nodes = scanner.nextInt();

                System.out.println("Enter the number of edges: ");

                int numEdges = scanner.nextInt();

                List<Edge> edges = new ArrayList<>();

                for (int i = 0; i < numEdges; i++) {
                    System.out.println("Enter the source node: ");
                    int source = scanner.nextInt();
                    System.out.println("Enter the destination node: ");
                    int destination = scanner.nextInt();
                    System.out.println("Enter the weight: ");
                    int weight = scanner.nextInt();
                    edges.add(new Edge(source, destination, weight));
                }

                System.out.println("What name would you like to save this graph as?");

                String fileName = scanner.next();

                controller.addNewGraph(nodes, edges, fileName);

                System.out.println("Graph saved as graphs/" + fileName + ".txt");

                displayMainMenu();
                break;
            case 2:
                this.chooseGraph();

                displayMainMenu();
                break;
            case 3:
                controller.printGraph();

                displayMainMenu();
                break;
            case 4:
                System.out.println("Breadth-first search");

                System.out.println("Enter the starting node: ");

                int startNode = scanner.nextInt();

                controller.BFS(startNode);

                displayMainMenu();
                break;
            case 5:
                System.out.println("Depth-first search");

                System.out.println("Enter the starting node: ");

                int dfsStartNode = scanner.nextInt();

                controller.DFS(dfsStartNode);

                displayMainMenu();
                break;
            case 6:
                this.exit();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    void chooseGraph() {
        System.out.println("Select a graph to load:");

        File[] graphs = controller.getGraphs();

        for (int i = 0; i < graphs.length; i++) {
            System.out.println((i + 1) + ". " + graphs[i].getName());
        }

        System.out.print("\nEnter your choice: ");

        int choice = scanner.nextInt();

        if (choice < 1 || choice > graphs.length) {
            System.out.println("Invalid choice");
            chooseGraph();
            return;
        }

        controller.loadGraph(choice, graphs);

        System.out.println("Graph loaded from graphs/" + graphs[choice - 1].getName());
    }

    void exit() {
        System.out.println("Goodbye!");
    }
}
