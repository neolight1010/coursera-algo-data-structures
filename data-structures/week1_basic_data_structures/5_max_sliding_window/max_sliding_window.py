from typing import List


def max_sliding_window_naive(sequence: List[int], m: int) -> List[int]:
    dequeue: list[int] = []
    max_list: list[int] = []

    # Initial dequeue
    for i in range(m):
        current = sequence[i]

        while dequeue and sequence[dequeue[0]] < current:
            dequeue.pop(0)

        dequeue.insert(0, i)

    max_list.append(sequence[dequeue[-1]])

    for i in range(m, len(sequence)):
        out_of_window_i = i - m
        current = sequence[i]

        while dequeue and dequeue[-1] <= out_of_window_i:
            dequeue.pop(-1)

        while dequeue and sequence[dequeue[0]] < current:
            dequeue.pop(0)

        dequeue.insert(0, i)
        max_list.append(sequence[dequeue[-1]])

    return max_list


if __name__ == '__main__':
    n = int(input())
    input_sequence = [int(i) for i in input().split()]
    assert len(input_sequence) == n
    window_size = int(input())

    print(*max_sliding_window_naive(input_sequence, window_size))

