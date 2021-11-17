import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<ArrayList<Integer>> adj, ArrayList<HashMap<Integer, Integer>> cost, int s, long[] dist, boolean[] reachable, boolean[] shortest) {
        reachable[s] = true;
        dist[s] = 0;

        ArrayList<Integer> prev = new ArrayList<>();

        for (int i = 0; i < adj.size(); i++) {
            prev.add(null);
        }

        // Repeat |V| - 1 times
        for (int i = 0; i < adj.size() - 1; i++) {
            // For each edge
            for (int node = 0; node < adj.size(); node++) {
                for (int neighbor : adj.get(node)) {
                    int edgeWeight = cost.get(node).get(neighbor);

                    if (dist[neighbor] > dist[node] + edgeWeight && !(dist[node] == Long.MAX_VALUE)) {
                        reachable[neighbor] = true;
                        prev.set(neighbor, node);

                        dist[neighbor] = dist[node] + edgeWeight;
                    }
                }
            }
        }

        // For each edge
        boolean thereIsCycle = false;
        ArrayList<Integer> cycleNodes = new ArrayList<>();

        for (int node = 0; node < adj.size(); node++) {
            for (int neighbor : adj.get(node)) {
                int edgeWeight = cost.get(node).get(neighbor);

                if (dist[neighbor] > dist[node] + edgeWeight && !(dist[node] == Long.MAX_VALUE)) {
                    thereIsCycle = true;
                    cycleNodes.add(neighbor);
                }
            }
        }

        if (!thereIsCycle) return;

        for (int cycleNode : cycleNodes) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(cycleNode);

            while (!queue.isEmpty()) {
                int node = queue.remove();
                shortest[node] = false;

                for (int neighbor : adj.get(node)) {
                    if (shortest[neighbor]) {
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<HashMap<Integer, Integer>> cost = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
            cost.add(new HashMap<>());
        }

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();

            adj.get(x - 1).add(y - 1);
            cost.get(x - 1).put(y - 1, w);
        }

        int s = scanner.nextInt() - 1;
        scanner.close();

        long distance[] = new long[n];
        boolean reachable[] = new boolean[n];
        boolean shortest[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = false;
            shortest[i] = true;
        }

        shortestPaths(adj, cost, s, distance, reachable, shortest);

        for (int i = 0; i < n; i++) {
            if (!reachable[i]) {
                System.out.println('*');
            } else if (!shortest[i]) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

