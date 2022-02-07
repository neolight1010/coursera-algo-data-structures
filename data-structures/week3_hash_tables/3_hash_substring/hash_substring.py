from typing import List


class RollingHash:
    _X = 1
    PRIME = 100000004987

    def __init__(self, text: str, window_size: int):
        self.text = text
        self.window_size: int = 0

        self.current_hash: int = 0
        self.window_index: int = 0

        while (self.window_size < window_size):
            self.expand_right()

    @staticmethod
    def _char_to_int(char: str) -> int:
        if (len(char) != 1):
            raise ValueError(f'"{char}" is not a char.')

        return ord(char) - ord("A")

    def expand_right(self):
        """Increases the size of the window by 1 to the right, while updating
        `current_hash`."""
        next_char = self.text[self.window_index + self.window_size]

        # Shift left current_hash value.
        self.current_hash *= RollingHash._X

        # Add new value to hash
        self.current_hash += RollingHash._char_to_int(next_char)
        self.current_hash %= RollingHash.PRIME

        self.window_size += 1

    def shrink_left(self):
        """Remove the first char of the window and update `current_hash`,
        effectively reducing `window_size` and incrementing `window_index` by
        one."""
        first_char = self.text[self.window_index]

        self.current_hash -= RollingHash._char_to_int(first_char) * \
                             (RollingHash._X ** (self.window_size - 1))

        self.window_size -= 1
        self.window_index += 1

    def shift_window_right(self):
        """Shift the current window one index to the right."""
        self.shrink_left()
        self.expand_right()


if __name__ == '__main__':
    pattern = input()
    text = input()

    pattern_hash = RollingHash(pattern, len(pattern)).current_hash

    rolling_hash = RollingHash(text, len(pattern))
    match_indices: List[int] = []

    for i in range(0, len(text) - len(pattern) + 1):
        if rolling_hash.current_hash == pattern_hash:
            # Check if substring is correct
            if text[i : i + len(pattern)] == pattern:
                match_indices.append(i)

        # Don't shift right in the last iteration
        if not i == len(text) - len(pattern):
            rolling_hash.shift_window_right()

    print(*match_indices)
