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


def compute_borders_power(x_base, y_base, z_, cells_powers):
    current_sum = 0
    # if z_ == 1:
    #     return cells_powers[y_base * 300 + x_base]

    for y in range(0, z_):
        current_sum = current_sum + cells_powers[(y + y_base) * 300 + x_base + z_ - 1]
    for x in range(0, z_ - 1):
        current_sum = current_sum + cells_powers[(y_base + z_ - 1) * 300 + x_base + x]
    return current_sum


for y_value in range(1, 301):
    for x_value in range(1, 301):
        cell_power = compute_cell_power(x_value, y_value, grid_serial_number)
        cells.append(cell_power)

max_sum = -9999999
max_x = -1
max_y = -1
max_z = -1

for y_index in range(0, 300 - y_size + 1):
    for x_index in range(0, 300 - x_size + 1):
        z_sum = 0
        for z in range(1, min(301 - y_index, 301 - x_index)):
            z_sum = z_sum + compute_borders_power(x_index, y_index, z, cells)
            if z_sum > max_sum:
                max_x = x_index
                max_y = y_index
                max_z = z
                max_sum = z_sum
    print(y_index)

# def print_cells(x_index, y_index, z, cells_powers_values):
#     for y in range(0, z):
#         for x in range(0, z):
#             print(cells_powers_values[(y_index + y) * 300 + x + x_index], end='')
#             print(' ', end='')
#         print()


# print_cells(0, 0, 10, cells)
# print(compute_borders_power(0, 0, 1, cells))
# print(compute_borders_power(0, 0, 2, cells))
# print(cells[300])

print(max_x + 1)
print(max_y + 1)
print(max_z)
print(max_sum)
