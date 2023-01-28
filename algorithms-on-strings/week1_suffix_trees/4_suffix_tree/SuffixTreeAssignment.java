import java.util.ArrayList;
import java.util.List;

public class SuffixTreeAssignment {
	public static void main(String[] args) {
		Tests.testAll();

		System.out.println("Hello World!");
	}
}

class Tests {
	public static void testAll() {
		testTrieNode();

		System.out.println("Tests passed!");
	}

	public static void testTrieNode() {
		{
			Node node = new TrieNode("a");

			assert node.getValue().equals("a") : "Creating a node.";
		}

		{
			Node node = new TrieNode("a");
			Node child = new TrieNode("b");

			node.addChild(child);

			assert node.getChildByValue("b").getValue().equals("b") : "Should return a child by value.";
		}

		{
			Node node = new TrieNode("a");

			assert node.getChildByValue("b") == null : "Should return null on missing child.";
		}

		{
			Node node = new TrieNode("a");

			assert node.getChildByValue("b") == null : "Should return null on missing child.";
		}

		{
			TrieNode node = new TrieNode("");
			node.addPattern("abcd");

			Node aNode = node.getChildByValue("a");
			Node bNode = aNode.getChildByValue("b");
			Node cNode = bNode.getChildByValue("c");
			Node dNode = cNode.getChildByValue("d");

			String testDescription = "Should add pattern 'abcd' correctly.";

			assert aNode != null : testDescription;
			assert bNode != null : testDescription;
			assert cNode != null : testDescription;
			assert dNode != null : testDescription;
		}

		{
			TrieNode node = new TrieNode("");
			node.addPattern("aa");
			node.addPattern("ab");

			Node aNode = node.getChildByValue("a");
			Node aaNode = aNode.getChildByValue("a");
			Node abNode = aNode.getChildByValue("b");

			String testDescription = "Should add patterns 'aa' and 'ab' correctly.";

			assert aNode != null : testDescription;
			assert aaNode != null : testDescription;
			assert abNode != null : testDescription;
		}

		{
			TrieNode root = new TrieNode("");
			root.addPattern("")
		}
	}
}

class TrieNode implements Node, Trie {
	final private char ENDING_CHAR = '$';

	private String value;
	private List<Node> children = new ArrayList<>();

	public TrieNode(String value) {
		this.value = value;
	}

	public void addChild(Node node) {
		this.children.add(node);
	};

	public Node getChildByValue(String value) {
		for (Node child : children) {
			if (child.getValue().equals(value)) {
				return child;
			}
		}

		return null;
	};

	public String getValue() {
		return this.value;
	}

	public void addPattern(String pattern) {
		Node currentNode = this;

		for (char c : pattern.toCharArray()) {
			Node matchingNode = currentNode.getChildByValue("" + c);

			if (matchingNode != null) {
				currentNode = matchingNode;
				continue;
			}

			Node newNode = new TrieNode("" + c);
			currentNode.addChild(newNode);

			currentNode = newNode;
		}
	}
}

interface Node {
	public String getValue();

	public void addChild(Node node);

	public Node getChildByValue(String value);
}

interface Trie {
	public void addPattern(String pattern);
}

