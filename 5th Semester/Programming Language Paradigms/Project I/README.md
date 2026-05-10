# Huffman Coding Compression

This project is a Haskell-based implementation of the Huffman coding algorithm for text compression. It reads an input text file, calculates character frequencies, constructs a binary Huffman tree, and generates a compressed binary string output.

## About the Project

The application demonstrates functional programming principles by applying pure functions and recursive data structures to solve a classic data compression problem. It processes text completely in memory and handles file I/O to read the raw text and export the encoded result.

### Key Features
* **Frequency Calculation**: Parses the input string to count character occurrences (Run-Length representation).
* **Huffman Tree Construction**: Uses a custom algebraic data type (Tree with Nodes and Leaves) to build the optimal prefix code tree via a frequency-based insertion sort mechanism.
* **Code Generation**: Traverses the binary tree to generate a mapping table of characters to their respective binary string representations ("0" and "1").
* **Text Encoding**: Translates the original text into a continuous binary string using the generated code table.
* **File Management**: Automatically reads from an in.txt file and exports the final binary sequence to an out.txt file.

## Technologies Used

* **Language**: Haskell.
* **Concepts Applied**: Functional programming, Algebraic Data Types (ADTs), recursion, anonymous functions (lambdas), and file I/O operations.

## How to Run the Project

1. **Prerequisites**: Ensure you have the Glasgow Haskell Compiler (GHC) installed on your system.
2. **File Preparation**: Create a text file named in.txt in the same directory as the source code and add the text you wish to compress.
3. **Execution**:
   Open your terminal, navigate to the folder containing the source code, and run the script using the following command:
   `runhaskell main.hs`
   *(Note: Save the provided code in a file named main.hs before running).*

4. **Output**:
   The program will print the following steps directly to the terminal:
   * The frequency list of the characters.
   * The structure of the generated Huffman Tree.
   * The generated table of prefix codes.
   * The final encoded binary string.
   
   It will also automatically create an out.txt file in the same directory containing the compressed binary string.

## Authors
* Miguel Piñeiro Coratolo Simões
* Bruno Germanetti Ramalho
* Thais Ferreira Canguçu
