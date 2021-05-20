#include <algorithm>
#include <cassert>
#include <iostream>
#include <vector>

using std::vector;

int binary_search(vector<int> a, int x, int dir = 1) {
  int l = 0;
  int r = a.size() - 1;

  while (l <= r) {
    int middle = (l + r) / 2;

    if (a[middle] == x) {
      int i = middle;
      while (a[i + dir] == x)
        i += dir;
      return i;
    }

    if (a[middle] < x)
      l = middle + 1;
    else
      r = middle - 1;
  }

  return l;
}

vector<int> fast_count_segments(vector<int> starts, vector<int> ends,
                                vector<int> points) {
  vector<int> cnt;

  std::sort(starts.begin(), starts.end());
  std::sort(ends.begin(), ends.end());

  for (int point : points) {
    int l = binary_search(starts, point) + 1;
    if (l > starts.size())
      l = starts.size();
    if (starts[l - 1] > point)
      l--;

    int r = starts.size() - binary_search(ends, point, -1);

    // std::cout << std::endl
    //           << "Point: " << point << "\nl: " << l << "\nr: " << r <<
    //           std::endl
    //           << std::endl;

    cnt.push_back((l + r) - starts.size());
  }

  return cnt;
}

vector<int> naive_count_segments(vector<int> starts, vector<int> ends,
                                 vector<int> points) {
  vector<int> cnt(points.size());
  for (size_t i = 0; i < points.size(); i++) {
    for (size_t j = 0; j < starts.size(); j++) {
      cnt[i] += starts[j] <= points[i] && points[i] <= ends[j];
    }
  }
  return cnt;
}

void stress_test(int max = 5) {
  while (true) {
    int n_segments = rand() % max + 1;
    int n_points = rand() % max + 1;

    vector<int> starts;
    vector<int> ends;
    vector<int> points;

    for (int i = 0; i < n_segments; i++) {
      int start = rand() % max - 15;
      int end = start + (rand() % max + 1);

      starts.push_back(start);
      ends.push_back(end);
    }

    for (int i = 0; i < n_points; i++) {
      points.push_back(rand() % (max * 2) - 15);
    }

    // Debug print
    std::cout << "Points: ";
    for (int x : points)
      std::cout << x << " ";
    std::cout << std::endl << "Segments:" << std::endl;
    for (int i = 0; i < n_segments; i++) {
      std::cout << starts[i] << " " << ends[i] << std::endl;
    }

    std::cout << std::endl << "Naive: ";
    for (int x : naive_count_segments(starts, ends, points))
      std::cout << x << " ";
    std::cout << std::endl << "Fast:  ";
    for (int x : fast_count_segments(starts, ends, points))
      std::cout << x << " ";

    std::cout << std::endl << std::endl;
    assert(naive_count_segments(starts, ends, points) ==
           fast_count_segments(starts, ends, points));
  }
}

int main() {
  // stress_test();
  int n, m;
  std::cin >> n >> m;
  vector<int> starts(n), ends(n);
  for (size_t i = 0; i < starts.size(); i++) {
    std::cin >> starts[i] >> ends[i];
  }
  vector<int> points(m);
  for (size_t i = 0; i < points.size(); i++) {
    std::cin >> points[i];
  }
  // use fast_count_segments
  vector<int> cnt = fast_count_segments(starts, ends, points);
  // vector<int> cnt = naive_count_segments(starts, ends, points);

  for (size_t i = 0; i < cnt.size(); i++) {
    std::cout << cnt[i] << ' ';
  }
}
