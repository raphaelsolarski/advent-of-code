grid_serial_number = 7511
# grid_serial_number = 18
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


def compute_square_power(x_, y_, cells_powers):
    current_sum = 0
    for y in range(0, y_size):
        for x in range(0, x_size):
            current_sum = current_sum + cells_powers[(y_ + y) * 300 + (x_ + x)]
    return current_sum


for y_value in range(1, 301):
    for x_value in range(1, 301):
        cell_power = compute_cell_power(x_value, y_value, grid_serial_number)
        cells.append(cell_power)

max_sum = -9999999
max_x = -1
max_y = -1

for y_index in range(0, 300 - y_size + 1):
    for x_index in range(0, 300 - x_size + 1):
        ssum = compute_square_power(x_index, y_index, cells)
        if ssum > max_sum:
            max_x = x_index
            max_y = y_index
            max_sum = ssum

print(max_x + 1)
print(max_y + 1)
print(max_sum)

# Result:
# 21
# 22
# 34