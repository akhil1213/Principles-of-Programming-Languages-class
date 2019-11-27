final class Icmpgt extends CmpInst
{
	Icmpgt(int label)
	{
		super(label);
	}

	String instName()
	{
		return "icmpgt";
	}

	boolean compare()
	{
		return ((Int)(VM.opStack[VM.top-1])).val > ((Int)(VM.opStack[VM.top])).val;
	}
}