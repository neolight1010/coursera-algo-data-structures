import java.util.*;
import java.io.*;

public class substring_equality {
	public class Solver {
		private static final int X = 7;
		private static final long P = 1000000007;
		private static final long Q = 1000000009;

		private ArrayList<Long> x_exp_p = new ArrayList<>();
		private ArrayList<Long> x_exp_q = new ArrayList<>();

		private ArrayList<Long> hashes_p = new ArrayList<>();
		private ArrayList<Long> hashes_q = new ArrayList<>();

		public Solver(String text) {
			// Initialize pre-computed hashes.
			hashes_p.add(0l);
			hashes_q.add(0l);

			x_exp_p.add(1l);
			x_exp_q.add(1l);

			for (int i = 1; i <= text.length(); i++) {
				char currentChar = text.charAt(i - 1);

				long newHashP = mod((hashes_p.get(i - 1) * X + currentChar), P);
				hashes_p.add(newHashP);

				long newHashQ = mod((hashes_q.get(i - 1) * X + currentChar), Q);
				hashes_q.add(newHashQ);

				x_exp_p.add((x_exp_p.get(i - 1) * X) % P);
				x_exp_q.add((x_exp_q.get(i - 1) * X) % Q);
			}
		}

		public long substring_hash_p(int i, int length) {
			return mod(hashes_p.get(i + length) - (x_exp_p.get(length) * hashes_p.get(i)), P);
		}

		public long substring_hash_q(int i, int length) {
			return mod(hashes_q.get(i + length) - (x_exp_q.get(length) * hashes_q.get(i)), Q);
		}

		public boolean ask(int a, int b, int l) {
			if (substring_hash_p(a, l) == substring_hash_p(b, l) &&
			    substring_hash_q(a, l) == substring_hash_q(b, l))
				return true;

			return false;
		}

		private long mod(long a, long b) {
			return ((a % b) + b) % b;
		}
	}

	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String s = in.next();
		int q = in.nextInt();
		Solver solver = new Solver(s);
		for (int i = 0; i < q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int l = in.nextInt();

			out.println(solver.ask(a, b, l) ? "Yes" : "No");
		}
		out.close();
	}

	static public void main(String[] args) throws IOException {
	    new substring_equality().run();
	}

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
}
