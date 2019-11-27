final class Icmplt extends CmpInst
{
	Icmplt(int label)
	{
		super(label);
	}

	String instName()
	{
		return "icmplt";
	}

	boolean compare()
	{
		return ((Int)(VM.opStack[VM.top-1])).val < ((Int)(VM.opStack[VM.top])).val;
	}
}