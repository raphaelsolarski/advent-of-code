import time
# from PIL import Image, ImageDraw
import matplotlib.pyplot as plt

def parse(line):
    pos_x = int(line[10:16].strip())
    pos_y = int(line[18:24].strip())
    vel_x = int(line[36:38].strip())
    vel_y = int(line[40:42].strip())
    # pos_x = int(line[10:12].strip())
    # pos_y = int(line[14:16].strip())
    # vel_x = int(line[28:30].strip())
    # vel_y = int(line[32:34].strip())
    return {'x': pos_x, 'y': pos_y, 'vel_x': vel_x, 'vel_y': vel_y}

# zero based second
def star_position_in_second(star, second):
    pos_x = star['x']
    pos_y = star['y']
    vel_x = star['vel_x']
    vel_y = star['vel_y']

    pos_x = pos_x + (vel_x * second)
    pos_y = pos_y + (vel_y * second)
    return (pos_x, pos_y)

def stars_position_in_second(stars, second):
    translated_stars = []
    for star in stars:
        translated_stars.append(star_position_in_second(star, second))

    # sorted_positions = sorted(translated_stars, key=lambda t: (t[1], t[0]))
    return translated_stars

def scale(points, scale):
    result = []
    for x, y in points:
        result.append((x + scale, y + scale))
    return result

def reverse_scale(points):
    result = []
    for x, y in points:
        result.append((x, -y))
    return result


stars_raw = []

with open(file='input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        parsed = parse(line)
        stars_raw.append(parsed)



for second in range(0, 400):
    translated = stars_position_in_second(stars_raw, 10300 + second)
    # translated = stars_position_in_second(stars_raw, second)
    in_reverse = reverse_scale(translated)
    plt.scatter(*zip(*in_reverse), marker='o', s=1)
    plt.title(str(second))
    plt.show()

# panlpapr