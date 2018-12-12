def parse_line(line):
    parts = line.split('@')[1].strip().split(':')
    x,y = parts[0].strip().split(',')
    width, tall = parts[1].strip().split('x')
    return {'x': int(x), 'y': int(y), 'width': int(width), 'tall': int(tall)}

def add_to_fields_counters(fields, claim):
    x,y = [claim['x'], claim['y']]
    width, tall = [claim['width'], claim['tall']]

    for width_index in range(0, width):
        for tall_index in range(0, tall):
            fields[(y+tall_index)*1000+(x+width_index)] = fields[(y+tall_index)*1000+(x+width_index)] + 1



fields = {}

for i in range(1000000):
    fields[i] = 0

with open(file='input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        claim = parse_line(line)
        add_to_fields_counters(fields, claim)

counter = 0

for v in fields.values():
    if v >= 2:
        counter = counter + 1

print(counter)