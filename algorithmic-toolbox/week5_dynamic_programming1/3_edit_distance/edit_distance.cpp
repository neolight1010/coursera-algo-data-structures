#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using std::string;

int edit_distance(const string &str1, const string &str2) {
  std::vector<std::vector<int>> dp_table;

  for (int i = 0; i < str2.length() + 1; i++) {
    for (int j = 0; j < str1.length() + 1; j++) {
      if (j == 0) {
        dp_table.push_back(std::vector<int>{i});
        continue;
      }

      if (i == 0) {
        dp_table[i].push_back(j);
        continue;
      }

      // Calculate minimum + 1.
      std::vector<int> i_check = {i - 1, i - 1, i};
      std::vector<int> j_check = {j, j - 1, j - 1};

      std::vector<int> prev_vals;
      for (int k = 0; k < i_check.size(); k++) {
        prev_vals.push_back(dp_table[i_check[k]][j_check[k]]);
      }

      int min = *std::min_element(prev_vals.begin(), prev_vals.end());

      if (str1[j - 1] == str2[i - 1])
        dp_table[i].push_back(prev_vals[1]);
      else
        dp_table[i].push_back(min + 1);
    }
  }

  return dp_table.back().back();
}

int main() {
  string str1;
  string str2;
  std::cin >> str1 >> str2;
  std::cout << edit_distance(str1, str2) << std::endl;
  return 0;
}
