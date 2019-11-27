final class Icmple extends CmpInst
{
	Icmple(int label)
	{
		super(label);
	}

	String instName()
	{
		return "icmple";
	}

	boolean compare()
	{
		return ((Int)(VM.opStack[VM.top-1])).val <= ((Int)(VM.opStack[VM.top])).val;
	}
}