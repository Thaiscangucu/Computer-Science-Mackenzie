# SAL Compiler - Lexical Analyzer Module

This project implements the Lexical Analysis phase for a custom programming language called SAL (Simple Algorithmic Language). It serves as the front-end scanner of the compiler pipeline, responsible for reading raw source code and converting it into a structured sequence of Tokens.

## About the Project

The core of this application is a custom-built, deterministic Finite State Machine (FSM) written in C. It reads characters sequentially from a source file and groups them into meaningful lexical categories such as identifiers, numbers, operators, and reserved keywords, while efficiently stripping out whitespace and comments.

### Key Features
* **FSM-Based Scanner (`lex.c`)**: Utilizes a highly optimized state machine design (using `goto` transitions) for rapid character categorization and token generation.
* **SAL Keyword Recognition**: Automatically detects and categorizes language-specific reserved words such as `module`, `main`, `loop`, `until`, `match`, `when`, `globals`, and `locals`.
* **Advanced Comment Filtering**: Accurately ignores single-line comments (starting with `@`) and block comments (enclosed in `@{` and `}@`) while maintaining strict line-number tracking for accurate error reporting.
* **Command-Line Interface (`opt.c`)**: Includes a dedicated argument parsing module to handle compiler execution flags.
* **Automated Build System**: Uses a `Makefile` to manage the compilation of the various C modules into a single executable.

## Technologies Used

* **Language**: C (C99 Standard).
* **Concepts Applied**: Lexical Analysis, Finite State Machines, Tokenization, Modular C Programming (Header/Source separation), and CLI Argument Parsing.

## How to Run the Project

1. **Prerequisites**: Ensure you have a C compiler (`gcc`) and the `make` utility installed on your operating system.
2. **Compilation**:
   Open your terminal in the directory containing the source files and run the following command to clean and build the project:
   ```bash
   make rebuild
   ```
   *(This will compile `main.c`, `lex.c`, `opt.c`, and other linked modules into the `salc` executable).*
3. **Execution**:
   Run the compiled compiler executable, passing a SAL source file as an argument:
   ```bash
   ./salc your_code_file.sal
   ```
   *(The program will output a detailed list of every token found in the file, including its line number, internal category ID, and literal lexeme string).*

## Command-Line Options

The compiler supports several optional flags to aid in debugging and compilation tracing. Append these to your execution command:
* `--tokens`: Enables the output of the token stream.
* `--symtab`: Enables the output of the compiler's symbol table.
* `--trace`: Enables parser tracing diagnostics.

**Example usage with flags:**
```bash
./salc my_program.sal --tokens --symtab
```

## Authors
* Thais Ferreira Canguçu
* Miguel Piñeiro Coratolo Simões 
