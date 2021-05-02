#include <iostream>
#include <vector>

using std::vector;

vector<int> optimal_summands(int n) {
  vector<int> summands;
  int sum = 0;

  if (n == 1)
    return (vector<int>) {1};

  for (int i=1; i <= n; i++) {
    if (sum + i > n) {
      summands.push_back(n - sum);
      break;
    }

    summands.push_back(i);
    sum += i;
  }

  summands.rbegin()[1] = summands.rbegin()[0] + summands.rbegin()[1];
  summands.erase(summands.end() - 1);

  return summands;
}

int main() {
  int n;
  std::cin >> n;
  vector<int> summands = optimal_summands(n);
  std::cout << summands.size() << '\n';
  for (size_t i = 0; i < summands.size(); ++i) {
    std::cout << summands[i] << ' ';
  }
}
