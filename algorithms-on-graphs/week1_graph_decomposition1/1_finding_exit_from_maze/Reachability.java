import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static ArrayList<Integer> explored = new ArrayList<Integer>();

    private static int reach(ArrayList<ArrayList<Integer>> adj, int x, int y) {
        explored.add(x);

        if (adj.get(x).contains(y)) {
            return 1;
        }

        for (int neighbor : adj.get(x)) {
            if (!explored.contains(neighbor)) {
                if (reach(adj, neighbor, y) == 1) {
                    return 1;
                }
            }
        }

        return 0;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));

        scanner.close();
    }
}
