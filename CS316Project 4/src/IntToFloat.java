final class IntToFloat extends Instruction
{
	String instName()
	{
		return "intToFloat";
	}

	void execute()
	{
		VM.opStack[VM.top] = new Floatp(((Int)(VM.opStack[VM.top])).val);
		VM.pc++;
	}
}