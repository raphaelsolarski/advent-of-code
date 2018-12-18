from collections import defaultdict
import uuid
import time

current_milli_time = lambda: int(round(time.time() * 1000))


def parse_file(file, _elves_power):
    with open(file=file, mode='tr', encoding='utf-8') as fd:
        _units = {}
        _map = []
        _height = 0
        _width = 0
        for _y, line in enumerate(fd):
            current_line_length = 0
            if line == '':
                break
            for _x, c in enumerate(line):
                if c == '\n':
                    continue
                if c == '#':
                    _map.append('#')
                if c == '.':
                    _map.append('.')
                if c == 'G':
                    _map.append('.')
                    _units[(_x, _y)] = {'pos': (_x, _y), 'fraction': 'G', 'hit_points': 200, 'attack_power': 3,
                                        'uuid': str(uuid.uuid4())}
                if c == 'E':
                    _map.append('.')
                    _units[(_x, _y)] = {'pos': (_x, _y), 'fraction': 'E', 'hit_points': 200,
                                        'attack_power': _elves_power,
                                        'uuid': str(uuid.uuid4())}
                current_line_length = current_line_length + 1
            _width = max(_width, current_line_length)
            _height = _height + 1
        return _map, _units, _height, _width


def print_map(_map, _units, _width, _height):
    for y in range(0, _height):
        for x in range(0, _width):
            t = _map[y * _width + x]
            if (x, y) in _units:
                print(_units[(x, y)]['fraction'], end='')
            else:
                print(t, end='')
        units_status = sorted(list(filter(lambda v: v['pos'][1] == y, _units.values())), key=lambda t: t['pos'][0])
        if len(units_status) > 0:
            for status in units_status:
                print(' {}({}) '.format(status['fraction'], status['hit_points']), end='')
            print()
        else:
            print()


def print_map_with_distances(_map, _units, _width, _height, _distances):
    for y in range(0, _height):
        for x in range(0, _width):
            t = _map[y * _width + x]
            if (x, y) in _units:
                print(_units[(x, y)]['fraction'], end='')
            elif (x, y) in _distances:
                print(_distances[(x, y)], end='')
            else:
                print(t, end='')
        print()


def find_all_from_fraction(_fraction, _units):
    return list(filter(lambda t: t[1]['fraction'] == _fraction, _units.items()))


def get_opposite_fraction(_fraction):
    if _fraction == 'G':
        return 'E'
    elif _fraction == 'E':
        return 'G'
    else:
        raise Exception('Unsupported fraction')


def field(_map, _x, _y, _width):
    assert _x >= 0
    assert _y >= 0
    return _map[_y * _width + _x]


def upper_field_coordinates(_x, _y):
    return _x, _y - 1


def upper_field(_map, _x, _y, _width):
    return field(_map, _x, _y - 1, _width)


def left_field(_map, _x, _y, _width):
    assert _x - 1 >= 0
    return field(_map, _x - 1, _y, _width)


def left_field_coordinates(_x, _y):
    return _x - 1, _y


def right_field(_map, _x, _y, _width):
    assert _x + 1 < _width
    return field(_map, _x + 1, _y, _width)


def right_field_coordinates(_x, _y):
    return _x + 1, _y


def down_field(_map, _x, _y, _width):
    return field(_map, _x, _y + 1, _width)


def down_field_coordinates(_x, _y):
    return _x, _y + 1


def get_all_blank_fields(_map, _units, _width, _height):
    blank = []
    for _y in range(0, _height):
        for _x in range(0, _width):
            if field(_map, _x, _y, _width) == '.' and (_x, _y) not in _units:
                blank.append((_x, _y))
    return blank


def get_neighbors(_map, _x, _y, _width, _height, _units):
    to_check = [
        upper_field_coordinates(_x, _y),
        right_field_coordinates(_x, _y),
        down_field_coordinates(_x, _y),
        left_field_coordinates(_x, _y)
    ]
    neighbors = []
    for coordinates in to_check:
        if field(_map, coordinates[0], coordinates[1], _width) == '.' and coordinates not in _units:
            neighbors.append(coordinates)
    return neighbors


def get_neighbors_enemies(_map, _x, _y, _width, _height, _units, _enemy_fraction):
    to_check = [
        upper_field_coordinates(_x, _y),
        right_field_coordinates(_x, _y),
        down_field_coordinates(_x, _y),
        left_field_coordinates(_x, _y)
    ]
    neighbors = []
    for coordinates in to_check:
        if coordinates in _units and _units[coordinates]['fraction'] == _enemy_fraction:
            neighbors.append(coordinates)
    return neighbors


def build_distance_map(_map, _from_x, _from_y, _width, _height, _units):
    # start = current_milli_time()

    unvisited = {node: None for node in get_all_blank_fields(_map, _units, _width, _height)}  # using None as +inf
    visited = {}
    current = (_from_x, _from_y)
    current_distance = 0
    unvisited[current] = current_distance

    while True:
        for neighbour in get_neighbors(_map, current[0], current[1], _width, _height, _units):
            if neighbour not in unvisited: continue
            new_distance = current_distance + 1
            if unvisited[neighbour] is None or unvisited[neighbour] > new_distance:
                unvisited[neighbour] = new_distance
        visited[current] = current_distance
        del unvisited[current]
        if len(unvisited) == 0 or not any(unvisited.values()):
            break
        candidates = [node for node in unvisited.items() if node[1]]
        current, current_distance = sorted(candidates, key=lambda t: t[1])[0]

    # print("flag1: {}".format(str(current_milli_time() - start)))
    return visited


# returns [((x,y), distance)] where x,y is field to make a step
# bug probably somewhere there:
def get_available_destinations(_map, _x, _y, _width, _height, _units, _possible_targets):
    results = defaultdict(lambda: None)
    source_neighbors_fields = get_neighbors(_map, _x, _y, _width, _height, _units)
    for enemy_position, enemy in _possible_targets:
        target_neighbors_fields = get_neighbors(_map, enemy_position[0], enemy_position[1], _width, _height, _units)
        for target_neighbor in target_neighbors_fields:
            _distance_map = build_distance_map(_map, target_neighbor[0], target_neighbor[1], _width, _height, _units)
            for source_neighbor in source_neighbors_fields:
                if source_neighbor in _distance_map:
                    results[source_neighbor] = min(_distance_map[source_neighbor], results[source_neighbor]) if results[
                                                                                                                    source_neighbor] is not None else \
                        _distance_map[source_neighbor]
    return results


def run_test(file_name, evlves_power):
    _cave_map, _units, _height, _width = parse_file(file_name, evlves_power)
    print('height: {}, width: {}'.format(_height, _width))
    print_map(_cave_map, _units, _width, _height)

    round_index = 0

    while True:
        # sorted units
        sorted_units = list(sorted(_units.items(), key=lambda t: (t[0][1], t[0][0])))
        for position, unit in sorted_units:
            if unit['hit_points'] <= 0:
                continue  # skip killed
            x, y = position
            fraction = unit['fraction']
            opposite_fraction = get_opposite_fraction(fraction)
            enemies = find_all_from_fraction(opposite_fraction, _units)
            neighbors_enemies_positions = get_neighbors_enemies(_cave_map, x, y, _width, _height, _units,
                                                                opposite_fraction)
            if len(enemies) == 0:
                print_map(_cave_map, _units, _width, _height)
                hit_sum = sum([remaining_unit['hit_points'] for remaining_unit in _units.values()])
                return hit_sum * round_index
            if len(neighbors_enemies_positions) > 0:
                pass
            else:
                available_destinations = get_available_destinations(_cave_map, x, y, _width, _height, _units, enemies)
                if len(available_destinations) > 0:
                    step = sorted(available_destinations.items(), key=lambda t: (t[1], (t[0][1], t[0][0])))[0]
                    _units[step[0]] = unit
                    del _units[unit['pos']]
                    unit['pos'] = step[0]
                    x, y = unit['pos']

            # fight
            neighbors_enemies_positions = get_neighbors_enemies(_cave_map, x, y, _width, _height, _units,
                                                                opposite_fraction)
            if len(neighbors_enemies_positions) > 0:
                neighbors_enemies = [_units[enemy_position] for enemy_position in neighbors_enemies_positions]
                enemy_to_attack = \
                    sorted(neighbors_enemies, key=lambda t: (t['hit_points'], (t['pos'][1], t['pos'][0])))[0]

                enemy_to_attack['hit_points'] = enemy_to_attack['hit_points'] - unit['attack_power']
                if enemy_to_attack['hit_points'] <= 0:
                    del _units[enemy_to_attack['pos']]
                    print(enemy_to_attack["fraction"] + " killed")
                    enemies = find_all_from_fraction(
                        opposite_fraction, _units)  # change to possible spaces of attack
                    if (enemy_to_attack["fraction"] == 'E'):
                        raise Exception('Elve killed')
        round_index = round_index + 1
        print("After round number {}".format(round_index))
        print_map(_cave_map, _units, _width, _height)


def brute_force(_file_name, test_range=range(4, 40)):
    for i in test_range:
        try:
            result = run_test(_file_name, i)
            print("success for {}".format(i))
            return result
        except Exception as e:
            print("Failed for {}".format(i))


assert brute_force('test2.txt') == 4988
assert brute_force('test4.txt') == 31284
assert brute_force('test5.txt') == 3478
assert brute_force('test6.txt') == 6474
assert brute_force('test7.txt') == 1140
assert brute_force('test10.txt') == 46 * 1457

# run_test(32) - ok
# run_test(16) - not ok
# run_test(20) - not ok
# run_test(28) - ok Exception: End of game: hit points: 1487 full_rounds: 32
# print(run_test(25)) # - ok End of game: hit points: 1487 full_rounds: 32 = 47 584
# run_test(23) - not ok
# run_test(24) - not ok 
# run_test(25) #- not ok 

# result = brute_force('input.txt', test_range=range(24,26))

# print(result)
# very slow - to optimize
# 181952
