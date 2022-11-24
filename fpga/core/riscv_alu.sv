module riscv_alu (
    input  [ 3:0] op,
    input  [63:0] input_1,
    input  [63:0] input_2,
    output [63:0] result,
);

always @(op or input_1 or input_2 or result)
begin
    case (op)
        4'b0000: result = input_1 + input_2; // ADD
        4'b0001: result = input_1 - input_2; // SUB
        4'b0010: result = input_1 * input_2; // OR
        4'b0011: result = input_1 / input_2; // AND
        4'b0100: result = input_1 % input_2; // XOR
        4'b0101: result = input_1 << input_2; // SLL
        4'b0110: result = input_1 >> input_2; // SRL
        4'b0111: result = input_1 < input_2; // SRA
        4'b1000: result = input_1 > input_2; // Signed Less Than
        4'b1001: result = input_1 >= input_2; // Unsigned Less Than
    endcase
end

assign result = input_1 + input_2;
endmodule : riscv_alu