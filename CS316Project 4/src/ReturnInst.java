abstract class ReturnInst extends Instruction
{
	void execute()
	{
		VM.pc = VM.runtimeStack[VM.topR].returnAdd;
		VM.runtimeStack[VM.topR] = null;
		VM.topR--;
	}
}