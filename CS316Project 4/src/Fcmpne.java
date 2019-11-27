final class Fcmpne extends CmpInst
{
	Fcmpne(int label)
	{
		super(label);
	}

	String instName()
	{
		return "fcmpne";
	}

	boolean compare()
	{
		return ((Floatp)(VM.opStack[VM.top-1])).val != ((Floatp)(VM.opStack[VM.top])).val;
	}
}