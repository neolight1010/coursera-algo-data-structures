#include <iostream>
#include <limits>
#include <vector>

/** Get minimum number of coins to change w with coins 1, 3, 4. */
int get_min_change(int m, std::vector<int> coins = {1, 3, 4}) {
  std::vector<int> min_changes = {0};

  for (int i = 1; i <= m; i++) {
    min_changes.push_back(i);

    for (int coin : coins) {
      if (i >= coin) {
        int change = min_changes[i - coin] + 1;

        if (change < min_changes[i]) {
          min_changes[i] = change;
        }
      }
    }
  }

  return min_changes[m];
}

int main() {
  int m;
  std::cin >> m;
  std::cout << get_min_change(m) << '\n';
}
