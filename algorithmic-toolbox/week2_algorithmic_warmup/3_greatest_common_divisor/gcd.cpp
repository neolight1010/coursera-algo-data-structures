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
  if (a == 0) return b;
  if (b == 0) return a;

  int a_mod_b = a % b;
  return gcd_fast(b, a_mod_b);
}

void test_solution(int n_tests = 50, int max=200) {
  assert(gcd_fast(18, 35) == 1);
  assert(gcd_fast(28851538, 1183019) == 17657);

  // Stress test
  for (int i=0; i < n_tests; i++) {
    int rand_a = rand() % max + 1;
    int rand_b = rand() % max + 1;

    int naive_res = gcd_naive(rand_a, rand_b);
    int fast_res = gcd_fast(rand_a, rand_b);

    std::cout << rand_a << " " << rand_b << ": " <<
      naive_res << " " << fast_res << std::endl;

    assert(naive_res == fast_res);
  }

  std::cout << "Test ended successfully";
}

int main() {
  int a, b;
  std::cin >> a >> b;

  // std::cout << gcd_naive(a, b) << std::endl;
  // test_solution(100, 500);
  std::cout << gcd_fast(a, b) << std::endl;

  return 0;
}
