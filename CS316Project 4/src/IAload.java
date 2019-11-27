final class IAload extends Load{
	IAload(int i)
	{
		super(i);
	}
	String instName()
	{
		return "IAload";
	}
	public void execute(int n){
		int top = VM.top;
		Data aref = VM.opStack[top-n];
		int size = aref.size;
		Data[] a = aref.arr;
		int rank = 0;//rank should be int but in Faload it should be double
		for(int i = 0; i < size; i++){
			rank+= i - a[i].getVal();
			//rank+= VM.opStack[top-n+i];
		}
		VM.opStack[top++] = a[rank].cloneData();
		Data[] arr = VM.runtimeStack[topR--].vars[k];
		VM.opStack[++VM.top] = arr;//this prob should go in execute @override
	}
}
