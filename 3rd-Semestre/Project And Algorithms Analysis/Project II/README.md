# LZW Compression Algorithm

This project is a C-based implementation of the Lempel-Ziv-Welch (LZW) data compression algorithm. It demonstrates the fundamental concepts of lossless dictionary-based compression by compressing and decompressing string inputs.

## About the Project

The application features an interactive command-line interface that allows users to test the LZW algorithm dynamically. It builds a dictionary on the fly to encode repeating patterns of text into shorter integer representations, and then reverses the process to perfectly reconstruct the original text.

### Key Features
* **Interactive Menu**: A simple CLI menu to navigate between different testing modes.
* **Custom Input**: Users can type or paste their own text strings to see real-time compression and decompression results.
* **Pre-defined Text Test**: Includes an integrated test using a snippet from the *Bee Movie* script to demonstrate compression on larger, repetitive text blocks.
* **Dynamic Dictionary Management**: Implements memory allocation strategies to manage the LZW dictionary up to a typical maximum size of 4096 entries.

## Technologies Used

* **Language**: C.
* **Libraries**: Standard I/O (stdio.h), Standard Library (stdlib.h), and String manipulation (string.h).
* **Concepts Applied**: Lossless data compression, dynamic memory allocation, and array/dictionary manipulation.

## How to Run the Project

1. **Prerequisites**: Ensure you have a C compiler installed on your system (such as gcc).
2. **Compilation**:
   Open your terminal in the directory containing the source code and compile the file:
   ```bash
   gcc main.c -o lzw_compression
   ```
3. **Execution**:
    Run the compiled executable:
     ```bash
   ./lzw_compression
    ```

## Application Menu
Upon execution, the program will present the following options:

Option 1: Type custom text to perform compression and immediately decompress it. The program will display the array of compressed integers followed by the restored string.

Option 2: Run a demonstration using a pre-defined multi-line text block to observe how the algorithm handles larger inputs.

Option 3: Exit the program.

## Authors
Pedro Moniz Canto

Thais Ferreira Canguçu


   
