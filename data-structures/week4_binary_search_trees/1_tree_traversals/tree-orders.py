import sys, threading
from typing import List, Union


sys.setrecursionlimit(10**6) # max depth of recursion
threading.stack_size(2**27)  # new thread will get stack of such size


class Node:
    def __init__(self, key: int, left: Union[int, None], right: Union[int, None]):
        self.key = key
        self.left = left
        """Left child index."""

        self.right = right
        """Right child index."""

    def __repr__(self):
        return f"Node | Key: {self.key}; Left: {self.left}; Right: {self.right}"


class TreeOrders:
    def __init__(self):
        self.tree: List[Node] = []
        self.result: List[int] = []

    def read(self):
        n = int(sys.stdin.readline())

        self.tree = [Node(-1, None, None) for _ in range(n)]

        for i in range(n):
            [key, left, right] = map(int, sys.stdin.readline().split())

            if left == -1: left = None
            if right == -1: right = None

            self.tree[i].key = key
            self.tree[i].left = left
            self.tree[i].right = right

    def root(self):
        return self.tree[0]

    def inOrder(self, node: Union[Node, None]) -> None:
        if node is None:
            return

        self.inOrder(self.tree[node.left] if node.left else None)
        self.result.append(node.key)
        self.inOrder(self.tree[node.right] if node.right else None)

    def preOrder(self, node: Union[Node, None]) -> None:
        if node is None:
            return

        self.result.append(node.key)
        self.preOrder(self.tree[node.left] if node.left else None)
        self.preOrder(self.tree[node.right] if node.right else None)

    def postOrder(self, node: Union[Node, None]) -> None:
        if node is None:
            return

        self.postOrder(self.tree[node.left] if node.left else None)
        self.postOrder(self.tree[node.right] if node.right else None)
        self.result.append(node.key)


def main():
    tree = TreeOrders()
    tree.read()

    tree.inOrder(tree.root())
    print(" ".join(str(x) for x in tree.result))

    tree.result.clear()
    tree.preOrder(tree.root())
    print(" ".join(str(x) for x in tree.result))

    tree.result.clear()
    tree.postOrder(tree.root())
    print(" ".join(str(x) for x in tree.result))


threading.Thread(target=main).start()
