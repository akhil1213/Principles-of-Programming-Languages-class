final class Fcmpge extends CmpInst
{
	Fcmpge(int label)
	{
		super(label);
	}

	String instName()
	{
		return "fcmpge";
	}

	boolean compare()
	{
		return ((Floatp)(VM.opStack[VM.top-1])).val >= ((Floatp)(VM.opStack[VM.top])).val;
	}
}