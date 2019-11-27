final class Icmpne extends CmpInst
{
	Icmpne(int label)
	{
		super(label);
	}

	String instName()
	{
		return "icmpne";
	}

	boolean compare()
	{
		return ((Int)(VM.opStack[VM.top-1])).val != ((Int)(VM.opStack[VM.top])).val;
	}
}