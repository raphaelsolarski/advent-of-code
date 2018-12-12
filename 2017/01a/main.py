sum = 0
counter = 0
with open(file='input.txt', encoding='utf-8', mode='rt') as fd:
    first = int(fd.read(1))
    previous = first
    while True:
        read = fd.read(1)
        if read and read != '\n':
            read = int(read)
            counter = counter + 1
            if read == previous:
                sum = sum + read
            previous = read
        elif not read:
            # eof
            if counter >= 2 and previous == first:
                sum = sum + first
            break
print(sum)
