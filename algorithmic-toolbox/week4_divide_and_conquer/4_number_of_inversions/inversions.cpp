#include <iostream>
#include <vector>

using std::vector;

struct MergeSortResult {
  vector<int> sorted;
  long long inversions;
};

MergeSortResult merge(vector<int> a, vector<int> b) {
  vector<int> new_vec;
  long long inversions = 0;

  while (a.size() != 0 && b.size() != 0) {
    if (a.front() <= b.front()) {
      new_vec.push_back(a.front());
      a.erase(a.begin());
    } else {
      inversions += a.size();
      new_vec.push_back(b.front());
      b.erase(b.begin());
    }
  }

  new_vec.insert(new_vec.end(), a.begin(), a.end());
  new_vec.insert(new_vec.end(), b.begin(), b.end());

  MergeSortResult result;
  result.sorted = new_vec;
  result.inversions = inversions;

  return result;
}

MergeSortResult merge_sort(vector<int> a) {
  MergeSortResult result;

  if (a.size() == 1) {
    result.sorted = a;
    result.inversions = 0;
    return result;
  }

  int m = a.size() / 2;
  vector<int> a_left(a.begin(), a.begin() + m);
  vector<int> a_right(a.begin() + m, a.end());

  MergeSortResult merged_left = merge_sort(a_left);
  MergeSortResult merged_right = merge_sort(a_right);

  result = merge(merged_left.sorted, merged_right.sorted);
  result.inversions += merged_left.inversions + merged_right.inversions;
  return result;
}


int main() {
  int n;
  std::cin >> n;
  vector<int> a(n);
  for (size_t i = 0; i < a.size(); i++) {
    std::cin >> a[i];
  }

  MergeSortResult result = merge_sort(a);

  // for (int e : result.sorted) {
  //   std::cout << e << " ";
  // }
  // std::cout << std::endl;
  std::cout << result.inversions;
}
