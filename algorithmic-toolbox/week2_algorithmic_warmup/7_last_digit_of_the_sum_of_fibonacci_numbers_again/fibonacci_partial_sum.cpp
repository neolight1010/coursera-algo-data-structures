#include <iostream>
#include <cassert>

int get_fibonacci_partial_sum_naive(long long from, long long to) {
    long long sum = 0;

    long long current = 0;
    long long next  = 1;

    for (long long i = 0; i <= to; ++i) {
        if (i >= from) {
            sum += current;
        }

        long long new_current = next;
        next = next + current;
        current = new_current;
    }

    return sum % 10;
}

int get_fibonacci_partial_sum_fast(long long from, long long to) {
    from = from % 60;
    to = from + (to - from) % 60;
    int sum = 0;

    int previous = 0;
    int current = 1;

    for (int i=1; i < to + 1; i++) {
        if (i >= from)
            sum = (sum + current) % 10;

        int temp_current = current;
        current = (previous + current) % 10;
        previous = temp_current;
    }

    return sum;
}

void test_solution(int n_tests = 40, int max = 60) {
    std::cout << "- " << 3 << ", " << 7 << ": " << get_fibonacci_partial_sum_fast(3, 7) << std::endl;
    assert(get_fibonacci_partial_sum_fast(3, 7) == 1);
    
    std::cout << "- " << 10 << ", " << 10 << ": " << get_fibonacci_partial_sum_fast(10, 10) << std::endl;
    assert(get_fibonacci_partial_sum_fast(10, 10) == 5);


    std::cout << "- " << 10 << ", " << 200 << ": " << get_fibonacci_partial_sum_fast(10, 200) << std::endl;
    assert(get_fibonacci_partial_sum_fast(10, 200) == 2);

    // Stress test
    for (int i=0; i < n_tests; i++) {
        long long rand_to = rand() % (max - 1) + 1;
        long long rand_from = rand() % (rand_to - 1) + 1;

        std::cout << "- " << rand_from << ", " << rand_to << std::endl;

        int naive_sol = get_fibonacci_partial_sum_naive(rand_to, rand_from);
        int fast_sol = get_fibonacci_partial_sum_fast(rand_to, rand_from);

        std::cout << "   " << naive_sol << " " << fast_sol << std::endl;

        assert(naive_sol == fast_sol);
    }
    std::cout << "Test ended successfully" << std::endl;
}

int main() {
    long long from, to;
    std::cin >> from >> to;

    // std::cout << get_fibonacci_partial_sum_naive(from, to) << '\n';
    // test_solution();
    std::cout << get_fibonacci_partial_sum_fast(from, to) << std::endl;

    return 0;
}
