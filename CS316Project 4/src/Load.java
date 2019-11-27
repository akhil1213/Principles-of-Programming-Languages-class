abstract class Load extends Instruction
{
	int index; // vars[index] to be pushed onto operand stack

	Load(int i)
	{
		index = i;
	}

	public String toString()
	{
		return instName() + " " + index;
	}

	void execute()
	{
		AR topAR = VM.runtimeStack[VM.topR];
		VM.top++;
		if ( VM.top == VM.opStackSize ) // operand stack overflow
			VM.runtimeError(1, VM.pc, toString(), 0);
		Data val = topAR.vars[index];
		if ( val == null )
			VM.runtimeError(3, VM.pc, toString(), 0);
		VM.opStack[VM.top] = val.cloneData();
		VM.pc++;
		VM.updateOpStackPeakSize();
	}
}