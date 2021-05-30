#include <algorithm>
#include <iostream>
#include <vector>

using std::vector;

vector<int> optimal_sequence(int n) {
  vector<int> min_operations = {0};
  vector<int> prev_nums = {0};

  for (int i = 1; i <= n; i++) {
    if (i == 1) {
      min_operations.push_back(0);
      prev_nums.push_back(1);
      continue;
    }

    int num_x2, num_x3, num_p1;
    bool found_x2 = false;
    bool found_x3 = false;

    num_p1 = min_operations[i - 1] + 1;

    if (i % 2 == 0) {
      found_x2 = true;
      num_x2 = min_operations[i / 2] + 1;
    }

    if (i % 3 == 0) {
      found_x3 = true;
      num_x3 = min_operations[i / 3] + 1;
    }

    // Change in case i wasn't divisible by 2 and 3.
    if (!found_x2)
      num_x2 = num_p1 + 1;

    if (!found_x3)
      num_x3 = num_p1 + 1;

    int min_op = std::min(num_x2, num_x3);
    min_op = std::min(min_op, num_p1);

    min_operations.push_back(min_op);

    // Detect previous number
    if (min_op == num_p1)
      prev_nums.push_back(i - 1);
    else if (min_op == num_x2)
      prev_nums.push_back(i / 2);
    else if (min_op == num_x3)
      prev_nums.push_back(i / 3);
  }

  vector<int> sequence = {n};
  int c = n;
  while (c != 1) {
    sequence.insert(sequence.begin(), prev_nums[c]);
    c = prev_nums[c];
  }

  return sequence;
}

int main() {
  int n;
  std::cin >> n;
  vector<int> sequence = optimal_sequence(n);
  std::cout << sequence.size() - 1 << std::endl;
  for (size_t i = 0; i < sequence.size(); ++i) {
    std::cout << sequence[i] << " ";
  }
}
