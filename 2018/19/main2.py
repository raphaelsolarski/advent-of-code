import copy


class Interpreter:
    def __init__(self):
        self._registers = [1, 0, 0, 0, 0, 0]
        self.opcodes = {
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
        self._loaded_program = None
        self._instruction_pointer = 0
        self._instruction_pointer_registry_number = None

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

    def _load_program(self, _program):
        self._loaded_program = []
        pointer_counter = 0
        ip_declaration = program[0]
        assert ip_declaration.startswith('#ip')
        self._instruction_pointer_registry_number = int(ip_declaration[4:])
        for command in _program[1:]:
            parts = command.split(' ')
            self._loaded_program.append((parts[0], int(parts[1]), int(parts[2]), int(parts[3])))
            pointer_counter = pointer_counter + 1

    def run_program(self, _program):
        self._load_program(_program)
        while True:
            if self._instruction_pointer >= len(self._loaded_program):
                break
            else:
                command = self._loaded_program[self._instruction_pointer]
                opcode = command[0]
                opcode_func = self.opcodes[opcode]
                print(self._instruction_pointer)
                self._registers[self._instruction_pointer_registry_number] = self._instruction_pointer
                opcode_func(command[1], command[2], command[3], self._registers)
                self._instruction_pointer = self._registers[self._instruction_pointer_registry_number]
                self._instruction_pointer = self._instruction_pointer + 1
        return copy.deepcopy(self._registers)


def parse_program(file_name):
    _program = []
    with open(file_name, mode='tr', encoding='utf-8') as fd:
        lines = fd.readlines()
        for line in lines:
            _program.append(line.strip().replace('\n', ''))
    return _program


if __name__ == '__main__':
    program = parse_program('input.txt')
    interpreter = Interpreter()
    print(interpreter.run_program(program))
