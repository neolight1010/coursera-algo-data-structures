#include <algorithm>
#include <iostream>
#include <vector>

using std::vector;

int get_majority_element(vector<int> &a, int left, int right) {
  if (left == right) return -1;
  if (left + 1 == right) return a[left];

  int middle_i = (left + right) / 2;
  int majority_left = get_majority_element(a, left, middle_i);
  int majority_right = get_majority_element(a, middle_i, right);

  int count_left = 0;
  int count_right = 0;

  for (int i=left; i < right; i++) {
    if (a[i] == majority_left) count_left++;
    if (a[i] == majority_right) count_right++;

    if (count_left > (right - left) / 2) return majority_left;
    if (count_right > (right - left) / 2) return majority_right;
  }

  return -1;
}

int main() {
  int n;
  std::cin >> n;
  vector<int> a(n);
  for (size_t i = 0; i < a.size(); ++i) {
    std::cin >> a[i];
  }
  std::cout << (get_majority_element(a, 0, a.size()) != -1) << '\n';
}
