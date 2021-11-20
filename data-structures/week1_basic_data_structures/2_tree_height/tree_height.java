import java.util.*;
import java.io.*;

public class tree_height {
	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}

		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class Node {
	    int key;
	    int level = 0;
	    ArrayList<Integer> children = new ArrayList<>();

	    Node(int key) {
		this.key = key;
	    }
	}

	public class TreeHeight {
		int n;
		int parent[];

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
		    // Generate tree.
		    ArrayList<Node> nodes = new ArrayList<>();
		    Node root = null;

		    for (int i = 0; i < n; i++) {
			nodes.add(new Node(i));
		    }

		    for (int i = 0; i < n; i++) {
			if (parent[i] == -1) {
			    root = nodes.get(i);
			    continue;
			} else {
			    nodes.get(parent[i]).children.add(i);
			}
		    }

		    // BFS
		    int maxLevel = 0;
		    Queue<Node> queue = new LinkedList<>();
		    queue.add(root);

		    while (!queue.isEmpty()) {
			Node node = queue.remove();

			for (int childIndex : node.children) {
			    Node child = nodes.get(childIndex);
			    child.level = node.level + 1;

			    queue.add(child);

			    if (child.level > maxLevel) {
				maxLevel = child.level;
			    }
			}
		    }

		    return maxLevel + 1;
		}
	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_height().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
