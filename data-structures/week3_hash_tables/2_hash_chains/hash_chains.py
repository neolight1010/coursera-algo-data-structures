from typing import List


class HashMap():
    """HashMap of strings. more like a Set, as it only uses keys, not values."""

    _P = 1000000007 
    """Prime number used for the hash function."""

    _X = 263
    """Multiplier number used for the hash function."""

    def __init__(self, buckets_n: int):
        self._m = buckets_n
        """Numbers of buckets for the hash function."""

        self.chains: List[List[str]] = [[] for _ in range(self._m)]

    def _hash(self, s: str):
        """Get the hash number of a string."""
        x = HashMap._X
        p = HashMap._P

        s_ = sum([ord(c) * x ** i for i, c in enumerate(s)])
        return s_ % p % self._m

    def add(self, s: str):
        """Add a string to the HashMap."""
        i = self._hash(s)

        if not s in self.chains[i]:
            self.chains[i].insert(0, s)

    def remove(self, s: str):
        """Remove a string from the HashMap."""
        if self.contains(s):
            i = self._hash(s)
            self.chains[i].remove(s)

    def contains(self, s: str) -> bool:
        """Check if a string exists in the HashMap."""
        i = self._hash(s)

        return s in self.chains[i]


class Query:
    def __init__(self, instruction: str):
        tokens = instruction.split()

        self.type = tokens[0]
        self.arg = tokens[1]

    def process(self, hash_map: HashMap):
        if self.type == "add":
            hash_map.add(self.arg)

        elif self.type == "del":
            hash_map.remove(self.arg)

        elif self.type == "find":
            if hash_map.contains(self.arg):
                print("yes")
            else:
                print("no")

        elif self.type == "check":
            i = int(self.arg)
            print(" ".join(hash_map.chains[i]))


if __name__ == "__main__":
    queries: List[Query] = []

    buckets_n = int(input())
    hash_map = HashMap(buckets_n)

    queries_n = int(input())

    for _ in range(queries_n):
        queries.append(Query(input()))

    for query in queries:
        query.process(hash_map)
