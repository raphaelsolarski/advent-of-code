from collections import defaultdict
from dateutil import parser

def parse(line):
    parts = line.split(']')
    time = parser.parse(parts[0][1:].strip())
    content = parts[1].strip()

    return {'time': time, 'content': content}

lines = []

with open(file='04/input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        lines.append(parse(line))

sortedLines = sorted(lines, key= lambda d: d['time'])


statistics = defaultdict(list)

current_elf = -1
start_minute = -1
end_minute = -1
for line in sortedLines:
    time = line['time']
    content = line['content']

    if content.startswith('Guard'):
        current_elf = content.split(' ')[1][1:]
    elif content == 'falls asleep':
        start_minute = time.minute
    elif content == 'wakes up':
        end_minute = time.minute
        statistics[current_elf].append((start_minute, end_minute, end_minute-start_minute))
    else:
        raise Exception('Error during parsing')
    

summed_statistics = []

for elf, stats in statistics.items():
    sum = 0
    for num in stats:
        sum = sum + num[2]
    summed_statistics.append((elf, sum))


max_minutes_for_elves = []

max_elf = sorted(summed_statistics, key=lambda t: t[1])[-1]

for elf in summed_statistics:
    statistics_for_elf = statistics[elf[0]]
    count_per_minute = defaultdict(lambda: 0)
    for stat in statistics_for_elf:
        start = stat[0]
        stop = stat[1]
        for minute in range(start, stop):
            count_per_minute[minute] = count_per_minute[minute] + 1

    max_min = sorted(count_per_minute.items(), key=lambda e: -e[1])[0][0]
    count = sorted(count_per_minute.items(), key=lambda e: -e[1])[0][1]
    elf_id = int(elf[0])
    max_minutes_for_elves.append((elf_id, max_min, count))


sorted_minutes = sorted(max_minutes_for_elves, key=lambda t: -t[2])
print(sorted_minutes[0])

print(sorted_minutes[0][0] * sorted_minutes[0][1])