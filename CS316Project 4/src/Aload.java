final class Aload extends Load{
	Aload(int i)
	{
		super(i);
	}
	String instName()
	{
		return "fload";
	}
	public void execute(int k){
		Data[] arr = VM.runtimeStack[topR--].vars[k];
		VM.opStack[++top] = arr;//this prob should go in execute @override
	}
}
