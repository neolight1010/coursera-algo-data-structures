#include <iostream>
#include <map>
#include <numeric>
#include <tuple>
#include <vector>

using std::vector;

int partition3(vector<int> &A, int a, int b, int c, int n,
               std::map<std::tuple<int, int, int, int>, int> &lookup) {
  if (a == 0 && b == 0 && c == 0)
    return 1;

  if (n < 0)
    return 0;

  std::tuple<int, int, int, int> key(a, b, c, n);

  // If key is not found in lookup map.
  if (lookup.find(key) == lookup.end()) {
    // Include in first subgroup.
    int _A = 0;
    if (a - A[n] >= 0)
      _A = partition3(A, a - A[n], b, c, n - 1, lookup);

    // Include in second subgroup.
    int _B = 0;
    if (_A == 0 && b - A[n] >= 0) {
      _B = partition3(A, a, b - A[n], c, n - 1, lookup);
    }

    // Include in third subgroup.
    int _C = 0;
    if (_A == 0 && _B == 0 && c - A[n] >= 0) {
      _C = partition3(A, a, b, c - A[n], n - 1, lookup);
    }

    int result = (_A == 1) || (_B == 1) || (_C == 1);
    lookup[key] = result;
  }
  return lookup[key];
}

int partition3_callback(vector<int> &A) {
  int sum = std::accumulate(A.begin(), A.end(), 0);

  if (sum % 3 != 0)
    return 0;

  std::map<std::tuple<int, int, int, int>, int> lookup;
  return partition3(A, sum / 3, sum / 3, sum / 3, A.size() - 1, lookup);
}

int main() {
  int n;
  std::cin >> n;
  vector<int> A(n);
  for (size_t i = 0; i < A.size(); ++i) {
    std::cin >> A[i];
  }
  std::cout << partition3_callback(A) << '\n';
}
