counter = 0
foo_range = range(1, 10551326 + 1)
for y in foo_range:
    for x in foo_range:
        y_x = y * x
        if y_x == 10551326:
            counter = counter + y
            break
        elif y_x > 10551326:
            break

print(counter)

# 15826992