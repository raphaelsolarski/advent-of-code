def print_map(_map):
    xs = [k[0] for k in _map.keys()]
    ys = [k[1] for k in _map.keys()]
    sorted_x = sorted(xs)
    sorted_y = sorted(ys)
    min_x = sorted_x[0]
    max_x = sorted_x[-1]
    min_y = sorted_y[0]
    max_y = sorted_y[-1]

    for _y in range(min_y, max_y + 1):
        for _x in range(min_x, max_x + 1):
            if (_x, _y) in _map:
                print(_map[(_x, _y)], end='')
            else:
                print('?', end='')
        print()


def explore_token(_current_pos, _token, _construction_map):
    cursor = 0
    start_pos = _current_pos
    while cursor < len(_token):
        r = _token[cursor]
        if r == 'N':
            _construction_map[_current_pos[0], _current_pos[1] - 1] = '-'
            _construction_map[_current_pos[0] - 1, _current_pos[1] - 1] = '#'
            _construction_map[_current_pos[0] + 1, _current_pos[1] - 1] = '#'
            _current_pos = (_current_pos[0], _current_pos[1] - 2)
        elif r == 'E':
            _construction_map[_current_pos[0] + 1, _current_pos[1]] = '|'
            _construction_map[_current_pos[0] + 1, _current_pos[1] - 1] = '#'
            _construction_map[_current_pos[0] + 1, _current_pos[1] + 1] = '#'
            _current_pos = (_current_pos[0] + 2, _current_pos[1])
        elif r == 'S':
            _construction_map[_current_pos[0], _current_pos[1] + 1] = '-'
            _construction_map[_current_pos[0] - 1, _current_pos[1] + 1] = '#'
            _construction_map[_current_pos[0] + 1, _current_pos[1] + 1] = '#'
            _current_pos = (_current_pos[0], _current_pos[1] + 2)
        elif r == 'W':
            _construction_map[_current_pos[0] - 1, _current_pos[1]] = '|'
            _construction_map[_current_pos[0] - 1, _current_pos[1] + 1] = '#'
            _construction_map[_current_pos[0] - 1, _current_pos[1] - 1] = '#'
            _current_pos = (_current_pos[0] - 2, _current_pos[1])
        elif r == '(':
            opening = _token.find('(')
            closing = _token.rfind(')')
            explore_token(_current_pos, _token[cursor+1:closing], _construction_map)
            cursor = closing
        elif r == '|':
            if _token[cursor + 1] == ')':
                pass
            else:
                _current_pos = start_pos
        else:
            raise Exception('Not supported token {}'.format(r))
        _construction_map[_current_pos] = '.'
        cursor = cursor + 1


def read_file(file_name):
    with open(file=file_name, mode='rt', encoding='utf-8') as fd:
        return fd.read().replace('\n', '')


if __name__ == '__main__':
    _construction_map = {}
    # '^ENWWW(NEEE|SSE(EE|N))$'
    raw = read_file('input.txt')
    token = raw[1:-1]
    print(token)

    current_pos = (0, 0)
    _construction_map[current_pos] = 'X'

    explore_token(current_pos, token, _construction_map)

    print_map(_construction_map)
