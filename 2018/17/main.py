debug = False


def parse_file(file_name):
    _ground_map = {}
    with open(file=file_name, mode='rt', encoding='utf-8') as fd:
        for line in fd:
            parsed_line = parse_line(line)
            for point in parsed_line:
                _ground_map[point] = '#'
    xs = [t[0] for t in _ground_map.keys()]
    ys = [t[1] for t in _ground_map.keys()]
    max_x = max(xs)
    max_y = max(ys)
    min_x = min(xs)
    min_y = min(ys)

    for y in range(0, max_y + 2):
        for x in range(min_x - 2, max_x + 2):
            if (x, y) in _ground_map:
                continue  # already filled
            elif (x, y) == (500, 0):
                _ground_map[(x, y)] = '+'
            else:
                _ground_map[(x, y)] = '.'

    return {'map': _ground_map, 'max_x': max_x, 'max_y': max_y, 'min_x': min_x, 'min_y': min_y}


def parse_line(_line):
    def parse_part(part):
        if '..' in part:
            range_bounds = [int(p) for p in part.split('..')]
            return list(range(range_bounds[0], range_bounds[1] + 1))
        else:
            return [int(part)]

    parts = [part.strip().replace('\n', '') for part in _line.split(",") if part != '']
    if parts[0].startswith('x'):
        xs = parse_part(parts[0][2:])
        ys = parse_part(parts[1][2:])
    else:
        xs = parse_part(parts[1][2:])
        ys = parse_part(parts[0][2:])

    points = []
    for _x in xs:
        for _y in ys:
            points.append((_x, _y))
    return points


def print_map(_parsed, file=None):
    print(file=file)
    _max_x = _parsed['max_x']
    _max_y = _parsed['max_y']
    _min_x = _parsed['min_x']
    _min_y = _parsed['min_y']
    _ground_map = _parsed['map']
    for y in range(0, _max_y + 2):
        for x in range(_min_x - 1, _max_x + 2):
            print(_ground_map[(x, y)], end='', file=file)
        print(file=file)


def simulate(map_with_meta):
    _max_x = map_with_meta['max_x']
    _max_y = map_with_meta['max_y']
    _min_x = map_with_meta['min_x']
    _min_y = map_with_meta['min_y']
    _ground_map = map_with_meta['map']
    not_simulated_falling_points = [(500, 0)]  # spring
    while len(not_simulated_falling_points) > 0:
        falling_point = not_simulated_falling_points.pop()
        new_falling_points = simulate_falling(falling_point, map_with_meta)
        not_simulated_falling_points.extend(new_falling_points)
        if debug:
            print_map(map_with_meta)


# returns list of new falling points
def simulate_falling(falling_point, _map_with_meta):
    new_falling_points = []
    _max_x = _map_with_meta['max_x']
    _max_y = _map_with_meta['max_y']
    _min_x = _map_with_meta['min_x']
    _min_y = _map_with_meta['min_y']
    _ground_map = _map_with_meta['map']
    _x, _y = falling_point
    while True:
        _y = _y + 1
        element = _ground_map[(_x, _y)]
        if element == '#':
            break  # falling phase ended
        elif _y >= _max_y:
            if debug:
                print('End of the map reached: {},{}'.format(_x, _y))
            _ground_map[(_x, _y)] = '|'
            return []  # end of map
        elif element == '|':
            return []
        elif element == '~':
            break  # need to compute filling
        elif element == '.':
            _ground_map[(_x, _y)] = '|'
    # filling phase

    while True:
        falling = False
        points = []
        start_x, start_y = _x, _y

        # expand left
        expand_x, expand_y = start_x, start_y
        while True:
            if _ground_map[(expand_x, expand_y)] == '#':
                break
            elif _ground_map[(expand_x, expand_y)] == '|' and _ground_map[
                (expand_x, expand_y + 1)] == '|' and expand_x != _x:
                falling = True
                break
            elif _ground_map[(expand_x, expand_y + 1)] == '.':
                falling = True
                new_falling_points.append((expand_x, expand_y))
                points.append((expand_x, expand_y))
                break
            else:
                points.append((expand_x, expand_y))
            expand_x = expand_x - 1

        # expand right
        expand_x, expand_y = start_x, start_y
        while True:
            if _ground_map[(expand_x, expand_y)] == '#':
                break
            elif _ground_map[(expand_x, expand_y)] == '|' and _ground_map[
                (expand_x, expand_y + 1)] == '|' and expand_x != _x:
                falling = True
                break
            elif _ground_map[(expand_x, expand_y + 1)] == '.':
                falling = True
                new_falling_points.append((expand_x, expand_y))
                points.append((expand_x, expand_y))
                break
            else:
                points.append((expand_x, expand_y))
            expand_x = expand_x + 1

        if falling:
            for point in points:
                _ground_map[point] = '|'
            break
        else:
            for point in points:
                _ground_map[point] = '~'
        _y = _y - 1

    return new_falling_points


def compute_watered(_map_with_meta):
    watered_counter = 0
    ground_map = _map_with_meta['map']
    min_y = _map_with_meta['min_y']
    for key, value in ground_map.items():
        if (value == '~' or value == '|') and key[1] >= min_y:
            watered_counter = watered_counter + 1
    return watered_counter


def compute_water(_map_with_meta):
    _water_counter = 0
    ground_map = _map_with_meta['map']
    min_y = _map_with_meta['min_y']
    for key, value in ground_map.items():
        if (value == '~') and key[1] >= min_y:
            _water_counter = _water_counter + 1
    return _water_counter


def compute_clay(_map_with_meta):
    clay_counter = 0
    for value in _map_with_meta['map'].values():
        if value == '#':
            clay_counter = clay_counter + 1
    return clay_counter


if __name__ == '__main__':
    # full_input_parsed = parse_file('input.txt')
    # with open('log.txt', mode='wt', encoding='utf-8') as fd:
    #     print_map(full_input_parsed, fd)
    # test1 = parse_file('test1.txt')
    # simulate(test1)
    # print_map(test1)
    # assert compute_watered(test1) == 57

    full_input_parsed = parse_file('input.txt')

    clay_before = compute_clay(full_input_parsed)
    simulate(full_input_parsed)
    print_map(full_input_parsed)
    counter = compute_watered(full_input_parsed)
    water_counter = compute_water(full_input_parsed)
    clay_after = compute_clay(full_input_parsed)
    with open('log.txt', mode='wt', encoding='utf-8') as fd:
        print_map(full_input_parsed, fd)

    assert clay_after == clay_before
    assert counter != 40318
    assert counter != 46671
    assert counter != 39563
    assert counter != 32984

    print(counter)
    print(water_counter)
