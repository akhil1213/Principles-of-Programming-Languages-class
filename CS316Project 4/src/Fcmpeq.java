final class Fcmpeq extends CmpInst
{
	Fcmpeq(int label)
	{
		super(label);
	}

	String instName()
	{
		return "fcmpeq";
	}

	boolean compare()
	{
		return ((Floatp)(VM.opStack[VM.top-1])).val == ((Floatp)(VM.opStack[VM.top])).val;
	}
}