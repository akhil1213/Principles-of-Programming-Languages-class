final class Invoke extends Instruction
{
	int startLabel;
	int numOfParams;
	int numOfLocalVars;

	static String invoke = "invoke";

	Invoke(int i, int j, int k)
	{
		startLabel = i;
		numOfParams = j;
		numOfLocalVars = k;
	}

	public String toString()
	{
		return invoke + " " + startLabel + ", " + numOfParams + ", " + numOfLocalVars;
	}

	String instName()
	{
		return invoke;
	}

	void updateLabel()
	{
		if ( VM.labelMap.containsKey(startLabel) )
			startLabel = VM.labelMap.get(startLabel);
		else
			VM.errorMsg(2, startLabel, invoke);
	}

	void execute()
	{
		AR ar = new AR();
		ar.vars = new Data[numOfParams+numOfLocalVars];

		// pass parameter values

		int i = 0;
		int j = VM.top-numOfParams+1;
		while ( i <= numOfParams-1 )
		{
			ar.vars[i] = VM.opStack[j];
			VM.opStack[j] = null;
			i++;
			j++;
		}

		VM.top = VM.top-numOfParams;
		ar.returnAdd = VM.pc+1;
		VM.topR++;
		if ( VM.topR == VM.runtimeStackSize ) // runtime stack overflow
			VM.runtimeError(2, VM.pc, toString(), 0);
		VM.runtimeStack[VM.topR] = ar;
		VM.pc = startLabel;
		if ( VM.topR == VM.runtimeStackPeakSize )
			VM.runtimeStackPeakSize++;
	}
}