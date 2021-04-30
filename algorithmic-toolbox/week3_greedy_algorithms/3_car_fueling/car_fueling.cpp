#include <iostream>
#include <vector>

using std::cin;
using std::cout;
using std::vector;

int compute_min_refills(int dist, int tank, vector<int> &stops) {
    const int full_tank = tank;
    int refills = 0;

    stops.push_back(dist);

    for (int i=0; i < stops.size(); i++) {
        int d_to_next_stop = stops[0];
        if (i != 0) d_to_next_stop = stops[i] - stops[i-1];

        if (d_to_next_stop > full_tank) return -1;
        if (d_to_next_stop > tank) {
            refills += 1;
            tank = full_tank;
        }

        tank -= d_to_next_stop;
    }

    return refills;
}


int main() {
    int d = 0;
    cin >> d;
    int m = 0;
    cin >> m;
    int n = 0;
    cin >> n;

    vector<int> stops(n);
    for (size_t i = 0; i < n; ++i)
        cin >> stops.at(i);

    cout << compute_min_refills(d, m, stops) << "\n";

    return 0;
}
