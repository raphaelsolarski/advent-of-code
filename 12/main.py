# initial_state = '#..#.#..##......###...###'
# rules = {
#     '...##': '#',
#     '..#..': '#',
#     '.#...': '#',
#     '.#.#.': '#',
#     '.#.##': '#',
#     '.##..': '#',
#     '.####': '#',
#     '#.#.#': '#',
#     '#.###': '#',
#     '##.#.': '#',
#     '##.##': '#',
#     '###..': '#',
#     '###.#': '#',
#     '####.': '#'
# }

initial_state = '#.#.#..##.#....#.#.##..##.##..#..#...##....###..#......###.#..#.....#.###.#...#####.####...#####.#.#'
rules = {
    '..#..': '.',
    '#...#': '.',
    '.#...': '#',
    '#.##.': '.',
    '..#.#': '#',
    '#.#.#': '.',
    '###..': '#',
    '###.#': '#',
    '.....': '.',
    '....#': '.',
    '.##..': '#',
    '#####': '.',
    '####.': '.',
    '..##.': '.',
    '##.#.': '#',
    '.#..#': '#',
    '##..#': '.',
    '.##.#': '.',
    '.####': '#',
    '..###': '.',
    '...##': '#',
    '#..##': '#',
    '#....': '.',
    '##.##': '.',
    '#.#..': '.',
    '##...': '.',
    '.#.##': '#',
    '.###.': '#',
    '...#.': '.',
    '#.###': '.',
    '#..#.': '#',
    '.#.#.': '.'
}


def substring_indexes(substring, string):
    """
    Generate indices of where substring begins in string

    # >>> list(find_substring('me', "The cat says meow, meow"))
    [13, 19]
    """
    last_found = -1  # Begin at -1 so the next position to search from is 0
    result = []
    while True:
        # Find next index of substring, by starting after its last known position
        last_found = string.find(substring, last_found + 1)
        if last_found == -1:
            return result
        result.append(last_found)


current_state = initial_state

print('generation: {} : {}'.format(0, current_state))

zero_index = 0

for generation_index in range(1, 21):
    if not current_state.startswith('....'):
        current_state = '....' + current_state
        zero_index = zero_index + 4

    if not current_state.endswith('....'):
        current_state = current_state + '....'

    next_generation = '.' * len(current_state)
    for pattern, replace in rules.items():
        indexes = substring_indexes(pattern, current_state)
        for index in indexes:
            next_generation = "".join((next_generation[:index + 2], replace, next_generation[index + 3:]))

    current_state = next_generation

    print('generation: {} : {}'.format(generation_index, current_state))

indexes_sum = 0

for index, plant in enumerate(current_state):
    if plant == '#':
        number = index - zero_index
        indexes_sum = indexes_sum + number

print(indexes_sum)

# Result: 1917

# count = initial_state.count('.', 0, 4)
# initial_state = '....' + initial_state
# print(initial_state)

# print(current_state.find('...##'))
