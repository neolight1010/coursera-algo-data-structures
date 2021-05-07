#include <algorithm>
#include <sstream>
#include <iostream>
#include <vector>
#include <string>

using std::vector;
using std::string;

bool compare_extended(string a, string b) {
  std::stringstream ss;
  string new_number_str;

  ss << a;
  ss << b;
  ss >> new_number_str;

  int first_number = std::stoi(new_number_str);

  ss.clear();
  new_number_str.clear();
  ss << b;
  ss << a;
  ss >> new_number_str;

  int second_number = std::stoi(new_number_str);

  return first_number > second_number;
}

string largest_number(vector<string> a) {
  std::sort(a.begin(), a.end(), compare_extended);

  std::stringstream ss;
  for (string i : a) {
    ss << i;
  }

  string result;
  ss >> result;

  return result;
}

int main() {
  int n;
  std::cin >> n;
  vector<string> a(n);
  for (size_t i = 0; i < a.size(); i++) {
    std::cin >> a[i];
  }
  std::cout << largest_number(a);
}
