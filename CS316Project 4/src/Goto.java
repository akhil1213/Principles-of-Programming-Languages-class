final class Goto extends Instruction
{
	int label; // target label

	static String goto_ = "goto";

	Goto(int i)
	{
		label = i;
	}

	public String toString()
	{
		return goto_ + " " +label;
	}

	String instName()
	{
		return goto_;
	}

	void updateLabel()
	{
		if ( VM.labelMap.containsKey(label) )
			label = VM.labelMap.get(label);
		else
			VM.errorMsg(2, label, goto_);
	}

	void execute()
	{
		VM.pc = label;
	}
}