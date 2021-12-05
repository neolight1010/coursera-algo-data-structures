import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        long[] currentTime = new long[numWorkers];

        Comparator<Integer> comp = (i, j) -> {
            if (currentTime[i] > currentTime[j]) {
                return 1;
            } else if (currentTime[j] > currentTime[i]) {
                return -1;
            } else {
                if (i > j) {
                    return 1;
                }
                return -1;
            }
        };

        PriorityQueue<Integer> queue = new PriorityQueue<>(comp);
        for (int i = 0; i < numWorkers; i++) {
            queue.add(i);
        }
        
        // Simulate
        for (int i = 0; i < jobs.length; i++) {
            int assigned = queue.remove();
            long time = currentTime[assigned];

            assignedWorker[i] = assigned;
            startTime[i] = time;

            currentTime[assigned] = time + jobs[i];
            queue.add(assigned);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
