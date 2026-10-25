# Binary Expression Tree Evaluator

This project is a Java-based terminal application that parses mathematical expressions, constructs a binary expression tree, and evaluates the result. It features an interactive menu to input expressions, visualize tree traversals, and calculate the final mathematical output.

## About the Project

The application serves as a practical implementation of tree data structures. It handles standard arithmetic operations by first converting an infix mathematical expression into a postfix notation, and then building a binary tree where internal nodes are operators and leaf nodes are operands.

### Key Features
* **Infix to Postfix Conversion**: Utilizes a stack-based algorithm to correctly parse operator precedence and parentheses.
* **Expression Validation**: Validates user input to prevent unbalanced parentheses, invalid characters, and incorrect operator placements.
* **Binary Tree Construction**: Builds a hierarchical tree structure to represent the mathematical expression.
* **Tree Traversals**: Allows the user to visualize the generated tree in Pre-order, In-order, and Post-order formats.
* **Recursive Evaluation**: Calculates the final result of the expression by recursively evaluating the left and right subtrees.

## Technologies Used

* **Language**: Java.
* **Data Structures**: Binary Trees, Stacks.
* **Concepts Applied**: Shunting-yard algorithm logic, recursion, string parsing, and regex pattern matching.

## How to Run the Project

1. **Prerequisites**: Ensure you have the Java Development Kit (JDK) installed on your system.
2. **Compilation**:
   Open your terminal in the folder containing the source code and compile all files:
   `javac Main.java Arvore.java Converter.java No.java`
3. **Execution**:
   Run the compiled main class:
   `java Main`

## Menu Options

Once the application is running, you interact with it using the following numbered menu options:

* **1. Entrada da expressão aritmética na notação infixa**: Prompts the user to type a mathematical expression (e.g., `(5+3)*2`). The system will validate the input.
* **2. Criação da árvore binária**: Converts the validated infix expression into postfix notation and builds the binary expression tree. It also displays a visual representation of the tree structure.
* **3. Exibição da árvore binária**: Prints the nodes of the tree in three different traversal orders: Pre-order, In-order, and Post-order.
* **4. Cálculo da expressão**: Evaluates the binary tree recursively and prints the final calculated mathematical result.
* **5. Encerramento do programa**: Closes the scanner and terminates the application.

## Authors
* Thais Ferreira Canguçu
* Pedro Moniz Canto
