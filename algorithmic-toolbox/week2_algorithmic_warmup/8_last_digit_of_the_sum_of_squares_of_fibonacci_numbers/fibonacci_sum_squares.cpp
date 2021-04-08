#include <iostream>
#include <cassert>

int fibonacci_sum_squares_naive(long long n) {
    if (n <= 1)
        return n;

    long long previous = 0;
    long long current  = 1;
    long long sum      = 1;

    for (long long i = 0; i < n - 1; ++i) {
        long long tmp_previous = previous;
        previous = current;
        current = tmp_previous + current;
        sum += current * current;
    }

    return sum % 10;
}

int fibonacci_sum_squares_fast(long long n) {
    if (n <= 1) return n;

    n = n % 60;
    int f_n, f_np1;

    int previous = 0;
    int current = 1;
    for (int i=0; i < n; i++) {
        int temp_current = current;
        current = (previous + current) % 10;
        previous = temp_current;

        if (i == n-2) f_n = current;
        if (i == n-1) f_np1 = current;
    }

    return (f_n * f_np1) % 10;
}

void test_solution(int n_tests = 60, int max_n =50) {
    std::cout << "- 7: " << fibonacci_sum_squares_fast(7) << std::endl;
    assert(fibonacci_sum_squares_fast(7) == 3);

    std::cout << "- 73: " << fibonacci_sum_squares_fast(73) << std::endl;
    assert(fibonacci_sum_squares_fast(73) == 1);

    std::cout << "- 1234567890: " << fibonacci_sum_squares_fast(1234567890) << std::endl;
    assert(fibonacci_sum_squares_fast(1234567890) == 0);

    // Stress test
    for (int i=0; i < n_tests; i++) {
        long long rand_n = rand() % max_n;

        int naive_sol = fibonacci_sum_squares_naive(rand_n);
        int fast_sol = fibonacci_sum_squares_fast(rand_n);

        std::cout << "- " << rand_n << ": " << naive_sol << " " << fast_sol << std::endl;
        assert(naive_sol == fast_sol);
    }

    std::cout << "Test ended successfully!" << std::endl;
}

int main() {
    long long n = 0;
    std::cin >> n;

    // std::cout << fibonacci_sum_squares_naive(n);
    // test_solution();
    std::cout << fibonacci_sum_squares_fast(n);

    return 0;
}
