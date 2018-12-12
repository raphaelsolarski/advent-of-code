def similarBy1(string1, string2):
    
    if len(string1) != len(string2):
        return False
    
    differences = 0
    for index, character in enumerate(string1):
        if string2[index] != character:
            differences = differences + 1
    
    return differences == 1

def commonChars(string1, string2):
    common = []
    for index, character in enumerate(string1):
        if string2[index] == character:
            common.append(character)
    return common

lines = []

with open(file='input.txt', mode='tr', encoding='utf-8') as fd:
    for line in fd:
        lines.append(line.replace('\n', ''))

for line1 in lines:
    for line2 in lines:
        if similarBy1(line1, line2):
            print(line1)
            print(line2)
            print(''.join(commonChars(line1, line2)))
            exit(0)

