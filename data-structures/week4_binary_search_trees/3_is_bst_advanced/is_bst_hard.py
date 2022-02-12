import sys, threading
from typing import List, Tuple, Union


MAX_INT: int = pow(2, 31)
MIN_INT: int = -MAX_INT

sys.setrecursionlimit(10**9) # max depth of recursion
threading.stack_size(2**27)  # new thread will get stack of such size


class Node:
    def __init__(self):
        self.key: int = 0
        self.left: Union[Node, None] = None
        self.right: Union[Node, None] = None


def is_binary_search_tree(node: Union[Node, None],
                          max_lim: int = MAX_INT,
                          min_lim: int = MIN_INT) -> bool:
    if node is None: return True

    if (node.left and node.left.key >= node.key) \
       or node.key >= max_lim:
        return False

    if (node.right and node.right.key < node.key) \
       or node.key < min_lim:
        return False

    return is_binary_search_tree(node.left, min(max_lim, node.key), min_lim) and is_binary_search_tree(node.right, max_lim, max(min_lim, node.key))


def main():
    n = int(sys.stdin.readline().strip())
    queries: List[Tuple[int, int, int]] = []

    tree: List[Node] = []

    for _ in range(n):
        queries.append(tuple(map(int, sys.stdin.readline().strip().split())))
        tree.append(Node())

    # Set tree's nodes' children
    for i, query in enumerate(queries):
        key, left_i, right_i = query

        tree[i].key = key
        tree[i].left = tree[left_i] if left_i >= 0 else None
        tree[i].right = tree[right_i] if right_i >= 0 else None

    if len(tree) == 0 or is_binary_search_tree(tree[0]):
        print("CORRECT")
    else:
        print("INCORRECT")

threading.Thread(target=main).start()
