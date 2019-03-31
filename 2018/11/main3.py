# grid_serial_number = 7511
# grid_serial_number = 18
grid_serial_number = 18
cells = []

x_size = 3
y_size = 3


def compute_cell_power(x_, y_, grid_num):
    rack_id = x_ + 10
    power_level = rack_id * y_
    power_level = power_level + grid_num
    power_level = power_level * rack_id
    power_level = int((abs(power_level) / 100) % 10)
    power_level = power_level - 5
    return power_level


assert compute_cell_power(122, 79, 57) == -5
assert compute_cell_power(217, 196, 39) == 0
assert compute_cell_power(101, 153, 71) == 4


def compute_square_power(x_, y_, z_, cells_powers):
    current_sum = 0
    for y in range(0, z_):
        for x in range(0, z_):
            current_sum = current_sum + cells_powers[(y_ + y) * 300 + (x_ + x)]
    return current_sum


def compute_borders_power(x_, y_, z_, cells_powers):
    current_sum = 0
    for y in range(0, z_):
        current_sum = current_sum + cells_powers[(y + y_) * 300 + x_]
    for x in range(0, z_ - 1):
        current_sum = current_sum + cells_powers[y_ * 300 + x_ + x]
    return current_sum


for y_value in range(1, 301):
    for x_value in range(1, 301):
        cell_power = compute_cell_power(x_value, y_value, grid_serial_number)
        cells.append(cell_power)

max_sum = -9999999
max_x = -1
max_y = -1


squares_cache = {}


for y_index in range(0, 300 - y_size + 1):
    for x_index in range(0, 300 - x_size + 1):
        z_sum = 0
        for z in range(1, min(298 - y_index, 298 - x_index)):
            s = '{}:{}:{}'.format(x_index, y_index, z)
            cached = squares_cache.get(s)
            if cached is None:
                z_sum = z_sum + compute_borders_power(x_index, y_index, z, cells)
                squares_cache[s] = z_sum
            else:
                z_sum = cached
            if z_sum > max_sum:
                    max_x = x_index
                    max_y = y_index
                    max_sum = z_sum
    print(y_index)

print(max_x + 1)
print(max_y + 1)
print(max_sum)
