final class Icmpeq extends CmpInst
{
	Icmpeq(int label)
	{
		super(label);
	}

	String instName()
	{
		return "icmpeq";
	}

	boolean compare()
	{
		return ((Int)(VM.opStack[VM.top-1])).val == ((Int)(VM.opStack[VM.top])).val;
	}
}