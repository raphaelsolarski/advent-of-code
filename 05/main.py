from collections import defaultdict
from dateutil import parser

def react(units):
    units = units.copy()
    pointer = 0
    while len(units) > pointer + 1:
        if units[pointer] != units[pointer+1] and units[pointer].lower() == units[pointer+1].lower():
            units.pop(pointer)
            units.pop(pointer)
            pointer = pointer - 1
        else:
            pointer = pointer + 1
    return units

units = []
chars = set()

with open(file='05/input.txt', mode='tr', encoding='utf-8') as fd:
    while True:
        c = fd.read(1)
        if not c:
            break
        if c != '\n':
            units.append(c)
            chars.add(c.lower())

results = {}

tested = 0
for char in chars:
    filtered = list(filter(lambda c: c.lower() != char, units))
    out = react(filtered)
    results[char] = len(out)

print(sorted(results.items(), key=lambda t: t[1]))

