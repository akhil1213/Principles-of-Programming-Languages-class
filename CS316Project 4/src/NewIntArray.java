class NewIntArray extends Data{
	Data[] arr;
	int size;
	NewIntArray(int n){//new int array
		arr = new Int[n];
		size = n;
		int count = 0;
		int top = VM.top;
		while(count < n){
			try{
				if(VM.opStack[top-n+count] <1){//if ei <=0
					throw new Exception("too small");
				}
			}catch(Exception e){
				System.out.print("too small");
				System.exit(0);
			}
			arr[count] = VM.opStack[top-n+count];
			count++;
			VM.top--;
		}
		VM.opStack[++top] = arr;//since top starts at -1
	}
}