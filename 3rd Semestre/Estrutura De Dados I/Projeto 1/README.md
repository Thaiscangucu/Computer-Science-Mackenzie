# Mathematical Expression Evaluator

This project is a Java-based terminal application designed to evaluate mathematical expressions using stacks and queues. It converts infix expressions to postfix notation (Reverse Polish Notation) to perform calculations and allows for variable assignment and command recording.

## About the Project

The application serves as a tool for data structure practice, specifically implementing custom stack and queue classes to manage operators, operands, and command history.

### Key Features
* **Infix to Postfix Conversion**: Uses algorithm logic to transform standard mathematical expressions into a format easily processed by a computer.
* **Variable Management**: Users can assign values to variables (A-Z) and use them within complex expressions.
* **Command Recording (Macro)**: Includes functionality to record a sequence of up to 10 commands and replay them.
* **Input Validation**: Validates both the formatting of mathematical expressions and the syntax of system commands.

## Technologies Used

* **Language**: Java.
* **Data Structures**: Custom implementations of Generic Stack (Pilha) and Generic Queue (Fila).
* **Concepts Applied**: Infix/Postfix notation, Stack-based evaluation, and Queue-based command scheduling.

## How to Run the Project

1. **Prerequisites**: Ensure you have the Java Development Kit (JDK) installed.
2. **Compilation**:
   Compile all project files in your terminal:
   ```bash
   javac Main.java Pilha.java Fila.java Variaveis.java
   ```
3. **Execution**:
Run the main class:
 ```bash
  java Main
```

## Supported Commands
* Variable Assignment: A=10 (Assigns a numeric value to a variable from A to Z).
* Mathematical Expressions: (A+B)*C (Evaluates the expression using stored variables and operators like +, -, *, /, and ^).
* VARS: Lists all currently defined variables and their respective values.
* REC: Starts recording a sequence of up to 10 valid commands into a queue.
* STOP: Interrupts the recording session or stops the playback of commands.
* PLAY: Executes the sequence of recorded commands stored in the queue.
* RESET: Clears all defined variables from the system.
* ERASE: Clears all commands currently stored in the recording queue.
* EXIT: Terminates the application.

## Authors
Thais Ferreira Canguçu

Pedro Moniz Canto

