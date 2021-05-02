#include <algorithm>
#include <iostream>
#include <climits>
#include <vector>

using std::vector;

struct Segment {
  int start, end;
};

bool greater_start(Segment a , Segment b) {
  return a.start > b.start;
}

vector<int> optimal_points(vector<Segment> &segments) {
  vector<int> points;

  // Base case
  if (segments.size() == 0)
    return points;

  std::sort(segments.begin(), segments.end(), greater_start);
  int point = segments.front().start;
  points.push_back(point);

  vector<Segment> new_segments;
  for (int i=0; i < segments.size(); i++) {
    if (!(point >= segments[i].start && point <= segments[i].end)) {
      new_segments.push_back(segments[i]);
    }
  }

  // Recursive call
  vector<int> new_points = optimal_points(new_segments);
  points.insert(points.end(), new_points.begin(), new_points.end());

  return points;
}

int main() {
  int n;
  std::cin >> n;
  vector<Segment> segments(n);
  for (size_t i = 0; i < segments.size(); ++i) {
    std::cin >> segments[i].start >> segments[i].end;
  }
  vector<int> points = optimal_points(segments);
  std::cout << points.size() << "\n";
  for (size_t i = 0; i < points.size(); ++i) {
    std::cout << points[i] << " ";
  }
}
