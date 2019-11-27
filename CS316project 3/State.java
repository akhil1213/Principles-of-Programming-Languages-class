public enum State 
{ 
// final states    ordinal number   token accepted 

	Id,              // 0       identifiers (only temporarily accepted)
	SignedInt,       // 1       signed integers
	UnsignedInt,     // 2       unsigned integers
	Float,           // 3       floats without exponentiation part
	FloatE,          // 4       floats with exponentiation part
	Colon,           // 5       :
	Comma,           // 6       ;

// non-final states                 string recognized    

	Start,           // 7       the empty string
	Add,             // 8       +
	Sub,             // 9       -
	Period,          // 10      ".", "+.", "-."
	E,               // 11      float parts ending with E or e
	EPlusMinus,      // 12      float parts ending with + or - in exponentiation part

// special instruction name states

	Iconst,
	Iload,
	Istore,
	Fconst,
	Fload,
	Fstore,
	Iadd,
	Isub,
	Imul,
	Idiv,
	Fadd,
	Fsub,
	Fmul,
	Fdiv,
	IntToFloat,
	Icmpeq,
	Icmpne,
	Icmplt,
	Icmple,
	Icmpgt,
	Icmpge,
	Fcmpeq,
	Fcmpne,
	Fcmplt,
	Fcmple,
	Fcmpgt,
	Fcmpge,
	Goto,
	Invoke,
	Return,
	Ireturn,
	Freturn,
	Print,

	UNDEF;

	boolean isFinal()
	{
		return this.compareTo(State.Comma) <= 0;  
	}

	boolean isInstName()
	{
		return this.compareTo(State.Iconst) >= 0 && this.compareTo(State.Print) <= 0;
	}

	boolean isArithInstruction()
	{
		return this.compareTo(State.Iadd) >= 0 && this.compareTo(State.Fdiv) <= 0;
	}

	boolean isCmpInstruction()
	{
		return this.compareTo(State.Icmpeq) >= 0 && this.compareTo(State.Fcmpge) <= 0;
	}
}