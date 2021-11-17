import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<ArrayList<Integer>> adj, ArrayList<ArrayList<Integer>> cost) {
        ArrayList<Integer> dist = new ArrayList<>();

        // TODO: Use Integer.MAX_VALUE and handle it differently
        for (int i = 0; i < adj.size(); i++) {
            dist.add(Integer.MAX_VALUE / 2);
        }

        // TODO: account for multiple SCCs?
        dist.set(0, 0);

        // V - 1 times
        for (int i = 0; i < adj.size() - 1; i++) {
            for (int node = 0; node < adj.size(); node++) {
                for (int neighbor : adj.get(node)) {
                    int edgeCost = cost.get(node).get(neighbor);

                    if (dist.get(neighbor) > dist.get(node) + edgeCost) {
                        dist.set(neighbor, dist.get(node) + edgeCost);
                    }
                }
            }
        }

        // For each edge
        for (int node = 0; node < adj.size(); node++) {
            for (int neighbor : adj.get(node)) {
                int edgeCost = cost.get(node).get(neighbor);

                if (dist.get(neighbor) > dist.get(node) + edgeCost) {
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

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> cost = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
            cost.add(new ArrayList<Integer>());

            for (int j = 0; j < n; j++) {
                cost.get(i).add(null);
            }
        }

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();

            adj.get(x - 1).add(y - 1);
            cost.get(x - 1).set(y - 1, w);
        }

        scanner.close();

        System.out.println(negativeCycle(adj, cost));
    }
}

