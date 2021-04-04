#include <iostream>
#include <cassert>

int gcd_naive(int a, int b) {
  int current_gcd = 1;
  for (int d = 2; d <= a && d <= b; d++) {
    if (a % d == 0 && b % d == 0) {
      if (d > current_gcd) {
        current_gcd = d;
      }
    }
  }
  return current_gcd;
}

int gcd_fast(int a, int b) {

}

int test_solution(int n_tests = 50, int max=200) {
  assert(gcd_fast(18, 35) == 1);
  assert(gcd_fast(28851538, 1183019) == 17657);

  // Stress test
  for (int i=0; i < n_tests; i++) {
    int rand_a = rand() % max;
    int rand_b = rand() % max;

    std::cout << rand_a << " " << rand_b;

    assert(gcd_naive(rand_a, rand_b) == gcd_fast(rand_a, rand_b));
  }

  std::cout << "Test ended successfully";
}

int main() {
  int a, b;
  // std::cin >> a >> b;

  // std::cout << gcd_naive(a, b) << std::endl;
  test_solution();

  return 0;
}
