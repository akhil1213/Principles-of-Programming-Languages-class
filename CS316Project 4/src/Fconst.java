final class Fconst extends Const
{
	double val; // val to be pushed onto operand stack

	Fconst(double d)
	{
		val = d;
	}

	public String toString()
	{
		return "fconst "+val;
	}

	String instName()
	{
		return "fconst";
	}

	Data getOperand()
	{
		return new Floatp(val);
	}
}