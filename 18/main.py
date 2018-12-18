import copy


def parse_file(file):
    _wood_map = []
    with open(file=file, mode='rt', encoding='utf-8') as fd:
        map_width = 0
        map_high = 0
        for line in fd:
            if line != '\n':
                line_length = 0
                for c in line:
                    if c != '\n':
                        _wood_map.append(c)
                        line_length = line_length + 1
                    else:
                        map_width = max(line_length, map_width)
                map_high = map_high + 1
    return {'map': _wood_map, 'width': map_width, 'high': map_high}


def gather_statistics_for_field(_map_with_meta, _x, _y):
    _map_width = _map_with_meta['width']
    _map_high = _map_with_meta['high']
    _map = _map_with_meta['map']
    all_neighbors = [
        (_x, _y - 1),
        (_x + 1, _y - 1),
        (_x + 1, _y),
        (_x + 1, _y + 1),
        (_x, _y + 1),
        (_x - 1, _y + 1),
        (_x - 1, _y),
        (_x - 1, _y - 1)
    ]
    neighbors_in_barrier = []
    for neighbor in all_neighbors:
        n_x, n_y = neighbor
        if n_x >= _map_width:
            continue
        elif n_x < 0:
            continue
        elif n_y >= _map_high:
            continue
        elif n_y < 0:
            continue
        else:
            neighbors_in_barrier.append(neighbor)
    wood_acres = 0
    lumberyard_acres = 0
    empty_acres = 0
    for neighbor in neighbors_in_barrier:
        n_x, n_y = neighbor
        acre = _map[n_y * _map_width + n_x]
        if acre == '.':
            empty_acres = empty_acres + 1
        elif acre == '|':
            wood_acres = wood_acres + 1
        else:
            lumberyard_acres = lumberyard_acres + 1
    return wood_acres, lumberyard_acres, empty_acres


def simulate(_map_with_meta, after):
    _map_width = _map_with_meta['width']
    _map_high = _map_with_meta['high']
    _map = _map_with_meta['map']
    for minute in range(0, after + 1):
        minute_map_with_meta_snapshot = copy.deepcopy(_map_with_meta)
        minute_map_snapshot = minute_map_with_meta_snapshot['map']
        for y in range(0, _map_high):
            for x in range(0, _map_width):
                wood_acres, lumberyard_acres, empty_acres = gather_statistics_for_field(minute_map_with_meta_snapshot,
                                                                                        x, y)
                field = minute_map_snapshot[y * _map_width + x]
                if field == '.' and wood_acres >= 3:
                    _map[y * _map_width + x] = '|'
                elif field == '|' and lumberyard_acres >= 3:
                    _map[y * _map_width + x] = '#'
                elif field == '#' and lumberyard_acres >= 1 and wood_acres >= 1:
                    pass
                elif field == '#' and (lumberyard_acres == 0 or wood_acres == 0):
                    _map[y * _map_width + x] = '.'


def print_map(_map_with_meta):
    _map_width = _map_with_meta['width']
    _map_high = _map_with_meta['high']
    _map = _map_with_meta['map']
    for y in range(0, _map_high):
        for x in range(0, _map_width):
            print(_map[y * _map_width + x], end='')
        print()


def compute_map_statistics(_map_with_meta):
    _map_width = _map_with_meta['width']
    _map_high = _map_with_meta['high']
    _map = _map_with_meta['map']
    wood_acres = 0
    lumberyard_acres = 0
    empty_acres = 0
    for y in range(0, _map_high):
        for x in range(0, _map_width):
            acre = _map[y * _map_width + x]
            if acre == '.':
                empty_acres = empty_acres + 1
            elif acre == '|':
                wood_acres = wood_acres + 1
            else:
                lumberyard_acres = lumberyard_acres + 1
    return wood_acres, lumberyard_acres, empty_acres


if __name__ == '__main__':
    map_with_meta = parse_file('input.txt')
    simulate(map_with_meta, 9)
    print_map(map_with_meta)
    wood_acres, lumberyard_acres, empty_acres = compute_map_statistics(map_with_meta)
    print(wood_acres * lumberyard_acres)
