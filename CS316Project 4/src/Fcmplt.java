final class Fcmplt extends CmpInst
{
	Fcmplt(int label)
	{
		super(label);
	}

	String instName()
	{
		return "fcmplt";
	}

	boolean compare()
	{
		return ((Floatp)(VM.opStack[VM.top-1])).val < ((Floatp)(VM.opStack[VM.top])).val;
	}
}