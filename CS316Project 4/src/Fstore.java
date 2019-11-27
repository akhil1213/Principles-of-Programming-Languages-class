final class Fstore extends Store
{
	Fstore(int i)
	{
		super(i);
	}

	String instName()
	{
		return "fstore";
	}
	public void execute(){
		FloatingPoint k = (FloatingPoint) VM.operandStack.pop();
		ActivationRecords ar = VM.runtimeStack.peek();
		ar.addToVars(k);
	}
}