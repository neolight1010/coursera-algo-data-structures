import java.util.ArrayList;
import java.util.Scanner;

public class StronglyConnected {
    private static boolean[] explored;
    private static ArrayList<Integer> postOrder = new ArrayList<>();

    private static int numberOfStronglyConnectedComponents(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        explored = new boolean[n];

        ArrayList<ArrayList<Integer>> revGraph = reverseGraph(adj);

        for (int i = 0; i < n; i++) {
            if (!explored[i]) {
                explore(revGraph, i, true);
            }
        }

        explored = new boolean[n];
        int nSCC = 0;

        for (int i = 0; i < n; i++) {
            int node = postOrder.get(i);

            if (!explored[node]) {{
                nSCC++;
                explore(adj, node, false);
            }}
        }

        return nSCC;
    }

    private static void explore(ArrayList<ArrayList<Integer>> adj, int initialNode, boolean modifyPostOrder) {
        explored[initialNode] = true;

        for (int neighbor : adj.get(initialNode)) {
            if (!explored[neighbor]) {
                explore(adj, neighbor, modifyPostOrder);
            }
        }

        if (modifyPostOrder) {
            postOrder.add(0, initialNode);
        }
    }

    private static ArrayList<ArrayList<Integer>> reverseGraph(ArrayList<ArrayList<Integer>> adj) {
        // Reverse graph
        ArrayList<ArrayList<Integer>> revGraph = new ArrayList<>();

        for (int i = 0; i < adj.size(); i++) {
            revGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < adj.size(); i++) {
            ArrayList<Integer> node = adj.get(i);

            for (int neighbor : node) {
                revGraph.get(neighbor).add(i);
            }
        }
        
        return revGraph;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj.get(x - 1).add(y - 1);
            // adj.get(y - 1).add(x - 1); // Reverse graph
        }

        scanner.close();

        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

