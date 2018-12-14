from llist import dllist


def recipes_from_sum(num):
    num_str = str(num)
    _new_recipes = []
    for c in num_str:
        _new_recipes.append(int(c))
    return _new_recipes


def node_forward(linked_list, node, steps):
    "return node steps numbers after node"
    current_node = node
    for i in range(steps):
        if current_node.next is None:  # loop
            current_node = linked_list.first
        else:
            current_node = current_node.next
    return current_node


input_value = 793031

elves = [0, 1]

start_recipes = dllist([3, 7])
recipes_counter = 2

elves_current_recipes = {
    "0": start_recipes.nodeat(0),
    "1": start_recipes.nodeat(1)
}

desired_recipes = 10 + input_value
current_sum = sum(map(lambda n: n.value, elves_current_recipes.values()))

while True:
    new_recipes = recipes_from_sum(sum(map(lambda n: n.value, elves_current_recipes.values())))
    for new_recipe in new_recipes:
        start_recipes.appendright(new_recipe)
        recipes_counter = recipes_counter + 1
        if recipes_counter >= desired_recipes:
            break
    for elf in elves:
        current_recipe = elves_current_recipes[str(elf)]
        steps_forward = 1 + current_recipe.value
        elves_current_recipes[str(elf)] = node_forward(start_recipes, current_recipe, steps_forward)

    # print(start_recipes)
    if recipes_counter >= desired_recipes:
        break

print("computed")

result = []
for i in range(0, 10):
    result.append(str(start_recipes[i-10]))


print("".join(result))


# (3)[7]
# (3)[7] 1  0
# 3  7  1 [0](1) 0
# 3  7  1  0 [1] 0 (1)
# (3) 7  1  0  1  0 [1] 2
# 3  7  1  0 (1) 0  1  2 [4]
# 3  7  1 [0] 1  0 (1) 2  4  5
# 3  7  1  0 [1] 0  1  2 (4) 5  1
# 3 (7) 1  0  1  0 [1] 2  4  5  1  5
# 3  7  1  0  1  0  1  2 [4](5) 1  5  8
# 3 (7) 1  0  1  0  1  2  4  5  1  5  8 [9]
# 3  7  1  0  1  0  1 [2] 4 (5) 1  5  8  9  1  6
# 3  7  1  0  1  0  1  2  4  5 [1] 5  8  9  1 (6) 7 