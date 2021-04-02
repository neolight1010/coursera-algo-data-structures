#include <iostream>
#include <vector>
#include <algorithm>

long long MaxPairwiseProduct(const std::vector<int>& numbers) {
    long long max_product = 0;
    int n = numbers.size();

    // Find max 2 numbers
    int max_i1, max_i2;
    for (int i=0; i<n; i++) {
        if (i==0) {
            max_i1 = i;
            continue;
        }

        if (numbers[i] > numbers[max_i1])
            max_i1 = i;
    }

    for (int i=0; i<n; i++) {
        if (i==0) {
            if (max_i1 != 0) max_i2 = i;
            else max_i2 = i + 1;
            continue;
        }

        if (numbers[i] > numbers[max_i2] && i != max_i1) {
            max_i2 = i;
        }
    }

    max_product = (long long) numbers[max_i1] * (long long) numbers[max_i2];
    return max_product;
}

int main() {
    int n;
    std::cin >> n;
    std::vector<int> numbers(n);
    for (int i = 0; i < n; ++i) {
        std::cin >> numbers[i];
    }

    std::cout << MaxPairwiseProduct(numbers) << "\n";
    return 0;
}
