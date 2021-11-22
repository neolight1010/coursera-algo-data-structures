from typing import List, NamedTuple


class Request(NamedTuple):
    arrived_at: int
    time_to_process: int


class Response(NamedTuple):
    was_dropped: bool
    started_at: int


class Buffer:
    def __init__(self, size: int):
        self.size = size
        self.finish_times: list[int] = []

    def process_request(self, request: Request) -> Response:
        # Process previous packages
        n_finished = 0

        for finish_time in self.finish_times:
            if request.arrived_at >= finish_time:
                n_finished += 1
            else:
                break

        for _ in range(n_finished):
            self.finish_times.pop(0)

        if len(self.finish_times) >= self.size:
            return Response(True, -1)

        # Process current request
        previous_time = None
        if len(self.finish_times) >= 1:
            previous_time = self.finish_times[-1]
        else:
            previous_time = request.arrived_at

        new_finish_time = previous_time + request.time_to_process

        self.finish_times.append(new_finish_time)

        return Response(False, previous_time)


def process_requests(requests: List[Request], buffer: Buffer) -> List[Response]:
    responses: list[Response] = []

    for request in requests:
        responses.append(buffer.process_request(request))

    return responses


def main():
    buffer_size, n_requests = map(int, input().split())

    requests: list[Request] = []
    for _ in range(n_requests):
        arrived_at, time_to_process = map(int, input().split())
        requests.append(Request(arrived_at, time_to_process))

    buffer = Buffer(buffer_size)
    responses = process_requests(requests, buffer)

    for response in responses:
        print(response.started_at if not response.was_dropped else -1)


def test_all():
    assert process_requests(
        [],
        Buffer(1)
    ) == []

    assert process_requests(
        [
            Request(0, 0)
        ],
        Buffer(1)
    ) == [
        Response(False, 0)
    ]

    assert process_requests(
        [
            Request(0, 1),
            Request(0, 1),
        ],
        Buffer(1)
    ) == [
        Response(False, 0),
        Response(True, -1),
    ]

    assert process_requests(
        [
            Request(0, 1),
            Request(1, 1),
        ],
        Buffer(1)
    ) == [
        Response(False, 0),
        Response(False, 1),
    ]


if __name__ == "__main__":
    # test_all()
    # print("Tests run successfully!")

    main()
