#include <iostream>
#include <cassert>

long long lcm_naive(int a, int b) {
  for (long l = 1; l <= (long long) a * b; ++l)
    if (l % a == 0 && l % b == 0)
      return l;

  return (long long) a * b;
}

int lcm_fast(int a, int b) {
  return 0;
}

void test_solution(int n_tests=80, int max=300) {
  assert(lcm_fast(6, 8) == 24);
  assert(lcm_fast(761457, 614573) == 467970912861);

  // Stress test
  for (int i=0; i < n_tests; i++) {
    int rand_a = rand() % (max - 2) + 2;
    int rand_b = rand() % (max - 2) + 2;

    int naive_sol = lcm_naive(rand_a, rand_b);
    int fast_sol = lcm_fast(rand_a, rand_b);

    std::cout << rand_a << " " << rand_b
      << ": " << naive_sol << " " << fast_sol << std::endl;

    assert(naive_sol == fast_sol);
  }

  std::cout << "Test ended successfully!" << std::endl;
}

int main() {
  int a, b;
  // std::cin >> a >> b;

  // std::cout << lcm_naive(a, b) << std::endl;
  test_solution();
  // std::cout << lcm_fast(a, b) << std::endl;

  return 0;
}
