import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
        swaps = new ArrayList<Swap>();

        for (int i = data.length / 2; i >= 0; i--) {
            shiftDown(i);
        }
    }

    private int leftChild(int i) {
        return (2 * (i + 1)) - 1;
    }

    private int rightChild(int i) {
        return leftChild(i) + 1;
    }

    private int parent(int i) {
        return ((i + 1) / 2) - 1;
    }

    private void swap(int i, int j) {
        int temp = data[i];

        data[i] = data[j];
        data[j] = temp;

        swaps.add(new Swap(i, j));
    }

    /*private void shiftUp(int i) {
        while (i > 0 && data[parent(i)] > data[i]) {
            swap(parent(i), i);
            i = parent(i);
        }
    }*/

    private void shiftDown(int i) {
        int minIndex = i;

        if (leftChild(i) < data.length && data[leftChild(i)] < data[minIndex]) {
            minIndex = leftChild(i);
        }

        if (rightChild(i) < data.length && data[rightChild(i)] < data[minIndex]) {
            minIndex = rightChild(i);
        }

        if (i != minIndex) {
            swap(i, minIndex);
            shiftDown(minIndex);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
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
