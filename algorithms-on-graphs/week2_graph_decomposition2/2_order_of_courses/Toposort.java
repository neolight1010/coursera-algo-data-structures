import java.util.ArrayList;
import java.util.Scanner;

public class Toposort {
    private static boolean[] explored;
    private static ArrayList<Integer> postOrder = new ArrayList<>();

    private static ArrayList<Integer> toposort(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        explored = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!explored[i]) {{
                explore(adj, i, true);
            }}
        }

        return postOrder;
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
        }

        scanner.close();

        for (int node : toposort(adj)) {
            System.out.print((node + 1) + " ");
        }
    }
}

