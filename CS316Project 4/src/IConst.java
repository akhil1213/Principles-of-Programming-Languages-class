final class Iconst extends Const
{
	int val; // val to be pushed onto operand stack

	Iconst(int i)
	{
		val = i;
	}

	public String toString()
	{
		return "iconst "+val;
	}

	String instName()
	{
		return "iconst";
	}

	Data getOperand()
	{
		return new Int(val);
	}
}