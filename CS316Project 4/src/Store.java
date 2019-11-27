abstract class Store extends Instruction
{
	int index; // top of operand stack popped and stored in vars[index]

	Store(int i)
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
		topAR.vars[index] = VM.opStack[VM.top];
		VM.opStack[VM.top] = null;
		VM.top--;
		VM.pc++;
	}
}