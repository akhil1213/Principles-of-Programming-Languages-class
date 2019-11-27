# Principles-of-Programming-Languages-class
Project 3:

Although our VM has only two data types, integers and floating-point numbers, it is beneficial to use an inheritance hierarchy with a view to smooth addition of more data types. Create an abstract class at the root of this hierarchy and two subclasses of integers and floating-point numbers. In the following this abstract class will be called Data.

Implement a single, universal operand stack for all function calls. The operand stack will contain objects of the Data class. In the following it will be referred to as stack.

Implement the variable area of each activation record by a 1-dimensional array of Data objects. In the following this array will be referred to as vars. So "the value in address k in variable area" will be implemented by vars[k].

Create a class of activation records. This class has just two fields: the vars array and the return address. The runtime stack will contain objects of this class.

Implement the program counter by an integer variable. In the following it will be called pc. The value of pc ranges over the indexes of the instStore array, and instStore[pc] has the instruction currently being executed.

The following is the detailed operation of the invoke and return instructions.
invoke k1, k2, k3
Construct a new activation record object ar.
Construct a new vars array of size k2+k3 in ar.
Pop top k2 objects from the operand stack; assign stack[top−k2+1], …, stack[top], respectively, to vars[0], …, vars[k2−1]. This performs parameter passing.
Set the return address in ar to pc+1.
Push ar onto the runtime stack.
Set pc to k1.
return, ireturn, freturn
Set pc to the return address contained in the activation record at top of the runtime stack.
Pop the top activation record from the runtime stack.
The operand and runtime stacks may be implemented by arrays, linked lists, or a library stack class.

The implementation of the other instructions should be relatively straightforward from the operational semantics provided in Project 2 description.

In the Instruction class at the root of the instruction hierarchy, declare an abstract function execute(), or interpret() if you prefer, and provide its body code in suitable descendant classes to implement execution/interpretation of single instructions. The interpretation of a whole instruction list starts by setting pc to 0, then repeatedly executes instStore[pc] until instStore[pc] = null. (So we have to assume instStore[] array has at least one null at the end.) Note that execute()/interpret() must update the value of pc properly.

Presume that the instruction list is always type correct, namely that each typed instruction, whenever executed, will have operand values of the required data types.

Also, the program is to compute and display the peak sizes of the operand and runtime stacks.

Your program is to read any text file containing (what is intended to be) an instruction list, parse and store the instructions in instStore array, and display the stored instructions along with their sequential indexes in an output file. It will then interpret the instruction list. The values to be printed by print instructions and the peak sizes of the operand/runtime stacks are to be displayed on the screen.

To make grading efficient and uniform, observe the following:
The program must read the input/output file names as external arguments to the main function. How to set external arguments to Java main function in Eclipse.
The main function to be invoked to run the program must be included in VM.java class.
If your Project 2 program isn't functioning, you may use this sample program.
