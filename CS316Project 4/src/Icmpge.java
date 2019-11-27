final class Icmpge extends CmpInst
{
	Icmpge(int label)
	{
		super(label);
	}

	String instName()
	{
		return "icmpge";
	}

	boolean compare()
	{
		return ((Int)(VM.opStack[VM.top-1])).val >= ((Int)(VM.opStack[VM.top])).val;
	}
}