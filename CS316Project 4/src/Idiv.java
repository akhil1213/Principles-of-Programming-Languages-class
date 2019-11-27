final class Idiv extends ArithInst
{
	String instName()
	{
		return "idiv";
	}

	void execute()
	{
		int divisor = ((Int)(VM.opStack[VM.top])).val;
		if ( divisor == 0 )
			VM.runtimeError(4, VM.pc, "idiv", 0);
		((Int)(VM.opStack[VM.top-1])).val = ((Int)(VM.opStack[VM.top-1])).val / divisor;
		VM.opStack[VM.top] = null;
		VM.top--;
		VM.pc++;
	}
}