import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Trie {
	private Node root = new Node(' ');

	public Node getRoot() {
		return root;
	}

	public void addPattern(String pattern) {
		Node currentNode = root;

		char[] patternChars = pattern.toCharArray();
		for (int i = 0; i < patternChars.length; i++) {
			char c = patternChars[i];
			boolean isLastChar = i == patternChars.length - 1;

			Node matchingChild = currentNode.childByValue(c);

			if (matchingChild == null) {
				Node newNode = new Node(c);

				if (isLastChar) {
					newNode.setIsLeaf(true);
				}

				currentNode.addChild(newNode);
				currentNode = newNode;

				continue;
			}

			if (isLastChar) {
				matchingChild.setIsLeaf(true);
			}

			currentNode = matchingChild;
		}
	}

	public List<Integer> match(String text) {
		List<Integer> matches = new ArrayList<>();
		String currentText = text;

		for (int i = 0; true; i++) {
			if (currentText.length() == 0) {
				break;
			}

			boolean foundLeaf = traverseUntilLeaf(currentText);
			if (foundLeaf) {
				matches.add(i);
			}

			currentText = currentText.substring(1);
		}

		return matches;
	}

	private boolean traverseUntilLeaf(String text) {
		Node currentNode = root;

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			Node matchingChild = currentNode.childByValue(c);

			if (matchingChild == null) {
				return false;
			}

			if (matchingChild != null) {
				if (matchingChild.isLeaf()) {
					return true;
				}

				currentNode = matchingChild;
				continue;
			}
		}

		return false;
	}
}

class Node {
	public char value;
	private List<Node> children = new ArrayList<>();
	private boolean isLeaf = false;

	public Node(char value) {
		this.value = value;
	}

	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}


	public void addChild(Node node) {
		children.add(node);
	}

	public int childrenSize() {
		return children.size();
	}

	public Node childByValue(char value) {
		for (Node child : children) {
			if (child.value == value) {
				return child;
			}
		}

		return null;
	}

	public boolean isLeaf() {
		return children.isEmpty() || this.isLeaf;
	}
}

class Testing {
	public static void testAll() {
		testTrie();
	}

	private static void testTrie() {
		{
			Trie trie = new Trie();
			trie.addPattern("abcd");

			assert trie.getRoot().childrenSize() == 1 :
				"Adding 1 pattern.";
		}

		{
			Trie trie = new Trie();
			trie.addPattern("aaaa");
			trie.addPattern("bbbb");

			assert trie.getRoot().childrenSize() == 2 :
				"Adding 2 patterns with distinct values.";

			assert trie.getRoot().childByValue('a').childrenSize() == 1;
			assert trie.getRoot().childByValue('b').childrenSize() == 1;
		}

		{
			Trie trie = new Trie();
			trie.addPattern("zbaa");
			trie.addPattern("z");

			assert trie.getRoot().childrenSize() == 1 :
				"Adding 2 patterns with similar values.";

			assert trie.getRoot().childByValue('z').isLeaf();
		}

		{
			Trie trie = new Trie();
			trie.addPattern("abcd");

			assert trie.match("efgh").equals(new ArrayList<>()) :
				"Matching no patterns";
		}

		{
			Trie trie = new Trie();
			trie.addPattern("efg");

			List<Integer> expected = new ArrayList<>(Arrays.asList(4));
			assert trie.match("abcdefgh").equals(expected) :
				"Matching 1 pattern";
		}

		{
			Trie trie = new Trie();
			trie.addPattern("efg");
			trie.addPattern("cde");
			trie.addPattern("lll");
			trie.addPattern("llf");

			List<Integer> expected = new ArrayList<>(Arrays.asList(0, 2, 6, 7, 8));
			assert trie.match("cdefghllllf").equals(expected) :
				"Matching multiple patterns";
		}
	}
}

class TrieMatchingExtended implements Runnable {
	private String text;
	private Trie trie = new Trie();

	public static void main(String[] args) {
		new Thread(new TrieMatchingExtended()).start();
	}

	public void run() {
		Testing.testAll();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			text = in.readLine();

			int nOfPatterns = Integer.parseInt(in.readLine());

			for (int i = 0; i < nOfPatterns; i++) {
				String pattern = in.readLine();

				trie.addPattern(pattern);
			}

			solveAndShowResults();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void solveAndShowResults() {
		List<Integer> matches = trie.match(text);
		Collections.sort(matches);

		List<String> matchesStr = new ArrayList<>();

		for (int match : matches) {
			matchesStr.add(Integer.toString(match));
		}

		System.out.println(String.join(" ", matchesStr));

	}
}
