from collections import defaultdict
import copy


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

    child_values = {}
    for child_index in range(1, number_of_children + 1):
        child_size, child_value = parse_node(numbers_, nodes_dict, next_read_index, deep)
        size = size + child_size
        next_read_index = next_read_index + child_size  
        child_values[str(child_index)] = child_value
    
    metadata_values = []
    for metadata_index in range(number_of_meta):
        metadata_value = numbers_[next_read_index]
        nodes_dict['metadata'].append(metadata_value)
        size = size + 1
        next_read_index = next_read_index + 1
        metadata_values.append(metadata_value)
    
    if number_of_children == 0:
        value = sum(metadata_values)
    else:
        summed_child_values = 0
        for metadata_value in metadata_values:
            if str(metadata_value) in child_values:
                summed_child_values = summed_child_values + child_values[str(metadata_value)]
        value = summed_child_values

    return (size, value)


numbers = []

with open(file='08/input.txt', mode='tr', encoding='utf-8') as fd:
    raw = fd.read()
    numbers = list(map(lambda x: int(x), raw.split(" ")))

nodes = {'metadata': []}
root_size, root_value = parse_node(numbers, nodes, 0, 0)


print(root_value)