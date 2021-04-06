#include <iostream>
#include <cassert>

long long get_fibonacci_huge_naive(long long n, long long m) {
    if (n <= 1)
        return n;

    long long previous = 0;
    long long current  = 1;

    for (long long i = 0; i < n - 1; ++i) {
        long long tmp_previous = previous;
        previous = current;
        current = tmp_previous + current;
    }

    return current % m;
}

long long get_fibonacci_huge_fast(long long n, long long m) {
    return 0;
}

void test_solution(int n_tests = 40, int max = 50) {
    assert(get_fibonacci_huge_fast(239, 1000) == 161);
    assert(get_fibonacci_huge_fast(2816213588, 239) == 151);

    for (int i=0; i<max; i++) {
        int rand_n = rand() % max + 1;
        int rand_m = rand() % max + 1;

        long long naive_sol = get_fibonacci_huge_naive(rand_n, rand_m);
        long long fast_sol = get_fibonacci_huge_fast(rand_n, rand_m);

        std::cout << rand_n << ' ' << rand_m << ": " << naive_sol
            << ' ' << fast_sol << std::endl;

        assert(naive_sol == fast_sol);
    }
    std::cout << "Test ended successfully" << std::endl;
}

int main() {
    long long n, m;
    // std::cin >> n >> m;

    // std::cout << get_fibonacci_huge_naive(n, m) << '\n';
    test_solution();
    // std::cout << get_fibonacci_huge_fast(n, m) << std::endl;

    return 0;
}
