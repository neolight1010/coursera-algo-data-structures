import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Acyclicity {
    static ArrayList<Integer> notExplored = new ArrayList<Integer>();
    static Stack<Integer> routeStack = new Stack<Integer>();

    private static int acyclic(ArrayList<ArrayList<Integer>> adj) {
        while (!notExplored.isEmpty()) {
            int current = notExplored.get(0);

            // If a cycle is found.
            if (explore(current, adj)) {
                return 1;
            }
        }

        return 0;
    }

    /*
     * Returns true if a cycle was found.
     */
    private static boolean explore(int node, ArrayList<ArrayList<Integer>> adj) {
        notExplored.remove(notExplored.indexOf(node));
        routeStack.push(node);

        for (int neighbor : adj.get(node)) {
            if (routeStack.contains(neighbor)) {
                return true;
            }

            if (notExplored.contains(neighbor)) {
                boolean cycleInExplore = explore(neighbor, adj);

                if (cycleInExplore) {
                    return true;
                }
            }
        }

        routeStack.pop();

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
            notExplored.add(i);
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj.get(x - 1).add(y - 1);
        }

        scanner.close();

        System.out.println(acyclic(adj));
    }
}

