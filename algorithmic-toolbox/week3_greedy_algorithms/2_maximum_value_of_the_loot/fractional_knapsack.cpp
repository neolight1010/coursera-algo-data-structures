#include <iostream>
#include <vector>
#include <algorithm>

using std::vector;

struct Item {
  int value, weight;
};

bool compare_value_per_weight(Item item1, Item item2) {
  return (double) item1.value / item1.weight > (double) item2.value / item2.weight;
}

double get_optimal_value(int capacity, vector<int> weights, vector<int> values) {
  double value = 0.0;
  vector<Item> vw_vec; // Vector containing grouped values and weights.

  // Get values per weight
  for (int i=0; i<weights.size(); i++) { 
    vw_vec.push_back({values[i], weights[i]});
  }

  // Sort according to value per weight
  std::sort(vw_vec.begin(), vw_vec.end(), compare_value_per_weight);

  for (int i=0; i < vw_vec.size(); i++) {
    if (capacity == 0)
      break;

    int weight = std::min<int>(capacity, vw_vec[i].weight);
    value += weight * ((double) vw_vec[i].value / vw_vec[i].weight);
    capacity -= weight;
  }

  return value;
}

int main() {
  int n;
  int capacity;
  std::cin >> n >> capacity;
  vector<int> values(n);
  vector<int> weights(n);
  for (int i = 0; i < n; i++) {
    std::cin >> values[i] >> weights[i];
  }

  double optimal_value = get_optimal_value(capacity, weights, values);

  std::cout.precision(10);
  std::cout << optimal_value << std::endl;
  return 0;
}
