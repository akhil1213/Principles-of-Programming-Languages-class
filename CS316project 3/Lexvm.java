import java.util.*;

public abstract class LexVM extends IO
{
	public static String t; // holds an extracted token
	public static State state; // the current state of the DFA

	private static State nextState[][] = new State[13][128];
 
          // This array implements the state transition function
          // State x (ASCII char set) --> State.
          // The state argument is converted to its ordinal number used as
          // the first array index from 0 through 12.

	private static HashMap<String, State> instNameMap = new HashMap<String, State>();

	private static void setInstNameMap()
	{
		instNameMap.put("iconst",     State.Iconst);
		instNameMap.put("iload",      State.Iload);
		instNameMap.put("istore",     State.Istore);
		instNameMap.put("fconst",     State.Fconst);
		instNameMap.put("fload",      State.Fload);
		instNameMap.put("fstore",     State.Fstore);
		instNameMap.put("iadd",       State.Iadd);
		instNameMap.put("isub",       State.Isub);
		instNameMap.put("imul",       State.Imul);
		instNameMap.put("idiv",       State.Idiv);
		instNameMap.put("fadd",       State.Fadd);
		instNameMap.put("fsub",       State.Fsub);
		instNameMap.put("fmul",       State.Fmul);
		instNameMap.put("fdiv",       State.Fdiv);
		instNameMap.put("intToFloat", State.IntToFloat);
		instNameMap.put("icmpeq",     State.Icmpeq);
		instNameMap.put("icmpne",     State.Icmpne);
		instNameMap.put("icmplt",     State.Icmplt);
		instNameMap.put("icmple",     State.Icmple);
		instNameMap.put("icmpgt",     State.Icmpgt);
		instNameMap.put("icmpge",     State.Icmpge);
		instNameMap.put("fcmpeq",     State.Fcmpeq);
		instNameMap.put("fcmpne",     State.Fcmpne);
		instNameMap.put("fcmplt",     State.Fcmplt);
		instNameMap.put("fcmple",     State.Fcmple);
		instNameMap.put("fcmpgt",     State.Fcmpgt);
		instNameMap.put("fcmpge",     State.Fcmpge);
		instNameMap.put("goto",       State.Goto);
		instNameMap.put("invoke",     State.Invoke);
		instNameMap.put("return",     State.Return);
		instNameMap.put("ireturn",    State.Ireturn);
		instNameMap.put("freturn",    State.Freturn);
		instNameMap.put("print",      State.Print);
	}

	private static int driver()

	// This is the driver of the DFA. 
	// If a valid token is found, assigns it to "t" and returns 1.
	// If an invalid token is found, assigns it to "t" and returns 0.
	// If end-of-stream is reached without finding any non-whitespace character, returns -1.

	{
		State nextSt; // the next state of the DFA

		t = "";
		state = State.Start;

		if ( Character.isWhitespace((char) a) )
			a = getChar(); // get the next non-whitespace character
		if ( a == -1 ) // end-of-stream is reached
			return -1;

		while ( a != -1 ) // do the body if "a" is not end-of-stream
		{
			c = (char) a;
			nextSt = nextState[state.ordinal()][a];
			if ( nextSt == State.UNDEF ) // The DFA will halt.
			{
				if ( state.isFinal() )
					return 1; // valid token extracted
				else // "c" is an unexpected character
				{
					t = t+c;
					a = getNextChar();
					return 0; // invalid token found
				}
			}
			else // The DFA will go on.
			{
				state = nextSt;
				t = t+c;
				a = getNextChar();
			}
		}

		// end-of-stream is reached while a token is being extracted

		if ( state.isFinal() )
			return 1; // valid token extracted
		else
			return 0; // invalid token found
	} // end driver

	private static void setNextState()
	{
		for (int s = 0; s <= 12; s++ )
			for (int c = 0; c <= 127; c++ )
				nextState[s][c] = State.UNDEF;

		for (char c = 'A'; c <= 'Z'; c++)
		{
			nextState[State.Start.ordinal()][c] = State.Id;
			nextState[State.Id   .ordinal()][c] = State.Id;
		}

		for (char c = 'a'; c <= 'z'; c++)
		{
			nextState[State.Start.ordinal()][c] = State.Id;
			nextState[State.Id   .ordinal()][c] = State.Id;
		}

		for (char d = '0'; d <= '9'; d++)
		{
			nextState[State.Start      .ordinal()][d] = State.UnsignedInt;
			nextState[State.UnsignedInt.ordinal()][d] = State.UnsignedInt;
			nextState[State.Add        .ordinal()][d] = State.SignedInt;
			nextState[State.Sub        .ordinal()][d] = State.SignedInt;
			nextState[State.SignedInt  .ordinal()][d] = State.SignedInt;						
			nextState[State.Period     .ordinal()][d] = State.Float;
			nextState[State.Float      .ordinal()][d] = State.Float;
			nextState[State.E          .ordinal()][d] = State.FloatE;
			nextState[State.EPlusMinus .ordinal()][d] = State.FloatE;
			nextState[State.FloatE     .ordinal()][d] = State.FloatE;
		}

		nextState[State.Start.ordinal()]['+']  = State.Add;
		nextState[State.Start.ordinal()]['-']  = State.Sub;

		nextState[State.Start.      ordinal()]['.'] = State.Period;
		nextState[State.Add.        ordinal()]['.'] = State.Period;
		nextState[State.Sub.        ordinal()]['.'] = State.Period;
		nextState[State.UnsignedInt.ordinal()]['.'] = State.Float;
		nextState[State.SignedInt.  ordinal()]['.'] = State.Float;

		nextState[State.Float.ordinal()]['e'] = state.E;
		nextState[State.Float.ordinal()]['E'] = state.E;
		
		nextState[State.E.ordinal()]['+'] = State.EPlusMinus;
		nextState[State.E.ordinal()]['-'] = State.EPlusMinus;

		nextState[State.Start.ordinal()][':']  = State.Colon;
		nextState[State.Start.ordinal()][',']  = State.Comma;

	} // end setNextState

	private static int instNameCheck()
	{
		State instNameState = instNameMap.get(t);
		if ( instNameState != null ) // "t" has an instruction name
		{
			state = instNameState;
			return 1;
		}
		else
			return 0;
	}

	public static void getToken()

	// Extract the next token using the driver of the DFA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if ( state == State.Id )
		{
			int j = instNameCheck();
			if ( j == 0 )
				displayln( t+" : Lexical Error, invalid token");
		}
		else if ( i == 0 )
			displayln( t+" : Lexical Error, invalid token");
	}

	public static void setLex()

	// Sets the nextState array and instNameMap.

	{
		setNextState();
		setInstNameMap();
	}

	public static void main(String argv[])

	// argv[0]: input file containing source code using tokens defined above
	// argv[1]: output file displaying a list of the tokens 

	{
		setIO( argv[0], argv[1] );
		setLex();

		int i;

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if ( i == 1 )
			{
				if ( state == State.Id )
				{
					int j = instNameCheck();
					if ( j == 0 )
						displayln( t+" : Lexical Error, invalid token");
					else
						displayln( t+"   : "+state.toString() );
				}
				else
					displayln( t+"   : "+state.toString() );
			}
			else if ( i == 0 )
				displayln( t+" : Lexical Error, invalid token");
		}

		closeIO();
	}
} 