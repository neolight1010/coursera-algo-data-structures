import java.util.*;

public class Dijkstra {
    private static long distance(ArrayList<ArrayList<Integer>> adj, ArrayList<ArrayList<Integer>> cost, int s, int t) {
        boolean[] visited = new boolean[adj.size()];

        ArrayList<Integer> dist = new ArrayList<>();

        for (int i = 0; i < adj.size(); i++) {
            dist.add(-1);
        }

        dist.set(s, 0);

        // Comparator
        Comparator<Integer> distComparator = (n1, n2) -> {
            return dist.get(n1) - dist.get(n2);
        };

        // Start algorithm
        PriorityQueue<Integer> queue = new PriorityQueue<>(distComparator);
        queue.add(s);

        while (!queue.isEmpty()) {
            int node = queue.remove();
            int nodeDist = dist.get(node);

            for (int neighbor : adj.get(node)) {
                int edgeCost = cost.get(node).get(neighbor);

                if ((nodeDist + edgeCost < dist.get(neighbor)) || dist.get(neighbor) < 0) {
                    dist.set(neighbor, nodeDist + edgeCost);

                    queue.add(neighbor);
                }
            }
        }

        return dist.get(t);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // TODO: make cost a HashMap?
        ArrayList<ArrayList<Integer>> cost = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
            cost.add(new ArrayList<Integer>());

            for (int j = 0; j < n; j++) {
                cost.get(i).add(-1);
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

        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;

        scanner.close();

        System.out.println(distance(adj, cost, x, y));
    }
}

