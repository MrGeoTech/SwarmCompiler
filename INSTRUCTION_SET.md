# Instruction Set

| OPCODE | Arguments                                              | Instruction                               | Action                                                                                         |
|--------|--------------------------------------------------------|-------------------------------------------|------------------------------------------------------------------------------------------------|
| ADDI   | 12b***i*** ***s***rc ***d***est                        | `iiii iiii iiii ssss s000 dddd d000 0011` | Add immediate and src to dest                                                                  |
| _NOP_  |                                                        | `0000 0000 0000 0000 0000 0000 0000 0001` | Adds 0 to 0 and saves to register 0, which is a read only constant 0                           |
| SLTI   | 12b***i*** ***s***rc ***d***est                        | `iiii iiii iiii ssss s001 dddd d000 0011` | Sets dest to 1 if src is less than the immediate else 0                                        |
| SLTIU  | 12b***i*** ***s***rc ***d***est                        | `iiii iiii iiii ssss s010 dddd d000 0011` | SLTI Unsigned                                                                                  |
| ANDI   | 12b***i*** ***s***rc ***d***est                        | `iiii iiii iiii ssss s011 dddd d000 0011` | Bitwise AND with immediate                                                                     |
| ORI    | 12b***i*** ***s***rc ***d***est                        | `iiii iiii iiii ssss s100 dddd d000 0011` | Bitwise OR with immediate                                                                      |
| XORI   | 12b***i*** ***s***rc ***d***est                        | `iiii iiii iiii ssss s101 dddd d000 0011` | Bitwise XOR with immediate                                                                     |
| _NOT_  | ***s***rc ***d***est                                   | `1000 0000 0000 ssss s101 dddd d000 0011` | Bitwise NOT with immediate (XOR -1)                                                            |
| SLLI   | 5bsh***a***mt ***s***rc ***d***est                     | `0000 000a aaaa ssss s110 dddd d000 0011` | Logical shift left with 5 bit shift amount immediate                                           |
| SRLI   | 5bsh***a***mt ***s***rc ***d***est                     | `0000 000a aaaa ssss s111 dddd d000 0011` | Logical shift right with 5 bit shift amount immediate                                          |
| SRAI   | 5bsh***a***mt ***s***rc ***d***est                     | `0100 000a aaaa ssss s000 dddd d000 0011` | Arithmetic shift right with 5 bit shift amount immediate                                       |
| LUI    | 20b***i*** ***d***est                                  | `iiii iiii iiii iiii iiii dddd d010 0011` | Loads immediate into destination's upper 20 bits                                               |
| AUIPC  | 20b***i*** ***d***est                                  | `iiii iiii iiii iiii iiii dddd d100 0011` | Sets destination's upper 20 bits to immediate plus pc. Lower 12 are set to 0s                  |
| ADD    | src***2*** ***s***rc ***d***est                        | `0000 0002 2222 ssss s000 dddd d110 0011` | Adds src1 and src2 and saves to dest                                                           |
| SLT    | src***2*** ***s***rc ***d***est                        | `0000 0002 2222 ssss s001 dddd d110 0011` | Sets dest to 1 if src1 is less than src2 else 0                                                |
| SLTU   | src***2*** ***s***rc ***d***est                        | `0000 0002 2222 ssss s010 dddd d110 0011` | STL Unsigned                                                                                   |
| AND    | src***2*** ***s***rc ***d***est                        | `0000 0002 2222 ssss s011 dddd d110 0011` | Bitwise AND                                                                                    |
| OR     | src***2*** ***s***rc ***d***est                        | `0000 0002 2222 ssss s100 dddd d110 0011` | Bitwise OR                                                                                     |
| XOR    | src***2*** ***s***rc ***d***est                        | `0000 0002 2222 ssss s101 dddd d110 0011` | Bitwise XOR                                                                                    |
| SLL    | src***2*** ***s***rc ***d***est                        | `0000 0002 2222 ssss s110 dddd d110 0011` | Logically shifts src1 left by src2                                                             |
| SRL    | src***2*** ***s***rc ***d***est                        | `0000 0002 2222 ssss s111 dddd d110 0011` | Logically shifts src1 right by src2                                                            |
| SRA    | src***2*** ***s***rc ***d***est                        | `0100 0002 2222 ssss s000 dddd d110 0011` | Arithmetic right shifts src1 by src2                                                           |
| SUB    | src***2*** ***s***rc ***d***est                        | `0100 0002 2222 ssss s000 dddd d110 0011` | Subtracts src2 from src1 and stores in dest                                                    |
| JAL    | 20b***i*** ***d***est                                  | `iiii iiii iiii iiii iiii dddd d010 0111` | Jumps to pc + immediate and stores current pc + 4 in dest (offset encoded as 20,10-1,11,19-12) |
| JALR   | 12b***i*** ***b***ase ***d***est                       | `iiii iiii iiii bbbb b000 dddd d100 0111` | Jumps to base + immediate and drop the LSB and stores the current pc + 4 in dest               |
| BEQ    | 12b***i*** src***2*** ***s***rc1                       | `iiii iii2 2222 ssss s000 iiii i110 0111` | Jumps to pc + immediate if src1 == src2 (immediate encoded as 12,10-5,4-1,11)                  |
| BNE    | 12b***i*** src***2*** ***s***rc1                       | `iiii iii2 2222 ssss s001 iiii i110 0111` | Jumps to pc + immediate if src1 != src2 (immediate encoded as 12,10-5,4-1,11)                  |
| BLT    | 12b***i*** src***2*** ***s***rc1                       | `iiii iii2 2222 ssss s010 iiii i110 0111` | Jumps to pc + immediate if src1 < src2 (immediate encoded as 12,10-5,4-1,11)                   |
| BLTU   | 12b***i*** src***2*** ***s***rc1                       | `iiii iii2 2222 ssss s011 iiii i110 0111` | Jumps to pc + immediate if src1 <(unsigned) src2 (immediate encoded as 12,10-5,4-1,11)         |
| BGE    | 12b***i*** src***2*** ***s***rc1                       | `iiii iii2 2222 ssss s100 iiii i110 0111` | Jumps to pc + immediate if src1 >= src2 (immediate encoded as 12,10-5,4-1,11)                  |
| BGEU   | 12b***i*** src***2*** ***s***rc1                       | `iiii iii2 2222 ssss s101 iiii i110 0111` | Jumps to pc + immediate if src1 >=(unsigned) src2 (immediate encoded as 12,10-5,4-1,11)        |
| LOAD   | 12b***i*** ***s***rc ***w***idth ***d***est            | `iiii iiii iiii ssss swww dddd d000 1011` | Loads value from memory to register                                                            |
| STORE  | 12b***i*** ***s***rc ***b***ase ***w***idth            | `iiii iiii ssss bbbb bwww iiii i010 1011` | Saves value in src to the memory address of base + immediate                                   |
| FENCE  | ***p***redecessor ***s***uccessor s***r***c ***d***est | `0000 pppp ssss rrrr r000 dddd d110 1011` | Provides sequencing for other hardware threads                                                 |

# Load/Store Instructions
| CODE | BYTES | EFFECT                                                                               |
|------|-------|--------------------------------------------------------------------------------------|
| LW   | `000` | Loads 32-bit value to register                                                       |
| LH   | `001` | Loads 16-bit value from register that is sign extended (16th bit is set to 32nd bit) |
| LHU  | `010` | Loads 16-bit value from register that is zero extended                               |
| LB   | `011` | Loads 8-bit value from register that is sign extended (8th bit is set to 32nd bit)   |
| LBU  | `100` | Loads 8-bit value from register that is zero extended                                |
| SW   | `101` | Stores 32-bit value from register to memory                                          |
| SH   | `110` | Stores 16-bit value from register to memory                                          |
| SB   | `111` | Stores 8-bit value from register to memory                                           |

# Fence Instructions
| PREDECESSOR | BYTES    | EFFECT          |
|-------------|----------|-----------------|
| PI          | `1xxx`   | Device input    |
| PO          | `x1xx`   | Device output   |
| PR          | `xx1x`   | Read            |
| PW          | `xxx1`   | Write           |

| SUCCESSOR   | BYTES    | OUTPUT          |
|-------------|----------|-----------------|
| SI          | `1xxx`   | Device input    |
| SO          | `x1xx`   | Device output   |
| SR          | `xx1x`   | Read            |
| SW          | `xxx1`   | Write           |

# Notes
- Software should be optimized so that the most common branch is sequential.
- Additional branch operations can be created by reversing the operands.

# Future Features
- Return-address prediction stacks (RAS)