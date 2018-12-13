import copy


def print_tracks(_tracks, _carts, _width, _height):
    for y in range(0, _height):
        for x in range(0, _width):
            t = _tracks[y * _width + x]
            if t is None:
                print(' ', end='')
            elif (str(x) + ':' + str(y)) in _carts:
                _cart = _carts[(str(x) + ':' + str(y))]
                print(_cart['dir'], end='')
            else:
                print(t, end='')
        print()


def parse_file(file):
    with open(file=file, mode='tr', encoding='utf-8') as fd:
        _carts = {}
        _tracks_map = []
        for _y, line in enumerate(fd):
            for _x, c in enumerate(line):
                if c == '\n':
                    continue
                if c == '^':
                    _carts[str(_x) + ":" + str(_y)] = {"dir": "^", 'pos': (_x, _y), 'next_turn': -1}
                    _tracks_map.append('|')
                if c == '>':
                    _carts[str(_x) + ":" + str(_y)] = {"dir": ">", 'pos': (_x, _y), 'next_turn': -1}
                    _tracks_map.append('-')
                if c == 'v':
                    _carts[str(_x) + ":" + str(_y)] = {"dir": "v", 'pos': (_x, _y), 'next_turn': -1}
                    _tracks_map.append('|')
                if c == '<':
                    _carts[str(_x) + ":" + str(_y)] = {"dir": "<", 'pos': (_x, _y), 'next_turn': -1}
                    _tracks_map.append('-')
                if c == ' ':
                    _tracks_map.append(None)
                if c == '|':
                    _tracks_map.append('|')
                if c == '-':
                    _tracks_map.append('-')
                if c == '\\':
                    _tracks_map.append('\\')
                if c == '/':
                    _tracks_map.append('/')
                if c == '+':
                    _tracks_map.append('+')
    return _carts, _tracks_map


carts, tracks = parse_file('13/input.txt')
# carts, tracks = parse_file('13/test2.txt')

# print_tracks(tracks, carts, 150, 150)
width = 150
height = 150

# width = 7
# height = 7

# print(carts)

# while True:
# sorted_carts = sorted(list(carts.items()), key=lambda t: (t[1]['pos']))
#
# print(sorted_carts)

turns = ['^', '>', 'v', '<']

number_of_carts = len(carts)

while True:
    # print_tracks(tracks, carts, _height=height, _width=width)
    sorted_carts = sorted(list(carts.items()), key=lambda t: (t[1]['pos'][1], t[1]['pos'][0]))
    for cart in sorted_carts:
        xpos, ypos = cart[1]['pos']
        if (str(xpos) + ":" + str(ypos)) not in carts:
            continue
        _dir = cart[1]['dir']
        next_dir = _dir
        next_turn_mod = cart[1]['next_turn']
        new_turn_mod = next_turn_mod

        next_x = xpos
        next_y = ypos
        if _dir == '^':
            next_y = ypos - 1
        elif _dir == '>':
            next_x = xpos + 1
        elif _dir == 'v':
            next_y = ypos + 1
        elif _dir == '<':
            next_x = xpos - 1

        next_map_element = tracks[next_y * width + next_x]
        if next_map_element == '+':
            current_dir_index = turns.index(_dir)
            next_dir = turns[(current_dir_index + next_turn_mod) % 4]

            if next_turn_mod == -1:
                new_turn_mod = 0
            elif next_turn_mod == 0:
                new_turn_mod = 1
            elif next_turn_mod == 1:
                new_turn_mod = -1
            else:
                raise Exception('Unknown turn modifiator: ' + next_turn_mod)
        elif next_map_element == '|':
            pass
        elif next_map_element == '-':
            pass
        elif next_map_element == '\\':
            if _dir == '^':
                next_dir = '<'
            elif _dir == '>':
                next_dir = 'v'
            elif _dir == 'v':
                next_dir = '>'
            else:
                next_dir = '^'
        elif next_map_element == '/':
            if _dir == '^':
                next_dir = '>'
            elif _dir == '>':
                next_dir = '^'
            elif _dir == 'v':
                next_dir = '<'
            else:
                next_dir = 'v'
        else:
            raise Exception('Unknown next element')

        del carts[str(xpos) + ":" + str(ypos)]
        if str(next_x) + ":" + str(next_y) in carts:
            # print_tracks(tracks, carts, _width=width, _height=height)
            print("Carts crashed: {},{}, remains: {}".format(next_x, next_y, number_of_carts))
            number_of_carts = number_of_carts - 2
            del carts[str(next_x) + ":" + str(next_y)]
            assert len(carts) == number_of_carts
        else:
            carts[str(next_x) + ":" + str(next_y)] = {"dir": next_dir, 'pos': (next_x, next_y),
                                                      'next_turn': new_turn_mod}
    if number_of_carts == 1:
        print(carts)
        break

# Result 86,18
