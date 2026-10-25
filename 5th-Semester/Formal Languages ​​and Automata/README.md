# Recursive Descent Parser

This project is a C-based application that implements a Recursive Descent Parser to validate mathematical expressions based on a specific Context-Free Grammar (CFG).

## About the Project

The program performs syntactic analysis on an arithmetic expression string. It checks if the provided string follows the predefined grammatical rules, which include basic arithmetic operations (+, -, *, /, %) and nested parentheses. It features a custom error handling system to pinpoint syntax mistakes.

### Key Features
* **Syntactic Validation**: Evaluates mathematical expressions to ensure they conform to the defined grammar.
* **Lexical Error Handling**: Identifies the exact position of errors such as unbalanced parentheses, invalid characters, or misplaced symbols.
* **Custom Grammar Rules**: Implements a strict set of production rules to resolve arithmetic structuring and nesting.

## Technologies Used

* **Language**: C.
* **Concepts Applied**: Context-Free Grammars (CFG), Lexical Analysis, Syntactic Parsing, Recursion, and Error Trapping.

## Supported Grammar

The parser evaluates expressions based on the following non-terminal rules (where 'e' represents an empty string / epsilon):
* X -> E$
* E -> T R
* R -> + T R | - T R | e
* T -> F Z
* Z -> * F Z | / F Z | % F Z | e
* F -> (E) | N | -N
* N -> 0 | 1D | 2D | 3D | 4D | 5D | 6D | 7D | 8D | 9D
* D -> 0D | 1D | 2D | 3D | 4D | 5D | 6D | 7D | 8D | 9D | e

## How to Run the Project

1. **Prerequisites**: Ensure you have a C compiler installed, such as gcc.
2. **Setup**: Save the code in a file named `parser.c`.
3. **Compilation**:
   Compile the project using your terminal:
   `gcc parser.c -o parser`
4. **Execution**:
   Run the compiled executable:
   `./parser`
5. **Testing Different Expressions**:
   Currently, the expression to be evaluated is hardcoded in the `main` function (e.g., `char palavra[]= "-7+(54+(8))$";`). To test different mathematical expressions, you must modify this variable directly in the source code, save, recompile, and run again. Note that all valid expressions must end with the `$` symbol to indicate the end of the string.

## Authors
* Beatriz Bellini Prado Garcia
* Fabio Oliveira da Silva
* Patrick Rocha de Andrade
* Thais Ferreira Canguçu
