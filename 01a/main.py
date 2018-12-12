sum = 0
frequencySet = {0}

while True:
    with open(file='input.txt', mode='tr', encoding='utf-8') as fd:
        for line in fd:
            sum = sum + int(line)
            if sum in frequencySet:
                print(sum)
                exit(0)
            else: 
                frequencySet.add(sum)


print(frequencySet)