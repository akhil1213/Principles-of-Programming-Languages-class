final class Print extends Instruction
{
	int index; // print value of vars[index]

	Print(int i)
	{
		index = i;
	}

	public String toString()
	{
		return "print "+index;
	}

	String instName()
	{
		return "print";
	}
	public void execute(){
		ActivationRecords top = VM.runtimeStack.peek();
		Data[] topVars = top.getVars();
		Data i = topVars[index];
		if(i instanceof Int) {
			Int obj = (Int)i;
			System.out.println(obj.get());
		}else {
			FloatingPoint obj = (FloatingPoint)i;
			System.out.println(obj.getVal());
		}
	}
}