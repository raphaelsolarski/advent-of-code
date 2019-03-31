from collections import defaultdict

counter2 = 0
counter3 = 0

def lettersCountMap(line):
    letters = defaultdict(list)
    for l in line:
        letters[l].append(l)
    return {k: len(v) for k, v in letters.items()}

with open(file='input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        line = line.replace("\n", "")
        lettersCount = lettersCountMap(line)
        containsAtLeastOneExact2 = False
        containsAtLeastOneExact3 = False
        for k, v in lettersCount.items():
            if v == 2:
                containsAtLeastOneExact2 = True
            elif v == 3:
                containsAtLeastOneExact3 = True
        if containsAtLeastOneExact2:
            counter2 = counter2 + 1
        if containsAtLeastOneExact3:
            counter3 = counter3 + 1

print(counter2)
print(counter3)
print(counter2 * counter3)