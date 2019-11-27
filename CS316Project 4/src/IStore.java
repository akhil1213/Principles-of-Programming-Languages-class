final class Istore extends Store
{
	Istore(int i)
	{
		super(i);
	}

	String instName()
	{
		return "istore";
	}
	public void execute(){
		Int k = (Int) VM.operandStack.pop();
		ActivationRecords ar = VM.runtimeStack.peek();
		ar.addToVars(k);
	}
}