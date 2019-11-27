final class Astore extends Store
{
	Astore(int i)
	{
		super(i);
	}
	int k;
	String instName()
	{
		return "astore";
	}
	public void execute(int k){
		Data[] arr = VM.opStack[VM.top--];
		VM.runtimeStack[VM.topR].vars[k] = arr;
	}
}