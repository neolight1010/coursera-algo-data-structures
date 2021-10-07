import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static ArrayList<Boolean> explored = new ArrayList<Boolean>();
    
    private static int numberOfComponents(ArrayList<ArrayList<Integer>> adj) {
        int n = 0;

        while (explored.contains(false)) {
            int currentNode = explored.indexOf(false);
            explore(currentNode, adj);
            n++;
        }

        return n;
    }

    private static void explore(int node, ArrayList<ArrayList<Integer>> adj) {
        explored.set(node, true);

        for (int neighbor: adj.get(node)) {
            if (!explored.get(neighbor)) {
                explore(neighbor, adj);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj.get(x - 1).add(y - 1);
            adj.get(y - 1).add(x - 1);
        }

        // Fill notExplored list
        for (int i = 0; i < n; i++) {
            explored.add(false);
        }

        System.out.println(numberOfComponents(adj));
        scanner.close();
    }
}

