import os
import pickle
from collections import defaultdict
import string

def save_obj(obj, name):
    os.makedirs('obj', exist_ok=True)
    with open('obj/' + name + '.pkl', 'wb') as f:
        pickle.dump(obj, f, pickle.HIGHEST_PROTOCOL)


def load_obj(name):
    with open('obj/' + name + '.pkl', 'rb') as f:
        return pickle.load(f)


def distances(x, y, coordinates_dict):
    "returns id of closest coo or None if none or multiple"
    measured_distances = []
    for k, value in coordinates_dict.items():
        x1 = value['x']
        y1 = value['y']
        distance = abs(x - x1) + abs(y - y1)
        measured_distances.append((k, distance))

    measured_distances = sorted(measured_distances, key=lambda t: t[1])
    min_d = measured_distances.pop(0)
    min_d_val = min_d[1]
    min_d_key = min_d[0]
    measured_distances_vals = list(map(lambda t: t[1], measured_distances))

    if min_d_val in measured_distances_vals:
        # print("equal distance found")
        return None
    else:
        return min_d_key

def parse(line):
    parts = line.split(',')
    x = parts[0].strip()
    y = parts[1].strip()
    return {'id': x + ':' + y, 'x': int(x), 'y': int(y)}

coordinates = []
coordinates_dict = {}

with open(file='06/input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        parsed = parse(line)
        coordinates.append(parsed)
        coordinates_dict[parsed['id']] = parsed

letters = string.ascii_lowercase + string.ascii_uppercase
coordinates_letters_by_id = {}
for index, id in enumerate(coordinates_dict.keys()):
    coordinates_letters_by_id[id] = letters[index]

coordinates_area_points = defaultdict(list)

xs = list(map(lambda t: t['x'], coordinates))
xmin = sorted(xs)[0]
xmax = sorted(xs)[-1]

print(xmin)
print(xmax)

ys = list(map(lambda t: t['y'], coordinates))
ymin = sorted(ys)[0]
ymax = sorted(ys)[-1]

print(ymin)
print(ymax)

# with open('06/asciart.txt', encoding='utf-8', mode='wt') as asci_art:
#     for y in range(ymin, ymax + 1):
#         for x in range(xmin, xmax + 1):
#             id = distances(x, y, coordinates_dict)
#             if id:
#                 coordinates_area_points[id].append((x, y))
#                 asci_art.write(coordinates_letters_by_id[id])
#             else:    
#                 asci_art.write('.')
#         asci_art.write('\n')

# above operation is time consuming
# save_obj(coordinates_area_points, 'coordinates_area_points')

coordinates_area_points = load_obj('coordinates_area_points')




non_finite = []
for k, points in coordinates_area_points.items():
    if any(t[0] == xmin or t[0] == xmax or t[1] == ymin or t[1] == ymax for t in points):
        pass
        # break
    else:
        non_finite.append((k, len(points)))

print(sorted(non_finite, key=lambda x: x[1]))