from collections import defaultdict
import copy

# import sys
# sys.setrecursionlimit(40000) # okazało się niepotrzebne domyślnie jest ok 1024


# Specifically, a node consists of:
# A header, which is always exactly two numbers:
# The quantity of child nodes.
# The quantity of metadata entries.
# Zero or more child nodes (as specified in the header).
# One or more metadata entries (as specified in the header).

def parse_node(numbers_, nodes_dict, start, deep):
    deep = deep + 1
    print("deep: {}", deep)
    number_of_children = numbers_[start]
    number_of_meta = numbers_[start + 1]
    next_read_index = start + 2 # zero based
    size = 2
    for child_index in range(number_of_children):
        child_size = parse_node(numbers_, nodes_dict, next_read_index, deep)
        size = size + child_size
        next_read_index = next_read_index + child_size  
    
    for metadata_index in range(number_of_meta):
        nodes_dict['metadata'].append(numbers_[next_read_index])
        size = size + 1
        next_read_index = next_read_index + 1
    
    return size


numbers = []

with open(file='08/input.txt', mode='tr', encoding='utf-8') as fd:
    raw = fd.read()
    numbers = list(map(lambda x: int(x), raw.split(" ")))

nodes = {'metadata': []}
parse_node(numbers, nodes, 0, 0)


print(sum(nodes['metadata']))