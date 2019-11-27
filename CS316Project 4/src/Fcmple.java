final class Fcmple extends CmpInst
{
	Fcmple(int label)
	{
		super(label);
	}

	String instName()
	{
		return "fcmple";
	}

	boolean compare()
	{
		return ((Floatp)(VM.opStack[VM.top-1])).val <= ((Floatp)(VM.opStack[VM.top])).val;
	}
}