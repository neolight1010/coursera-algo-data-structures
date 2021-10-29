import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private enum Side {
        UNSET,
        LEFT,
        RIGHT
    }

    private static int bipartite(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();

        ArrayList<Side> sides = new ArrayList<Side>();
        for (int i = 0; i < n; i++) {
            sides.add(Side.UNSET);
        }

        for (int i = 0; i < n; i++) {
            if (sides.get(i) == Side.UNSET) {
                Queue<Integer> exploreQueue = new LinkedList<Integer>();
                exploreQueue.add(i);

                sides.set(i, Side.LEFT);

                while (!exploreQueue.isEmpty()) {
                    int u = exploreQueue.remove();

                    for (int v : adj.get(u)) {
                        if (sides.get(v) == Side.UNSET) {
                            exploreQueue.add(v);

                            if (sides.get(u) == Side.LEFT) {
                                sides.set(v, Side.RIGHT);
                            } else if (sides.get(u) == Side.RIGHT) {
                                sides.set(v, Side.LEFT);
                            }
                        } else if (sides.get(v) == sides.get(u)) {
                            return 0;
                        }
                    }
                }
            }
        }

        return 1;
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

        scanner.close();

        System.out.println(bipartite(adj));
    }
}

