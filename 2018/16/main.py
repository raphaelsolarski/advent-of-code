import copy


class Interpreter:
    def __init__(self):
        self._registers = [0, 0, 0, 0]
        self._opcodes = {
            'addr': self._addr,
            'addi': self._addi,
            'mulr': self._mulr,
            'muli': self._muli,
            'banr': self._banr,
            'bani': self._bani,
            'borr': self._borr,
            'bori': self._bori,
            'setr': self._setr,
            'seti': self._seti,
            'gtir': self._gtir,
            'gtri': self._gtri,
            'gtrr': self._gtrr,
            'eqir': self._eqir,
            'eqri': self._eqri,
            'eqrr': self._eqrr
        }

    def _addr(self, in1, in2, out, reg):
        reg[out] = reg[in1] + reg[in2]

    def _addi(self, in1, in2, out, reg):
        reg[out] = reg[in1] + in2

    def _mulr(self, in1, in2, out, reg):
        reg[out] = reg[in1] * reg[in2]

    def _muli(self, in1, in2, out, reg):
        reg[out] = reg[in1] * in2

    def _banr(self, in1, in2, out, reg):
        reg[out] = reg[in1] & reg[in2]

    def _bani(self, in1, in2, out, reg):
        reg[out] = reg[in1] & in2

    def _borr(self, in1, in2, out, reg):
        reg[out] = reg[in1] | reg[in2]

    def _bori(self, in1, in2, out, reg):
        reg[out] = reg[in1] | in2

    def _setr(self, in1, in2, out, reg):
        reg[out] = reg[in1]

    def _seti(self, in1, in2, out, reg):
        reg[out] = in1

    def _gtir(self, in1, in2, out, reg):
        reg[out] = 1 if in1 > reg[in2] else 0

    def _gtri(self, in1, in2, out, reg):
        reg[out] = 1 if reg[in1] > in2 else 0

    def _gtrr(self, in1, in2, out, reg):
        reg[out] = 1 if reg[in1] > reg[in2] else 0

    def _eqir(self, in1, in2, out, reg):
        reg[out] = 1 if in1 == reg[in2] else 0

    def _eqri(self, in1, in2, out, reg):
        reg[out] = 1 if reg[in1] == in2 else 0

    def _eqrr(self, in1, in2, out, reg):
        reg[out] = 1 if reg[in1] == reg[in2] else 0

    # test_case = {'reg_before': [1,2,3,4], 'reg_after': [1,2,3,4], 'command': [1,2,3,4]}
    def run_against_all(self, test_case):
        _counter = 0
        reg_before = test_case['reg_before']
        expected_reg = test_case['reg_after']
        command = test_case['command']
        for name, opcode_func in self._opcodes.items():
            registers = copy.deepcopy(reg_before)
            opcode_func(command[1], command[2], command[3], registers)
            if registers == expected_reg:
                _counter = _counter + 1
        return _counter


def parse_samples(file_name):
    _samples = []
    _index = 0
    with open(file_name, mode='tr', encoding='utf-8') as fd:
        lines = fd.readlines()
        while _index < len(lines):
            before = [int(v.strip()) for v in lines[_index].strip()[9:-1].split(',') if v != '']
            command = [int(v.strip()) for v in lines[_index + 1].split(' ') if v != '']
            after = [int(v.strip()) for v in lines[_index + 2].strip()[9:-1].split(',') if v != '']
            _samples.append({'command': command, 'reg_before': before, 'reg_after': after})
            _index = _index + 4
    return _samples


if __name__ == '__main__':
    samples = parse_samples('samples.txt')

    interpreter = Interpreter()
    counter = 0
    for sample in samples:
        result = interpreter.run_against_all(sample)
        if result >= 3:
            counter = counter + 1

    print(counter)
    # 592