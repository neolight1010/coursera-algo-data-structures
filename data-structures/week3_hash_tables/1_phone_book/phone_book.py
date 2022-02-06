from typing import List

class Query:
    def __init__(self, query):
        self.type: str = query[0] # add, del or find
        self.number: int = int(query[1])

        if self.type == 'add':
            self.name: str = query[2]

def read_queries() -> List[Query]:
    n = int(input())
    return [Query(input().split()) for _ in range(n)]

def write_responses(result: List[str]) -> None:
    print('\n'.join(result))

def process_queries(queries: List[Query]) -> List[str]:
    contacts: dict[int, str] = {}
    result: List[str] = []

    for query in queries:
        if query.type == "add":
            contacts[query.number] = query.name
        elif query.type == "del":
            contacts.pop(query.number, None)
        elif query.type == "find":
            if query.number in contacts:
                result.append(contacts[query.number])
            else:
                result.append('not found')

    return result

if __name__ == '__main__':
    write_responses(process_queries(read_queries()))

