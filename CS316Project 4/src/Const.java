abstract class Const extends Instruction
{
	abstract Data getOperand();

	void execute()
	{
		VM.top++;
		if ( VM.top == VM.opStackSize )  // operand stack overflow
			VM.runtimeError(1, VM.pc, toString(), 0);
		VM.opStack[VM.top] = getOperand();
		VM.pc++;
		VM.updateOpStackPeakSize();
	}
}