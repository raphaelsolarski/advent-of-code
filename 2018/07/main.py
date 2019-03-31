from collections import defaultdict
import copy

def parse(line):
    parts = line.split(' ')
    on = parts[1]
    depends = parts[7]
    return {'depends': depends, 'on': on}

def find_first_without_requirements(remain_steps, remain_requirements):
    for step_name in sorted(list(steps)):
        if len(remain_requirements[step_name]) == 0:
            return step_name


instructions = []
steps = set()
requirements = defaultdict(list)
remains = copy.deepcopy(requirements)

with open(file='07/input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        parsed = parse(line)
        instructions.append(parsed)
        requirements[parsed['depends']].append(parsed['on'])
        steps.add(parsed['depends'])
        steps.update(parsed['on'])

order = []

print(len(steps))
while True:
    if len(steps) == 0:
        break
    else:
        next_step = find_first_without_requirements(steps, requirements)
        order.append(next_step)
        del requirements[next_step]
        steps.remove(next_step)
        for k, v in requirements.items():
            if next_step in v:
                v.remove(next_step)
        print(next_step, end='')


print(order)
print(len(order))