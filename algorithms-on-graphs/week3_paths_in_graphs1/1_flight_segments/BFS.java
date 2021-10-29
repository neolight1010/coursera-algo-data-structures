import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<ArrayList<Integer>> adj, int s, int t) {
        ArrayList<Integer> dist = new ArrayList<>();
        for (int i = 0; i < adj.size(); i++) {
            dist.add(-1);
        }

        dist.set(s, 0);

        ArrayList<Integer> exploreQueue = new ArrayList<>();
        exploreQueue.add(s);

        while (!exploreQueue.isEmpty()) {
            int node = exploreQueue.remove(0);

            for (int neighbor : adj.get(node)) {
                if (dist.get(neighbor) == -1) {
                    exploreQueue.add(neighbor);
                    dist.set(neighbor, dist.get(node) + 1);
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

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
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
        
        scanner.close();

        System.out.println(distance(adj, x, y));
    }
}
