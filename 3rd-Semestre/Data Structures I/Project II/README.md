# Assembly REPL Interpreter

This project is a Java-based REPL (Read-Eval-Print Loop) interpreter for a simplified version of Assembly language. It allows users to load code from files, manipulate registers, perform arithmetic operations, and control program flow through jumps.

## About the Project

The application implements a custom execution environment using fundamental data structures. It uses a Linked List to store the program instructions and a Queue to manage the system's registers (A-Z).

### Key Features
* **File Management**: Load program files with the `.ed1` extension and save modifications back to disk.
* **Register System**: Supports 26 registers (A to Z), each storing an integer value, managed through a Queue structure.
* **Instruction Set**: Implements standard operations such as MOV, ADD, SUB, MUL, DIV, INC, DEC, and OUT.
* **Flow Control**: Supports the JNZ (Jump if Not Zero) instruction for creating loops and conditional logic.
* **Interactive Shell**: A REPL interface that allows executing commands like LOAD, LIST, RUN, and CLEAR in real-time.

## Technologies Used

* **Language**: Java.
* **Data Structures**: Custom Singly Linked List and Circular Queue.
* **Concepts Applied**: Lexical parsing, assembly simulation, and file I/O handling.

## How to Run the Project

1. **Prerequisites**: Ensure you have the Java Development Kit (JDK) installed.
2. **Compilation**:
   Compile all project files in your terminal:
   ```bash
   javac Main.java Node.java LinkedList.java Fila.java Par.java Run.java
   ```
2. **Execution**:
  Run the interpreter:
   ```bash
   java Main
   ```
## Supported Commands
LOAD <filename.ed1>: Loads an assembly file into memory.

LIST: Displays all instructions currently loaded in the program.

RUN: Executes the loaded program line by line.

CLEAR: Clears the current program from memory.

EXIT: Terminates the interpreter.

Assembly Instructions

MOV  <val/reg>: Moves a value or register content to a target register.

ADD/SUB/MUL/DIV  <val/reg>: Performs arithmetic between a register and a value/register.

INC/DEC : Increments or decrements the register value by 1.

JNZ  : Jumps to the specified line if the register value is not zero.

OUT <reg/val>: Prints the value to the terminal.

## Authors
Thais Ferreira Canguçu

Pedro Moniz Canto
