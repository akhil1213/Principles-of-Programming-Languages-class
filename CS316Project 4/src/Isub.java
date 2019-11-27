final class Isub extends ArithInst
{
	String instName()
	{
		return "isub";
	}

	void execute()
	{
		((Int)(VM.opStack[VM.top-1])).val = ((Int)(VM.opStack[VM.top-1])).val - ((Int)(VM.opStack[VM.top])).val;
		VM.opStack[VM.top] = null;
		VM.top--;
		VM.pc++;
	}
}