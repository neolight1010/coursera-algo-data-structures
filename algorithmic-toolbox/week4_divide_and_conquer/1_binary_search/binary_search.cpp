#include <iostream>
#include <cassert>
#include <vector>

using std::vector;

int binary_search(const vector<int> &a, int x) {
  int left = 0, right = (int) a.size() - 1; 

  while (left <= right) {
    int middle = (left + right) / 2;

    if (a[middle] == x) return middle;
    if (a[middle] < x) left = middle + 1;
    else right = middle - 1;
  }

  return -1;
}

int linear_search(const vector<int> &a, int x) {
  for (size_t i = 0; i < a.size(); ++i) {
    if (a[i] == x) return i;
  }
  return -1;
}

void stress_test(int max_n=30, int max_diff=50) {
  while (1) {
    int n = rand() % max_n + 1;
    vector<int> a;

    // Fill with random values.
    for (int i=0; i < n; i++) {
      int e;
      if (i==0) e = 0 + rand() % max_diff + 1;
      else e = a[i-1] + rand() % max_diff + 1;
      a.push_back(e);
    }

    // Choose random index.
    int index = rand() % (int) (a.size() + (a.size() * 0.3));
    int element;

    if (index > a.size() - 1) {
      element = a.back() + rand() % max_diff + 1;
      index = -1;
    }
    else element = a[index];

    // Print vales.
    std::cout << std::endl << "Vector:" << std::endl;
    for (int e : a) std::cout << e << " ";
    std::cout << std::endl << "Find: " << element << "; Index?: " << index << std::endl;
    std::cout << "Binary search result: " << binary_search(a, element) << std::endl;

    // Assert.
    assert(binary_search(a, element) == index);
  }
}

int main() {
  // stress_test();

  int n;
  std::cin >> n;
  vector<int> a(n);
  for (size_t i = 0; i < a.size(); i++) {
    std::cin >> a[i];
  }
  int m;
  std::cin >> m;
  vector<int> b(m);
  for (int i = 0; i < m; ++i) {
    std::cin >> b[i];
  }
  for (int i = 0; i < m; ++i) {
    std::cout << binary_search(a, b[i]) << ' ';
    // std::cout << linear_search(a, b[i]) << ' ';
  }
}
