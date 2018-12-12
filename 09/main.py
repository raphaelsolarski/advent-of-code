from collections import defaultdict
from llist import dllist, dllistnode

def next_player(players_number, current):
    return ((current + 1) % players_number)

def node_back(linked_list, node, steps):
    "return node steps numbers before node"
    current_node = node
    for i in range(steps):
        if current_node.prev is None: # loop
            current_node = linked_list.last
        else:
            current_node = current_node.prev
    return current_node

def node_forward(linked_list, node, steps):
    "return node steps numbers after node"
    current_node = node
    for i in range(steps):
        if current_node.next is None: # loop
            current_node = linked_list.first
        else:
            current_node = current_node.next
    return current_node

players_scores = defaultdict(lambda: 0)
players_number = 455
last_marble_points = 7122300

marbles_list = dllist([0])

current_player = -1
current_node = marbles_list.first

for marble in range(1, last_marble_points + 1):
    current_player = next_player(players_number, current_player)
    if marble % 23 == 0:
        current_points = players_scores[current_player]

        marble_7_counter_clockwise = node_back(marbles_list, current_node, 7)
        next_current_marble = node_forward(marbles_list, marble_7_counter_clockwise, 1)

        removed_value = marble_7_counter_clockwise.value
        marbles_list.remove(marble_7_counter_clockwise)
        players_scores[current_player] =  current_points + marble + removed_value

        current_node = next_current_marble
    else:
        node_after_insert = node_forward(marbles_list, current_node, 2)
        current_node = marbles_list.insert(marble, node_after_insert)

print(max(players_scores.values()))
