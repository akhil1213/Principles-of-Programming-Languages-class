abstract class CmpInst extends Instruction
{
	int label; // go-to target label

	CmpInst(int i)
	{
		label = i;
	}

	public String toString()
	{
		return instName() + " " + label;
	}

	void updateLabel()
	{
		if ( VM.labelMap.containsKey(label) )
			label = VM.labelMap.get(label);
		else
			VM.errorMsg(2, label, instName());
	}

	abstract boolean compare();

	void execute()
	{
		if ( compare() )
			VM.pc = label;
		else
			VM.pc++;
		VM.opStack[VM.top] = null;
		VM.opStack[VM.top-1] = null;
		VM.top = VM.top-2;
	}
}