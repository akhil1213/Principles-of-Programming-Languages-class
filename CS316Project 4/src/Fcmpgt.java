final class Fcmpgt extends CmpInst
{
	Fcmpgt(int label)
	{
		super(label);
	}

	String instName()
	{
		return "fcmpgt";
	}

	boolean compare()
	{
		return ((Floatp)(VM.opStack[VM.top-1])).val > ((Floatp)(VM.opStack[VM.top])).val;
	}
}