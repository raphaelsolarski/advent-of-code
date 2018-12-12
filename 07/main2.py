from collections import defaultdict
import copy

def parse(line):
    parts = line.split(' ')
    on = parts[1]
    depends = parts[7]
    return {'depends': depends, 'on': on}

def find_first_without_requirements(remain_steps, remain_requirements):
    for step_name in sorted(list(remain_steps)):
        if len(remain_requirements[step_name]) == 0:
            return step_name
    return None


instructions = []
steps = set()
requirements = defaultdict(list)

with open(file='07/input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        parsed = parse(line)
        instructions.append(parsed)
        requirements[parsed['depends']].append(parsed['on'])
        steps.add(parsed['depends'])
        steps.add(parsed['on'])

order = []
duration = 60
letters_duration = {}
for index, step in enumerate(sorted(list(steps))):
    letters_duration[step] = index + 1 + duration

workers = [1,2,3,4,5]

# worker_id -> (task_id, remain_duration)
workers_tasks = {}
phase_number = 0
done = []
todo = copy.deepcopy(steps)


with open('07/log2.txt', mode='wt', encoding='utf-8') as logger:
    while True:
        if len(done) == len(steps):
            break
        else:
            phase_number = phase_number + 1
            for worker_number in workers:
                worker_task = workers_tasks.get(worker_number)

                # ending summary
                if worker_task:
                    task_id, remain_duration = worker_task
                    if remain_duration == 0:
                        logger.write("worker {} ended {} and can get new task in this phase: {}\n".format(worker_number, task_id, phase_number))
                        for k, v in requirements.items():
                            if task_id in v:
                                v.remove(task_id)
                        del workers_tasks[worker_number]
                        done.append(task_id)

            for worker_number in workers:
                worker_task = workers_tasks.get(worker_number)
                # scheduling next phase
                if worker_task is None:
                    next_step = find_first_without_requirements(todo, requirements)
                    if next_step:
                        del requirements[next_step]
                        todo.remove(next_step)
                        workers_tasks[worker_number] = (next_step, letters_duration[next_step])
                        logger.write("worker {} started working on {} at the beginning of phase {}\n".format(worker_number, next_step, phase_number))

                # working 
                worker_task = workers_tasks.get(worker_number)
                if worker_task:
                    task_id, remain_duration = worker_task
                    workers_tasks[worker_number] = (task_id, remain_duration - 1)
                    logger.write("worker {} is working on {} in phase number {} after that {} remained\n".format(worker_number, task_id, phase_number, remain_duration - 1))
                else:
                    logger.write("worker {} is idle in phase {}\n".format(worker_number, phase_number))


print(phase_number - 1)


# print(sum(list(map(lambda x: x[1], letters_duration.items()))))