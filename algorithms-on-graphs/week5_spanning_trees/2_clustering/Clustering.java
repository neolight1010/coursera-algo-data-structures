import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Clustering {
       private static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge {
        Node a;
        Node b;

        Edge(Node a, Node b) {
            this.a = a;
            this.b = b;
        }

        public double length() {
            return Math.sqrt((a.y - b.y) * (a.y - b.y) + (a.x - b.x) * (a.x - b.x));
        }
    }

    private static class DisjointSet<T> {
        private HashMap<T, T> parent = new HashMap<>();

        public DisjointSet(Collection<T> a) {
            for (T e : a) {
                this.parent.put(e, e);
            }
        }

        public T find(T x) {
            T xParent = parent.get(x);

            if (xParent == x) {
                return x;
            }

            return find(xParent);
        }

        public void union(T a, T b) {
            T x = find(a);
            T y = find(b);

            parent.put(x, y);
        }

        public int nSets() {
            HashSet<T> roots = new HashSet<>();

            for (T key : parent.keySet()) {
                roots.add(find(key));
            }

            return roots.size();
        }
    }

    private static Comparator<Edge> distComparator = (a,
            b) -> {
        if (a.length() >= b.length()) {
            return 1;
        }

        return -1;
    };

    private static double clustering(int[] x, int[] y, int k) {
        ArrayList<Node> nodes = new ArrayList<>();
        PriorityQueue<Edge> edges = new PriorityQueue<>(distComparator);

        for (int i = 0; i < x.length; i++) {
            Node node = new Node(x[i], y[i]);

            nodes.add(node);
        }

        for (int i = 0; i < nodes.size(); i++) {
            Node nodeA = nodes.get(i);

            for (int j = i + 1; j < nodes.size(); j++) {
                Node nodeB = nodes.get(j);

                edges.add(new Edge(nodeA, nodeB));
            }
        }

        // Kruskal's algorithm
        DisjointSet<Node> forest = new DisjointSet<>(nodes);
        Edge minEdge = null;

        while (true) {
            Edge edge = edges.remove();

            if (forest.find(edge.a) != forest.find(edge.b)) {
                if (forest.nSets() == k) {
                    minEdge = edge;
                    break;
                }

                forest.union(edge.a, edge.b);
            }
        }

        return minEdge.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        scanner.close();

        System.out.println(clustering(x, y, k));
    }
}

