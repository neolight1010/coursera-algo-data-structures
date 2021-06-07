#include <iostream>
#include <vector>

using std::vector;

int optimal_weight(int W, const vector<int> &w) {
  vector<vector<int>> dp_table;

  for (int i = 0; i <= w.size(); i++) {
    for (int j = 0; j <= W; j++) {
      if (j == 0)
        dp_table.push_back(vector<int>{});

      if (i == 0 || j == 0) {
        dp_table[i].push_back(0);
        continue;
      }

      dp_table[i].push_back(dp_table[i - 1][j]);

      int weight = w[i - 1];
      if (j >= weight) {
        if (dp_table[i - 1][j - weight] + weight > dp_table[i][j])
          dp_table[i][j] = dp_table[i - 1][j - weight] + weight;
      }
    }
  }

  return dp_table.back().back();
}

int main() {
  int n, W;
  std::cin >> W >> n;
  vector<int> w(n);
  for (int i = 0; i < n; i++) {
    std::cin >> w[i];
  }
  std::cout << optimal_weight(W, w) << '\n';
}
