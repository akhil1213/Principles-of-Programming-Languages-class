final class Fsub extends ArithInst
{
	String instName()
	{
		return "fsub";
	}

	void execute()
	{
		((Floatp)(VM.opStack[VM.top-1])).val = ((Floatp)(VM.opStack[VM.top-1])).val - ((Floatp)(VM.opStack[VM.top])).val;
		VM.opStack[VM.top] = null;
		VM.top--;
		VM.pc++;
	}
}