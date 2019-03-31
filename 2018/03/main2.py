def parse_line(line):
    parts = line.split('@')[1].strip().split(':')
    id = line.split('@')[0].strip()[1:]
    x,y = parts[0].strip().split(',')
    width, tall = parts[1].strip().split('x')
    return {'id': id, 'x': int(x), 'y': int(y), 'width': int(width), 'tall': int(tall), 'overlaps': False}

def add_claim_id_to_field(fields, claims, claim):
    id = claim['id']
    x,y = [claim['x'], claim['y']]
    width, tall = [claim['width'], claim['tall']]

    for width_index in range(0, width):
        for tall_index in range(0, tall):
            current_value = fields[(y+tall_index)*1000+(x+width_index)]
            if current_value == 0:
                fields[(y+tall_index)*1000+(x+width_index)] = id
            else:
                claims[current_value]['overlaps'] = True
                claims[id]['overlaps'] = True


claims = {}
fields = {}

for i in range(1000000):
    fields[i] = 0

with open(file='input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        claim = parse_line(line)
        claims[claim['id']] = claim

for claim in claims.values():
    add_claim_id_to_field(fields, claims, claim)

for id, claim in claims.items():
    if claim['overlaps'] == False:
        print(id)
